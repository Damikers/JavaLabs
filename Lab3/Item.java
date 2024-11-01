package com.gildedrose;

public class Item {

    private final ItemName name;
    private final SellIn sellIn;
    private final Quality quality;

    public Item(ItemName name, SellIn sellIn, Quality quality) {
        this.name = name;
        this.sellIn = sellIn;
        this.quality = quality;
    }

    @Override
    public String toString() {
        return name.nameIs() + ", " + sellIn.sellInIs() + ", " + quality.qualityIs();
    }

    public void decreaseQuality() {
        quality.decreaseQualityByOne();
    }

    public void increaseQuality() {
        quality.increaseQualityByOne();
    }

    public void resetQuality() {
        quality.resetQuality();
    }

    public void decreaseSellIn() {
        sellIn.decreaseSellInByOne();
    }

    public boolean isSellIn() {
        return sellIn.isSellIn();
    }

    public boolean sellInValueLessThan(int value) {
        return sellIn.sellInIs() < value;
    }

    public boolean isNamed(String itemName) {
        return name.nameIs(itemName);
    }
}
