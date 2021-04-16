package com.ukable.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.ukable.model.Employee;

public interface EmployeeRepository extends CrudRepository<Employee,String>{

	List<Employee> findByEmployeeName(String name);
	List<Employee> findByDepartmentId(String departmentId);

}
