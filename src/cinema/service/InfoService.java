package cinema.service;

import cinema.data.Food;
import cinema.data.Movie;
import cinema.data.Screening;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class InfoService {
    public static List<Food> getAllFoods() throws IOException {
        return GetterService.getAllFood();
    }

    public static List<Movie> getMoviesFromDay(int year, int month, int day) throws IOException {
        LocalDate date = LocalDate.of(year, month, day);

        Set<Long> movieIds = new TreeSet<>();
        for (Screening screening : GetterService.getAllScreening()) {
            if (screening.getStartTime().equals(date)) {
                movieIds.add(screening.getMovieId());
            }
        }

        List<Movie> ans = new ArrayList<>();
        for (Movie movie : GetterService.getAllMovie()) {
            if ( movieIds.contains(movie.getId()) ) {
                ans.add(movie);
            }
        }

        return ans;
    }

    public static List<Movie> getMoviesAfterDay(int year, int month, int day) throws IOException {
        LocalDate date = LocalDate.of(year, month, day);

        Set<Long> movieIds = new TreeSet<>();
        for (Screening screening : GetterService.getAllScreening()) {
            if (screening.getStartTime().compareTo(date) >= 0) {
                movieIds.add(screening.getMovieId());
            }
        }

        List<Movie> ans = new ArrayList<>();
        for (Movie movie : GetterService.getAllMovie()) {
            if ( movieIds.contains(movie.getId()) ) {
                ans.add(movie);
            }
        }

        return ans;
    }

    public  static List<Screening> getScreeningsForMovieAfter(long movieId, int year, int month, int day) throws IOException {
        LocalDate date = LocalDate.of(year, month, day);

        List<Screening> ans = new ArrayList<>();
        for (Screening screening : GetterService.getAllScreening()) {
            if (screening.getMovieId() == movieId) {
                ans.add(screening);
            }
        }

        return ans;
    }
}
