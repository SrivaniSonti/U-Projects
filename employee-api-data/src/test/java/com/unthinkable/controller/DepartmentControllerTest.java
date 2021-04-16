package com.unthinkable.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.unthinkable.model.Department;
import com.unthinkable.service.DepartmentService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {DepartmentController.class})
public class DepartmentControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private DepartmentService departmentService;

	ObjectMapper objectMapper = new ObjectMapper();

	@Test
	@DisplayName("Test to get all departments")
	public void getAllDepartmentsTest() throws Exception {

		Department mockDepartment1 = new Department();
		mockDepartment1.setId("1");
		mockDepartment1.setName("Java");

		Department mockDepartment2 = new Department();
		mockDepartment2.setId("2");
		mockDepartment2.setName("Testing");

		List<Department> departmentList = new ArrayList<>();
		departmentList.add(mockDepartment1);
		departmentList.add(mockDepartment2);

		Mockito.when(departmentService.getAllDepartments()).thenReturn(departmentList);
		MvcResult result = mockMvc
				.perform(MockMvcRequestBuilders.get("/departments").accept(MediaType.APPLICATION_JSON)).andReturn();
		assertThat(result.getResponse().getContentAsString()).isEqualTo(this.mapToJson(departmentList));
	}

	@Test
	@DisplayName("Test to get Department by ID")
	public void getDepartmentTest() throws Exception {

		Department mockDepartment = new Department();
		mockDepartment.setId("1");
		mockDepartment.setName("Java");

		Mockito.when(departmentService.getDepartment(Mockito.anyString())).thenReturn(mockDepartment);

		MvcResult result = mockMvc
				.perform(MockMvcRequestBuilders.get("/departments/1").accept(MediaType.APPLICATION_JSON)).andReturn();

		assertThat(result.getResponse().getContentAsString()).isEqualTo(this.mapToJson(mockDepartment));
	}

	@Test
	@DisplayName("Test to add Department")
	public void addDepartmentTest() throws Exception {

		Department mockDepartment = new Department();
		mockDepartment.setId("3");
		mockDepartment.setName("Testing");
		String inputInJson = this.mapToJson(mockDepartment);

		Mockito.when(departmentService.addDepartment(Mockito.any(Department.class))).thenReturn(mockDepartment);

		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/departments")
				.accept(MediaType.APPLICATION_JSON).content(inputInJson).contentType(MediaType.APPLICATION_JSON))
				.andReturn();

		assertThat(result.getResponse().getContentAsString()).isEqualTo(this.mapToJson(mockDepartment));
		assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());
	}
	
	private String mapToJson(Object object) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.writeValueAsString(object);
	}

}
