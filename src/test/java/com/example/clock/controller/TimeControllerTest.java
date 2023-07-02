package com.example.clock.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.clock.service.TimeService;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@RunWith(SpringRunner.class)
@WebMvcTest(TimeController.class)
public class TimeControllerTest {

	@Autowired
	private MockMvc mvc;

	@MockBean
	private TimeService timeService;

	public static final int OK_STATUS = 200;

	@Test
	public void testConvertInputTimeForMidnight() throws Exception {
		when(timeService.convertToWords(0, 0)).thenReturn("Midnight");
		MvcResult result = mvc.perform(MockMvcRequestBuilders.get("http://localhost:8082/api/convertInputTime/0/0"))
				.andExpect(status().isOk()).andReturn();
		int status = result.getResponse().getStatus();
		String var = result.getResponse().getContentAsString();
		Assert.assertEquals(var, "It's Midnight");
		Assert.assertEquals(status, OK_STATUS);
	}

	@Test
	public void testConvertInputTimeForMidDay() throws Exception {
		when(timeService.convertToWords(12, 0)).thenReturn("Midday");
		MvcResult result = mvc.perform(MockMvcRequestBuilders.get("http://localhost:8082/api/convertInputTime/12/0"))
				.andExpect(status().isOk()).andReturn();
		int status = result.getResponse().getStatus();
		String var = result.getResponse().getContentAsString();
		Assert.assertEquals(var, "It's Midday");
		Assert.assertEquals(status, OK_STATUS);
	}

	@Test
	public void testConvertInputTimeForZeroMin() throws Exception {
		when(timeService.convertToWords(11, 0)).thenReturn("eleven o' clock");
		MvcResult result = mvc.perform(MockMvcRequestBuilders.get("http://localhost:8082/api/convertInputTime/11/0"))
				.andExpect(status().isOk()).andReturn();
		int status = result.getResponse().getStatus();
		String var = result.getResponse().getContentAsString();
		Assert.assertEquals(var, "It's eleven o' clock");
		Assert.assertEquals(status, OK_STATUS);
	}

	@Test
	public void testConvertInputTimeForOneMin() throws Exception {
		when(timeService.convertToWords(11, 1)).thenReturn("one minute past eleven");
		MvcResult result = mvc.perform(MockMvcRequestBuilders.get("http://localhost:8082/api/convertInputTime/11/1"))
				.andExpect(status().isOk()).andReturn();
		int status = result.getResponse().getStatus();
		String var = result.getResponse().getContentAsString();
		Assert.assertEquals(var, "It's one minute past eleven");
		Assert.assertEquals(status, OK_STATUS);
	}

	@Test
	public void testConvertInputTimeForFifNinemin() throws Exception {
		when(timeService.convertToWords(13, 59)).thenReturn("one minute to two");
		MvcResult result = mvc.perform(MockMvcRequestBuilders.get("http://localhost:8082/api/convertInputTime/13/59"))
				.andExpect(status().isOk()).andReturn();
		int status = result.getResponse().getStatus();
		String var = result.getResponse().getContentAsString();
		Assert.assertEquals(var, "It's one minute to two");
		Assert.assertEquals(status, OK_STATUS);
	}

	@Test
	public void testConvertInputTimeForFifteenMin() throws Exception {
		when(timeService.convertToWords(12, 15)).thenReturn("quarter past twelve");
		MvcResult result = mvc.perform(MockMvcRequestBuilders.get("http://localhost:8082/api/convertInputTime/12/15"))
				.andExpect(status().isOk()).andReturn();
		int status = result.getResponse().getStatus();
		String var = result.getResponse().getContentAsString();
		Assert.assertEquals(var, "It's quarter past twelve");
		Assert.assertEquals(status, OK_STATUS);
	}

	@Test
	public void testConvertInputTimeForThirtyMin() throws Exception {
		when(timeService.convertToWords(12, 30)).thenReturn("half past twelve");
		MvcResult result = mvc.perform(MockMvcRequestBuilders.get("http://localhost:8082/api/convertInputTime/12/30"))
				.andExpect(status().isOk()).andReturn();
		int status = result.getResponse().getStatus();
		String var = result.getResponse().getContentAsString();
		Assert.assertEquals(var, "It's half past twelve");
		Assert.assertEquals(status, OK_STATUS);
	}

	@Test
	public void testConvertInputTimeForFortyFiveMin() throws Exception {
		when(timeService.convertToWords(12, 45)).thenReturn("quarter to one");
		MvcResult result = mvc.perform(MockMvcRequestBuilders.get("http://localhost:8082/api/convertInputTime/12/45"))
				.andExpect(status().isOk()).andReturn();
		int status = result.getResponse().getStatus();
		String var = result.getResponse().getContentAsString();
		Assert.assertEquals(var, "It's quarter to one");
		Assert.assertEquals(status, OK_STATUS);
	}

	@Test
	public void testConvertInputTimeForLessThirty() throws Exception {
		when(timeService.convertToWords(12, 17)).thenReturn("seventeen minutes past twelve");
		MvcResult result = mvc.perform(MockMvcRequestBuilders.get("http://localhost:8082/api/convertInputTime/12/17"))
				.andExpect(status().isOk()).andReturn();
		int status = result.getResponse().getStatus();
		String var = result.getResponse().getContentAsString();
		Assert.assertEquals(var, "It's seventeen minutes past twelve");
		Assert.assertEquals(status, OK_STATUS);
	}

	@Test
	public void testConvertInputTimeForGreaterThirty() throws Exception {
		when(timeService.convertToWords(8, 50)).thenReturn("ten minutes to nine");
		MvcResult result = mvc.perform(MockMvcRequestBuilders.get("http://localhost:8082/api/convertInputTime/8/50"))
				.andExpect(status().isOk()).andReturn();
		int status = result.getResponse().getStatus();
		String var = result.getResponse().getContentAsString();
		Assert.assertEquals(var, "It's ten minutes to nine");
		Assert.assertEquals(status, OK_STATUS);
	}

}
