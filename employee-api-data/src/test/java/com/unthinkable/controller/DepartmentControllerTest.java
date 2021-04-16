package com.unthinkable.controller;

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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ukable.controller.DepartmentController;
import com.ukable.model.Department;
import com.ukable.service.DepartmentService;

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
				.perform(MockMvcRequestBuilders.get("/departments").accept(MediaType.APPLICATION_JSON)).andReturn();

		assertThat(result.getResponse().getContentAsString()).isEqualTo(this.mapToJson(allDepartments));
	}

	private String mapToJson(Object object) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.writeValueAsString(object);
	}
}
