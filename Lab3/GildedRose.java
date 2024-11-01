// package com.gildedrose;

class GildedRose {
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateInventory() {
        for (Item item : items) {
            updateItem(item);
        }
    }

    private void updateItem(Item item) {
        updateQuality(item);
        updateSellIn(item);
        if (isSellIn(item)) {
            processSellIn(item);
        }
    }

    private void updateQuality(Item item) {
        if (isBrie(item)) {
            item.increaseQualityByOne();
        } else if (isBackstage(item)) {
            updateQualityForBackstage(item);
        } else if (isSulfuras(item)) {
            return;
        }
        item.decreaseQualityByOne();
    }

    private void updateQualityForBackstage(Item item) {
        item.increaseQualityByOne();

        if (item.sellIn < 11) {
            item.increaseQualityByOne();
        }

        if (item.sellIn < 6) {
            item.increaseQualityByOne();
        }
    }

    private void updateSellIn(Item item) {
        if (isSulfuras(item)) {
            return;
        }
        item.sellIn--;
    }

    private boolean isSellIn(Item item) {
        return item.sellIn < 0;
    }

    private void processSellIn(Item item) {
        if (isBrie(item)) {
            item.increaseQualityByOne();
        } else if (isBackstage(item)) {
            item.quality = 0;
        } else if (!isSulfuras(item)) {
            item.decreaseQualityByOne();
        }
    }

    private boolean isSulfuras(Item item) {
        return item.name.equals("Sulfuras, Hand of Ragnaros");
    }

    private boolean isBackstage(Item item) {
        return item.name.equals("Backstage passes to a TAFKAL80ETC concert");
    }

    private boolean isBrie(Item item) {
        return item.name.equals("Aged Brie");
    }
}
