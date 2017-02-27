package com.mcarter.pricebasket;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.math.BigDecimal;

import org.junit.Test;

import com.mcarter.pricebasket.items.Apples;
import com.mcarter.pricebasket.items.Bread;
import com.mcarter.pricebasket.items.Item;
import com.mcarter.pricebasket.items.Milk;

public class PriceBasketTest {

	@Test
	public void basketCalculatesTotalCostOfItemsWithNoDiscountsApplied() {
		Basket basket = new Basket(Item::getCost);
		basket.add(new Apples(100));
		basket.add(new Milk(130));
		basket.add(new Bread(80));
		assertThat(basket.getTotal(), is(310));
	}
}
