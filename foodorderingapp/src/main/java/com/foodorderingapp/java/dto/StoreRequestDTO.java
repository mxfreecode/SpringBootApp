package com.foodorderingapp.java.dto;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;


import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.foodorderingapp.java.entity.Address;

public class StoreRequestDTO {
	
	@NotEmpty(message = "Store name should not be empty")
	private String storeName;
	
	@Size(min = 1, message = "Adress should not be empty")
	private List<Address> address = new ArrayList<>();
	
	private float rating;
	
	//@JsonDeserialize(using = LocalTimeDeserializer.class)
	//@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH-mm")
	private String openTill;
	
	@NotEmpty(message = "Store description should not be empty")
	private String storeDescription;
	
	public String getStoreName() {
		return storeName;
	}
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
	public List<Address> getAddress() {
		return address;
	}
	public void setAddress(List<Address> address) {
		this.address = address;
	}
	public float getRating() {
		return rating;
	}
	public void setRating(float rating) {
		this.rating = rating;
	}
	public String getOpenTill() {
		return openTill;
	}
	public void setOpenTill(String localTime) {
		this.openTill = localTime;
	}
	public String getStoreDescription() {
		return storeDescription;
	}
	public void setStoreDescription(String storeDescription) {
		this.storeDescription = storeDescription;
	}
}
