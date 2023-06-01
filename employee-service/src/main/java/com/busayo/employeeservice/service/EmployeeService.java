package com.busayo.employeeservice.service;

import com.busayo.employeeservice.dto.EmployeeDto;
import com.busayo.employeeservice.dto.ResponseDto;

public interface EmployeeService {
    EmployeeDto saveEmployee(EmployeeDto employeeDto);
    ResponseDto getEmployeeById(Long employeeId);
}
