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

    public List<Movie> getWatchedMovies() throws IOException {
        Set<Screening> screenings = new TreeSet<>(this.getScreenings());

        Set<Long> movieIds = new TreeSet<>();
        for (Screening screening : screenings) {
            movieIds.add(screening.getMovieId());
        }

//        Main.logger.debug(movieIds);

        List<Movie> ans = new ArrayList<>();
        for (Movie movie : GetterService.getAllMovie()) {
            if ( movieIds.contains(movie.getId()) ) {
                ans.add(movie);
            }
        }

        return ans;
    }

    public List<Screening> getScreenings() throws IOException {
        Set<Long> screeningIds = new TreeSet<>();

        for (AssociativeEntry entry : GetterService.getAllScreeningClient()) {
            if (entry.getSecondId() == this.clientId) {
                screeningIds.add(entry.getFirstId());
            }
        }

        List<Screening> ans = new ArrayList<>();
        for (Screening screening : GetterService.getAllScreening()) {
            if (screeningIds.contains(screening.getId())) {
                ans.add(screening);
            }
        }

        return ans;
    }

    public List<Purchase> getPurchases() throws IOException {
        List<Purchase> ans = new ArrayList<>();

        for (Purchase purchase : GetterService.getAllTicketPurchase()) {
            if (purchase.getClientId() == this.clientId) {
                ans.add(purchase);
            }
        }

        for (Purchase purchase : GetterService.getAllFoodPurchase()) {
            if (purchase.getClientId() == this.clientId) {
                ans.add(purchase);
            }
        }

        return ans;
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
}
