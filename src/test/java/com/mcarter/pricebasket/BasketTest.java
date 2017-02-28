package com.mcarter.pricebasket;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import org.junit.Test;

import com.mcarter.pricebasket.discounts.Discount;
import com.mcarter.pricebasket.items.Item;
import com.mcarter.pricebasket.items.Soup;

public class BasketTest {

	@Test
	public void calculatesTotalCostOfItemsWithNoDiscountsApplied() {
		Basket basket = new Basket();
		basket.add("Apples");
		basket.add("Milk");
		basket.add("Bread");
		assertThat(basket.getTotal(), is(310));
	}

	@Test
	public void usesPerItemDiscountsInFinalCalculation() {
		final Discount appleDiscount = new Discount() {
			@Override
			public String getItem() {
				return "Apples";
			}

			@Override
			public int getDiscountPercent() {
				return 10;
			}
		};
		final Discount breadDiscount = new Discount() {
			@Override
			public String getItem() {
				return "Bread";
			}

			@Override
			public int getDiscountPercent() {
				return 25;
			}
		};
		Basket basket = new Basket(Arrays.asList(appleDiscount, breadDiscount));
		basket.add("Apples");
		basket.add("Apples");
		basket.add("Apples");
		basket.add("Milk");
		basket.add("Bread");
		assertThat(basket.getTotalWithDiscounts(), is(460));
	}

	@Test
	public void usesOneMultiBuyDiscountInFinalCalculation() {
		final Discount breadMultiDiscount = new Discount() {
			@Override
			public String getItem() {
				return "Bread";
			}

			@Override
			public int getDiscountPercent() {
				return 50;
			}

			@Override
			public int apply(Collection<Item> items) {
				long soupCount = items.stream().filter(item -> item instanceof Soup).count();
				return items.stream().filter(item -> item.getName().equals(getItem())).limit(soupCount / 2)
						.mapToInt(applyDiscount()).sum();
			}
		};
		Basket basket = new Basket(Collections.singletonList(breadMultiDiscount));
		basket.add("Bread");
		basket.add("Soup");
		basket.add("Soup");
		basket.add("Soup");
		basket.add("Bread");
		basket.add("Soup");
		basket.add("Soup");
		assertThat(basket.getTotalWithDiscounts(), is(405));
	}
}
