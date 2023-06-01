package com.busayo.departmentservice.service.impl;

import com.busayo.departmentservice.dto.DepartmentDto;
import com.busayo.departmentservice.entity.Department;
import com.busayo.departmentservice.mapper.DepartmentMapper;
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

        Department department = DepartmentMapper.mapToDepartment(departmentDto);

        Department savedDepartment= departmentRepository.save(department);

        DepartmentDto savedDepartmentDto = DepartmentMapper.mapToDepartmentDto(savedDepartment);
        return savedDepartmentDto;
    }

    @Override
    public DepartmentDto getDepartmentByCode(String code) {
        Department department = departmentRepository.findByDepartmentCode(code);

        DepartmentDto departmentDto = DepartmentMapper.mapToDepartmentDto(department);
        return departmentDto;
    }
}
