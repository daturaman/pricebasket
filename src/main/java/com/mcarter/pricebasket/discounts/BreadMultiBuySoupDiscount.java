package com.mcarter.pricebasket.discounts;

import com.mcarter.pricebasket.items.Item;
import com.mcarter.pricebasket.items.Soup;

import java.util.Collection;
import java.util.stream.IntStream;

/**
 * Applies a discount of 50% to a loaf of bread for every 2 soups in a basket of items.
 */
public class BreadMultiBuySoupDiscount extends Discount {
    @Override
    public String getItem() {
        return "Bread";
    }

    @Override
    public int getDiscountPercent() {
        return 50;
    }

    @Override
    public IntStream apply(Collection<Item> items) {
        long soupCount = items.stream().filter(item -> item instanceof Soup).count();
        return items.stream().filter(item -> item.getName().equals(getItem())).limit(soupCount / 2)
                .mapToInt(applyDiscount());
    }
}
