package com.mcarter.pricebasket;

import java.util.ArrayList;
import java.util.Collection;
import java.util.function.ToIntFunction;

import com.mcarter.pricebasket.items.Item;

/**
 * A shopping basket for adding {@link Item}s to. It can calculate the total cost of all items, including any discounts
 * applied.
 * 
 * @author mcarter
 */
public class Basket {

	private Collection<Item> items = new ArrayList<>();

	public void add(Item item) {
		items.add(item);
	}

	public int getTotal() {
		return items.stream().mapToInt(Item::getCost).sum();
	}

	public int getTotalWithDiscount(ToIntFunction<Item> discountFunction) {
		return items.stream().mapToInt(discountFunction).sum();
	}
}
