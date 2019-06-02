package cinema.graphics;

import cinema.data.*;
import cinema.service.*;

import javax.xml.crypto.Data;
import java.io.ObjectInputStream;
import java.time.LocalDate;
import java.util.List;
import java.util.logging.Logger;

import java.sql.Connection;

public interface ListAction {
    public String getActionName();
    public String[] getLabelNames();

    // Returns a String array of two items: Operation Description and Operation Output.
    public String[] run(String[] textFields, Connection conn) throws Exception;

    private static String getMessage(String title, String[] textFields, int num) {
        String message = title + "\n";
        message += textFields[0];
        for (int i = 1; i < num; ++i) {
            message += "\n" + textFields[i];
        }

        return message;
    }






    // Admin Tab

    public class AddCategory implements ListAction {
        @Override
        public String getActionName() {
            return "Add a category";
        }

        @Override
        public String[] getLabelNames() {
            return new String[] {
                    "Name (String)",
                    "Miminum Age (int)"
            };
        }

        @Override
        public String[] run(String[] textFields, Connection conn) throws Exception {
            AdminService adminService = new AdminService(conn);
            long newId = adminService.addCategory(textFields[0], Integer.parseInt(textFields[1]));

            String[] ret = new String[] {
                    ListAction.getMessage("Added a Category with:", textFields, 2),
                    "New Category Id = " + newId
            };

            return ret;
        }
    }

    public class AddFood implements ListAction {
        @Override
        public String getActionName() {
            return "Add a food item";
        }

        @Override
        public String[] getLabelNames() {
            return new String[] {
                    "Name (String)",
                    "Price (double)"
            };
        }

        @Override
        public String[] run(String[] textFields, Connection conn) throws Exception {
            AdminService adminService = new AdminService(conn);
            long newId = adminService.addFood(textFields[0], Double.parseDouble(textFields[1]));

            String[] ret = new String[] {
                    ListAction.getMessage("Added a Food Item with:", textFields, 2),
                    "New Food Item Id = " + newId
            };

            return ret;
        }
    }

    public class AddMovie implements ListAction {
        @Override
        public String getActionName() {
            return "Add a movie";
        }

        @Override
        public String[] getLabelNames() {
            return new String[] {
                    "Name (String)",
                    "Duration (min) (int)",
                    "Categories (comma separated) (String)"
            };
        }

        @Override
        public String[] run(String[] textFields, Connection conn) throws Exception {
            AdminService adminService = new AdminService(conn);
            long newId = adminService.addMovie(
                    textFields[0],
                    Integer.parseInt(textFields[1]),
                    textFields[2].split("[ ,;.\\t\\n]+")
            );

            String[] ret = new String[] {
                    ListAction.getMessage("Added a Movie Item with:", textFields, 3),
                    "New Movie Id = " + newId
            };

            return ret;
        }
    }

    public class AddEmployee implements ListAction {
        @Override
        public String getActionName() {
            return "Add an employee";
        }

        @Override
        public String[] getLabelNames() {
            return new String[]{
                    "First Name (String)",
                    "Last Name (String)",
                    "Email (String)",
                    "Birth Year (int)",
                    "Birth Month (int)",
                    "Birth Day (int)",
                    "Hire Year (int)",
                    "Hire Month (int)",
                    "Hire Day (int)",
                    "Salary (double)"
            };
        }

        @Override
        public String[] run(String[] textFields, Connection conn) throws Exception {
            AdminService adminService = new AdminService(conn);
            long newId = adminService.addEmployee(
                    textFields[0],
                    textFields[1],
                    textFields[2],
                    Integer.parseInt(textFields[3]),
                    Integer.parseInt(textFields[4]),
                    Integer.parseInt(textFields[5]),
                    Integer.parseInt(textFields[6]),
                    Integer.parseInt(textFields[7]),
                    Integer.parseInt(textFields[8]),
                    Double.parseDouble(textFields[9])
            );

            String[] ret = new String[]{
                    ListAction.getMessage("Added an Employee with:", textFields, 10),
                    "New Employee Id = " + newId
            };

            return ret;
        }
    }

    public class AddAuditorium implements ListAction {
        @Override
        public String getActionName() {
            return "Add an auditorium";
        }

        @Override
        public String[] getLabelNames() {
            return new String[]{
                    "Number of seats (int)"
            };
        }

        @Override
        public String[] run(String[] textFields, Connection conn) throws Exception {
            AdminService adminService = new AdminService(conn);
            long newId = adminService.addAuditorium(
                    Integer.parseInt(textFields[0])
            );

            String[] ret = new String[] {
                    ListAction.getMessage("Added an Auditorium with:", textFields, 1),
                    "New Auditorium Id = " + newId
            };

            return ret;
        }
    }

    public class AddScreeningToAuditorium implements ListAction {
        @Override
        public String getActionName() {
            return "Add a Screening to an Auditorium";
        }

        @Override
        public String[] getLabelNames() {
            return new String[]{
                    "Auditorium Id (int)",
                    "Movie Id (int)",
                    "Ticket Price (double)",
                    "Year (int)",
                    "Month (int)",
                    "Day (int)",
                    "Hour (int)",
                    "Technician Id (int)",
            };
        }

        @Override
        public String[] run(String[] textFields, Connection conn) throws Exception {
            AdminService adminService = new AdminService(conn);
            long newId = adminService.addScreeningToAuditorium(
                    Integer.parseInt(textFields[0]),
                    Integer.parseInt(textFields[1]),
                    Double.parseDouble(textFields[2]),
                    Integer.parseInt(textFields[3]),
                    Integer.parseInt(textFields[4]),
                    Integer.parseInt(textFields[5]),
                    Integer.parseInt(textFields[6]),
                    Integer.parseInt(textFields[7])
            );

            String[] ret = new String[] {
                    ListAction.getMessage("Added a Screening to an Auditorium with:", textFields, 8),
                    "New Screening Id = " + newId
            };

            return ret;
        }
    }

    public class AddUsherToScreening implements ListAction {
        @Override
        public String getActionName() {
            return "Add an Usher To a Screening";
        }

        @Override
        public String[] getLabelNames() {
            return new String[]{
                    "Screening Id (int)",
                    "Employee Id (int)"
            };
        }

        @Override
        public String[] run(String[] textFields, Connection conn) throws Exception {
            AdminService adminService = new AdminService(conn);
            adminService.addUsherToScreening(
                    Integer.parseInt(textFields[0]),
                    Integer.parseInt(textFields[1])
            );

            String[] ret = new String[] {
                    ListAction.getMessage("Added an Usher to a Screening with:", textFields, 2),
                    "OK"
            };

            return ret;
        }
    }


    public class AddClient implements ListAction {
        @Override
        public String getActionName() {
            return "Add a Client";
        }

        @Override
        public String[] getLabelNames() {
            return new String[] {
                    "First Name (String)",
                    "Last Name (String)",
                    "Email (String)",
                    "Birth Year (int)",
                    "Birth Month (int)",
                    "Birth Day (int)"
            };
        }

        @Override
        public String[] run(String[] textFields, Connection conn) throws Exception {
            AdminService adminService = new AdminService(conn);
            long newId = adminService.addClient(
                    textFields[0],
                    textFields[1],
                    textFields[2],
                    Integer.parseInt(textFields[3]),
                    Integer.parseInt(textFields[4]),
                    Integer.parseInt(textFields[5])
            );

            String[] ret = new String[] {
                    ListAction.getMessage("Added a Client with:", textFields, 6),
                    "New Client Id = " + newId
            };

            return ret;
        }
    }


    public class AddFundsToClient implements ListAction {
        @Override
        public String getActionName() {
            return "Add Funds to a Client";
        }

        @Override
        public String[] getLabelNames() {
            return new String[] {
                    "Client id (int)",
                    "Amount (double)"
            };
        }

        @Override
        public String[] run(String[] textFields, Connection conn) throws Exception {
            AdminService adminService = new AdminService(conn);
            int clientId = Integer.parseInt(textFields[0]);
            adminService.addFundsToClient(
                    clientId,
                    Double.parseDouble(textFields[1])
            );

//            ClientService clientService = new ClientService(conn, clientId);
//            long funds = clientService.f

            String[] ret = new String[] {
                    ListAction.getMessage("Added Funds to a Client with:", textFields, 2),
                    "OK"
            };

            return ret;
        }
    }


    public class PurchaseTicketForClient implements ListAction {
        @Override
        public String getActionName() {
            return "Purchase a Ticket for a Client";
        }

        @Override
        public String[] getLabelNames() {
            return new String[] {
                    "Client id (int)",
                    "Year (int)",
                    "Month (int)",
                    "Day (int)",
                    "Screening Id (int)",
                    "Seat Number (int)"
            };
        }

        @Override
        public String[] run(String[] textFields, Connection conn) throws Exception {
            AdminService adminService = new AdminService(conn);
            long newId = adminService.purchaseTicketForClient(
                    Integer.parseInt(textFields[0]),
                    Integer.parseInt(textFields[1]),
                    Integer.parseInt(textFields[2]),
                    Integer.parseInt(textFields[3]),
                    Integer.parseInt(textFields[4]),
                    Integer.parseInt(textFields[5])
            );

            String[] ret = new String[] {
                    ListAction.getMessage("Purchased a Ticket for a Client with:", textFields, 6),
                    "New TicketPurchase id = " + newId
            };

            return ret;
        }
    }


    public class PurchaseFoodForClient implements ListAction {
        @Override
        public String getActionName() {
            return "Purchase a Food item for a Client";
        }

        @Override
        public String[] getLabelNames() {
            return new String[] {
                    "Client id (int)",
                    "Food Id (int)",
                    "Year (int)",
                    "Month (int)",
                    "Day (int)"
            };
        }

        @Override
        public String[] run(String[] textFields, Connection conn) throws Exception {
            AdminService adminService = new AdminService(conn);
            long newId = adminService.purchaseFoodForClient(
                    Integer.parseInt(textFields[0]),
                    Integer.parseInt(textFields[1]),
                    Integer.parseInt(textFields[2]),
                    Integer.parseInt(textFields[3]),
                    Integer.parseInt(textFields[4])
            );

            String[] ret = new String[] {
                    ListAction.getMessage("Purchased a Food Item for a Client with:", textFields, 5),
                    "New FoodPurchase id = " + newId
            };

            return ret;
        }
    }


    public class GetPersonsAtScreening implements ListAction {
        @Override
        public String getActionName() {
            return "Get Persons at Screening";
        }

        @Override
        public String[] getLabelNames() {
            return new String[] {
                    "Screening id (int)"
            };
        }

        @Override
        public String[] run(String[] textFields, Connection conn) throws Exception {
            AdminService adminService = new AdminService(conn);
            List<Person> personList = adminService.getPersonsAtScreening(Integer.parseInt(textFields[0]));

            String operationResult = "";
            for (Person person : personList) {
                operationResult += "Person id: " + person.getId() + "; Person name: " + person.getName() + "\n";
            }

            String[] ret = new String[] {
                    ListAction.getMessage("Got Persons at Screening with:", textFields, 1),
                    operationResult
            };

            return ret;
        }
    }

    public class GetScreeningsForEmployee implements ListAction {
        @Override
        public String getActionName() {
            return "Get Screenings for Employee";
        }

        @Override
        public String[] getLabelNames() {
            return new String[]{
                    "Employee id (int)"
            };
        }

        @Override
        public String[] run(String[] textFields, Connection conn) throws Exception {
            AdminService adminService = new AdminService(conn);
            List<Long> list = adminService.getScreeningsForEmployee(
                    Integer.parseInt(textFields[0])
            );

            String operationResult = "This employee was at the screenings with the following ids: ";
            for (Long id : list) {
                operationResult += id + " ";
            }

            String[] ret = new String[]{
                    ListAction.getMessage("Got Screenings for Employee with:", textFields, 1),
                    operationResult
            };

            return ret;
        }
    }








    // Client Tab

    public class GetClientInfo implements ListAction {
        @Override
        public String getActionName() {
            return "Get Client Info";
        }

        @Override
        public String[] getLabelNames() {
            return new String[]{
                    "Client id (int)"
            };
        }

        @Override
        public String[] run(String[] textFields, Connection conn) throws Exception {
            int clientId = Integer.parseInt(textFields[0]);
            GetterService getterService = new GetterService(conn);
            Client client = getterService.getClient(clientId);

            String operationResult = "";
            operationResult += client.toString() + "\n";

            String[] ret = new String[]{
                    ListAction.getMessage("Got client info with:", textFields, 1),
                    operationResult
            };

            return ret;
        }
    }


    public class GetTotalSpentForClient implements ListAction {
        @Override
        public String getActionName() {
            return "Get Total Spent for Client";
        }

        @Override
        public String[] getLabelNames() {
            return new String[]{
                    "Client id (int)"
            };
        }

        @Override
        public String[] run(String[] textFields, Connection conn) throws Exception {
            int clientId = Integer.parseInt(textFields[0]);
            ClientService clientService = new ClientService(conn, clientId);
            double totalSpent = clientService.getTotalSpent();

            String operationResult = "The client with id " + clientId + "spent a total of " + totalSpent;

            String[] ret = new String[]{
                    ListAction.getMessage("Got Total Spent for Client with:", textFields, 1),
                    operationResult
            };

            return ret;
        }
    }


    public class GetWatchedMoviesForClient implements ListAction {
        @Override
        public String getActionName() {
            return "Get Watched Movies for Client";
        }

        @Override
        public String[] getLabelNames() {
            return new String[]{
                    "Client id (int)"
            };
        }

        @Override
        public String[] run(String[] textFields, Connection conn) throws Exception {
            int clientId = Integer.parseInt(textFields[0]);
            ClientService clientService = new ClientService(conn, clientId);

            String operationResult = "";

            List<Movie> list = clientService.getWatchedMovies();
            operationResult += "The client with id " + clientId + " watched:\n";
            for (Movie movie : list) {
                operationResult += movie.getName() + "\n";
            }

            String[] ret = new String[]{
                    ListAction.getMessage("Got Watched Movies for Client with:", textFields, 1),
                    operationResult
            };

            return ret;
        }
    }


    public class GetScreeningsForClient implements ListAction {
        @Override
        public String getActionName() {
            return "Get Screenings for Client";
        }

        @Override
        public String[] getLabelNames() {
            return new String[]{
                    "Client id (int)"
            };
        }

        @Override
        public String[] run(String[] textFields, Connection conn) throws Exception {
            int clientId = Integer.parseInt(textFields[0]);
            ClientService clientService = new ClientService(conn, clientId);

            String operationResult = "";

            List<Screening> list = clientService.getScreenings();
            operationResult += "The client with id " + clientId + " was at the screenings with the following ids:\n";
            for (Screening screening : list) {
                operationResult += screening.getId() + ", ";
            }

            String[] ret = new String[]{
                    ListAction.getMessage("Got Screenings for Client with:", textFields, 1),
                    operationResult
            };

            return ret;
        }
    }


    public class ClientIsOldEnoughForMovie implements ListAction {
        @Override
        public String getActionName() {
            return "Client is Old enough for Movie";
        }

        @Override
        public String[] getLabelNames() {
            return new String[]{
                    "Client id (int)",
                    "Movie id (int)"
            };
        }

        @Override
        public String[] run(String[] textFields, Connection conn) throws Exception {
            int clientId = Integer.parseInt(textFields[0]);
            int movieId = Integer.parseInt(textFields[1]);
            ClientService clientService = new ClientService(conn, clientId);

            String operationResult = "";

            boolean result = clientService.isOldEnoughFor(movieId);
            operationResult += "Result of isClientOldEnoughForMovie: " + result + "\n";

            String[] ret = new String[]{
                    ListAction.getMessage("Client is Old enough for Movie with:", textFields, 2),
                    operationResult
            };

            return ret;
        }
    }


    public class GetPurchasesForClient implements ListAction {
        @Override
        public String getActionName() {
            return "Get Purchases for Client";
        }

        @Override
        public String[] getLabelNames() {
            return new String[]{
                    "Client id (int)"
            };
        }

        @Override
        public String[] run(String[] textFields, Connection conn) throws Exception {
            int clientId = Integer.parseInt(textFields[0]);
            ClientService clientService = new ClientService(conn, clientId);

            String operationResult = "";
            final String foodPurchaseStart = "FoodPurchase:          ";
            final String tickPurchaseStart = "TicketPurchase:        ";
            operationResult += foodPurchaseStart + String.join(DatabaseConstants.SEPARATOR, DatabaseConstants.FOOD_PURCHASE_HEADER) + "\n";
            operationResult += tickPurchaseStart + String.join(DatabaseConstants.SEPARATOR, DatabaseConstants.TICKET_PURCHASE_HEADER) + "\n\n\n";

            List<Purchase> list = clientService.getPurchases();
            operationResult += "The client with id " + clientId + " has the following purchases:\n";
            for (Purchase purchase : list) {
                if (purchase instanceof FoodPurchase) {
                    operationResult += foodPurchaseStart;
                }
                else {
                    operationResult += tickPurchaseStart;
                }
                operationResult += purchase.toString() + "\n";
            }

            String[] ret = new String[]{
                    ListAction.getMessage("Got Purchases for Client with:", textFields, 1),
                    operationResult
            };

            return ret;
        }
    }


    public class RefundLastPurchase implements ListAction {
        @Override
        public String getActionName() {
            return "Refund last Purchase";
        }

        @Override
        public String[] getLabelNames() {
            return new String[]{
                    "Client id (int)"
            };
        }

        @Override
        public String[] run(String[] textFields, Connection conn) throws Exception {
            int clientId = Integer.parseInt(textFields[0]);
            ClientService clientService = new ClientService(conn, clientId);
            clientService.refundLastPurchase();

            String[] ret = new String[]{
                    ListAction.getMessage("Refunded last Purchase with:", textFields, 1),
                    "OK"
            };

            return ret;
        }
    }








    // Info Tab


    private static String getTable(String[] header, List<?> objects) {
        StringBuilder ret = new StringBuilder();
        ret.append( String.join(DatabaseConstants.SEPARATOR, header) );
        ret.append( '\n' );

        for (Object object : objects) {
            ret.append( object.toString() );
            ret.append( '\n' );
        }

        return ret.toString();
    }



    public class GetAuditoriums implements ListAction {
        @Override
        public String getActionName() {
            return "Get Auditoriums";
        }

        @Override
        public String[] getLabelNames() {
            return new String[]{
            };
        }

        @Override
        public String[] run(String[] textFields, Connection conn) throws Exception {
            GetterService getterService = new GetterService(conn);

            String operationResult = ListAction.getTable(
                    DatabaseConstants.AUDITORIUM_HEADER,
                    getterService.getAllAuditorium()
            );

            String[] ret = new String[]{
                    ListAction.getMessage("Got all Auditoriums with:", textFields, 0),
                    operationResult
            };

            return ret;
        }
    }

    public class GetCategories implements ListAction {
        @Override
        public String getActionName() {
            return "Get Categories";
        }

        @Override
        public String[] getLabelNames() {
            return new String[]{
            };
        }

        @Override
        public String[] run(String[] textFields, Connection conn) throws Exception {
            GetterService getterService = new GetterService(conn);

            String operationResult = ListAction.getTable(
                    DatabaseConstants.CATEGORY_HEADER,
                    getterService.getAllCategory()
            );

            String[] ret = new String[]{
                    ListAction.getMessage("Got all Categories with:", textFields, 0),
                    operationResult
            };

            return ret;
        }
    }

    public class GetFoods implements ListAction {
        @Override
        public String getActionName() {
            return "Get Foods";
        }

        @Override
        public String[] getLabelNames() {
            return new String[]{
            };
        }

        @Override
        public String[] run(String[] textFields, Connection conn) throws Exception {
            GetterService getterService = new GetterService(conn);

            String operationResult = ListAction.getTable(
                    DatabaseConstants.FOOD_HEADER,
                    getterService.getAllFood()
            );

            String[] ret = new String[]{
                    ListAction.getMessage("Got all Foods with:", textFields, 0),
                    operationResult
            };

            return ret;
        }
    }

    public class GetMovies implements ListAction {
        @Override
        public String getActionName() {
            return "Get Movies";
        }

        @Override
        public String[] getLabelNames() {
            return new String[]{
            };
        }

        @Override
        public String[] run(String[] textFields, Connection conn) throws Exception {
            GetterService getterService = new GetterService(conn);

            String operationResult = ListAction.getTable(
                    DatabaseConstants.MOVIE_HEADER,
                    getterService.getAllMovie()
            );

            String[] ret = new String[]{
                    ListAction.getMessage("Got all Movies with:", textFields, 0),
                    operationResult
            };

            return ret;
        }
    }

    public class GetClients implements ListAction {
        @Override
        public String getActionName() {
            return "Get Clients";
        }

        @Override
        public String[] getLabelNames() {
            return new String[]{
            };
        }

        @Override
        public String[] run(String[] textFields, Connection conn) throws Exception {
            GetterService getterService = new GetterService(conn);

            String operationResult = ListAction.getTable(
                    DatabaseConstants.CLIENT_HEADER,
                    getterService.getAllClient()
            );

            String[] ret = new String[]{
                    ListAction.getMessage("Got all Clients with:", textFields, 0),
                    operationResult
            };

            return ret;
        }
    }


    public class GetEmployees implements ListAction {
        @Override
        public String getActionName() {
            return "Get Employee";
        }

        @Override
        public String[] getLabelNames() {
            return new String[]{
            };
        }

        @Override
        public String[] run(String[] textFields, Connection conn) throws Exception {
            GetterService getterService = new GetterService(conn);

            String operationResult = ListAction.getTable(
                    DatabaseConstants.EMPLOYEE_HEADER,
                    getterService.getAllEmployee()
            );

            String[] ret = new String[]{
                    ListAction.getMessage("Got all Employees with:", textFields, 0),
                    operationResult
            };

            return ret;
        }
    }


    public class GetFoodPurchases implements ListAction {
        @Override
        public String getActionName() {
            return "Get Food Purchases";
        }

        @Override
        public String[] getLabelNames() {
            return new String[]{
            };
        }

        @Override
        public String[] run(String[] textFields, Connection conn) throws Exception {
            GetterService getterService = new GetterService(conn);

            String operationResult = ListAction.getTable(
                    DatabaseConstants.FOOD_PURCHASE_HEADER,
                    getterService.getAllFoodPurchase()
            );

            String[] ret = new String[]{
                    ListAction.getMessage("Got all Food Purchases with:", textFields, 0),
                    operationResult
            };

            return ret;
        }
    }


    public class GetTicketPurchases implements ListAction {
        @Override
        public String getActionName() {
            return "Get Ticket Purchases";
        }

        @Override
        public String[] getLabelNames() {
            return new String[]{
            };
        }

        @Override
        public String[] run(String[] textFields, Connection conn) throws Exception {
            GetterService getterService = new GetterService(conn);

            String operationResult = ListAction.getTable(
                    DatabaseConstants.TICKET_PURCHASE_HEADER,
                    getterService.getAllTicketPurchase()
            );

            String[] ret = new String[]{
                    ListAction.getMessage("Got all Ticket Purchases with:", textFields, 0),
                    operationResult
            };

            return ret;
        }
    }


    public class GetScreenings implements ListAction {
        @Override
        public String getActionName() {
            return "Get Screenings";
        }

        @Override
        public String[] getLabelNames() {
            return new String[]{
            };
        }

        @Override
        public String[] run(String[] textFields, Connection conn) throws Exception {
            GetterService getterService = new GetterService(conn);

            String operationResult = ListAction.getTable(
                    DatabaseConstants.SCREENING_HEADER,
                    getterService.getAllScreening()
            );

            String[] ret = new String[]{
                    ListAction.getMessage("Got all Screenings with:", textFields, 0),
                    operationResult
            };

            return ret;
        }
    }

    public class GetMovieCategory implements ListAction {
        @Override
        public String getActionName() {
            return "Get Movie_Category";
        }

        @Override
        public String[] getLabelNames() {
            return new String[]{
            };
        }

        @Override
        public String[] run(String[] textFields, Connection conn) throws Exception {
            GetterService getterService = new GetterService(conn);

            String operationResult = ListAction.getTable(
                    DatabaseConstants.MOVIE_CATEGORY_HEADER,
                    getterService.getAllMovieCategory()
            );

            String[] ret = new String[]{
                    ListAction.getMessage("Got all Movie_Category with:", textFields, 0),
                    operationResult
            };

            return ret;
        }
    }

    public class GetScreeningEmployee implements ListAction {
        @Override
        public String getActionName() {
            return "Get Screening_Employee";
        }

        @Override
        public String[] getLabelNames() {
            return new String[]{
            };
        }

        @Override
        public String[] run(String[] textFields, Connection conn) throws Exception {
            GetterService getterService = new GetterService(conn);

            String operationResult = ListAction.getTable(
                    DatabaseConstants.SCREENING_EMPLOYEE_HEADER,
                    getterService.getAllScreeningEmployee()
            );

            String[] ret = new String[]{
                    ListAction.getMessage("Got all Screening_Employee with:", textFields, 0),
                    operationResult
            };

            return ret;
        }
    }



    public class GetMoviesAfterDay implements ListAction {
        @Override
        public String getActionName() {
            return "Get Movies after Day";
        }

        @Override
        public String[] getLabelNames() {
            return new String[]{
                    "Year (int)",
                    "Month (int)",
                    "Day (int)"
            };
        }

        @Override
        public String[] run(String[] textFields, Connection conn) throws Exception {
            InfoService infoService = new InfoService(conn);
            int year = Integer.parseInt(textFields[0]);
            int month  = Integer.parseInt(textFields[1]);
            int day = Integer.parseInt(textFields[2]);

            String operationResult = "";

            List<Movie> list = infoService.getMoviesAfterDay(year, month, day);
            operationResult +=  "Movies after the day: " + day + "/" + month + "/" + year + ":\n";
            for (Movie movie : list) {
                operationResult += movie.getName() + "\n";
            }

            String[] ret = new String[]{
                    ListAction.getMessage("Got Movies after Day with:", textFields, 3),
                    operationResult
            };

            return ret;
        }
    }


    public class GetScreeningsAfterDay implements ListAction {
        @Override
        public String getActionName() {
            return "Get Screenings for Movie after Day";
        }

        @Override
        public String[] getLabelNames() {
            return new String[]{
                    "Year (int)",
                    "Month (int)",
                    "Day (int)",
                    "Movie Id (int)"
            };
        }

        @Override
        public String[] run(String[] textFields, Connection conn) throws Exception {
            InfoService infoService = new InfoService(conn);
            int year = Integer.parseInt(textFields[0]);
            int month  = Integer.parseInt(textFields[1]);
            int day = Integer.parseInt(textFields[2]);
            int movieId = Integer.parseInt(textFields[3]);

            String operationResult = "";

            List<Screening> list = infoService.getScreeningsForMovieAfter(movieId, year, month, day);
            operationResult += "Screenings for the movie with id " + movieId + " after " + day + "/" + month + "/" + year + ":\n";
            for (Screening screening : list) {
                LocalDate d = screening.getStartTime();
                operationResult += "Screening id: " + screening.getId() + "; on " + d.getDayOfMonth() + "/" + d.getMonthValue() + "/" + d.getYear() + "\n";
            }

            String[] ret = new String[]{
                    ListAction.getMessage("Got Screenings for Movie after Day with:", textFields, 4),
                    operationResult
            };

            return ret;
        }
    }


}
