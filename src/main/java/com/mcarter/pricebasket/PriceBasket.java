/*
 * <copyright>
 *
 * Copyright (c) 2010-2017 Gresham Technologies plc. All rights reserved.
 *
 * </copyright>
 */
package com.mcarter.pricebasket;

/**
 * @author mcarter
 */
public class PriceBasket {
	public static void main(String [] args){
		if(!args[0].equals("PriceBasket"))throw new IllegalArgumentException("Args must start with 'PriceBasket'");
	}
}
