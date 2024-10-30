package com.gildedrose;

class GildedRose {
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (Item item : items) {
            if (!items.name.equals("Aged Brie")
                    && !items.name.equals("Backstage passes to a TAFKAL80ETC concert")) {
                if (items].quality > 0) {
                    if (!items.name.equals("Sulfuras, Hand of Ragnaros")) {
                        items.quality = items.quality - 1;
                    }
                }
            } else {
                if (items.quality < 50) {
                    items.quality = items.quality + 1;

                    if (items.name.equals("Backstage passes to a TAFKAL80ETC concert")) {
                        if (items.sellIn < 11) {
                            if (items.quality < 50) {
                                items.quality = items.quality + 1;
                            }
                        }

                        if (items.sellIn < 6) {
                            if (items.quality < 50) {
                                items.quality = items.quality + 1;
                            }
                        }
                    }
                }
            }

            if (!items.name.equals("Sulfuras, Hand of Ragnaros")) {
                items.sellIn = items.sellIn - 1;
            }

            if (items.sellIn < 0) {
                if (!items.name.equals("Aged Brie")) {
                    if (!items.name.equals("Backstage passes to a TAFKAL80ETC concert")) {
                        if (items.quality > 0) {
                            if (!items.name.equals("Sulfuras, Hand of Ragnaros")) {
                                items.quality = items.quality - 1;
                            }
                        }
                    } else {
                        items.quality = 0;
                    }
                } else {
                    if (items.quality < 50) {
                        items.quality = items.quality + 1;
                    }
                }
            }
        }
    }
}
