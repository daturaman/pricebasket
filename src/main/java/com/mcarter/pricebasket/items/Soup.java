/*
 * <copyright>
 *
 * Copyright (c) 2010-2017 Gresham Technologies plc. All rights reserved.
 *
 * </copyright>
 */
package com.mcarter.pricebasket.items;

/**
 * @author mcarter
 */
public class Soup implements Item {
	private final int cost;

	public Soup(int cost) {
		this.cost = cost;
	}

	@Override
	public int getCost() {
		return cost;
	}
}
