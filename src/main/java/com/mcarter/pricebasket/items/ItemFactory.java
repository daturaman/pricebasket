/*
 * <copyright>
 *
 * Copyright (c) 2010-2017 Gresham Technologies plc. All rights reserved.
 *
 * </copyright>
 */
package com.mcarter.pricebasket.items;

import java.util.Objects;

/**
 * Factory for creating {@link Item}s from a string representation.
 * 
 * @author mcarter
 */
public class ItemFactory {

	public static Item createItem(String name) {
		Objects.requireNonNull(name, "Name cannot be null");
		Item item;
		switch (name) {
		case "Apples":
			item = new Apples(100);
			break;
		case "Soup":
			item = new Soup(65);
			break;
		case "Bread":
			item = new Bread(80);
			break;
		case "Milk":
			item = new Milk(130);
			break;
		default:
			throw new IllegalArgumentException("Unknown item passed to factory");
		}
		return item;
	}
}
