package com.mcarter.pricebasket;

import java.util.HashMap;
import java.util.Map;
import java.util.function.ToIntFunction;

import com.mcarter.pricebasket.items.Item;
import com.mcarter.pricebasket.items.ItemEnum;

/**
 * A shopping basket for adding {@link Item}s to. It can calculate the total cost of all items,
 * including any discounts applied.
 * 
 * @author mcarter
 */
public class Basket {

	private Map<String, Integer> items = new HashMap<>();// TODO Map<String, Collection<Item>

	public void add(String item) {
		// TODO Check valid item
		if (!items.containsKey(item)) {
			items.put(item, Integer.valueOf(1));
		} else {
			int itemCount = items.get(item);
			items.put(item, Integer.valueOf(++itemCount));
		}
	}

	public int getTotal() {
		return items.entrySet().stream()
				.mapToInt(entry -> ItemEnum.valueOf(entry.getKey().toUpperCase()).cost * entry.getValue()).sum();
	}

	public int getTotalWithDiscount(ToIntFunction<Item> discountFunction) {
		return 0;
	}

	public int getTotalWithDiscount(Discount discount) {
		int undiscountedItemsTotal = items.entrySet().stream()
				.filter(entry -> !entry.getKey().equalsIgnoreCase(discount.getItem()))
				.mapToInt(entry -> ItemEnum.valueOf(entry.getKey().toUpperCase()).cost * entry.getValue()).sum();
		int discountedItemsTotal = items.entrySet().stream()
				.filter(entry -> entry.getKey().equalsIgnoreCase(discount.getItem())).mapToInt(discount.getDiscount())
				.sum();
		return undiscountedItemsTotal + discountedItemsTotal;
	}
}
