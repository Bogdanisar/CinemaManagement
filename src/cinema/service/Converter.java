package cinema.service;

import cinema.data.*;

import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public interface Converter {
    // interface method
    public Identifiable convert(ResultSet rs) throws java.sql.SQLException;


    // static constants
    public static final DateTimeFormatter localDateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    public static final DateTimeFormatter localDateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm - dd/MM/yyyy");

    // static helper methods
    public static String localDateToString(LocalDate date) {
        return date.format(Converter.localDateFormatter);
    }

    public static LocalDate stringToLocalDate(String date) {
        return LocalDate.parse(date, Converter.localDateFormatter);
    }

    public static String localDateTimeToString(LocalDateTime date) {
        return date.format(Converter.localDateFormatter);
    }

    public static LocalDateTime stringToLocalDateTime(String date) {
        return LocalDateTime.parse(date, Converter.localDateFormatter);
    }

    // implementing classes
    public static class AssociativeEntry implements Converter {
        @Override
        public cinema.data.AssociativeEntry convert(ResultSet rs) throws SQLException {
            long id = rs.getLong("id");
            long firstId = rs.getLong("first_id");
            long secondId = rs.getLong("second_id");

            return new cinema.data.AssociativeEntry(id, firstId, secondId);
        }
    }

    public static class Auditorium implements Converter {
        @Override
        public cinema.data.Auditorium convert(ResultSet rs) throws SQLException {
            long id = rs.getLong("id");
            int number_of_seats = rs.getInt("number_of_seats");

            return new cinema.data.Auditorium(id, number_of_seats);
        }
    }

    public static class Category implements Converter {
        @Override
        public cinema.data.Category convert(ResultSet rs) throws SQLException {
            long id = rs.getLong("id");
            String name = rs.getString("name");
            int minimumAge = rs.getInt("minimum_age");

            return new cinema.data.Category(id, name, minimumAge);
        }
    }

    public static class Client implements Converter {
        @Override
        public cinema.data.Client convert(ResultSet rs) throws SQLException {
            long id = rs.getLong("id");
            String firstName = rs.getString("first_name");
            String lastName = rs.getString("last_name");
            String email = rs.getString("email");
            LocalDate birthDate = Converter.stringToLocalDate(rs.getString("birth_date"));
            double funds = Double.valueOf(rs.getDouble("funds"));

            cinema.data.Client client = new cinema.data.Client(id, firstName, lastName, email, birthDate);
            client.setFunds(funds);
            return client;
        }
    }

    public static class Employee implements Converter {
        @Override
        public cinema.data.Employee convert(ResultSet rs) throws SQLException {
            long id = rs.getLong("id");
            String firstName = rs.getString("first_name");
            String lastName = rs.getString("last_name");
            String email = rs.getString("email");
            LocalDate birthDate = Converter.stringToLocalDate(rs.getString("birth_date"));
            LocalDate hireDate = Converter.stringToLocalDate(rs.getString("hire_date"));
            double salary = Double.valueOf(rs.getDouble("salary"));

            return new cinema.data.Employee(id, firstName, lastName, email, birthDate, hireDate, salary);
        }
    }

    public static class Food implements Converter {
        @Override
        public cinema.data.Food convert(ResultSet rs) throws SQLException {
            long id = rs.getLong("id");
            String name = rs.getString("name");
            double price = rs.getDouble("price");

            return new cinema.data.Food(id, name, price);
        }
    }


    public static class FoodPurchase implements Converter {
        @Override
        public cinema.data.FoodPurchase convert(ResultSet rs) throws SQLException {
            long purchaseId = rs.getLong("id");
            long clientId = rs.getLong("client_id");
            LocalDate purchaseDate = Converter.stringToLocalDate(rs.getString("purchase_date"));
            long foodProductId = rs.getLong("food_product_id");
            double price = rs.getDouble("price");

            return new cinema.data.FoodPurchase(purchaseId, clientId, purchaseDate, foodProductId, price);
        }
    }


    public static class Movie implements Converter {
        @Override
        public cinema.data.Movie convert(ResultSet rs) throws SQLException {
            long id = rs.getLong("id");
            String name = rs.getString("name");
            int duration = rs.getInt("duration");

            return new cinema.data.Movie(id, name, duration);
        }
    }


    public static class Screening implements Converter {
        @Override
        public cinema.data.Screening convert(ResultSet rs) throws SQLException {
            long id = rs.getLong("id");
            long movieId = rs.getLong("movie_id");
            long auditoriumId = rs.getLong("auditorium_id");
            double price = rs.getDouble("price");
            LocalDate startTime = Converter.stringToLocalDate(rs.getString("start_time"));
            int hour = rs.getInt("hour");
            long technicianId = rs.getLong("technician_id");

            return new cinema.data.Screening(id, movieId, auditoriumId, price, startTime, hour, technicianId);
        }
    }


    public static class TicketPurchase implements Converter {
        @Override
        public cinema.data.TicketPurchase convert(ResultSet rs) throws SQLException {
            long purchaseId = rs.getLong("id");
            long clientId = rs.getLong("client_id");
            LocalDate purchaseDate = Converter.stringToLocalDate(rs.getString("purchase_date"));
            long screeningId = rs.getLong("screening_id");
            int seatNumber = rs.getInt("seat_number");

            return new cinema.data.TicketPurchase(purchaseId, clientId, purchaseDate, screeningId, seatNumber);
        }
    }



    public static <NewType> List<NewType> cast(List<?> list) {
        List<NewType> ret = new ArrayList<>();

        for (Object obj : list) {
            ret.add( (NewType) obj );
        }

        return ret;
    }
}
