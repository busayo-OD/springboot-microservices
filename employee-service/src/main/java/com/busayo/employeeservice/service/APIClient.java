package com.busayo.employeeservice.service;

import com.busayo.employeeservice.dto.DepartmentDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "DEPARTMENT-SERVICE")
public interface APIClient {

    @GetMapping("departments/{code}")
    DepartmentDto getDepartment(@PathVariable String code);

}
