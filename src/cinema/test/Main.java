package cinema.test;

import cinema.data.*;
import cinema.exception.CinemaException;
import cinema.service.AdminService;
import cinema.service.ClientService;
import cinema.service.InfoService;
import cinema.service.LoggerService;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.*;

public class Main {
    public static Logger logger = null;

    public static void main(String[] args) throws CinemaException, IOException {
        logger = LoggerService.getInstance();


        String[] prompts = {
                "1.  AdminService: Add Category",
                "2.  AdminService: Add Food",
                "3.  AdminService: Add Movie",
                "4.  AdminService: Add Employee",
                "5.  AdminService: Add Auditorium",
                "6.  AdminService: Add Screening To Auditorium",
                "7.  AdminService: Add Usher To Screening",
                "8.  AdminService: Add Client",
                "9.  AdminService: Add funds to client",
                "10. AdminService: Purchase ticket for client",
                "11. AdminService: Purchase food for client",
                "12. AdminService: Get persons at screening",
                "13. AdminService: Get screenings for employee",
                "14. ClientService: Get total spent for client",
                "15. ClientService: Get watched movies for client",
                "16. ClientService: Get screenings for client",
                "17. ClientService: Client is old enough for movie",
                "18. InfoService: Get foods",
                "19. InfoService: Get movies after day",
                "20. InfoService: Get screenings for movie after day"
        };


        logger.info("Program started");

        System.out.println("Choices:");
        for (String p : prompts) {
            System.out.println(p);
        }
        System.out.println();

        boolean caughtException = false;
        try {
            AdminService as = new AdminService();
            Scanner scanner = new Scanner(System.in);
            int choice = scanner.nextInt();
            if (choice == 1) {
                logger.info(prompts[choice - 1]);

                System.out.println(prompts[choice - 1] + ": (String name, int minimumAge)");
                as.addCategory(scanner.next(), scanner.nextInt());
            }
            else if (choice == 2) {
                logger.info(prompts[choice - 1]);

                System.out.println(prompts[choice - 1] + ": (String name, double price)");
                as.addFood(scanner.next(), scanner.nextDouble());
            }
            else if (choice == 3) {
                logger.info(prompts[choice - 1]);

                System.out.println(prompts[choice - 1] + ": (String name, int durationInMinutes, String categoryName)");
                as.addMovie(scanner.next(), scanner.nextInt(), scanner.next());
            }
            else if (choice == 4) {
                logger.info(prompts[choice - 1]);

                System.out.println(prompts[choice - 1] + ": (String firstName, String lastName, String email,\n" +
                        "                           int birthYear, int birthMonth, int birthDay,\n" +
                        "                           int hireYear, int hireMonth, int hireDay, double salary)");
                as.addEmployee(
                        scanner.next(),
                        scanner.next(),
                        scanner.next(),
                        scanner.nextInt(),
                        scanner.nextInt(),
                        scanner.nextInt(),
                        scanner.nextInt(),
                        scanner.nextInt(),
                        scanner.nextInt(),
                        scanner.nextDouble()
                );
            }
            else if (choice == 5) {
                logger.info(prompts[choice - 1]);

                System.out.println(prompts[choice - 1] + ": (int numberOfSeats)");
                as.addAuditorium(scanner.nextInt());
            }
            else if (choice == 6) {
                logger.info(prompts[choice - 1]);

                System.out.println(prompts[choice - 1] + ": (long auditoriumId, long movieId, double price, int year, int month, int day, int hour, long technicianId)");
                as.addScreeningToAuditorium(
                        scanner.nextLong(),
                        scanner.nextLong(),
                        scanner.nextDouble(),
                        scanner.nextInt(),
                        scanner.nextInt(),
                        scanner.nextInt(),
                        scanner.nextInt(),
                        scanner.nextLong()
                );
            }
            else if (choice == 7) {
                logger.info(prompts[choice - 1]);

                System.out.println(prompts[choice - 1] + ": (long screeningId, long employeeId)");
                as.addUsherToScreening(
                        scanner.nextLong(),
                        scanner.nextLong()
                );
            }
            else if (choice == 8) {
                logger.info(prompts[choice - 1]);

                System.out.println(prompts[choice - 1] + ": (String firstName, String lastName, String email, int birthYear, int birthMonth, int birthDay)");
                as.addClient(
                        scanner.next(),
                        scanner.next(),
                        scanner.next(),
                        scanner.nextInt(),
                        scanner.nextInt(),
                        scanner.nextInt()
                );
            }
            else if (choice == 9) {
                logger.info(prompts[choice - 1]);

                System.out.println(prompts[choice - 1] + ": (long clientId, double amount)");
                as.addFundsToClient(
                        scanner.nextLong(),
                        scanner.nextDouble()
                );
            }
            else if (choice == 10) {
                logger.info(prompts[choice - 1]);

                System.out.println(prompts[choice - 1] + ": (long clientId, int year, int month, int day, long screeningId, int seatNumber)");
                as.purchaseTicketForClient(
                        scanner.nextLong(),
                        scanner.nextInt(),
                        scanner.nextInt(),
                        scanner.nextInt(),
                        scanner.nextLong(),
                        scanner.nextInt()
                );
            }
            else if (choice == 11) {
                logger.info(prompts[choice - 1]);

                System.out.println(prompts[choice - 1] + ": (long clientId, long foodId, int year, int month, int day)");
                as.purchaseFoodForClient(
                        scanner.nextLong(),
                        scanner.nextLong(),
                        scanner.nextInt(),
                        scanner.nextInt(),
                        scanner.nextInt()
                );
            }
            else if (choice == 12) {
                logger.info(prompts[choice - 1]);

                System.out.println(prompts[choice - 1] + ": (long screeningId)");
                List<Person> personList = as.getPersonsAtScreening(scanner.nextLong());

                System.out.println("The following people were at this screening:");
                for (Person person : personList) {
                    System.out.println("Person id: " + person.getId() + "; Person name: " + person.getName());
                }
            }
            else if (choice == 13) {
                logger.info(prompts[choice - 1]);

                System.out.println(prompts[choice - 1] + ": (long employeeId)");
                List<Long> list = as.getScreeningsForEmployee(scanner.nextLong());

                System.out.println("This employee was at the screenings with the following ids: ");
                for (Long id : list) {
                    System.out.print(id + " ");
                }
            }
            else if (choice == 14) {
                logger.info(prompts[choice - 1]);

                System.out.println(prompts[choice - 1] + ": (long clientId");
                long clientId = scanner.nextLong();
                double totalSpent = ( new ClientService(clientId) ).getTotalSpent();
                System.out.println("Total spent amount of client " + clientId + " is " + totalSpent);
            }
            else if (choice == 15) {
                logger.info(prompts[choice - 1]);

                System.out.println(prompts[choice - 1] + ": (long clientId)");

                long clientId = scanner.nextLong();
                List<Movie> list = (new ClientService(clientId)).getWatchedMovies();

                System.out.println("The client with id " + clientId + " watched:");
                for (Movie movie : list) {
                    System.out.println(movie.getName());
                }
            }
            else if (choice == 16) {
                logger.info(prompts[choice - 1]);

                System.out.println(prompts[choice - 1] + ": (long clientId)");

                long clientId = scanner.nextLong();
                List<Screening> list = (new ClientService(clientId)).getScreenings();

                System.out.println("The client with id " + clientId + " was at the screenings with the following ids:");
                for (Screening screening : list) {
                    System.out.println(screening.getId());
                }
            }
            else if (choice == 17) {
                logger.info(prompts[choice - 1]);

                System.out.println(prompts[choice - 1] + ": (long clientId, long movieId)");

                long clientId = scanner.nextLong();
                long movieId = scanner.nextLong();
                boolean result = (new ClientService(clientId)).isOldEnoughFor(movieId);
                System.out.println("Result of isClientOldEnoughForMovie: " + result);
            }
            else if (choice == 18) {
                logger.info(prompts[choice - 1]);

                System.out.println(prompts[choice - 1] + ": Get foods: ()");

                System.out.println("All available foods: ");
                List<Food> list = InfoService.getAllFoods();
                for (Food food : list) {
                    System.out.println("Food name: " + food.getName() + "; Food price: " + food.getPrice());
                }
            }
            else if (choice == 19) {
                logger.info(prompts[choice - 1]);

                System.out.println(prompts[choice - 1] + ": (int year, int month, int day");
                int year = scanner.nextInt();
                int month = scanner.nextInt();
                int day = scanner.nextInt();
                List<Movie> list = InfoService.getMoviesAfterDay(year, month, day);

                System.out.println("Movies after the day: " + day + "/" + month + "/" + year + ":");
                for (Movie movie : list) {
                    System.out.println(movie.getName());
                }
            }
            else if (choice == 20) {
                logger.info(prompts[choice - 1]);

                System.out.println(prompts[choice - 1] + ": (long movieId, int year, int month, int day)");
                long movieId = scanner.nextLong();
                int year = scanner.nextInt();
                int month = scanner.nextInt();
                int day = scanner.nextInt();

                List<Screening> list = InfoService.getScreeningsForMovieAfter(movieId, year, month, day);
                System.out.println("Screenings for the movie with id " + movieId + " after " + day + "/" + month + "/" + year + ":");
                for (Screening screening : list) {
                    LocalDate d = screening.getStartTime();
                    System.out.println("Screening id: " + screening.getId() + "; on " + d.getDayOfMonth() + "/" + d.getMonthValue() + "/" + d.getYear());
                }
            }
        }
        catch (Exception except) {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            except.printStackTrace(pw);
            String sStackTrace = sw.toString();

            caughtException = true;
            logger.severe("Abnormal program termination with stack trace:\n" + sStackTrace);
            except.printStackTrace();
        }

        if (!caughtException) {
            logger.info("Normal program termination");
        }
    }
}
