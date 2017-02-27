package com.mcarter.pricebasket.items;

/**
 * @author Michael
 *
 */
public enum ItemEnum {
	SOUP(65, "Soup"), BREAD(80, "Bread"), MILK(130, "Milk"), APPLES(100, "Apples");

	/**
	 * Default constructor for Items.
	 * 
	 * @param cost The cost of one unit in pence.
	 * @param name TODO
	 */
	private ItemEnum(int cost, String name) {
		this.cost = cost;
		this.name = name;
	}

	public final int cost;
	private final String name;
}
