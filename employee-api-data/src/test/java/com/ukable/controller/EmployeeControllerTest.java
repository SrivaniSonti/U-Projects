package com.ukable.controller;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ukable.model.Department;
import com.ukable.model.Employee;
import com.ukable.service.EmployeeService;

@RunWith(MockitoJUnitRunner.class)
public class EmployeeControllerTest {

	private MockMvc mockMvc;

	@Mock
	private EmployeeService employeeService;

	@InjectMocks
	private EmployeeController employeeController;

	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.standaloneSetup(employeeController).build();
	}

	@Test
	public void getAllEmployeesTest() throws Exception {
		List<Employee> allEmployees = Arrays.asList(new Employee("1", "Srivani", "987757", new Department("1", "Dev")),
													new Employee("2", "Shiva", "123456", new Department("2", "Testing")));
		Mockito.when(employeeService.getAllEmployees()).thenReturn(allEmployees);
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/employees").accept(MediaType.APPLICATION_JSON))
				.andReturn();
		assertThat(result.getResponse().getContentAsString()).isEqualTo(this.mapToJson(allEmployees));
	}
	
	@Test
	public void getEmployeesByNameTest() throws Exception {
		List<Employee> allEmployees = Arrays.asList(new Employee("1", "srivani", "987757", new Department("1", "Dev")),
				new Employee("2", "Shiva", "123456", new Department("2", "Testing")));
		Mockito.when(employeeService.getEmployeesByName("srivani")).thenReturn(allEmployees);
		mockMvc.perform(MockMvcRequestBuilders.get("/employees/{name}","srivani").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}
	
	@Test
	public void deleteEmployeeTest() throws Exception {
		Mockito.when(employeeService.deleteEmployee("1")).thenReturn("Success");
		mockMvc.perform(MockMvcRequestBuilders.delete("/departments/{department_Id}/employees/{employee_id}","1","1").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}
	
	private String mapToJson(Object object) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.writeValueAsString(object);
	}
}
