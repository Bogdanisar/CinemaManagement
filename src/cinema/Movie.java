package cinema;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public final class Movie implements Comparable<Movie> {
    private final String name;
    private final int durationInMinutes;
    private final List<Category> categories;

    public static class Builder {
        String name;
        int durationInMinutes;
        List<Category> categories;

        public Builder(String name, int durationInMinutes) {
            this.name = name;
            this.durationInMinutes = durationInMinutes;
            categories = new ArrayList<>();
        }

        public Builder withCategory(Category category) {
            categories.add(category);
            return this;
        }

        public Movie build() {
            Movie ret = new Movie(name, durationInMinutes, categories);
            return ret;
        }
    }

    private Movie(String name, int durationInMinutes, List<Category> categories) {
        this.name = name;
        this.durationInMinutes = durationInMinutes;
        this.categories = categories;
    }

    public String getName() {
        return name;
    }

    public int getDurationInMinutes() {
        return durationInMinutes;
    }

    public List<Category> getCategories() {
        return new ArrayList<Category>(categories);
    }

    @Override
    public int compareTo(Movie other) {
        return this.name.compareTo(other.name);
    }
}
