package com.example.noths;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import com.example.noths.dto.CheckoutItem;
import com.example.noths.dto.Product;
import com.example.noths.promotion.MultiDiscountPromotion;
import com.example.noths.promotion.Promotion;

class MultiDiscountPromotionTest {
	private Promotion promotion = new MultiDiscountPromotion(MockData.TRAVEL_CARD_HOLDER.getProductCode(), 2, BigDecimal.valueOf(8.50));

	@BeforeEach
	void setUp() throws Exception {
	
	}

	@ParameterizedTest
	@MethodSource("parameterisedTest")
	void test(List<Product> products, BigDecimal expectedPrice) {
		ShoppingBasket basket = new ShoppingBasket();
		//convert Product to CheckoutItem
		products.forEach(product -> basket.addItem(CheckoutItem.getCheckoutItem(product)));
		promotion.applyOffer(basket);
		
		assertTrue(expectedPrice.compareTo(basket.getTotalPrice()) == 0);
	}

	private static Stream<Arguments> parameterisedTest() {
	    return Stream.of(
	      //Two Travel Card Holder fulfilling promotion
	      Arguments.of(Arrays.asList(MockData.TRAVEL_CARD_HOLDER,MockData.KIDS_T_SHIRT, 
	    		  MockData.TRAVEL_CARD_HOLDER), BigDecimal.valueOf(36.95)),
	      //One Travel Card Holder not fulfilling promotion
	      Arguments.of(Arrays.asList(MockData.TRAVEL_CARD_HOLDER,MockData.PERSONALISED_CUFFLINKS, 
	    		  MockData.KIDS_T_SHIRT), BigDecimal.valueOf(74.20)),
	    //Three Travel Card Holder fulfilling promotion
	      Arguments.of(Arrays.asList(MockData.TRAVEL_CARD_HOLDER,MockData.KIDS_T_SHIRT, 
	    		  MockData.TRAVEL_CARD_HOLDER, MockData.PERSONALISED_CUFFLINKS, MockData.TRAVEL_CARD_HOLDER), BigDecimal.valueOf(90.45)),
	    //No Travel Card Holder 
	      Arguments.of(Arrays.asList(MockData.PERSONALISED_CUFFLINKS, MockData.KIDS_T_SHIRT,
	    		  MockData.KIDS_T_SHIRT), BigDecimal.valueOf(84.90)),
	    //empty list  
	      Arguments.of(Collections.emptyList(), BigDecimal.ZERO)
	      );
	}
	
	
}
