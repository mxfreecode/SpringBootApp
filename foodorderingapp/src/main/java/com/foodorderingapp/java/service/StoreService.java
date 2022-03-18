package com.foodorderingapp.java.service;

import com.foodorderingapp.java.dto.StoreRequestDTO;
import com.foodorderingapp.java.dto.StoreResponseDTO;
import com.foodorderingapp.java.entity.Store;

public interface StoreService {

	StoreResponseDTO getAllStoreDetails(Integer pageNo, Integer pageSize);

	Store saveStoreDetails(StoreRequestDTO storeRequestDTO);

}
