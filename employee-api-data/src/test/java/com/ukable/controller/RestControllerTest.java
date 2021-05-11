package com.ukable.controller;

import static org.junit.Assert.assertEquals;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ukable.model.Department;
import com.ukable.model.Employee;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class RestControllerTest {

	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext wc;

	List<Employee> employees = new ArrayList<Employee>();
	ObjectMapper MAPPER = new ObjectMapper();

	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(wc).build();
	}

	@Test
	public void saveDepartmentTest() throws JsonProcessingException, Exception {
		Department department = new Department("1", "Testing");
		MvcResult result = mockMvc.perform(
				post("/api/v1/departments").contentType(APPLICATION_JSON_VALUE).content(MAPPER.writeValueAsString(department)))
				.andExpect(status().isOk()).andReturn();
		Department response = MAPPER.readValue(result.getResponse().getContentAsString(), Department.class);
		assertEquals("1", response.getId());

	}

	@Test
	public void createEmployeeTest() throws JsonProcessingException, Exception {
		Employee employee = new Employee("1", "srivani", "123456", new Department("1", "Testing"));
		MvcResult result = mockMvc.perform(post("/api/v1/departments/{department_Id}/employees", "1")
				.contentType(APPLICATION_JSON_VALUE).content(MAPPER.writeValueAsString(employee)))
				.andExpect(status().isOk()).andReturn();
		assertEquals("Employee Created", result.getResponse().getContentAsString());

	}

	@Test
	public void saveEmployeeTest() throws JsonProcessingException, Exception {
		Employee employee = new Employee("1", "srivani", "123456", new Department("1", "Testing"));
		mockMvc.perform(put("/api/v1/departments/{department_Id}/employees/{employee_id}", "1", "1")
				.contentType(APPLICATION_JSON_VALUE).content(MAPPER.writeValueAsString(employee)))
				.andExpect(status().isOk()).andReturn();
	}

	@Test
	public void getEmployees() throws Exception {
		MvcResult result = mockMvc.perform(get("/api/v1/employees").contentType(APPLICATION_JSON_VALUE))
				.andExpect(status().isOk()).andReturn();
		List<Employee> response = MAPPER.readValue(result.getResponse().getContentAsString(),
				new TypeReference<List<Employee>>() {
				});
		assertEquals(employees, response);
	}

	@Test
	public void deleteEmployee() throws Exception {
		MvcResult result = mockMvc.perform(delete("/api/v1/departments/{department_Id}/employees/{employee_id}","1","1").contentType(APPLICATION_JSON_VALUE))
				.andExpect(status().isOk()).andReturn();
	}

}