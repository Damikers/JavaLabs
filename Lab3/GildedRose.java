package com.gildedrose;

class GildedRose {
    Item[] item;

    public GildedRose(Item[] item) {
        this.item = item;
    }

    public void updateQuality() {
        for (Item item : item) {
            if (item.name.equals("Aged Brie")
                    || item.name.equals("Backstage passes to a TAFKAL80ETC concert")) {
                if (item.name.equals("Sulfuras, Hand of Ragnaros")) {
                    if (item.quality > 0) {
                        item.decreaseQualityByOne();
                    }
                }
            } else if (item.quality < 50) {
                item.increaseQualityByOne();

                if (item.name.equals("Backstage passes to a TAFKAL80ETC concert")) {
                    if (item.sellIn < 11) {
                        item.increaseQualityByOne();
                    }

                    if (item.sellIn < 6) {
                        item.increaseQualityByOne();
                    }
                }
            }

            if (!item.name.equals("Sulfuras, Hand of Ragnaros")) {
                item.sellIn = item.sellIn - 1;
            }

            if (item.sellIn < 0) {
                if (item.name.equals("Aged Brie")) {
                    if (!item.name.equals("Backstage passes to a TAFKAL80ETC concert")) {           
                        if (!item.name.equals("Sulfuras, Hand of Ragnaros")) {
                            item.decreaseQualityByOne();
                        }
                    } else {
                        item.quality = 0;
                    }
                } else {
                    item.increaseQualityByOne();
                }
            }
        }
    }
}
