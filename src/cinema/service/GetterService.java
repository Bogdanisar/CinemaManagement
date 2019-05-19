package cinema.service;

import cinema.data.*;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

public class GetterService {
    private Connection conn;
    public GetterService(Connection conn) {
        this.conn = conn;
    }

    public Identifiable getIdentifiable(String tableName, long targetId, Converter converter) throws IOException, SQLException {
        Identifiable ans = null;

        Statement stm = this.conn.createStatement();
        ResultSet rs = stm.executeQuery("SELECT * FROM " + tableName + " WHERE id = " + targetId + ";");

        while (rs.next()) {
            Identifiable currentIdentifiable = converter.convert(rs);
            if (targetId == currentIdentifiable.getId()) {
                return currentIdentifiable;
            }
        }

        stm.close();
        return ans;
    }

    public Auditorium getAuditorium(long id) throws IOException, SQLException {
        return (Auditorium) this.getIdentifiable(DatabaseConstants.AUDITORIUM_TABLE, id, new Converter.Auditorium());
    }

    public Screening getScreening(long id) throws IOException, SQLException {
        return (Screening) this.getIdentifiable(DatabaseConstants.SCREENING_TABLE, id, new Converter.Screening());
    }

    public Category getCategory(long id) throws IOException, SQLException {
        return (Category) this.getIdentifiable(DatabaseConstants.CATEGORY_TABLE, id, new Converter.Category());
    }

    public Movie getMovie(long id) throws IOException, SQLException {
        return (Movie) this.getIdentifiable(DatabaseConstants.MOVIE_TABLE, id, new Converter.Movie());
    }

    public Food getFood(long id) throws IOException, SQLException {
        return (Food) this.getIdentifiable(DatabaseConstants.FOOD_TABLE, id, new Converter.Food());
    }

    public Client getClient(long id) throws IOException, SQLException {
        return (Client) this.getIdentifiable(DatabaseConstants.CLIENT_TABLE ,id, new Converter.Client());
    }

    public Employee getEmployee(long id) throws IOException, SQLException {
        return (Employee) this.getIdentifiable(DatabaseConstants.EMPLOYEE_TABLE ,id, new Converter.Employee());
    }

    public TicketPurchase getTicketPurchase(long id) throws IOException, SQLException {
        return (TicketPurchase) this.getIdentifiable(DatabaseConstants.TICKET_PURCHASE_TABLE ,id, new Converter.TicketPurchase());
    }

    public FoodPurchase getFoodPurchase(long id) throws IOException, SQLException {
        return (FoodPurchase) this.getIdentifiable(DatabaseConstants.FOOD_PURCHASE_TABLE ,id, new Converter.FoodPurchase());
    }





    public List<Identifiable> getAll(String tableName, Converter converter) throws IOException, SQLException, SQLException {
        Statement stm = this.conn.createStatement();
        ResultSet rs = stm.executeQuery("SELECT * FROM " + tableName + ";");

        List<Identifiable> ret = new ArrayList<>();
        while (rs.next()) {
            Identifiable currentIdentifiable = converter.convert(rs);
            ret.add(currentIdentifiable);
        }

        stm.close();
        return ret;
    }


    public List<cinema.data.AssociativeEntry> getAllScreeningEmployee() throws IOException, SQLException {
        return Converter.cast(
                this.getAll(DatabaseConstants.SCREENING_EMPLOYEE_TABLE, new Converter.AssociativeEntry())
        );
    }
    public List<cinema.data.AssociativeEntry> getAllMovieCategory() throws IOException, SQLException {
        return Converter.cast(
                this.getAll(DatabaseConstants.MOVIE_CATEGORY_TABLE, new Converter.AssociativeEntry())
        );
    }

    public List<cinema.data.Auditorium> getAllAuditorium() throws IOException, SQLException {
        return Converter.cast(
                this.getAll(DatabaseConstants.AUDITORIUM_TABLE, new Converter.Auditorium())
        );
    }

    public List<cinema.data.Category> getAllCategory() throws IOException, SQLException {
        return Converter.cast(
                this.getAll(DatabaseConstants.CATEGORY_TABLE, new Converter.Category())
        );
    }

    public List<cinema.data.Client> getAllClient() throws IOException, SQLException {
        return Converter.cast(
                this.getAll(DatabaseConstants.CLIENT_TABLE, new Converter.Client())
        );
    }

    public List<cinema.data.Employee> getAllEmployee() throws IOException, SQLException {
        return Converter.cast(
                this.getAll(DatabaseConstants.EMPLOYEE_TABLE, new Converter.Employee())
        );
    }

    public List<cinema.data.Food> getAllFood() throws IOException, SQLException {
        return Converter.cast(
                this.getAll(DatabaseConstants.FOOD_TABLE, new Converter.Food())
        );
    }

    public List<cinema.data.FoodPurchase> getAllFoodPurchase() throws IOException, SQLException {
        return Converter.cast(
                this.getAll(DatabaseConstants.FOOD_PURCHASE_TABLE, new Converter.FoodPurchase())
        );
    }

    public List<cinema.data.Movie> getAllMovie() throws IOException, SQLException {
        return Converter.cast(
                this.getAll(DatabaseConstants.MOVIE_TABLE, new Converter.Movie())
        );
    }

    public List<cinema.data.Screening> getAllScreening() throws IOException, SQLException {
        return Converter.cast(
                this.getAll(DatabaseConstants.SCREENING_TABLE, new Converter.Screening())
        );
    }

    public List<cinema.data.TicketPurchase> getAllTicketPurchase() throws IOException, SQLException {
        return Converter.cast(
                this.getAll(DatabaseConstants.TICKET_PURCHASE_TABLE, new Converter.TicketPurchase())
        );
    }
}
