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
import static org.mockito.Matchers.any;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ukable.model.Department;
import com.ukable.service.DepartmentService;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@RunWith(MockitoJUnitRunner.class)
public class DepartmentControllerTest {

	private MockMvc mockMvc;

	@Mock
	private DepartmentService departmentService;

	@InjectMocks
	private DepartmentController departmentController;

	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.standaloneSetup(departmentController).build();
	}

	@Test
	public void getAllDepartmentsTest() throws Exception {
		List<Department> allDepartments = Arrays.asList(new Department("1", "Srivani"), new Department("2", "Shiva"));
		Mockito.when(departmentService.getAllDepartments()).thenReturn(allDepartments);
		MvcResult result = mockMvc
				.perform(MockMvcRequestBuilders.get("/api/v1/departments").accept(MediaType.APPLICATION_JSON)).andReturn();
		assertThat(result.getResponse().getContentAsString()).isEqualTo(this.mapToJson(allDepartments));
	}

	@Test
	public void getDepartmentTest() throws Exception {
		Department department = new Department("1", "Srivani");
		Mockito.when(departmentService.getDepartment("1")).thenReturn(department);
		MvcResult result = mockMvc
				.perform(MockMvcRequestBuilders.get("/api/v1/departments/1").accept(MediaType.APPLICATION_JSON)).andReturn();
		assertThat(result.getResponse().getContentAsString()).isEqualTo(this.mapToJson(department));
	}

	@Test
	public void addDepartmentTest() throws Exception {
		Department department = new Department("1", "Srivani");
		Mockito.when(departmentService.addDepartment(department)).thenReturn(department);
		MvcResult result = mockMvc
				.perform(MockMvcRequestBuilders.post("/api/v1/departments").accept(MediaType.APPLICATION_JSON)).andReturn();
		//assertThat(result.getResponse().getContentAsString()).isEqualTo(this.mapToJson(department));

	}

	private String mapToJson(Object object) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.writeValueAsString(object);
	}
}
