package cinema.data;

import cinema.service.DatabaseConstants;

public final class Food implements Identifiable {
    protected long id;
    protected String name;
    protected double price;

    public Food(long id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        String ret = "";
        ret += id + DatabaseConstants.SEPARATOR;
        ret += name + DatabaseConstants.SEPARATOR;
        ret += price;

        return ret;
    }
}
