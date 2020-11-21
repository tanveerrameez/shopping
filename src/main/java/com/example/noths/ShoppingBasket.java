package com.example.noths;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.example.noths.dto.CheckoutItem;

import lombok.Getter;
import lombok.Setter;

/**
 * Class representing the shopping baster, containing the items to be checked out and the total price
 *  after taking into account the promotions if available.
 * @author tanveerrameez
 *
 */
public class ShoppingBasket {
	@Getter private List<CheckoutItem> checkoutItems;
	
	@Getter @Setter private BigDecimal totalPrice;
	
	public ShoppingBasket() {
		checkoutItems = new ArrayList<>();
		totalPrice = BigDecimal.ZERO;
	}
	public void addItem(CheckoutItem item) {
		if(item != null)
		   checkoutItems.add(item);
	}
	public void removeItem(CheckoutItem item) {
		checkoutItems.remove(item);
	}

	public  void calculateTotal() {
		totalPrice = checkoutItems.stream().map(product -> product.getPrice()).reduce(BigDecimal.ZERO, BigDecimal::add);
	}
}
