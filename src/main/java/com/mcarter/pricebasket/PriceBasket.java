/*
 * <copyright>
 *
 * Copyright (c) 2010-2017 Gresham Technologies plc. All rights reserved.
 *
 * </copyright>
 */
package com.mcarter.pricebasket;

import java.text.NumberFormat;
import java.util.Arrays;
import java.util.Collection;
import java.util.Locale;
import java.util.stream.Collectors;

import com.mcarter.pricebasket.discounts.ApplesDiscount;
import com.mcarter.pricebasket.discounts.BreadMultiBuySoupDiscount;
import com.mcarter.pricebasket.discounts.Discount;

/**
 * Adds items to a basket from command line arguments and prints a summary.
 * 
 * @author mcarter
 */
class PriceBasket {

	private static final int MIN_ARGS = 2;
    private static final NumberFormat NF = NumberFormat.getCurrencyInstance(Locale.UK);
	private static final double PERCENT = 100.0;
	static Basket basket  = new Basket(Arrays.asList(new ApplesDiscount(), new BreadMultiBuySoupDiscount()));

	public static void main(String[] args) {
		if (args.length < MIN_ARGS) {
			System.err.println("Please enter 'PriceBasket', followed by at least one item to be added to the basket.");
		}
		if (!args[0].equals("PriceBasket")) {
			throw new IllegalArgumentException("Args must start with 'PriceBasket'");
		}
		for (int i = 1; i < args.length; i++) {
			try {
				basket.add(args[i]);
			} catch (IllegalArgumentException e) {
				System.err.printf("Error: unknown item '%s' added to basket.\n", args[i]);
				return;
			}
		}

		System.out.printf("Subtotal: %s\n", NF.format(basket.getTotal()/ PERCENT));
		int totalWithDiscounts = basket.getTotalWithDiscounts();
		Collection<Discount> appliedDiscounts = basket.getDiscounts().stream()
                                                      .filter(discount -> !discount.discountedItems().isEmpty()).collect(Collectors.toList());
		if (appliedDiscounts.isEmpty()) {
			System.out.println("(No offers available)");
		} else {
		    for(Discount discount : appliedDiscounts){
				for(Object item : discount.discountedItems()){
					System.out.printf("%s %s%% off: -%sp\n", discount.getDiscountItem().getName(), discount.getDiscountPercent(), discount.getDiscountedAmount());
				}
			}
		}
		System.out.printf("Total: %s\n", NF.format(totalWithDiscounts/ PERCENT));
	}
}
