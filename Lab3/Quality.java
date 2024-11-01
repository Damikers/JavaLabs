public class Quality {
    private int value;

    public Quality(int value) {
        this.value = value;
    }

    public void increaseQualityByOne() {
        if (value < 50) {
            value ++;
        }
    }

    public void decreaseQualityByOne() {
        if (value > 0) {
            value--;
        }
    }

    public void resetQuality() {
        value = 0;
    }

    public int qualityIs() {
        return value;
    }
}