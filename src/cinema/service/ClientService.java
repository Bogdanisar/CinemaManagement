package cinema.service;

import cinema.data.*;
import cinema.exception.CinemaException;

import java.time.LocalDate;
import java.util.*;

public class ClientService {
//    protected final Long clientId;
//    protected final Client client;
//    private final AdminService admin;
//
//    public ClientService(Long id) throws CinemaException {
//        this.clientId = id;
//        admin = new AdminService("admin", "123");
//        client = admin.getObjectFromId(clientId, admin.getClientMap());
//    }
//
//    public Long getClientId() {
//        return clientId;
//    }
//
//    public Client getClient() {
//        return client;
//    }
//
//    public double getTotalSpent() {
//        double ret = 0;
//        for (Purchase purchase : client.purchaseList) {
//            ret += purchase.getPrice();
//        }
//
//        return ret;
//    }
//
//    public List<Food> getAllFoods() {
//        List<Food> ret = new ArrayList<>();
//
//        for (Map.Entry<String, Food> foodEntry : admin.getFoodMap().entrySet()) {
//            if (foodEntry.getKey() == null) {
//                continue;
//            }
//
//            ret.add(foodEntry.getValue());
//        }
//
//        return ret;
//    }
//
//    public Map<Movie, Integer> getWatchedMovies() {
//        Map<Movie, Integer> ret = new TreeMap<>();
//
//        for (Auditorium auditorium : admin.getAuditoriumMap().values()) {
//            for (Screening screening : auditorium.screeningTreeSet) {
//                if (screening.clients.contains(client)) {
//                    ret.put(screening.movie, ret.getOrDefault(screening.movie, 0) + 1);
//                }
//            }
//        }
//
//        return ret;
//    }
//
//    public List<Screening> getScreenings() {
//        Set<Screening> ret = new TreeSet<>(new ScreeningComparatorById());
//
//        for (Purchase purchase : client.purchaseList) {
//            if (purchase instanceof TicketPurchase) {
//                TicketPurchase ticket = (TicketPurchase)purchase;
//                ret.add(ticket.screening);
//            }
//        }
//
//        return new ArrayList<>(ret);
//    }
//
//    public List<Purchase> getPurchases() {
//        return new ArrayList<Purchase>(client.purchaseList);
//    }
//
//    public boolean isOldEnoughForAt(String movieName, LocalDate date) throws CinemaException {
//        Movie movie = admin.getObjectFromId(movieName, admin.getMovieMap());
//
//        int maxMinimumAge = 0;
//        for (Category category : movie.getCategories()) {
//            maxMinimumAge = Math.max(maxMinimumAge, category.getMinimumAge());
//        }
//
//        return client.getAgeAt(date) >= maxMinimumAge;
//    }
//
//    public boolean isOldEnoughFor(String movieName) throws CinemaException {
//        return isOldEnoughForAt(movieName, LocalDate.now());
//    }
//
//    public List<Movie> getMoviesFromDay(int year, int month, int day) {
//        LocalDate date = LocalDate.of(year, month, day);
//
//        Set<Movie> movieSet = new TreeSet<>();
//
//        for (Auditorium auditorium : admin.getAuditoriumMap().values()) {
//            for (Screening screening : auditorium.screeningTreeSet) {
//                LocalDate screeningDate = screening.startTime.toLocalDate();
//
//                if (date.equals(screeningDate)) {
//                    movieSet.add(screening.getMovie());
//                }
//            }
//        }
//
//        return new ArrayList<>(movieSet);
//    }
//
//    public List<Movie> getMoviesAfterDay(int year, int month, int day) {
//        LocalDate date = LocalDate.of(year, month, day);
//
//        Set<Movie> movieSet = new TreeSet<>();
//
//        for (Auditorium auditorium : admin.getAuditoriumMap().values()) {
//            for (Screening screening : auditorium.screeningTreeSet) {
//                LocalDate screeningDate = screening.startTime.toLocalDate();
//
//                if (date.compareTo(screeningDate) <= 0) {
//                    movieSet.add(screening.getMovie());
//                }
//            }
//        }
//
//        return new ArrayList<>(movieSet);
//    }
//
//    public List<Screening> getScreeningsForMovieAfter(String movieName, int year, int month, int day) {
//        LocalDate date = LocalDate.of(year, month, day);
//        List<Screening> ret = new ArrayList<>();
//
//        for (Auditorium auditorium : admin.getAuditoriumMap().values()) {
//            for (Screening screening : auditorium.screeningTreeSet) {
//                LocalDate screeningDate = screening.startTime.toLocalDate();
//
//                if (date.compareTo(screeningDate) <= 0 && screening.getMovie().getName().equals(movieName)) {
//                    ret.add(screening);
//                }
//            }
//        }
//
//        return ret;
//    }
}
