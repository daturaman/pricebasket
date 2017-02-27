/*
 * <copyright>
 *
 * Copyright (c) 2010-2017 Gresham Technologies plc. All rights reserved.
 *
 * </copyright>
 */
package com.mcarter.pricebasket;

import java.util.Map;
import java.util.function.ToIntFunction;

/**
 * @author mcarter
 */
public interface Discount {
	String getItem();

	ToIntFunction<Map.Entry<String, Integer>> getDiscount();
}
