package com.gildedrose;

public class Item {

    public String name;

    public int sellIn;

    public int quality;

    public Item(String name, int sellIn, int quality) {
        this.name = name;
        this.sellIn = sellIn;
        this.quality = quality;
    }

   @Override
   public String toString() {
        return this.name + ", " + this.sellIn + ", " + this.quality;
    }

    public void increaseQualityByOne() {
        if (quality < 50) {  
            quality += 1;
        }
    }

    public void decreaseQualityByOne() {
        if (quality > 0) {
            quality -= 1;
        }
    }
}
