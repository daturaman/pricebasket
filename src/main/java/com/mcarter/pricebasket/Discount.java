/*
 * <copyright>
 *
 * Copyright (c) 2010-2017 Gresham Technologies plc. All rights reserved.
 *
 * </copyright>
 */
package com.mcarter.pricebasket;

import java.util.Collection;

import com.mcarter.pricebasket.items.Item;

/**
 * @author mcarter
 */
@FunctionalInterface
public interface Discount {
	int apply(Collection<Item> items);
}
