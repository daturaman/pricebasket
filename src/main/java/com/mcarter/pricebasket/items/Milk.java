/*
 * <copyright>
 *
 * Copyright (c) 2010-2017 Gresham Technologies plc. All rights reserved.
 *
 * </copyright>
 */
package com.mcarter.pricebasket.items;

/**
 * Represents one bottle of Milk.
 *
 * @author mcarter
 */
public class Milk extends Item {

	private int cost;

	public Milk(int cost) {
		this.cost = cost;
	}

	@Override
	public int getCost() {
		return cost;
	}

	@Override
	public String getName() {
		return "Milk";
	}
}
