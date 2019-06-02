package cinema.graphics;

import cinema.data.Person;
import cinema.service.AdminService;
import cinema.service.ClientService;

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
}
