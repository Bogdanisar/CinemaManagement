package cinema;

public final class Category {
    private final String name;
    private final int minimumAge;

    public Category(String name, int minimumAge) {
        this.name = name;
        this.minimumAge = minimumAge;
    }

    public String getName() {
        return name;
    }

    public int getMinimumAge() {
        return minimumAge;
    }
}
