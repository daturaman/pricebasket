/*
 * <copyright>
 *
 * Copyright (c) 2010-2017 Gresham Technologies plc. All rights reserved.
 *
 * </copyright>
 */
package com.mcarter.pricebasket.discounts;

import java.util.Collection;
import java.util.function.ToIntFunction;

import com.mcarter.pricebasket.items.Item;

/**
 * Defines a discount that can be applied to {@link Item}s of a specified type.
 * 
 * @author mcarter
 */
// TODO convert to abstract
public interface Discount {

	/**
	 * Gets the name of the Item being discounted.
	 * 
	 * @return name of the item being discounted as a string.
	 */
	String getItem();

	/**
	 * Gets the discount as a percentage.
	 * 
	 * @return the discount percentage as an integer.
	 */
	int getDiscountPercent();

	/**
	 * Provides a printable description of this Discount.
	 * 
	 * @return a description of the Discount.
	 */
	default String getDescription() {
		return String.format("%1$s %2$d% off: -%3$d p", getItem(), getDiscountPercent(), 10);
	}

	/**
	 * Provides an {@link ToIntFunction} that can be apply this Discount to a stream containing
	 * items of the target type.
	 * 
	 * @return an {@link ToIntFunction} to be used in a stream operation.
	 */
	default ToIntFunction<Item> applyDiscount() {
		return item -> {
			int discount = 0;
			if (item.getName().equals(getItem())) {
				discount = calculatePercentage(item.getCost());
			}
			return discount;
		};
	}

	/**
	 * Calculates the actual amount to be discounted.
	 * 
	 * @param cost the cost of the item.
	 * @return the amount to be discounted.
	 */
	// TODO make this private in abstract class
	default int calculatePercentage(int cost) {
		return (int) (cost * (getDiscountPercent() / 100.0f));
	}

	/**
	 * Applies this Discount to the all targets in a collection of items.
	 * 
	 * @param items a collection that may contain the targeted item.
	 * @return the total amount that was discounted.
	 */
	default int apply(Collection<Item> items) {
		return items.stream().filter(item -> item.getName().equals(getItem())).mapToInt(applyDiscount()).sum();
	}
}
