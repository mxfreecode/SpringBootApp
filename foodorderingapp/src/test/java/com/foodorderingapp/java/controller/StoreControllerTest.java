package com.foodorderingapp.java.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.foodorderingapp.java.dto.StoreRequestDTO;
import com.foodorderingapp.java.entity.Address;
import com.foodorderingapp.java.service.StoreService;

@WebMvcTest(StoreController.class)
public class StoreControllerTest {

	@MockBean
	StoreService storeService;
	
	@Autowired
	private MockMvc mockMvc;
	
	StoreRequestDTO storeRequestDTO;
	List<Address> addresses;
	Address address;
	
	@BeforeEach
	public void setUp() {
		storeRequestDTO = new StoreRequestDTO();
		addresses = new ArrayList<>();
		address = new Address();
		
		address.setCity("Guadalajara");
		address.setPincode("40000");
		address.setStreet("Reforma");
		
		addresses.add(address);
		
		storeRequestDTO.setStoreName("Pollo loco");
		storeRequestDTO.setAddress(addresses);
		storeRequestDTO.setRating(4);
		storeRequestDTO.setOpenTill("21:00");
		storeRequestDTO.setStoreDescription("Pollos asados");
	}
	
	@Test
	public void saveStore() throws Exception {
		mockMvc.perform(post("/store")
				.content(asJsonString(storeRequestDTO))
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isAccepted())
		.andExpect(jsonPath("$.message").value("Store Saved Success"))
		.andExpect(jsonPath("$.statusCode").value(200));
	}

	private String asJsonString(StoreRequestDTO storeRequestDTO2) {
		try {
			return new ObjectMapper().writeValueAsString(storeRequestDTO);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return null;
	}
}
