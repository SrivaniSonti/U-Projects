package com.unthinkable.employee;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface EmployeeRepository extends CrudRepository<Employee,String>{

	List<Employee> findByEmployeeName(String name);
	List<Employee> findByDepartmentId(String departmentId);

}
