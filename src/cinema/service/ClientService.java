package cinema.service;

import cinema.data.*;
import cinema.exception.CinemaException;

import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

public class ClientService {
    protected final long clientId;

    public ClientService(long id) throws CinemaException {
        this.clientId = id;
    }

    public long getClientId() {
        return clientId;
    }

    public double getTotalSpent() throws IOException {
        double ret = 0;
        for (Purchase purchase : GetterService.getAllFoodPurchase()) {
            if (purchase.getClientId() == clientId) {
                ret += purchase.getPrice();
            }
        }

        for (Purchase purchase : GetterService.getAllTicketPurchase()) {
            if (purchase.getClientId() == clientId) {
                ret += purchase.getPrice();
            }
        }

        return ret;
    }

    public List<Food> getAllFoods() throws IOException {
        return GetterService.getAllFood();
    }

    public List<Long> getWatchedMovies() throws IOException {
        Set<Long> screeningIds = new TreeSet<>(this.getScreenings());

        Set<Long> movieIds = new TreeSet<>();
        List<Screening> allScreenings = GetterService.getAllScreening();
        for (Screening screening : allScreenings) {
            if (screeningIds.contains(screening.getId())) {
                movieIds.add(screening.getMovieId());
            }
        }

        return new ArrayList<Long>(movieIds);
    }

    public List<Long> getScreenings() throws IOException {
        Set<Long> screeningIds = new TreeSet<>();

        for (AssociativeEntry entry : GetterService.getAllScreeningClient()) {
            if (entry.getSecondId() == this.clientId) {
                screeningIds.add(entry.getFirstId());
            }
        }

        return new ArrayList<>(screeningIds);
    }

    public List<Long> getPurchases() throws IOException {
        List<Long> ret = new ArrayList<>();

        for (Purchase purchase : GetterService.getAllFoodPurchase()) {
            if (purchase.getClientId() == this.clientId) {
                ret.add(purchase.getId());
            }
        }

        for (Purchase purchase : GetterService.getAllTicketPurchase()) {
            if (purchase.getClientId() == this.clientId) {
                ret.add(purchase.getId());
            }
        }

        return ret;
    }

    public boolean isOldEnoughForAt(long movieId, LocalDate date) throws CinemaException, IOException {
        Movie movie = GetterService.getMovie(movieId);
        AdminService.checkReference(movie);
        Client client = GetterService.getClient(clientId);
        AdminService.checkReference(client);

        int maxMinimumAge = 0;
        List<Long> categoryIds = new ArrayList<>();
        for (AssociativeEntry entry : GetterService.getAllMovieCategory()) {
            if (entry.getFirstId() == movieId) {
                categoryIds.add(entry.getSecondId());
            }
        }

        List<Category> categories = new ArrayList<>();
        for (Category category : GetterService.getAllCategory()) {
            maxMinimumAge = Math.max(maxMinimumAge, category.getMinimumAge());
        }

        return client.getAgeAt(date) >= maxMinimumAge;
    }

    public boolean isOldEnoughFor(long movieId) throws CinemaException, IOException {
        return isOldEnoughForAt(movieId, LocalDate.now());
    }

    public List<Movie> getMoviesFromDay(int year, int month, int day) throws IOException {
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

    public List<Movie> getMoviesAfterDay(int year, int month, int day) throws IOException {
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

    public List<Screening> getScreeningsForMovieAfter(long movieId, int year, int month, int day) throws IOException {
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
