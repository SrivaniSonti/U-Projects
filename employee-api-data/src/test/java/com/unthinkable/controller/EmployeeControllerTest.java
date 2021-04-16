package com.unthinkable.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.unthinkable.EmployeeApiDataApplicationTests;
import com.unthinkable.model.Department;
import com.unthinkable.service.EmployeeService;

@RunWith(SpringRunner.class)
@WebMvcTest(value = EmployeeController.class)
public class EmployeeControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private EmployeeService employeeService;

	ObjectMapper objectMapper = new ObjectMapper();

	@Test
	@DisplayName("Test to get all departments")
	public void getAllDepartmentsTest() throws Exception {

		/*Employee mockEmployee1 = new Employee();

		Department mockDepartment2 = new Department();
		mockDepartment2.setId("2");
		mockDepartment2.setName("Testing");

		List<Department> departmentList = new ArrayList<>();
		departmentList.add(mockDepartment1);
		departmentList.add(mockDepartment2);

		Mockito.when(employeeService.getAllDepartments()).thenReturn(departmentList);
		MvcResult result = mockMvc
				.perform(MockMvcRequestBuilders.get("/employees").accept(MediaType.APPLICATION_JSON)).andReturn();
		assertThat(result.getResponse().getContentAsString()).isEqualTo(this.mapToJson(departmentList));*/
	}

}
