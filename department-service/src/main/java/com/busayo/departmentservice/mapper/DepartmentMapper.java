package com.busayo.departmentservice.mapper;

import com.busayo.departmentservice.dto.DepartmentDto;
import com.busayo.departmentservice.entity.Department;

public class DepartmentMapper {
    public static DepartmentDto mapToDepartmentDto(Department department){
        DepartmentDto departmentDto = new DepartmentDto(
                department.getId(),
                department.getName(),
                department.getDescription(),
                department.getDepartmentCode()
        );
        return departmentDto;
    }

    public static Department mapToDepartment(DepartmentDto departmentDto){
        Department department = new Department(
                departmentDto.getId(),
                departmentDto.getName(),
                departmentDto.getDescription(),
                departmentDto.getDepartmentCode()
        );
        return  department;
    }
}
