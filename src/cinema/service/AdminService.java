package cinema.service;

import cinema.data.*;
import cinema.exception.CinemaException;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.logging.Logger;
import java.sql.*;

public final class AdminService {
    private Connection conn;
    private GetterService getterService;
    private SetterService setterService;

    public AdminService(Connection conn) {
        this.conn = conn;
        this.getterService = new GetterService(this.conn);
        this.setterService = new SetterService(this.conn);
    }

    public static void checkReference(Identifiable identifiable) throws CinemaException {
        if (identifiable == null) {
            throw new CinemaException("Couldn't find the line with that id: " + identifiable.getId());
        }
    }

    public long addCategory(String name, int minimumAge) throws IOException, SQLException, SQLException {
        Category category = new Category(-1, name, minimumAge);
        return this.setterService.update(category);
    }

    public long addFood(String name, double price) throws IOException, SQLException {
        Food food = new Food(-1, name, price);
        return this.setterService.update(food);
    }

    public long addMovie(String name, int duration, String... categoryNames) throws CinemaException, IOException, SQLException {
        Movie movie = new Movie(-1, name, duration);
        long newMovieId = this.setterService.update(movie);

        List<Category> allCategories = this.getterService.getAllCategory();

        for (String categoryName : categoryNames) {
            boolean found = false;

            for (Category category : allCategories) {
                if (categoryName != null && categoryName.equals(category.getName())) {
                    AssociativeEntry entry = new AssociativeEntry(-1, newMovieId, category.getId());
                    this.setterService.updateMovieCategory(entry);

                    found = true;
                    break;
                }
            }

            if (!found) {
                throw new CinemaException("Couldn't find that category: " + categoryName);
            }
        }

        return newMovieId;
    }

    public long addEmployee(String firstName, String lastName, String email,
                           int birthYear, int birthMonth, int birthDay,
                           int hireYear, int hireMonth, int hireDay, double salary) throws IOException, SQLException {

        LocalDate birthDate = LocalDate.of(birthYear, birthMonth, birthDay);
        LocalDate hireDate = LocalDate.of(hireYear, hireMonth, hireDay);
        Employee employee = new Employee(-1L, firstName, lastName, email, birthDate, hireDate, salary);
        return this.setterService.update(employee);
    }

    public long addEmployee(String firstName, String lastName, String email,
                           int birthYear, int birthMonth, int birthDay, double salary) throws IOException, SQLException {

        LocalDate birthDate = LocalDate.of(birthYear, birthMonth, birthDay);
        LocalDate hireDate = LocalDate.now();
        Employee employee = new Employee(-1L, firstName, lastName, email, birthDate, hireDate, salary);
        return this.setterService.update(employee);
    }

    public long addAuditorium(int number_of_seats) throws IOException, SQLException {
        Auditorium auditorium = new Auditorium(-1L, number_of_seats);
        return this.setterService.update(auditorium);
    }

    public long addScreeningToAuditorium(long auditoriumId, long movieId, double price, int year, int month, int day, int hour, long technicianId) throws CinemaException, IOException, SQLException {
        Auditorium auditorium = this.getterService.getAuditorium(auditoriumId);
        Movie movie = this.getterService.getMovie(movieId);
        checkReference(auditorium);
        checkReference(movie);

        LocalDate date = LocalDate.of(year, month, day);
        Screening screening = new Screening(-1, movieId, auditoriumId, price, date, hour, technicianId);
        return this.setterService.update(screening);
    }

    public long addScreeningToAuditorium(long auditoriumId, long movieId, double price, int hour, long technicianId) throws CinemaException, IOException, SQLException {
        LocalDate d = LocalDate.now();
        return addScreeningToAuditorium(
                auditoriumId,
                movieId,
                price,
                d.getYear(),
                d.getMonthValue(),
                d.getDayOfMonth(),
                hour,
                technicianId);
    }

    public void addUsherToScreening(long screeningId, long employeeId) throws CinemaException, IOException, SQLException {
        Screening screening = this.getterService.getScreening(screeningId);
        checkReference( screening );
        Employee usher = this.getterService.getEmployee(employeeId);
        checkReference( usher );

        if (employeeId == screening.getTechnicianId()) {
            String message = "Can't add usher to screening with id " + screeningId +
                    "; Employee with id " + employeeId + " is already a technician";
            throw new CinemaException(message);
        }

        if (usher.getHireDate().compareTo(screening.getStartTime()) > 0) {
            throw new CinemaException("Employee with id " + employeeId + " can't be an usher before his hire date for the screening with id " + screeningId);
        }

        AssociativeEntry entry = new AssociativeEntry(-1, screeningId, employeeId);
        this.setterService.updateScreeningEmployee(entry);
    }

    public long addClient(String firstName, String lastName, String email, int birthYear, int birthMonth, int birthDay) throws IOException, SQLException {
        LocalDate birthDate = LocalDate.of(birthYear, birthMonth, birthDay);
        Client client = new Client((long) -1, firstName, lastName, email, birthDate);

        return this.setterService.update(client);
    }

    public void addFundsToClient(long clientId, double amount) throws CinemaException, IOException, SQLException {
        Client client = this.getterService.getClient(clientId);
        client.setFunds(client.getFunds() + amount);

        this.setterService.update(client);
    }

    public long purchaseTicketForClient(long clientId, int year, int month, int day, long screeningId, int seatNumber) throws CinemaException, IOException, SQLException {
        Client client = this.getterService.getClient(clientId);
        checkReference(client);
        Screening screening = this.getterService.getScreening(screeningId);
        checkReference(screening);
        Auditorium auditorium = this.getterService.getAuditorium(screening.getAuditoriumId());
        checkReference(auditorium);

        if (client.getFunds() < screening.getPrice()) {
            throw new CinemaException("Client " + clientId + " named " + client.getFirstName() + " doesn't have enough funds for the screening with movie id: " + screening.getMovieId());
        }

        // check if the auditorium has such a seat
        if (!(0 < seatNumber && seatNumber <= auditorium.getNumber_of_seats())) {
            throw new CinemaException("The seat id " + seatNumber + " is more than " + auditorium.getNumber_of_seats());
        }

        // check if the client is old enough to see the movie
        ClientService cs = new ClientService(this.conn, clientId);
        if (cs.isOldEnoughForAt(screening.getMovieId(), screening.getStartTime()) == false) {
            throw new CinemaException("Client with id " + clientId + " is not old enough for the movie with id: " + screening.getMovieId());
        }

        client.setFunds(client.getFunds() - screening.getPrice());
        this.setterService.update(client);

        LocalDate purchaseDate = LocalDate.of(year, month, day);
        TicketPurchase purchase = new TicketPurchase(-1, clientId, purchaseDate, screeningId, seatNumber);
        return this.setterService.update(purchase);
    }

    public long purchaseFoodForClient(long clientId, long foodId, int year, int month, int day) throws CinemaException, IOException, SQLException {
        Client client = this.getterService.getClient(clientId);
        checkReference(client);
        Food food = this.getterService.getFood(foodId);
        checkReference(food);

        if (client.getFunds() < food.getPrice()) {
            String mes = "Client " + clientId + " " + client.getFirstName() + " doesn't have enough funds for: " + food.getName();
            throw new CinemaException(mes);
        }

        client.setFunds(client.getFunds() - food.getPrice());
        this.setterService.update(client);

        LocalDate date = LocalDate.of(year, month, day);
        FoodPurchase purchase = new FoodPurchase(-1L, clientId, date, foodId, food.getPrice());
        return this.setterService.update(purchase);
    }

    public List<Person> getPersonsAtScreening(long screeningId) throws CinemaException, IOException, SQLException {
        List<Person> ret = new ArrayList<>();
        Screening screening = this.getterService.getScreening(screeningId);
        AdminService.checkReference(screening);

        Set<Long> clientIdSet = new HashSet<>();
        for (TicketPurchase purchase : this.getterService.getAllTicketPurchase()) {
            if (purchase.getScreeningId() == screeningId) {
                clientIdSet.add(purchase.getClientId());
            }
        }
        for (Person person : this.getterService.getAllClient()) {
            if (clientIdSet.contains(person.getId())) {
                ret.add(person);
            }
        }

        Set<Long> employeeIdSet = new HashSet<>();
        employeeIdSet.add(screening.getTechnicianId());
        for (AssociativeEntry entry : this.getterService.getAllScreeningEmployee()) {
            if (entry.getFirstId() == screeningId) {
                employeeIdSet.add(entry.getSecondId());
            }
        }

        for (Person person : this.getterService.getAllEmployee()) {
            if (employeeIdSet.contains(person.getId())) {
                ret.add(person);
            }
        }

        return ret;
    }

    public List<Long> getScreeningsForEmployee(long employeeId) throws CinemaException, IOException, SQLException {
        Set<Long> retSet = new TreeSet<>();

        List<Screening> allScreenings = this.getterService.getAllScreening();
        for (Screening screening : allScreenings) {
            if (screening.getTechnicianId() == employeeId) {
                retSet.add(screening.getId());
            }
        }

        for (AssociativeEntry entry : this.getterService.getAllScreeningEmployee()) {
            if (entry.getSecondId() == employeeId) {
                retSet.add(entry.getFirstId());
            }
        }

        return new ArrayList<>(retSet);
    }
}
