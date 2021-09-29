package net.javaguides.springboot;

package com.spring.boot.curd.app;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.boot.curd.app.pojo.Employee;
import com.spring.boot.curd.app.pojo.Response;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EmsApiSpringJpaDataHibernateMySqlApplicationTests {

	private MockMvc mockMvc;
	private ObjectMapper om = new ObjectMapper();
	@Autowired(required = true)
	private WebApplicationContext webApplicationContext;

	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
				.build();
	}

	@Test
	public void addEmployeeTest() throws Exception {
		Employee employee = new Employee();
		employee.setId(117);
		employee.setName("user8");
		employee.setDept("IT");
		employee.setSalary(40000);
		String request = om.writeValueAsString(employee);
		MvcResult result = mockMvc
				.perform(
						post("/EMS/add").content(request).contentType(
								MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk()).andReturn();
		String resultContent = result.getResponse().getContentAsString();
		Response response = om.readValue(resultContent, Response.class);
		Assert.assertTrue(response.isSuccess() == true);
	}

	@Test
	public void getAllEmployeeTest() throws Exception {
		MvcResult result = mockMvc
				.perform(
						MockMvcRequestBuilders.get("/EMS/getAllEmployee")
								.contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
		String resultContent = result.getResponse().getContentAsString();
		Response response = om.readValue(resultContent, Response.class);
		Assert.assertTrue(response.isSuccess() == true);
	}

	@Test
	public void getEmployeeTest() throws Exception {
		int id = 234;
		MvcResult result = mockMvc
				.perform(
						MockMvcRequestBuilders.get("/EMS/getEmployee/" + id)
								.contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
		String resultContent = result.getResponse().getContentAsString();
		Response response = om.readValue(resultContent, Response.class);
		Assert.assertTrue(response.isSuccess() == true);
	}

	@Test
	public void deleteEmployeeTest() throws Exception {
		int id = 512;
		MvcResult result = mockMvc
				.perform(
						MockMvcRequestBuilders.delete("/EMS/delete/" + id)
								.contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
		String resultContent = result.getResponse().getContentAsString();
		Response response = om.readValue(resultContent, Response.class);
		Assert.assertTrue(response.isSuccess() == true);
	}
}
