public class SellIn {
    private int value;

    public SellIn(int value) {
        this.value = value;
    }

    public boolean isSellIn() {
        return value < 0;
    }

    public void decreaseSellInByOne() {
        value--;
    }

    public int sellInIs() {
        return value;
    }
}
