import cinema.data.*;
import cinema.exception.CinemaException;
import cinema.service.AdminService;
import cinema.service.ClientService;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.*;

public class Main {
    public static void main(String[] args) throws CinemaException, IOException {
        Logger logger = Logger.getLogger("cinema.Logger");

        FileHandler handler = new FileHandler("logging/logMessages.txt", true);
        handler.setFormatter(new Formatter() {
            @Override
            public String format(LogRecord record) {
                String level = record.getLevel().toString();

                LocalDateTime date = LocalDateTime.ofInstant(Instant.ofEpochMilli( record.getMillis() ), ZoneId.systemDefault());
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss - dd/MM/yyyy");;
                String timestamp = date.format(formatter);

                String action = record.getMessage();
                String name = record.getLoggerName();

                String ans = "\n" + "Level: " + level + "; at " + timestamp + ":\n================\n" + action + "\n================\nLogger: " + name + "\n";
                return ans;
            }
        });
        logger.addHandler(handler);





        logger.info("\nProgram started");

        System.out.println("Choices:");
        System.out.println("1.  Add Category");
        System.out.println("2.  Add Food");
        System.out.println("3.  Add Movie");
        System.out.println("4.  Add Employee");
        System.out.println("5.  Add Auditorium");
        System.out.println("6.  Add Screening To Auditorium");
        System.out.println("7.  Add Usher To Screening");
        System.out.println("8.  Add Client");
        System.out.println("9.  Add funds to client");
        System.out.println("10. Purchase ticket for client");
        System.out.println("11. Purchase food for client");
        System.out.println("12. Get persons at screening");
        System.out.println("13. Get screenings for employee");

        boolean caughtException = false;
        try {
            AdminService as = new AdminService();
            Scanner scanner = new Scanner(System.in);
            int choice = scanner.nextInt();
            if (choice == 1) {
                System.out.println("Insert Category: (String name, int minimumAge)");
                as.addCategory(scanner.next(), scanner.nextInt());
            }
            else if (choice == 2) {
                System.out.println("Insert Food: (String name, double price)");
                as.addFood(scanner.next(), scanner.nextDouble());
            }
            else if (choice == 3) {
                System.out.println("Insert Movie: (String name, int durationInMinutes, String categoryName)");
                as.addMovie(scanner.next(), scanner.nextInt(), scanner.next());
            }
            else if (choice == 4) {
                System.out.println("Insert Employee: (String firstName, String lastName, String email,\n" +
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
                System.out.println("Insert Auditorium: (int numberOfSeats)");
                as.addAuditorium(scanner.nextInt());
            }
            else if (choice == 6) {
                System.out.println("Add screening to auditorium: (long auditoriumId, long movieId, double price, int year, int month, int day, int hour, long technicianId)");
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
                System.out.println("Add usher to screening: (long screeningId, long employeeId)");
                as.addUsherToScreening(
                        scanner.nextLong(),
                        scanner.nextLong()
                );
            }
            else if (choice == 8) {
                System.out.println("Add client: (String firstName, String lastName, String email, int birthYear, int birthMonth, int birthDay)");
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
                System.out.println("Add funds to client: (long clientId, double amount)");
                as.addFundsToClient(
                        scanner.nextLong(),
                        scanner.nextDouble()
                );
            }
            else if (choice == 10) {
                System.out.println("Purchase ticket for client: (long clientId, int year, int month, int day, long screeningId, int seatNumber)");
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
                System.out.println("purchase Food For Client: (long clientId, long foodId, int year, int month, int day)");
                as.purchaseFoodForClient(
                        scanner.nextLong(),
                        scanner.nextLong(),
                        scanner.nextInt(),
                        scanner.nextInt(),
                        scanner.nextInt()
                );
            }
            else if (choice == 12) {
                System.out.println("get Persons At Screening: (long screeningId)");
                List<Long> personList = as.getPersonsAtScreening(scanner.nextLong());

                System.out.println("The people with the following ids were at this screening:");
                for (Long id : personList) {
                    System.out.print(id + " ");
                }
            }
            else if (choice == 13) {
                System.out.println("get Screenings For Employee: (long employeeId)");
                List<Long> list = as.getScreeningsForEmployee(scanner.nextLong());

                System.out.println("This employee was at the screenings with the following ids: ");
                for (Long id : list) {
                    System.out.print(id + " ");
                }
            }
        }
        catch (Exception except) {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            except.printStackTrace(pw);
            String sStackTrace = sw.toString();

            logger.severe("Abnormal program termination with stack trace:\n" + sStackTrace);
            caughtException = true;
        }

        if (!caughtException) {
            logger.info("Normal program termination\n");
        }
    }
}
