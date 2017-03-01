package com.mcarter.pricebasket;

import static java.util.Collections.singletonList;
import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Collections;

import com.mcarter.pricebasket.discounts.ApplesDiscount;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class PriceBasketTest {

	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();

	@Before
	public void setUpStreams() {
		System.setOut(new PrintStream(outContent));
		System.setErr(new PrintStream(errContent));
	}

	@After
	public void cleanUpStreams() {
		System.setOut(null);
		System.setErr(null);
	}

	@Test
	public void printsExpectedMessageForBasketWithoutDiscounts() {
		PriceBasket.basket = new Basket(singletonList(new ApplesDiscount()));
		PriceBasket.main(new String[] { "PriceBasket", "Milk", "Milk", "Bread" });
		final String expectedOutput = "Subtotal: £3.40\n(No offers available)\nTotal: £3.40";
		assertEquals(expectedOutput, outContent.toString());
	}

}
