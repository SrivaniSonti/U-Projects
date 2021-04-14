package com.unthinkable.employee;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	public List<Employee> getAllEmployees() {
		List<Employee> employees= new ArrayList<>();
		employeeRepository.findAll()
		.forEach(employees::add);
		return employees;
	}
	
	public List<Employee> getAllEmployeesByDepartmentId(String id) {
		List<Employee> employees= new ArrayList<>();
		employeeRepository.findByDepartmentId(id)
		.forEach(employees::add);
		return employees;
	}
	
	public Employee getEmployeeById(String id) {
		return employeeRepository.findById(id).get();
	}
	
	
	public List<Employee> getEmployeeByName(String name) {
		return employeeRepository.findByEmployeeName(name);
	}

	public void addEmployee(Employee employee) {
		employeeRepository.save(employee);
	}
	
	public void updateEmployee(String id, Employee employee) {
		employeeRepository.save(employee);
	}

	public String deleteEmployee(String id) {
		employeeRepository.deleteById(id);
		return "deleted";
		
	}


}
