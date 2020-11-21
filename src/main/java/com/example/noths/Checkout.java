package com.example.noths;

import java.math.RoundingMode;
import java.util.List;

import com.example.noths.dto.CheckoutItem;
import com.example.noths.dto.Product;
import com.example.noths.promotion.Promotion;

/**
 * Class to checkout the items , updating the total price taking into account the promotions
 * @author tanveerrameez
 *
 */
public class Checkout {
	/**
	 * List of promotions
	 */
	private List<Promotion> promotionalRules;
	
	private ShoppingBasket basket;

	public Checkout() {
		basket = new ShoppingBasket();
	}

	public Checkout(List<Promotion> promotionalRules) {
		this.promotionalRules = promotionalRules;
		basket = new ShoppingBasket();
	}

	public void addPromotionalRule(Promotion promotionalRule) {
		if (promotionalRule != null)
			promotionalRules.add(promotionalRule);
	}

	public void removePromotionalRule(Promotion promotionalRule) {
		promotionalRules.remove(promotionalRule);
	}

	public void scan(Product product) {
		if (product != null)
			basket.addItem(CheckoutItem.getCheckoutItem(product));
	}

	public double total() {
		if (promotionalRules != null && !promotionalRules.isEmpty()) {
			//Promotion are applied in the order they are set in the promotionList
			promotionalRules.forEach(promotion -> {
				promotion.applyOffer(basket);
			});
		} else
			basket.calculateTotal();
		return basket.getTotalPrice().setScale(2, RoundingMode.HALF_UP).doubleValue();
	}

}
