package com.unthinkable.repository;

import org.springframework.data.repository.CrudRepository;

import com.unthinkable.entity.Department;

public interface DepartmentRepository  extends CrudRepository<Department,String>{

}
