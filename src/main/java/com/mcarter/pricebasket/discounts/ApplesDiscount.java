package com.mcarter.pricebasket.discounts;

/**
 * Applies a discount of 10% for every apple in a basket of items.
 */
public class ApplesDiscount extends Discount {
    @Override
    public String getItem() {
        return "Apples";
    }

    @Override
    public int getDiscountPercent() {
        return 10;
    }
}
