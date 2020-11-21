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
import com.example.noths.promotion.PercentOffPromotion;
import com.example.noths.promotion.Promotion;

class PercentOffPromotionTest {

	Promotion promotion = new PercentOffPromotion(BigDecimal.valueOf(60), BigDecimal.valueOf(10));
	
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
	      //Spend over £60, hence fulfilling promotion discount of 10%
	      Arguments.of(Arrays.asList(MockData.TRAVEL_CARD_HOLDER,MockData.PERSONALISED_CUFFLINKS, 
	    		  MockData.KIDS_T_SHIRT), BigDecimal.valueOf(66.78)),
	      //Spend less than £60, not fulfilling promotion
	      Arguments.of(Arrays.asList(MockData.TRAVEL_CARD_HOLDER,MockData.PERSONALISED_CUFFLINKS), BigDecimal.valueOf(54.25)),
	    
	      //empty list  
	      Arguments.of(Collections.emptyList(), BigDecimal.ZERO)
	      );
	}

}
