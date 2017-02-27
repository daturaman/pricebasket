package com.mcarter.pricebasket;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.util.Map.Entry;
import java.util.function.ToIntFunction;

import org.junit.Ignore;
import org.junit.Test;

import com.mcarter.pricebasket.items.Apples;
import com.mcarter.pricebasket.items.Bread;
import com.mcarter.pricebasket.items.Item;
import com.mcarter.pricebasket.items.ItemEnum;
import com.mcarter.pricebasket.items.Milk;
import com.mcarter.pricebasket.items.Soup;

public class PriceBasketTest {

	// if item instanceof BRead
	// and items contains 2 soups

	@Test
	public void calculatesTotalCostOfItemsWithNoDiscountsApplied() {
		Basket basket = new Basket();
		basket.add("Apples");
		basket.add("Milk");
		basket.add("Bread");
		assertThat(basket.getTotal(), is(310));
	}

	@Test
	public void usesPerItemDiscountInFinalCalculation() {
		Basket basket = new Basket();
		basket.add("Apples");
		// basket.add("Apples");
		// basket.add("Apples");
		basket.add("Milk");
		basket.add("Bread");
		assertThat(basket.getTotalWithDiscount(new Discount() {
			@Override
			public String getItem() {
				return "APPLES";
			}

			@Override
			public ToIntFunction<Entry<String, Integer>> getDiscount() {
				return entry -> {
					int cost = ItemEnum.valueOf(entry.getKey().toUpperCase()).cost;
					if (entry.getKey().equalsIgnoreCase("APPLES")) {
						cost -= 10;
					}
					return cost;
				};
			}
		}), is(300));
	}
	//
	// @Test
	// public void usesOneMultiBuyDiscountInFinalCalculation() {
	// Basket basket = new Basket();
	// basket.add(new Soup(65));
	// basket.add(new Soup(65));
	// basket.add(new Soup(65));
	// basket.add(new Bread(80));// Should cost £0.40 after multi buy discount
	// final Discount soupMultiBuy = items -> {
	// long breadVoucherCount = items.stream().filter(item -> item instanceof Soup).count();
	// int soupCount = 0;
	// int total = 0;
	// for (Item item : items) {
	// if (item instanceof Soup)
	// soupCount++;
	// if ((item instanceof Bread) && soupCount == 2) {
	// total += item.getCost() / 2;
	// soupCount = 0;
	// } else {
	// total += item.getCost();
	// }
	// }
	// return total;
	// };
	// assertThat(basket.getTotalWithDiscount(soupMultiBuy), is(235));
	// }
}
