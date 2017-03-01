package com.mcarter.pricebasket.discounts;

/**
 * Applies a discount of 25% for every loaf of bread in a basket of items.
 */
public class BreadDiscount extends Discount {
    @Override
    public String getItem() {
        return "Bread";
    }

    @Override
    public int getDiscountPercent() {
        return 25;
    }
}
