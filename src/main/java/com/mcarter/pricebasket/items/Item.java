package com.mcarter.pricebasket.items;

public abstract class Item {

    private boolean applied;

    public abstract int getCost();

    public abstract String getName();

    @Override
    public int hashCode() {
        return getName().hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return getName().equals(obj);
    }
}
