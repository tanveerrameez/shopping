package com.example.noths.promotion;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import com.example.noths.ShoppingBasket;
import com.example.noths.dto.CheckoutItem;

/**
 * Promotion implementation that offers discounted price per item
 * when multiple of the items are checked out 
 * @author tanveerrameez
 *
 */
public class MultiDiscountPromotion implements Promotion {
	private String productCode;
	private int itemCountForDiscount;
	private BigDecimal discountedPrice;
	
	public MultiDiscountPromotion(String productCode, int itemCountForDiscount, BigDecimal discountedPrice) {
		this.productCode = productCode;
		this.itemCountForDiscount = itemCountForDiscount;
		this.discountedPrice = discountedPrice;
	}

	@Override
	public void applyOffer(ShoppingBasket basket) {
		List<CheckoutItem> items = basket.getCheckoutItems();
		//find the items under current offer
		List<CheckoutItem> itemsUnderPromotion = items.stream().filter(checkoutItem -> checkoutItem.getProductCode().equals(productCode)).
				collect(Collectors.toList());
		int productUnderPromotionCount = itemsUnderPromotion.size();
		//if itemCountForDiscount has been reached, set the price of the items on offer to discounted price
		if(productUnderPromotionCount >= itemCountForDiscount) {
			itemsUnderPromotion.forEach(item -> item.setPrice(discountedPrice));	
		}
	    basket.calculateTotal();
	}
}
