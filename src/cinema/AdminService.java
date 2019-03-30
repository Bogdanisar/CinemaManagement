package cinema;

import cinema.Exception.CinemaException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

public final class AdminService {
    private static Logger logger = Logger.getLogger(Movie.class.getName());
    private static Map<String, Category> categoryMap = new HashMap<>();
    private static Map<String, Food> foodMap = new HashMap<>();
    private static Map<String, Movie> movieMap = new HashMap<>();
    private static Map<Long, Client> clientMap = new HashMap<>();
    private static Map<Long, Employee> employeeMap = new HashMap<>();
    private static Map<Long, Auditorium> auditoriumMap = new HashMap<>();
    private static Map<Long, Screening> screeningMap = new HashMap<>();
    private static long personCounter = 0;
    private static long auditoriumCounter = 0;
    private static long screeningCounter = 0;

    public AdminService(String username, String password) throws CinemaException {
        if (username.equals("admin") == false || password.equals("123") == false) {
            throw new CinemaException("bad admin login");
        }

        // for debugging in .getObjectFromId
        categoryMap.put(null, new Category(null, 0));
        foodMap.put(null, new Food(null,0.0));
        movieMap.put(null, new Movie.Builder(null, 0).build());
        clientMap.put(null, new Client(0L,null,null,null,LocalDate.now()));
        employeeMap.put(null, new Employee(0L,null, null, null,null, null, 0.0));
        auditoriumMap.put(null, new Auditorium(0L, 0));
        screeningMap.put(null, new Screening(0L, movieMap.get(null), null, 0.0, LocalDateTime.now(), null));
    }

    public static Map<String, Category> getCategoryMap() {
        return categoryMap;
    }

    public static Map<String, Food> getFoodMap() {
        return foodMap;
    }

    public static Map<String, Movie> getMovieMap() {
        return movieMap;
    }

    public static Map<Long, Client> getClientMap() {
        return clientMap;
    }

    public static Map<Long, Employee> getEmployeeMap() {
        return employeeMap;
    }

    public static Map<Long, Auditorium> getAuditoriumMap() {
        return auditoriumMap;
    }

    public static Map<Long, Screening> getScreeningMap() {
        return screeningMap;
    }

    public static long getPersonCounter() {
        return personCounter;
    }

    public static long getAuditoriumCounter() {
        return auditoriumCounter;
    }

    public static long getScreeningCounter() {
        return screeningCounter;
    }

    <Key, Value> Value getObjectFromId(Key id, Map<Key, ? extends Value> map) throws CinemaException {
        if (map.get(id) == null) {
            CinemaException ex = null;
            if (map.get(null) != null) {
                ex = new CinemaException("id " + id + " is not a valid id from " + map.get(null).getClass().getSimpleName());
            }
            else {
                ex = new CinemaException("id " + id + " is not a valid id from map");
            }
            throw ex;
        }

        return map.get(id);
    }

    public void addCategory(String name, int minimumAge) {
        categoryMap.put(name, new Category(name, minimumAge));
    }

    public void addFood(String name, double price) {
        foodMap.put(name, new Food(name, price));
    }

    public void addMovie(String name, int duration, String... categoryNames) throws CinemaException {
        Movie.Builder builder = new Movie.Builder(name, duration);
        for (String catName : categoryNames) {
            Category cat = getObjectFromId(catName, categoryMap);
            builder.withCategory(cat);
        }

        Movie movie = builder.build();
        movieMap.put(movie.getName(), movie);
    }

    public long addEmployee(String firstName, String lastName, String email,
                           int birthYear, int birthMonth, int birthDay,
                           int hireYear, int hireMonth, int hireDay, double salary) {

        LocalDate birth = LocalDate.of(birthYear, birthMonth, birthDay);
        LocalDate hire = LocalDate.of(hireYear, hireMonth, hireDay);
        Employee employee = new Employee(++personCounter, firstName, lastName, email, birth, hire, salary);

        employeeMap.put(employee.id, employee);
        return employee.id;
    }

    public long addEmployee(String firstName, String lastName, String email,
                           int birthYear, int birthMonth, int birthDay, double salary) {

        LocalDate now = LocalDate.now();
        return addEmployee(firstName, lastName, email, birthYear, birthMonth, birthDay, now.getYear(), now.getMonthValue(), now.getDayOfMonth(), salary);
    }

    public long addAuditorium(int number_of_seats) {
        Auditorium auditorium = new Auditorium(++auditoriumCounter, number_of_seats);
        auditoriumMap.put(auditorium.getId(), auditorium);
        return auditorium.getId();
    }

    public long addScreeningToAuditorium(Long auditoriumId, String movieName, double price, int year, int month, int day, int hour, int minute, Long technicianId) throws CinemaException {
        Movie movie = getObjectFromId(movieName, movieMap);
        Employee technician = getObjectFromId(technicianId, employeeMap);
        Auditorium auditorium = getObjectFromId(auditoriumId, auditoriumMap);
        Screening screening = new Screening(++screeningCounter, movie, auditorium, price, LocalDateTime.of(year, month, day, hour, minute), technician);

        if (auditorium.screeningTreeSet.add(screening) == false) {
            --screeningCounter;
            throw new CinemaException("New screening of " + movieName + " overlaps with other screenings for auditorium with id " + auditoriumId);
        }

        screeningMap.put(screening.getId(), screening);
        return screening.getId();
    }

    public long addScreeningToAuditorium(Long auditoriumId, String movieName, double price, Long technicianId) throws CinemaException {
        LocalDateTime d = LocalDateTime.now();
        return addScreeningToAuditorium(auditoriumId, movieName, price, d.getYear(), d.getMonthValue(), d.getDayOfMonth(), d.getHour(), d.getMinute(), technicianId);
    }

    public void addUsherToScreening(Long screeningId, Long employeeId) throws CinemaException {
        Screening screening = getObjectFromId(screeningId, screeningMap);
        Employee usher = getObjectFromId(employeeId, employeeMap);

        if (usher == screening.technician) {
            String message = "Can't add usher to screening with id " + screeningId +
                    "; Employee with id " + employeeId + " is already a technician";
            throw new CinemaException(message);
        }

        if (usher.hireDate.atStartOfDay().compareTo(screening.startTime) > 0) {
            throw new CinemaException("Employee with id " + employeeId + " can't be an usher before his hire date for the screening with id " + screeningId);
        }

        screening.ushers.add(usher);
    }

    public long addClient(String firstName, String lastName, String email, int birthYear, int birthMonth, int birthDay) {
        Client client = new Client(++personCounter, firstName, lastName, email, LocalDate.of(birthYear, birthMonth, birthDay));
        clientMap.put(client.id, client);
        return client.id;
    }

    public void addFundsToClient(Long clientId, double amount) throws CinemaException {
        Client client = getObjectFromId(clientId, clientMap);
        client.funds += amount;
    }

    public void purchaseTicketForClient(Long clientId, int year, int month, int day, int hour, int minute, Long screeningId, int seatNumber) throws CinemaException {
        Client client = getObjectFromId(clientId, clientMap);
        Screening screening = getObjectFromId(screeningId, screeningMap);

        if (client.funds < screening.price) {
            throw new CinemaException("Client " + clientId + " named " + client.getFirstName() + " doesn't have enough funds for the screening " + screeningId + " of movie " + screening.movie.getName());
        }

        // check if the auditorium has such a seat
        if (!(0 < seatNumber && seatNumber <= screening.auditorium.number_of_seats)) {
            throw new CinemaException("The seat id " + seatNumber + " is more than " + screening.auditorium.number_of_seats);
        }
        // check if the seat is already taken
        if (screening.seatSet.contains(seatNumber)) {
            throw new CinemaException("The seat id " + seatNumber +
                    " is already taken for the screening with movie " + screening.getMovie().getName());
        }

        // check if the client is old enough to see the movie
        ClientService cs = new ClientService(clientId);
        if (cs.isOldEnoughForAt(screening.getMovie().getName(), screening.startTime.toLocalDate()) == false) {
            throw new CinemaException("Client with id " + clientId + " is not old enough for the movie " + screening.getMovie().getName());
        }

        TicketPurchase purchase = new TicketPurchase(LocalDateTime.of(year, month, day, hour, minute), screening, seatNumber);
        client.purchaseList.add(purchase);
        client.funds -= screening.price;
        screening.addClient(client);
        screening.seatSet.add(seatNumber);
    }

    public void purchaseFoodForClient(Long clientId, String foodName, int year, int month, int day, int hour, int minute) throws CinemaException {
        Client client = getObjectFromId(clientId, clientMap);
        Food food = getObjectFromId(foodName, foodMap);

        if (client.funds < food.getPrice()) {
            String mes = "Client " + clientId + " " + client.getFirstName() + " doesn't have enough funds for: " + food.getName();
            throw new CinemaException(mes);
        }

        LocalDateTime date = LocalDateTime.of(year, month, day, hour, minute);
        FoodPurchase purchase = new FoodPurchase(food, date);
        client.purchaseList.add(purchase);
        client.funds -= food.getPrice();
    }

    public List<Person> getPersonsAtScreening(Long screeningId) throws CinemaException {
        Screening screening = getObjectFromId(screeningId, screeningMap);
        List<Person> ret = new ArrayList<>();
        for (Person p : screening.clients) {
            ret.add(p);
        }
        for (Person p : screening.ushers) {
            ret.add(p);
        }
        ret.add(screening.technician);

        return ret;
    }

    public List<Screening> getScreeningsForEmployee(Long employeeId) throws CinemaException {
        Employee employee = getObjectFromId(employeeId, employeeMap);
        List<Screening> ret = new ArrayList<>();

        for (Screening screening : screeningMap.values()) {
            if (employee == screening.technician) {
                ret.add(screening);
                continue;
            }

            for (Employee usher : screening.ushers) {
                if (employee == usher) {
                    ret.add(screening);
                    break;
                }
            }
        }

        return ret;
    }

//    public static void main(String[] args) throws CinemaException {
//        AdminService as = new AdminService("admin", "123");
//        as.getObjectFromId(20L, as.clientMap);
//        as.getObjectFromId("hi", as.foodMap);
//
//        as.addMovie("test", 2);
//    }
}
