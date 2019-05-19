package cinema.service;

import cinema.data.*;
import cinema.exception.CinemaException;

import java.io.IOException;
import java.time.LocalDate;
import java.util.*;
import java.sql.*;

public class ClientService {
    protected final long clientId;

    private Connection conn;
    private GetterService getterService;

    public ClientService(Connection conn, long id) {
        this.conn = conn;
        this.getterService = new GetterService(this.conn);
        this.clientId = id;
    }

    public long getClientId() {
        return clientId;
    }

    public double getTotalSpent() throws IOException, SQLException  {
        double ret = 0;
        for (Purchase purchase : this.getterService.getAllFoodPurchase()) {
            if (purchase.getClientId() == clientId) {
                ret += purchase.getPrice(this.conn);
            }
        }

        for (Purchase purchase : this.getterService.getAllTicketPurchase()) {
            if (purchase.getClientId() == clientId) {
                ret += purchase.getPrice(this.conn);
            }
        }

        return ret;
    }

    public List<Movie> getWatchedMovies() throws IOException, SQLException  {
        Set<Screening> screenings = new TreeSet<>(this.getScreenings());

        Set<Long> movieIds = new TreeSet<>();
        for (Screening screening : screenings) {
            movieIds.add(screening.getMovieId());
        }

        for (Long id : movieIds) {
            cinema.test.Main.logger.severe("found movie id: " + id);
        }

        List<Movie> ans = new ArrayList<>();
        for (Movie movie : this.getterService.getAllMovie()) {
            if ( movieIds.contains(movie.getId()) ) {
                ans.add(movie);
            }
        }

        return ans;
    }

    public List<Screening> getScreenings() throws IOException, SQLException  {
        Set<Long> screeningIds = new TreeSet<>();

        for (TicketPurchase purchase : this.getterService.getAllTicketPurchase()) {
            if (purchase.getClientId() == this.clientId) {
                screeningIds.add(purchase.getScreeningId());
            }
        }

        List<Screening> ans = new ArrayList<>();
        for (Screening screening : this.getterService.getAllScreening()) {
            if (screeningIds.contains(screening.getId())) {
                ans.add(screening);
            }
        }

        return ans;
    }

    public List<Purchase> getPurchases() throws IOException, SQLException  {
        List<Purchase> ans = new ArrayList<>();

        for (Purchase purchase : this.getterService.getAllTicketPurchase()) {
            if (purchase.getClientId() == this.clientId) {
                ans.add(purchase);
            }
        }

        for (Purchase purchase : this.getterService.getAllFoodPurchase()) {
            if (purchase.getClientId() == this.clientId) {
                ans.add(purchase);
            }
        }

        return ans;
    }

    public boolean isOldEnoughForAt(long movieId, LocalDate date) throws CinemaException, IOException, SQLException {
        Movie movie = this.getterService.getMovie(movieId);
        AdminService.checkReference(movie);
        Client client = this.getterService.getClient(clientId);
        AdminService.checkReference(client);

        int maxMinimumAge = 0;
        List<Long> categoryIds = new ArrayList<>();
        for (AssociativeEntry entry : this.getterService.getAllMovieCategory()) {
            if (entry.getFirstId() == movieId) {
                categoryIds.add(entry.getSecondId());
            }
        }

        List<Category> categories = new ArrayList<>();
        for (Category category : this.getterService.getAllCategory()) {
            maxMinimumAge = Math.max(maxMinimumAge, category.getMinimumAge());
        }

        return client.getAgeAt(date) >= maxMinimumAge;
    }

    public boolean isOldEnoughFor(long movieId) throws CinemaException, IOException, SQLException {
        return isOldEnoughForAt(movieId, LocalDate.now());
    }
}
