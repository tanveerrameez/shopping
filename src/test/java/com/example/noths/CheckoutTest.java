package com.example.noths;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import com.example.noths.dto.Product;
import com.example.noths.promotion.MultiDiscountPromotion;
import com.example.noths.promotion.PercentOffPromotion;
import com.example.noths.promotion.Promotion;

class CheckoutTest {
	private Checkout checkout;
	
	@BeforeEach
	void setUp() throws Exception {
		//promotions added in a particular order: PercentOffPromotion added after MultiDiscountPromotion
		List<Promotion> promotionalRules = new ArrayList<>();
		promotionalRules.add(new MultiDiscountPromotion(MockData.TRAVEL_CARD_HOLDER.getProductCode(), 
				2, BigDecimal.valueOf(8.50)));
		promotionalRules.add(new PercentOffPromotion(BigDecimal.valueOf(60), BigDecimal.valueOf(10)));
		//Assign the promotion list into the Checkout
		checkout = new Checkout(promotionalRules);
	}

	@ParameterizedTest
	@MethodSource("parameterisedTest")
	void testCheckout(List<Product> products, double expectedPrice) {
		//scan the products into the checkout which converts them into CheckoutItem
		products.forEach(product -> checkout.scan(product));
		Double price = checkout.total();
		assertEquals(expectedPrice, price);
	}

	private static Stream<Arguments> parameterisedTest() {
	    return Stream.of(
	      //One Travel Card Holder not fulfilling Multi-discount promotion but fulfilling PercentOffPromotion 
	  	  Arguments.of(Arrays.asList(MockData.TRAVEL_CARD_HOLDER,MockData.PERSONALISED_CUFFLINKS, 
	  	    		  MockData.KIDS_T_SHIRT), 66.78),
	      //Two Travel Card Holder fulfilling Multi-discount promotion
	      Arguments.of(Arrays.asList(MockData.TRAVEL_CARD_HOLDER,MockData.KIDS_T_SHIRT, 
	    		  MockData.TRAVEL_CARD_HOLDER), 36.95),
	    //Two Travel Card Holder fulfilling Multi-discount promotion and then fulfilling PercentOffPromotion 
	      Arguments.of(Arrays.asList(MockData.TRAVEL_CARD_HOLDER,  MockData.PERSONALISED_CUFFLINKS, 
	    		  MockData.TRAVEL_CARD_HOLDER, MockData.KIDS_T_SHIRT), 73.76),
	      
	    //Three Travel Card Holder fulfilling Multi-discount promotion and then fulfilling PercentOffPromotion
	      Arguments.of(Arrays.asList(MockData.TRAVEL_CARD_HOLDER,MockData.KIDS_T_SHIRT, 
	    		  MockData.TRAVEL_CARD_HOLDER, MockData.PERSONALISED_CUFFLINKS, MockData.TRAVEL_CARD_HOLDER), 81.41),
	    //No Travel Card Holder not fulfilling Multi-discount promotion but fulfilling PercentOffPromotion 
	      Arguments.of(Arrays.asList(MockData.PERSONALISED_CUFFLINKS, MockData.KIDS_T_SHIRT,
	    		  MockData.KIDS_T_SHIRT), 76.41),
	    
	      //empty list
	      Arguments.of(Collections.emptyList(), 0),
	      //null items in list
	      Arguments.of(Arrays.asList(null, null), 0)
	      );
	}
	
	@ParameterizedTest
	@MethodSource("parameterisedTestForCheckoutWithNoPromotions")
	void testCheckOutWithNoPromotions(List<Product> products, double expectedPrice) {
		checkout = new Checkout();
		products.forEach(product -> checkout.scan(product));
		Double price = checkout.total();
		assertEquals(expectedPrice, price);
	}
	
	@ParameterizedTest
	@MethodSource("parameterisedTestForCheckoutWithNoPromotions")
	void testCheckOutWithEmptyPromotionList(List<Product> products, double expectedPrice) {
		checkout = new Checkout(Collections.emptyList());
		products.forEach(product -> checkout.scan(product));
		Double price = checkout.total();
		assertEquals(expectedPrice, price);
	}
	
	private static Stream<Arguments> parameterisedTestForCheckoutWithNoPromotions() {
	    return Stream.of(
	      //One Travel Card Holder not fulfilling Multi-discount promotion but fulfilling PercentOffPromotion 
	  	  Arguments.of(Arrays.asList(MockData.TRAVEL_CARD_HOLDER,MockData.PERSONALISED_CUFFLINKS, 
	  	    		  MockData.KIDS_T_SHIRT), 74.20),
	      //Two Travel Card Holder fulfilling Multi-discount promotion
	      Arguments.of(Arrays.asList(MockData.TRAVEL_CARD_HOLDER,MockData.KIDS_T_SHIRT, 
	    		  MockData.TRAVEL_CARD_HOLDER), 38.45),
	    //Two Travel Card Holder fulfilling Multi-discount promotion and then fulfilling PercentOffPromotion 
	      Arguments.of(Arrays.asList(MockData.TRAVEL_CARD_HOLDER,  MockData.PERSONALISED_CUFFLINKS, 
	    		  MockData.TRAVEL_CARD_HOLDER, MockData.KIDS_T_SHIRT), 83.45),
	      //empty list
	      Arguments.of(Collections.emptyList(), 0),
	      //null items in list
	      Arguments.of(Arrays.asList(null, null), 0)
	      );
	}
	
}
