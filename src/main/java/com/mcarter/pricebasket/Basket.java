package com.mcarter.pricebasket;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Objects;

import com.mcarter.pricebasket.discounts.Discount;
import com.mcarter.pricebasket.items.Item;
import com.mcarter.pricebasket.items.ItemFactory;

/**
 * A shopping basket for adding {@link Item}s to. It can calculate the total cost of all itemsMap,
 * including any discounts applied.
 * 
 * @author mcarter
 */
public class Basket {

	private Collection<Item> items = new ArrayList<>();
	private Collection<Discount> discounts = Collections.emptyList();

	/**
	 * Constructs a basket with discounts.
	 * 
	 * @param discounts the discounts to be applied to this basket.
	 */
	public Basket(Collection<Discount> discounts) {
		Objects.requireNonNull(discounts, "Discounts cannot be null");
		this.discounts = discounts;
	}

	/**
	 * Default constructor for baskets with no discounts.
	 */
	public Basket() {
	}

	/**
	 * Adds an {@link Item} matching the provided itemName to the basket.
	 * 
	 * @param itemName the name of a type of Item.
	 */
	public void add(String itemName) {
		Item item = ItemFactory.createItem(itemName);
		items.add(item);
	}

	/**
	 * Gets the total cost of items in this basket without any discounts applied.
	 * 
	 * @return the total cost without discounts.
	 */
	public int getTotal() {
		return items.stream().mapToInt(Item::getCost).sum();
	}

	/**
	 * Gets the total cost of items in this basket after discounts are applied.
	 * 
	 * @return the total cost, including discounts.
	 */
	public int getTotalWithDiscounts() {
		int total = getTotal();
		for (Discount discount : discounts) {
			total -= discount.apply(items);
		}
		return total;
	}
}
