package com.foodorderingapp.java.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.foodorderingapp.java.dto.StoreRequestDTO;
import com.foodorderingapp.java.dto.StoreResponseDTO;
import com.foodorderingapp.java.entity.Address;
import com.foodorderingapp.java.entity.Product;
import com.foodorderingapp.java.entity.ProductCategory;
import com.foodorderingapp.java.entity.Store;
import com.foodorderingapp.java.repo.StoreRepo;

@ExtendWith(MockitoExtension.class)
public class StoreServiceImplTest {
	@Mock
    StoreRepo storeRepo; //Mock object

    /*@Mock
    ProductRepo productRepo; //Mock object*/

    @InjectMocks
    StoreServiceImpl storeServiceImpl; //Real object whit mock dependency objects

    StoreRequestDTO storeRequestDTO;

    Store store;
    Pageable paging;

    @BeforeEach
    public void setup() {
        Product product = new Product();
        
        paging = PageRequest.of(1, 1);
 
        storeRequestDTO = new StoreRequestDTO();
        storeRequestDTO.setOpenTill("20:00");
        storeRequestDTO.setRating(4f);
        storeRequestDTO.setStoreDescription("Chicken & grill");
        storeRequestDTO.setStoreName("Crazy Chicken");

        Address address = new Address();
        address.setCity("Guadalajara");
        address.setPincode("44500");
        address.setStreet("Av. Chapultepec");

        store = new Store();
        store.setStoreId(1);
        store.setProductList(List.of(product));
        store.setAddress(address);

        product.setStore(store);
        product.setProductId(1);
        product.setProductCategory(ProductCategory.NONVEG);
        product.setProductDescription("Chicken with chili");
        product.setAvailable(true);
        product.setProductName("Chicken");
        product.setProductPrice(20);
        

    }

    @Test
    @DisplayName("save store details: positive")
    public void saveStoreDetailsTest() {
        //storeRepo.save
        when(storeRepo.save(any(Store.class))).thenAnswer(i -> {
            Store store = i.getArgument(0);
            store.setStoreId(1);
            return store;
        });

        Store storeResult = storeServiceImpl.saveStoreDetails(storeRequestDTO);
        assertNotNull(storeResult);
        assertEquals(1, storeResult.getStoreId());
        assertEquals("Crazy Chicken", storeResult.getStoreName());
    }
    
    @Test
    @DisplayName("Get all store details: negative")
    public void getAllStoreDetails() {
    	
    	when(storeRepo.findAllStores(paging)).thenReturn(List.of(store));
    	
    	StoreResponseDTO storeResponseDTO = storeServiceImpl.getAllStoreDetails(1, 1);
    	
    	assertNotNull(storeResponseDTO);
    	assertEquals("Store Details Fetch Success", storeResponseDTO.getMessage());
    	assertEquals(200, storeResponseDTO.getStatusCode());
    }
    
    @Test
    @DisplayName("Get all store details: negative")
    public void getAllStoreDetails2() {
    	
    	when(storeRepo.findAllStores(paging)).thenReturn(Collections.emptyList());
    	
    	assertThrows(NullPointerException.class, () -> storeServiceImpl.getAllStoreDetails(1, 1));
    }
}
