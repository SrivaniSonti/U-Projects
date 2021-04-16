package com.ukable.repository;

import org.springframework.data.repository.CrudRepository;

import com.ukable.model.Department;

public interface DepartmentRepository  extends CrudRepository<Department,String>{

}
