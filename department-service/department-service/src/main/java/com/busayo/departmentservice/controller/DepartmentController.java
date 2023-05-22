package com.busayo.departmentservice.controller;

import com.busayo.departmentservice.dto.DepartmentDto;
import com.busayo.departmentservice.service.DepartmentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/departments")
@AllArgsConstructor
public class DepartmentController {

    private DepartmentService departmentService;

    @PostMapping("/save")
    public ResponseEntity<DepartmentDto> saveDepartment(@RequestBody DepartmentDto departmentDto){
        DepartmentDto savedDepartment = departmentService.saveDepartment(departmentDto);
        return new ResponseEntity<>(savedDepartment, HttpStatus.CREATED);
    }

    @GetMapping("{code}")
    public ResponseEntity<DepartmentDto> getDepartment(@PathVariable String code){
        DepartmentDto departmentDto = departmentService.getDepartmentByCode(code);
        return new ResponseEntity<>(departmentDto, HttpStatus.OK);
    }
}
