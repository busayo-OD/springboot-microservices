package com.busayo.employeeservice.controller;

import com.busayo.employeeservice.dto.EmployeeDto;
import com.busayo.employeeservice.dto.ResponseDto;
import com.busayo.employeeservice.service.EmployeeService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(
        name = "Employee Service - EmployeeController",
        description = "Employee controller exposes REST APIs for Employee Service"
)
@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping("/save")
    public ResponseEntity<EmployeeDto> saveEmployee(@RequestBody EmployeeDto employeeDto) {
        EmployeeDto savedEmployee = employeeService.saveEmployee(employeeDto);
        return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<ResponseDto> getEmployee(@PathVariable Long id){
        ResponseDto responseDto = employeeService.getEmployeeById(id);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }
}
