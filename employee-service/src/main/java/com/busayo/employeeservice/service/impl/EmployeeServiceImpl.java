package com.busayo.employeeservice.service.impl;

import com.busayo.employeeservice.dto.DepartmentDto;
import com.busayo.employeeservice.dto.EmployeeDto;
import com.busayo.employeeservice.dto.OrganizationDto;
import com.busayo.employeeservice.dto.ResponseDto;
import com.busayo.employeeservice.entity.Employee;
import com.busayo.employeeservice.mapper.EmployeeMapper;
import com.busayo.employeeservice.repository.EmployeeRepository;
// import com.busayo.employeeservice.service.APIClient;
import com.busayo.employeeservice.service.EmployeeService;
// import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
// import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
// import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeServiceImpl.class);

    @Autowired
    private EmployeeRepository employeeRepository;

//    @Autowired
//    private RestTemplate restTemplate;

    @Autowired
    private WebClient webClient;

//    @Autowired
//    private APIClient apiClient;

    @Override
    public EmployeeDto saveEmployee(EmployeeDto employeeDto) {
        Employee employee = EmployeeMapper.mapToEmployee(employeeDto);

        Employee savedEmployee = employeeRepository.save(employee);

        EmployeeDto savedEmployeeDto = EmployeeMapper.mapToEmployeeDto(savedEmployee);

        return savedEmployeeDto;
    }

//    @CircuitBreaker(name = "${spring.application.name}", fallbackMethod = "getDefaultDepartment")
    @Retry(name = "${spring.application.name}", fallbackMethod = "getDefaultDepartment")
    @Override
    public ResponseDto getEmployeeById(Long employeeId) {

        LOGGER.info("inside getEmployeeById() method");
        Employee employee = employeeRepository.findById(employeeId).get();

//        ResponseEntity<DepartmentDto> responseEntity = restTemplate.getForEntity("http://localhost:8080/departments/" + employee.getDepartmentCode(),
//                DepartmentDto.class);
//
//        DepartmentDto departmentDto = responseEntity.getBody();

        DepartmentDto departmentDto = webClient.get()
                .uri("http://localhost:8080/departments/" + employee.getDepartmentCode())
                .retrieve()
                .bodyToMono(DepartmentDto.class)
                .block();

        OrganizationDto organizationDto = webClient.get()
                .uri("http://localhost:8083/organizations/" + employee.getOrganizationCode())
                .retrieve()
                .bodyToMono(OrganizationDto.class)
                .block();

//        DepartmentDto departmentDto = apiClient.getDepartment(employee.getDepartmentCode());
        EmployeeDto employeeDto = EmployeeMapper.mapToEmployeeDto(employee);

        ResponseDto responseDto = new ResponseDto();
        responseDto.setEmployee(employeeDto);
        responseDto.setDepartment(departmentDto);
        responseDto.setOrganization(organizationDto);
        return responseDto;
    }

    public ResponseDto getDefaultDepartment(Long employeeId, Exception exception) {

        LOGGER.info("inside getDefaultDepartment() method");
        Employee employee = employeeRepository.findById(employeeId).get();

        DepartmentDto departmentDto = new DepartmentDto();
        departmentDto.setName("R&D Department");
        departmentDto.setDepartmentCode("RD001");
        departmentDto.setDescription("Research and Development Department");

        EmployeeDto employeeDto = EmployeeMapper.mapToEmployeeDto(employee);

        ResponseDto responseDto = new ResponseDto();
        responseDto.setEmployee(employeeDto);
        responseDto.setDepartment(departmentDto);
        return responseDto;
    }
}
