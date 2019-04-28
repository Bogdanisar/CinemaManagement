import cinema.data.*;
import cinema.exception.CinemaException;
import cinema.service.AdminService;
import cinema.service.ClientService;

import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws CinemaException {
//        Movie m = new Movie.Builder("name", 3)
//                .withCategory(new Category("action", 20))
//                .withCategory(new Category("drama", 18))
//                .build();

        AdminService as = new AdminService("admin","123");

        // add categories
        String category1 = "Action", category2 = "Drama", category3 = "Animation", category4 = "Horror";
        as.addCategory(category1, 15);
        as.addCategory(category2, 18);
        as.addCategory(category3, 12);
        as.addCategory(category4, 18);

        // add foods
        String food1 = "Popcorn - large", food2 = "Popcorn - small",
                food3 = "Soda - large", food4 = "Soda - small";
        as.addFood(food1, 13);
        as.addFood(food2, 7);
        as.addFood(food3, 25);
        as.addFood(food4, 15);

        // add movies
        String movie1 = "Fallen", movie2 = "Risen", movie3 = "Shine", movie4 = "Live";
        as.addMovie(movie1, 120, "Action", "Horror");
//        as.addMovie(movie2, 115, "Action", "Thriller"); // id Thriller is not a valid id from Category
        as.addMovie(movie2, 115, "Drama");
        as.addMovie(movie3, 135, "Animation");
        as.addMovie(movie4, 180, "Action", "Drama", "Horror");

        // add employees
        Long employeeId1 = as.addEmployee(
                "Ion",
                "Ionescu",
                "ionescu@gmail.com",
                1995,
                8,
                23,
                2.00
        );
        Long employeeId2 = as.addEmployee(
                "Mihai",
                "Mihailescu",
                "mihai@gmail.com",
                1998,
                2,
                20,
                5.00
        );
        Long employeeId3 = as.addEmployee(
                "Maria",
                "Marinescu",
                "maria@gmail.com",
                1951,
                7,
                28,
                1983,
                10,
                17,
                5.00
        );
        Long employeeId4 = as.addEmployee(
                "Vasile",
                "Vasilescu",
                "vasile@gmail.com",
                1960,
                1,
                1,
                1980,
                11,
                7,
                3.00
        );

        // add auditoriums
        Long auditoriumId1 = as.addAuditorium(20);
        Long auditoriumId2 = as.addAuditorium(30);

        // add screenings
        Long screeningId1 = as.addScreeningToAuditorium(auditoriumId1, movie1, 30, 2020, 1, 1, 12, 0, employeeId1);

        // New screening of Fallen overlaps with other screenings for auditorium with id 1
//        Long screeningId2 = as.addScreeningToAuditorium(auditoriumId1, movie1, 30, 2020, 1, 1, 11, 0, employeeId1);

        Long screeningId2 = as.addScreeningToAuditorium(auditoriumId1, movie2, 30, 2020, 1, 1, 18, 0, employeeId1);
        Long screeningId3 = as.addScreeningToAuditorium(auditoriumId1, movie3, 30, 2020, 1, 4, 10, 0, employeeId1);
        Long screeningId4 = as.addScreeningToAuditorium(auditoriumId1, movie4, 30, 2020, 1, 4, 19, 0, employeeId1);
        Long screeningId5 = as.addScreeningToAuditorium(auditoriumId1, movie2, 30, 2021, 1, 1, 11, 0, employeeId2);
        Long screeningId6 = as.addScreeningToAuditorium(auditoriumId1, movie4, 30, 2021, 1, 2, 11, 0, employeeId2);

        Long screeningId7 = as.addScreeningToAuditorium(auditoriumId2, movie2, 10, 2018, 3, 20, 11, 5, employeeId1);
        Long screeningId8 = as.addScreeningToAuditorium(auditoriumId2, movie3, 10, employeeId2);

        // add ushers;
        as.addUsherToScreening(screeningId1, employeeId3);
        as.addUsherToScreening(screeningId1, employeeId4);
        as.addUsherToScreening(screeningId2, employeeId3);
        as.addUsherToScreening(screeningId2, employeeId4);
        as.addUsherToScreening(screeningId3, employeeId3);
        as.addUsherToScreening(screeningId3, employeeId4);
        as.addUsherToScreening(screeningId4, employeeId3);
        as.addUsherToScreening(screeningId4, employeeId4);
        as.addUsherToScreening(screeningId5, employeeId3);
        as.addUsherToScreening(screeningId5, employeeId4);
        as.addUsherToScreening(screeningId6, employeeId3);
        as.addUsherToScreening(screeningId6, employeeId4);

//        Can't add usher to screening with id 7; Employee with id 1 is already a technician
//        as.addUsherToScreening(screeningId7, employeeId1);

//        Employee with id 2 can't be an usher before his hire date for the screening with id 7
//        as.addUsherToScreening(screeningId7, employeeId2);



        // add clients
        Long clientId1 = as.addClient("Mircea", "Mirculescu", "mircea@gmail.com", 1960, 11, 21);
        Long clientId2 = as.addClient("Alin", "Alinescu", "alin@gmail.com", 2006, 11, 21);

        // add funds
        as.addFundsToClient(clientId1, 100);

        as.addFundsToClient(clientId2, 100);
        as.addFundsToClient(clientId2, 100);

        // purchase tickets for Mircea
//        The seat id 200 is more than 20
//        as.purchaseTicketForClient(clientId1, 1900, 1, 1, 1, 1, screeningId1, 200);

        as.purchaseTicketForClient(clientId1, 1900, 1, 1, 1, 1, screeningId1, 20);
        as.purchaseTicketForClient(clientId1, 1900, 1, 1, 1, 1, screeningId1, 19);

//        The seat id 19 is already taken for the screening with movie Fallen
//        as.purchaseTicketForClient(clientId1, 1900, 1, 1, 1, 1, screeningId1, 19);

        as.purchaseTicketForClient(clientId1, 1900, 1, 1, 1, 1, screeningId2, 1);

//        Client 5 named Mircea doesn't have enough funds for the screening 3 of movie Risen
//        as.purchaseTicketForClient(clientId1, 1900, 1, 1, 1, 1, screeningId3, 4);

        as.addFundsToClient(clientId1, 200);
        as.purchaseTicketForClient(clientId1, 1900, 1, 1, 1, 1, screeningId3, 4);


        // purchase tickets for Alin
        as.purchaseTicketForClient(clientId2, 1900, 1, 1, 1, 1, screeningId3, 12); // is old enough
        as.purchaseTicketForClient(clientId2, 1900, 1, 1, 1, 1, screeningId3, 13); // is old enough
        as.purchaseTicketForClient(clientId2, 1900, 1, 1, 1, 1, screeningId8, 14); // is old enough

//        Client with id 6 is not old enough for the movie Live
//        as.purchaseTicketForClient(clientId2, 1900, 1, 1, 1, 1, screeningId4, 12); // is not old enough

        // purchase foods
        as.purchaseFoodForClient(clientId1, "Popcorn - large", 1900, 1, 1,1, 1);

//        id Cake is not a valid id from Food
//        as.purchaseFoodForClient(clientId1, "Cake", 1900, 1, 1,1, 1);

        as.purchaseFoodForClient(clientId2, "Soda - large", 1900, 1, 1,1, 1);
        as.purchaseFoodForClient(clientId2, "Soda - small", 1900, 1, 1,1, 1);
        as.purchaseFoodForClient(clientId2, "Popcorn - large", 1900, 1, 1,1, 1);
        as.purchaseFoodForClient(clientId2, "Popcorn - small", 1900, 1, 1,1, 1);
        as.purchaseFoodForClient(clientId2, "Popcorn - large", 1900, 1, 1,1, 1);
        as.purchaseFoodForClient(clientId2, "Popcorn - large", 1900, 1, 1,1, 1);
        as.purchaseFoodForClient(clientId2, "Popcorn - large", 1900, 1, 1,1, 1);
        as.purchaseFoodForClient(clientId2, "Popcorn - large", 1900, 1, 1,1, 1);
        as.purchaseFoodForClient(clientId2, "Popcorn - large", 1900, 1, 1,1, 1);

//        Client 6 Alin doesn't have enough funds for: Soda - large
//        as.purchaseFoodForClient(clientId2, "Soda - large", 1900, 1, 1,1, 1);
//        as.purchaseFoodForClient(clientId2, "Soda - large", 1900, 1, 1,1, 1);
//        as.purchaseFoodForClient(clientId2, "Soda - large", 1900, 1, 1,1, 1);
//        as.purchaseFoodForClient(clientId2, "Soda - large", 1900, 1, 1,1, 1);
//        as.purchaseFoodForClient(clientId2, "Soda - large", 1900, 1, 1,1, 1);


        // getPersonsAtScreening, getScreeningsForEmployee queries;
        List<Person> personList;
        personList = as.getPersonsAtScreening(screeningId1);
        for (Person person : personList) {
            System.out.println(person.getName() + " was at the screening with id " + screeningId1);
        }
        System.out.println("\n");

        personList = as.getPersonsAtScreening(screeningId3);
        for (Person person : personList) {
            System.out.println(person.getName() + " was at the screening with id " + screeningId2);
        }
        System.out.println("\n");

        List<Screening> screeningList = as.getScreeningsForEmployee(employeeId1);
        for (Screening screening : screeningList) {
            System.out.println("Employee with id " + employeeId1 + " has attended the screening with id " + screening.getId());
        }
        System.out.println("\n");

        // clientService
        System.out.println();
        ClientService cs = new ClientService(clientId2);
        System.out.println("Age of " + cs.getClient().getName() + " is " + cs.getClient().getAge());

        System.out.println(cs.getClient().getName() + ((cs.isOldEnoughFor("Live")) ? " is " : " is not ") + "old enough for Live" );
        System.out.println("Total spending of " + cs.getClient().getName() + " is " + cs.getTotalSpent());

        System.out.println("Available foods: ");
        for (Food food : cs.getAllFoods()) {
            System.out.print(food.getName() + ", ");
        }
        System.out.println("\n");

        System.out.println("Watched movies: ");
        for (Map.Entry<Movie, Integer> movieEntry : cs.getWatchedMovies().entrySet()) {
            System.out.println("Client watched " + movieEntry.getKey().getName() + " " + movieEntry.getValue() + " times");
        }
        System.out.println("\n");

        System.out.println("Screenings attended: ");
        for (Screening screening : cs.getScreenings()) {
            System.out.println("Client watched " + screening.getMovie().getName() + " at screening with id " + screening.getId());
        }
        System.out.println("\n");

        System.out.println("Purchases made: ");
        for (Purchase purchase : cs.getPurchases()) {
            System.out.println("Purchase - " + purchase.getName());
        }
        System.out.println("\n");

        System.out.println("Movies on 04/01/2020: ");
        for (Movie movie : cs.getMoviesFromDay(2020, 1, 4)) {
            System.out.println("Movie " + movie.getName() + " plays on 04/01/2020");
        }
        System.out.println("\n");

        System.out.println("Movies after 04/01/2020: ");
        for (Movie movie : cs.getMoviesAfterDay(2020, 1, 4)) {
            System.out.println("Movie " + movie.getName() + " plays after 04/01/2020");
        }
        System.out.println("\n");

        System.out.println("Movie " + movie4 + " can be seen at the following screenings:");
        for (Screening screening : cs.getScreeningsForMovieAfter(movie4, 2020, 1, 4)) {
            System.out.println("Movie " + screening.getMovie().getName() + " is available for watching at screening with id " + screening.getId());
        }
        System.out.println("\n");
    }
}
