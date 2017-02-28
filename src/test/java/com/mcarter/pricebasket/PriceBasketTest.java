package com.mcarter.pricebasket;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

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
		PriceBasket.main(new String[] { "PriceBasket", "Apples", "Milk", "Bread" });
		final String expectedOutput = "Subtotal: £3.10\n(No offers available)\nTotal: £3.10";
		assertEquals(expectedOutput, outContent.toString());
	}

}
