package com.unthinkable.repository;

import org.springframework.data.repository.CrudRepository;

import com.unthinkable.model.Department;

public interface DepartmentRepository  extends CrudRepository<Department,String>{

}
