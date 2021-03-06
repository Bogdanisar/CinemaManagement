package cinema.data;

import cinema.service.DatabaseConstants;

import java.util.ArrayList;
import java.util.List;

public final class Movie implements Identifiable {
    protected long id;
    protected String name;
    protected int durationInMinutes;

    public Movie(long id, String name, int durationInMinutes) {
        this.id = id;
        this.name = name;
        this.durationInMinutes = durationInMinutes;
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


    public int getDurationInMinutes() {
        return durationInMinutes;
    }

    public void setDurationInMinutes(int durationInMinutes) {
        this.durationInMinutes = durationInMinutes;
    }


    @Override
    public String toString() {
        String ret = "";
        ret += id + DatabaseConstants.SEPARATOR;
        ret += name + DatabaseConstants.SEPARATOR;
        ret += durationInMinutes;

        return ret;
    }
}
