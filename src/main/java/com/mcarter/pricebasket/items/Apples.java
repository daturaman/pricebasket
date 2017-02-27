package com.mcarter.pricebasket.items;

public class Apples implements Item {

	private int cost;

	public Apples(int cost) {
		this.cost = cost;
	}

	public int getCost() {
		return cost;
	}
}
