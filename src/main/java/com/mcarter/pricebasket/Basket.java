package com.mcarter.pricebasket;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Objects;

import com.mcarter.pricebasket.discounts.Discount;
import com.mcarter.pricebasket.items.Item;
import com.mcarter.pricebasket.items.ItemFactory;

/**
 * A shopping basket for adding {@link Item}s to. It can calculate the total cost of all itemsMap, including any
 * discounts applied.
 *
 * @author mcarter
 */
class Basket {

	private final Collection<Item> items = new ArrayList<>();
	private final Collection<Discount> discounts;

	/**
	 * Constructs a basket with discounts.
	 *
	 * @param discounts the discounts to be applied to this basket.
	 */
	Basket(Collection<Discount> discounts) {
		Objects.requireNonNull(discounts, "Discounts cannot be null");
		this.discounts = discounts;
	}

	/**
	 * Adds an {@link Item} matching the provided itemName to the basket.
	 *
	 * @param itemName the name of a type of Item.
	 */
	void add(String itemName) {
		Item item = ItemFactory.createItem(itemName);
		items.add(item);
	}

	/**
	 * Gets the total cost of items in this basket without any discounts applied.
	 *
	 * @return the total cost without discounts.
	 */
	int getTotal() {
		return items.stream().mapToInt(Item::getCost).sum();
	}

	/**
	 * Gets the total cost of items in this basket after discounts are applied.
	 *
	 * @return the total cost, including discounts.
	 */
	int getTotalWithDiscounts() {
		int total = getTotal();
		for (Discount discount : discounts) {
			total -= discount.apply(items);
		}
		return total;
	}

	/**
	 * Gets all the {@link Discount}s for this basket.
	 *
	 * @return a collection of {@link Discount}s
	 */
	Collection<Discount> getDiscounts() {
		return Collections.unmodifiableCollection(discounts);
	}
}
