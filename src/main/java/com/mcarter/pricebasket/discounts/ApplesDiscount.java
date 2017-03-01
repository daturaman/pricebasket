package com.mcarter.pricebasket.discounts;

import com.mcarter.pricebasket.items.Apples;
import com.mcarter.pricebasket.items.ItemFactory;

/**
 * Applies a discount of 10% for every apple in a basket of items.
 */
public class ApplesDiscount extends Discount<Apples> {

    private final Apples apples = (Apples) ItemFactory.createItem("Apples");

    public ApplesDiscount() {
        super();
    }

    @Override
    public Apples getDiscountItem() {
        return apples;
    }

    @Override
    public int getDiscountPercent() {
        return 10;
    }
}
