package com.mcarter.pricebasket.items;

public class Apples implements Item {

	private final int cost;

	public Apples(int cost) {
		this.cost = cost;
	}

	public int getCost() {
		return cost;
	}

	@Override
	public String getName() {
		return "Apples";
	}
}
