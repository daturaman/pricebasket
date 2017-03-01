/*
 * <copyright>
 *
 * Copyright (c) 2010-2017 Gresham Technologies plc. All rights reserved.
 *
 * </copyright>
 */
package com.mcarter.pricebasket.discounts;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.function.ToIntFunction;

import com.mcarter.pricebasket.items.Item;

/**
 * Defines a discount that can be applied to {@link Item}s of a specified type.
 *
 * @author mcarter
 */
public abstract class Discount<T extends Item> {

	private final Collection<Item> discountedItems = new ArrayList<>();

    /**
	 * Gets the name of the Item being discounted.
	 *
	 * @return name of the item being discounted as a string.
	 */
	public abstract T getDiscountItem();

	/**
	 * Gets the discount as a percentage.
	 *
	 * @return the discount percentage as an integer.
	 */
	public abstract int getDiscountPercent();

	/**
	 * Applies this Discount to the all targets in a collection of items.
	 *
	 * @param items a collection that may contain the targeted item.
	 * @return the total amount that was discounted.
	 */
	public int apply(Collection<Item> items) {
		return items.stream().filter(item -> item.getName().equals(getDiscountItem().getName())).mapToInt(applyDiscount())
                    .sum();
	}

	/**
	 * Returns a list of all items that had a discount applied to them.
	 *
	 * @return a {@link Collection} of items that have all been discounted
	 */
	public Collection<Item> discountedItems() {
		return Collections.unmodifiableCollection(discountedItems);
	}

	/**
	 * Calculates the actual amount to be discounted.
	 *
	 * @return the amount to be discounted.
	 */
	public int getDiscountedAmount() {
		return (int) (getDiscountItem().getCost() * (getDiscountPercent() / 100.0f));
	}

	/**
	 * Provides an {@link ToIntFunction} that can be apply this Discount to a stream containing items of the target
	 * type.
	 *
	 * @return an {@link ToIntFunction} to be used in a stream operation.
	 */
	ToIntFunction<Item> applyDiscount() {
		return item -> {
			int discount = 0;
			if (item.getName().equals(getDiscountItem().getName())) {
				discount = getDiscountedAmount();
				discountedItems.add(item);
			}
			return discount;
		};
	}
}
