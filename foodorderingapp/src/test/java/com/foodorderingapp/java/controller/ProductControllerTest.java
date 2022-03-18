package com.foodorderingapp.java.controller;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.foodorderingapp.java.dto.ProductRequestDTO;
import com.foodorderingapp.java.service.ProductService;

@WebMvcTest(ProductController.class)
public class ProductControllerTest {
	
	@MockBean
    ProductService productservice;

    @Autowired
    MockMvc mockMvc;

    ProductRequestDTO productRequestDTO;

    @BeforeEach
    public void setup() {
        productRequestDTO = new ProductRequestDTO();
        productRequestDTO.setProductName("Cookies");
        productRequestDTO.setProductDescription("Cookies with chocolate");
        productRequestDTO.setAvailable(true);
        productRequestDTO.setProductCategory("Generic food");
        productRequestDTO.setProductPrice(3);
        productRequestDTO.setStoreId(3);
    }

    @Test
    public void saveProductDetailsTest() throws Exception{
        mockMvc.perform(post("/products")
                .content(asJsonString(productRequestDTO))
                .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isAccepted())
        .andExpect(jsonPath("$.message").value("Product Saved Success"))
        .andExpect(jsonPath("$.statusCode").value(200));
    }
 
    private String asJsonString(ProductRequestDTO productRequestDTO) {
        try {
            return new ObjectMapper().writeValueAsString(productRequestDTO);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }
	
	@Test
	public void getAllProductDetails() throws Exception {
		mockMvc.perform(get("/stores/{storeId}/products", 1)
				.contentType("application/json")
				.param("storeId", "1"))
			.andExpect(status().isOk());
	}
}
