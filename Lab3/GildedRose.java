package com.gildedrose;

class GildedRose {
    private final Item[] items;

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
        if (item.sellInValueLessThan(0)) {
            processSellIn(item);
        }
    }

    private void updateQuality(Item item) {
        if (isBrie(item)) {
            item.increaseQuality();
        } else if (isBackstage(item)) {
            updateQualityForBackstage(item);
        } else if (isSulfuras(item)) {
            return;
        }
        item.decreaseQuality();
    }

    private void updateQualityForBackstage(Item item) {
        item.increaseQuality();

        if (item.sellInValueLessThan(11)) {
            item.increaseQuality();
        }

        if (item.sellInValueLessThan(6)) {
            item.increaseQuality();
        }
    }

    private void updateSellIn(Item item) {
        if (isSulfuras(item)) {
            return;
        }
        item.decreaseSellIn();
    }

    private void processSellIn(Item item) {
        if (isBrie(item)) {
            item.increaseQuality();
        } else if (isBackstage(item)) {
            item.resetQuality();
        } else if (!isSulfuras(item)) {
            item.decreaseQuality();
        }
    }

    private boolean isSulfuras(Item item) {
        return item.isNamed("Sulfuras, Hand of Ragnaros");
    }

    private boolean isBackstage(Item item) {
        return item.isNamed("Backstage passes to a TAFKAL80ETC concert");
    }

    private boolean isBrie(Item item) {
        return item.isNamed("Aged Brie");
    }
}
