/*
 * <copyright>
 *
 * Copyright (c) 2010-2017 Gresham Technologies plc. All rights reserved.
 *
 * </copyright>
 */
package com.mcarter.pricebasket.items;

/**
 * Represents one loaf of bread.
 *
 * @author mcarter
 */
public class Bread implements Item {

	private final int cost;

	public Bread(int cost) {
		this.cost = cost;
	}

	@Override
	public int getCost() {
		return cost;
	}

	@Override
	public String getName() {
		return "Bread";
	}
}
