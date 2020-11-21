package com.example.noths.promotion;

import java.math.BigDecimal;

import com.example.noths.ShoppingBasket;

/**
 * Promotion implementation that offers discount on the total price
 * when the total price exceed a threshold
 * @author tanveerrameez
 *
 */
public class PercentOffPromotion implements Promotion {

	private BigDecimal minSpend;
	private BigDecimal percentOffInDecimals;

	public PercentOffPromotion(BigDecimal minSpend, BigDecimal percentOff) {
		this.minSpend = minSpend;
		this.percentOffInDecimals = percentOff.divide(BigDecimal.valueOf(100));
	}

	@Override
	public void applyOffer(ShoppingBasket basket) {
		basket.calculateTotal();
		//if totalPrice is more than minimum spend
		if(basket.getTotalPrice().compareTo(minSpend) == 1){
			basket.setTotalPrice(basket.getTotalPrice().multiply(BigDecimal.ONE.subtract(percentOffInDecimals)));
		}
	}

}
