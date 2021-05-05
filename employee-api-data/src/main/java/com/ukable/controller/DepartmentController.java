package com.ukable.controller;

import com.ukable.model.Department;
import com.ukable.model.Employee;
import com.ukable.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @GetMapping(value = "/departments")
    public List<Department> getAllDepartments() {
        return departmentService.getAllDepartments();
    }

    @GetMapping(value = "/departments/{id}")
    public Department getDepartment(@PathVariable String id) {
        return departmentService.getDepartment(id);
    }

    @PostMapping(value = "/departments")
    public Department addDepartment(@RequestBody Department department) {
        return departmentService.addDepartment(department);
    }

    @PutMapping(value = "/departments/{id}")
    public Department updateDepartment(@RequestBody Department department, @PathVariable String id) {
        return departmentService.updateDepartment(id, department);
    }

    @DeleteMapping(value = "/departments/{id}")
    public void deleteDepartment(@PathVariable String id) {
    	departmentService.deleteDepartment(id);
    }

}
