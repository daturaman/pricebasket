package com.mcarter.pricebasket;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import com.mcarter.pricebasket.discounts.Discount;
import com.mcarter.pricebasket.items.Item;
import com.mcarter.pricebasket.items.ItemFactory;

/**
 * A shopping basket for adding {@link Item}s to. It can calculate the total cost of all itemsMap,
 * including any discounts applied.
 * 
 * @author mcarter
 */
public class Basket {

	private Collection<Item> items = new ArrayList<>();
	private Collection<Discount> discounts = Collections.emptyList();

	public Basket(Collection<Discount> discounts) {
		this.discounts = discounts;
	}

	public Basket() {

	}

	public void add(String itemName) {
		Item item = ItemFactory.createItem(itemName);
		items.add(item);
	}

	public int getTotal() {
		return items.stream().mapToInt(Item::getCost).sum();
	}

	public int getTotalWithDiscounts(){
		int total = getTotal();
		for(Discount discount : discounts){
			total -= discount.apply(items);
		}
		return total;
	}
}
