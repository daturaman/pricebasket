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
 * @author mcarter
 */
public interface Discount {

	String getItem();

	int getDiscountPercent();

	default String getDescription() {
		return String.format("%1$s %2$d% off: -%3$d p", getItem(), 10, 10);
	}

	default ToIntFunction<Item> getDiscount() {
		return item -> {
			int discount = 0;
			if (item.getName().equals(getItem())) {
				discount = (int)(item.getCost() * (getDiscountPercent()/100.0f));
			}
			return discount;
		};
	}

	default int apply(Collection<Item> items){
		return items.stream().filter(item -> item.getName().equals(getItem())).mapToInt(getDiscount()).sum();
	}
}
