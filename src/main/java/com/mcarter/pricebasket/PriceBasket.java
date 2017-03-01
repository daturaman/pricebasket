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

    private static final int MIN_ARGS = 2;
    public static Basket basket;

    public static void main(String[] args) {
        if(args.length < MIN_ARGS){
            System.err.println("Please enter 'PriceBasket', followed by at least one item to be added to the basket.");
        }
        if (!args[0].equals("PriceBasket")) {
            throw new IllegalArgumentException("Args must start with 'PriceBasket'");
        }
        // Subtotal: £3.10
        // Apples 10% off: -10p
        // Total: £3.00
        for(int i = 1; i < args.length; i++){
            basket.add(args[i]);
        }
        System.out.printf("Subtotal: £ %d", basket.getTotal());

    }
}
