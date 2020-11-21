package com.example.noths;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.example.noths.dto.Product;

public class MockData {
	
	public static final Product TRAVEL_CARD_HOLDER = new Product("001","Travel Card Holder", BigDecimal.valueOf(9.25));
	public static final Product PERSONALISED_CUFFLINKS = new Product("002","Personalised cufflinks", BigDecimal.valueOf(45.00));
	public static final Product KIDS_T_SHIRT = new Product("003","Kids T-shirt ", BigDecimal.valueOf(19.95));
	public static List<Product> productList = new ArrayList<>();
	
	static {
		productList.add(TRAVEL_CARD_HOLDER);
		productList.add(PERSONALISED_CUFFLINKS);
		productList.add(KIDS_T_SHIRT);
	}
	
	public static List<Product> getMockProducts() {
		return productList;
	}
	
	

}
