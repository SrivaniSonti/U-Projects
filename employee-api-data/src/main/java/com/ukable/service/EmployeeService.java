package com.ukable.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ukable.model.Department;
import com.ukable.model.Employee;
import com.ukable.repository.DepartmentRepository;
import com.ukable.repository.EmployeeRepository;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
	private DepartmentRepository departmentRepository;

	public List<Employee> getAllEmployees() {
		List<Employee> employees = new ArrayList<>();
		employeeRepository.findAll().forEach(employees::add);
		return employees;
	}

	public List<Employee> getAllEmployeesByDepartmentId(String id) {
		List<Employee> employees = new ArrayList<>();
		employeeRepository.findByDepartmentId(id).forEach(employees::add);
		return employees;
	}

	public Employee getEmployeeById(String id) {
		Employee employee = new Employee();
		employee =  employeeRepository.findById(id).get();
		return employee;
	}

	public List<Employee> getEmployeesByName(String name) {
		List<Employee> employees = new ArrayList<>();
		employees = employeeRepository.findByEmployeeName(name);
		return employees;
	}

	public String addEmployee(String deptId , Employee employee) {
		Boolean departmentFound = departmentRepository.findById(deptId).isPresent();
		if(departmentFound) {
			employee.setDepartment(new Department(deptId,""));
			employeeRepository.save(employee);
			return "Employee Created";
		}else {
			return "Unable to Create Employee, Department ID not found";
		}
	}

	public String updateEmployee(String deptId, String empId, Employee employee) {
		Boolean employeeFound = employeeRepository.findById(empId).isPresent();
		if(employeeFound) {
			employeeRepository.save(employee);
			return "Employee Updated";
		}else
		{
			Boolean departmentFound = departmentRepository.findById(deptId).isPresent();
		
			if(departmentFound) {
				employee.setDepartment(new Department(deptId,""));
				employeeRepository.save(employee);
				return "New Employee Created";
			}
			return "Created new Employee";
		}
			
	}

	public String deleteEmployee(String empId) {
		Boolean employeeFound = employeeRepository.findById(empId).isPresent();
		if(employeeFound) {
		employeeRepository.deleteById(empId);
		return "Employee Deleted";
		}else
		return "Employee not found";

	}

}
