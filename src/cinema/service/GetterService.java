package cinema.service;

import cinema.data.*;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class GetterService {
    public static Identifiable getIdentifiable(String pathName, long targetId, Converter converter) {
        FileReader reader = null;
        CSVParser parser = null;
        Identifiable ans = null;

        try {
            reader = new FileReader(pathName);
            parser = new CSVParser(reader, CSVFormat.DEFAULT);

            for (CSVRecord record : parser) {
                Identifiable currentIdentifiable = converter.convert(record);
                if (targetId == currentIdentifiable.getId()) {
                    return currentIdentifiable;
                }
            }
        }
        catch (Exception except) {
            except.printStackTrace();
            System.out.println();
        }

        return ans;
    }

    public static Auditorium getAuditorium(long id) {
        return (Auditorium) GetterService.getIdentifiable(DatabaseConstants.AUDITORIUM_FILE, id, new Converter.Auditorium());
    }

    public static Screening getScreening(long id) {
        return (Screening) GetterService.getIdentifiable(DatabaseConstants.SCREENING_FILE ,id, new Converter.Screening());
    }

    public static Category getCategory(long id) {
        return (Category) GetterService.getIdentifiable(DatabaseConstants.CATEGORY_FILE ,id, new Converter.Category());
    }

    public static Movie getMovie(long id) {
        return (Movie) GetterService.getIdentifiable(DatabaseConstants.MOVIE_FILE ,id, new Converter.Movie());
    }

    public static Food getFood(long id) {
        return (Food) GetterService.getIdentifiable(DatabaseConstants.FOOD_FILE ,id, new Converter.Food());
    }

    public static Client getClient(long id) {
        return (Client) GetterService.getIdentifiable(DatabaseConstants.CLIENT_FILE ,id, new Converter.Client());
    }

    public static Employee getEmployee(long id) {
        return (Employee) GetterService.getIdentifiable(DatabaseConstants.EMPLOYEE_FILE ,id, new Converter.Employee());
    }

    public static TicketPurchase getTicketPurchase(long id) {
        return (TicketPurchase) GetterService.getIdentifiable(DatabaseConstants.TICKET_PURCHASE_FILE ,id, new Converter.TicketPurchase());
    }

    public static FoodPurchase getFoodPurchase(long id) {
        return (FoodPurchase) GetterService.getIdentifiable(DatabaseConstants.FOOD_PURCHASE_FILE ,id, new Converter.FoodPurchase());
    }



    public static List<Identifiable> getAll(String pathName, Converter converter) {
        FileReader reader = null;
        CSVParser parser = null;

        List<Identifiable> ret = new ArrayList<>();
        try {
            reader = new FileReader(pathName);
            parser = new CSVParser(reader, CSVFormat.DEFAULT);

            for (CSVRecord record : parser) {
                Identifiable currentIdentifiable = converter.convert(record);
                ret.add(currentIdentifiable);
            }
        }
        catch (Exception except) {
            except.printStackTrace();
            System.out.println();
        }

        return ret;
    }

    public static List<cinema.data.Auditorium> getAllAuditorium() {
        return Converter.cast(
                GetterService.getAll(DatabaseConstants.AUDITORIUM_FILE, new Converter.Auditorium())
        );
    }

    public static List<cinema.data.Category> getAllCategory() {
        return Converter.cast(
                GetterService.getAll(DatabaseConstants.CATEGORY_FILE, new Converter.Category())
        );
    }

    public static List<cinema.data.Client> getAllClient() {
        return Converter.cast(
                GetterService.getAll(DatabaseConstants.CLIENT_FILE, new Converter.Client())
        );
    }

    public static List<cinema.data.Employee> getAllEmployee() {
        return Converter.cast(
                GetterService.getAll(DatabaseConstants.EMPLOYEE_FILE, new Converter.Employee())
        );
    }

    public static List<cinema.data.Food> getAllFood() {
        return Converter.cast(
                GetterService.getAll(DatabaseConstants.FOOD_FILE, new Converter.Food())
        );
    }

    public static List<cinema.data.FoodPurchase> getAllFoodPurchase() {
        return Converter.cast(
                GetterService.getAll(DatabaseConstants.FOOD_PURCHASE_FILE, new Converter.FoodPurchase())
        );
    }

    public static List<cinema.data.Movie> getAllMovie() {
        return Converter.cast(
                GetterService.getAll(DatabaseConstants.MOVIE_FILE, new Converter.Movie())
        );
    }

    public static List<cinema.data.Screening> getAllScreening() {
        return Converter.cast(
                GetterService.getAll(DatabaseConstants.SCREENING_FILE, new Converter.Screening())
        );
    }

    public static List<cinema.data.TicketPurchase> getAllTicketPurchase() {
        return Converter.cast(
                GetterService.getAll(DatabaseConstants.TICKET_PURCHASE_FILE, new Converter.TicketPurchase())
        );
    }
}
