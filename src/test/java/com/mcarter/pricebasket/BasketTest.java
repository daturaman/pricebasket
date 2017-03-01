package com.mcarter.pricebasket;

import com.mcarter.pricebasket.discounts.ApplesDiscount;
import com.mcarter.pricebasket.discounts.BreadDiscount;
import com.mcarter.pricebasket.discounts.BreadMultiBuySoupDiscount;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class BasketTest {

    private static final String APPLES = "Apples";
    private static final String MILK = "Milk";
    private static final String BREAD = "Bread";
    private static final String SOUP = "Soup";

    @Test
    public void calculatesTotalCostOfItemsWithNoDiscountsApplied() {
        Basket basket = new Basket(Collections.emptyList());
        basket.add(APPLES);
        basket.add(MILK);
        basket.add(BREAD);
        assertThat(basket.getTotal(), is(310));
    }

    @Test
    public void usesPerItemDiscountsInFinalCalculation() {
        Basket basket = new Basket(Arrays.asList(new ApplesDiscount(), new BreadDiscount()));
        basket.add(APPLES);
        basket.add(APPLES);
        basket.add(APPLES);
        basket.add(MILK);
        basket.add(BREAD);
        assertThat(basket.getTotalWithDiscounts(), is(460));
    }

    @Test
    public void usesOneMultiBuyDiscountInFinalCalculation() {
        Basket basket = new Basket(Collections.singletonList(new BreadMultiBuySoupDiscount()));
        basket.add(BREAD);
        basket.add(SOUP);
        basket.add(SOUP);
        basket.add(SOUP);
        basket.add(BREAD);
        basket.add(SOUP);
        basket.add(SOUP);
        assertThat(basket.getTotalWithDiscounts(), is(405));
    }
}
