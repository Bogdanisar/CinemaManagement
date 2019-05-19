package cinema.service;

import cinema.data.Food;
import cinema.data.Movie;
import cinema.data.Screening;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class InfoService {
    private Connection conn;
    private GetterService getterService;

    public InfoService(Connection conn) {
        this.conn = conn;
        this.getterService = new GetterService(this.conn);
    }

    public List<Food> getAllFoods() throws IOException, SQLException {
        return this.getterService.getAllFood();
    }

    public List<Movie> getMoviesFromDay(int year, int month, int day) throws IOException, SQLException {
        LocalDate date = LocalDate.of(year, month, day);

        Set<Long> movieIds = new TreeSet<>();
        for (Screening screening : this.getterService.getAllScreening()) {
            if (screening.getStartTime().equals(date)) {
                movieIds.add(screening.getMovieId());
            }
        }

        List<Movie> ans = new ArrayList<>();
        for (Movie movie : this.getterService.getAllMovie()) {
            if ( movieIds.contains(movie.getId()) ) {
                ans.add(movie);
            }
        }

        return ans;
    }

    public List<Movie> getMoviesAfterDay(int year, int month, int day) throws IOException, SQLException {
        LocalDate date = LocalDate.of(year, month, day);

        Set<Long> movieIds = new TreeSet<>();
        for (Screening screening : this.getterService.getAllScreening()) {
            if (screening.getStartTime().compareTo(date) >= 0) {
                movieIds.add(screening.getMovieId());
            }
        }

        List<Movie> ans = new ArrayList<>();
        for (Movie movie : this.getterService.getAllMovie()) {
            if ( movieIds.contains(movie.getId()) ) {
                ans.add(movie);
            }
        }

        return ans;
    }

    public List<Screening> getScreeningsForMovieAfter(long movieId, int year, int month, int day) throws IOException, SQLException {
        LocalDate date = LocalDate.of(year, month, day);

        List<Screening> ans = new ArrayList<>();
        for (Screening screening : this.getterService.getAllScreening()) {
            if (screening.getMovieId() == movieId) {
                ans.add(screening);
            }
        }

        return ans;
    }
}
