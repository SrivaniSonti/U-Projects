package com.unthinkable.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.unthinkable.entity.Department;
import com.unthinkable.entity.Employee;
import com.unthinkable.service.EmployeeService;

@RestController
//@RequestMapping("/get")
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService;
	
	@GetMapping(value ="/employees")
	public List<Employee> getAllEmployees() {
		return employeeService.getAllEmployees(); 
	}
	
	@GetMapping(value ="/departments/{department_Id}/employees")
	public List<Employee> getAllEmployeesByDepartmentId(@PathVariable String department_Id) {
		return employeeService.getAllEmployeesByDepartmentId(department_Id); 
	}
	
	@GetMapping(value ="/departments/{department_Id}/employees/{employee_Id}")
	public Employee getEmployeeById(@PathVariable String employee_Id) {
		return employeeService.getEmployeeById(employee_Id); 
	}
	
	@GetMapping(value = "/employees/{name}")
	public List<Employee> getEmployeeByName(@PathVariable String name) {
		return employeeService.getEmployeeByName(name);
	}
	
	@PostMapping(value = "/departments/{department_Id}/employees")
	public void addEmployee(@RequestBody Employee employee, @PathVariable String department_Id) {
		employee.setDepartment(new Department(department_Id,""));
		employeeService.addEmployee(employee);
	}
	
	@PutMapping( value = "/departments/{department_Id}/employees/{employee_id}")
	public void updateEmployee(@RequestBody Employee employee,@PathVariable String department_Id ,@PathVariable String employee_id) {
		employee.setDepartment(new Department(department_Id,""));
		employeeService.updateEmployee(employee_id,employee);
	}

	@DeleteMapping( value = "/departments/{department_Id}/employees/{employee_id}")
	public void deleteEmployee(@PathVariable String employee_id) {
		employeeService.deleteEmployee(employee_id);
	}
}
