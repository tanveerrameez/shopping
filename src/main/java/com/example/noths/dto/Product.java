package com.example.noths.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Encapsulates the product to be sold and contains the information of the product
 * @author tanveerrameez
 *
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product {

	private String productCode;
	private String name;
	private BigDecimal price;

	public boolean equals(Object o) {
		if(o instanceof Product) {
			Product i = (Product)o;
			return (this.productCode.equals(i.getProductCode()));
		} else return false;
	}
	
	public int hashCode() {
		int hash = 7;
	    return 31 * hash + (int) productCode.hashCode();
	}
	
}
