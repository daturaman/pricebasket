package com.mcarter.pricebasket.discounts;

import com.mcarter.pricebasket.items.Bread;
import com.mcarter.pricebasket.items.Item;
import com.mcarter.pricebasket.items.ItemFactory;
import com.mcarter.pricebasket.items.Soup;

import java.util.Collection;

/**
 * Applies a discount of 50% to a loaf of bread for every 2 soups in a basket of items.
 */
public class BreadMultiBuySoupDiscount extends Discount<Bread> {

	private final Bread bread = (Bread) ItemFactory.createItem("Bread");

	public BreadMultiBuySoupDiscount() {
		super();
	}

	@Override
	public Bread getDiscountItem() {
		return bread;
	}

	@Override
	public int getDiscountPercent() {
		return 50;
	}

	@Override
	public int apply(Collection<Item> items) {
		long soupCount = items.stream().filter(item -> item instanceof Soup).count();
		return items.stream().filter(item -> item.getName().equals(getDiscountItem().getName())).limit(soupCount / 2)
                    .mapToInt(applyDiscount()).sum();
	}
}
