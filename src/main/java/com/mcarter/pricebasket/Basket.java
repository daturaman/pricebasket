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
	private ToIntFunction<Item> calculator;

	public Basket(ToIntFunction<Item> calculator) {
		this.calculator = calculator;
	}

	public void add(Item item) {
		items.add(item);
	}

	public int getTotal() {
		return items.stream().mapToInt(calculator).sum();
	}
}
