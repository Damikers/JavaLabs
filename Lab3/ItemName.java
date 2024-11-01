public class ItemName {
    private String value;

    public ItemName(String value) {
        this.value = value;
    }

    public boolean nameIs(String name) {
        return value.equals(name);
    }

    public String nameIs() {
        return value;
    }
}
