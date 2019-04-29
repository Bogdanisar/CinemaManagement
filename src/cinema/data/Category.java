package cinema.data;

public final class Category implements Identifiable {
    protected long id;
    protected String name;
    protected int minimumAge;

    public Category(long id, String name, int minimumAge) {
        this.id = id;
        this.name = name;
        this.minimumAge = minimumAge;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public int getMinimumAge() {
        return minimumAge;
    }

    public void setMinimumAge(int minimumAge) {
        this.minimumAge = minimumAge;
    }
}
