package com.example.noths.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Item to be checked out. This item is obtained by checking out the Product
 * and hence contains the basic minimum information required to checkout a product
 * @author tanveerrameez
 *
 */

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CheckoutItem {
	private String productCode;
	@Setter private BigDecimal price;
	
	public static CheckoutItem getCheckoutItem(Product product) {
		return new CheckoutItem(product.getProductCode(), product.getPrice());
	}
	

}
