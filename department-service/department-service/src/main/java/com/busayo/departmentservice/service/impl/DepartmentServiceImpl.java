package com.busayo.departmentservice.service.impl;

import com.busayo.departmentservice.dto.DepartmentDto;
import com.busayo.departmentservice.entity.Department;
import com.busayo.departmentservice.repository.DepartmentRepository;
import com.busayo.departmentservice.service.DepartmentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    private DepartmentRepository departmentRepository;

    @Override
    public DepartmentDto saveDepartment(DepartmentDto departmentDto) {

        Department department = new Department(
                departmentDto.getId(),
                departmentDto.getName(),
                departmentDto.getDescription(),
                departmentDto.getDepartmentCode()
        );

        Department savedDepartment= departmentRepository.save(department);

        DepartmentDto savedDepartmentDto = new DepartmentDto(
                savedDepartment.getId(),
                savedDepartment.getName(),
                savedDepartment.getDescription(),
                savedDepartment.getDepartmentCode()
        );
        return savedDepartmentDto;
    }

    @Override
    public DepartmentDto getDepartmentByCode(String code) {
        Department department = departmentRepository.findByDepartmentCode(code);

        DepartmentDto departmentDto = new DepartmentDto(
                department.getId(),
                department.getName(),
                department.getDescription(),
                department.getDepartmentCode()
        );
        return departmentDto;
    }
}
