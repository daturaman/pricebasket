package com.mcarter.pricebasket.discounts;

import com.mcarter.pricebasket.items.Bread;
import com.mcarter.pricebasket.items.ItemFactory;

/**
 * Applies a discount of 25% for every loaf of bread in a basket of items.
 */
public class BreadDiscount extends Discount<Bread> {

	private final Bread bread = (Bread) ItemFactory.createItem("Bread");

	public BreadDiscount() {
		super();
	}

	@Override
	public Bread getDiscountItem() {
		return bread;
	}

	@Override
	public int getDiscountPercent() {
		return 25;
	}
}
