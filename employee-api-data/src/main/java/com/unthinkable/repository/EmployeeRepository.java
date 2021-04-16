package com.unthinkable.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.unthinkable.model.Employee;

public interface EmployeeRepository extends CrudRepository<Employee,String>{

	List<Employee> findByEmployeeName(String name);
	List<Employee> findByDepartmentId(String departmentId);

}
