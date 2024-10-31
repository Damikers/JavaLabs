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
        if (item.name.equals("Aged Brie")) {
            item.increaseQualityByOne();
        } else if (item.name.equals("Backstage passes to a TAFKAL80ETC concert")) {
            updateQualityForBackstage(item);
        } else if (item.name.equals("Sulfuras, Hand of Ragnaros")) {
            return;
        } else if (item.quality > 0) {
            item.quality --;
        }
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
        if (item.name.equals("Sulfuras, Hand of Ragnaros")) {
            return;
        }
        item.sellIn --;
    }

    private boolean isSellIn(Item item) {
        return item.sellIn < 0;
    }

    private void processSellIn(Item item) {
        if (!item.name.equals("Aged Brie")) {
            if (!item.name.equals("Backstage passes to a TAFKAL80ETC concert")) {
                if (item.quality > 0) {
                    if (!item.name.equals("Sulfuras, Hand of Ragnaros")) {
                        item.quality --;
                    }
                }
            } else {
                item.quality = 0;
            }
        } else {
            item.increaseQualityByOne();
        }

    }

}
