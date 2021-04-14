package com.unthinkable.employee;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.unthinkable.entity.Employee;
import com.unthinkable.repository.EmployeeRepository;
import com.unthinkable.service.EmployeeService;

@SpringBootTest
class EmployeeApiDataApplicationTests {

	@Autowired
	private EmployeeService employeeService;

	@MockBean
	private EmployeeRepository employeeRepository;

	
	  @Test public void getAllEmployeesTest() {
	  when(employeeRepository.findAll()).thenReturn(Stream .of(new
	  Employee("","","", null),new Employee("","","", null)).collect(Collectors.toList()));
	  assertEquals(2, employeeService.getAllEmployees().size());
	  
	  }
	  
	  @Test public void getEmployeeByIdTest() { String name = "srivani";
	  when(employeeRepository.findByEmployeeName(name)).thenReturn(Stream .of(new
	  Employee("AP-2098","Srivani","5849123658",null),new
	  Employee("AP-2009","shiva","5849123658",null)).collect(Collectors.toList()));
	  assertEquals(2, employeeService.getEmployeeByName(name).size()); }
	 

}
