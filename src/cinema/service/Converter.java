package cinema.service;

import cinema.data.*;

import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public interface Converter {
    // interface method
    public Identifiable convert(CSVRecord record);
    public void parse(CSVPrinter printer, Identifiable object) throws IOException;


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
        public cinema.data.AssociativeEntry convert(CSVRecord record) {
            long id = Long.parseLong(record.get(0));
            long firstId = Long.parseLong(record.get(1));
            long secondId = Long.parseLong(record.get(1));

            return new cinema.data.AssociativeEntry(id, firstId, secondId);
        }

        @Override
        public void parse(CSVPrinter printer, Identifiable object) throws IOException {
            cinema.data.AssociativeEntry item = (cinema.data.AssociativeEntry) object;
            printer.printRecord(
                    item.getId(),
                    item.getFirstId(),
                    item.getSecondId()
            );
        }
    }

    public static class Auditorium implements Converter {
        @Override
        public cinema.data.Auditorium convert(CSVRecord record) {
            long id = Long.parseLong(record.get(0));
            int number_of_seats = Integer.parseInt(record.get(1));

            return new cinema.data.Auditorium(id, number_of_seats);
        }

        @Override
        public void parse(CSVPrinter printer, Identifiable object) throws IOException {
            cinema.data.Auditorium auditorium = (cinema.data.Auditorium) object;
            printer.printRecord(
                    auditorium.getId(),
                    auditorium.getNumber_of_seats()
            );
        }
    }

    public static class Category implements Converter {
        @Override
        public cinema.data.Category convert(CSVRecord record) {
            long id = Long.parseLong(record.get(0));
            String name = record.get(1);
            int minimumAge = Integer.parseInt(record.get(2));

            return new cinema.data.Category(id, name, minimumAge);
        }

        @Override
        public void parse(CSVPrinter printer, Identifiable object) throws IOException {
            cinema.data.Category category = (cinema.data.Category) object;
            printer.printRecord(
                    category.getId(),
                    category.getName(),
                    category.getMinimumAge()
            );
        }
    }

    public static class Client implements Converter {
        @Override
        public cinema.data.Client convert(CSVRecord record) {
            long id = Long.parseLong(record.get(0));
            String firstName = record.get(1);
            String lastName = record.get(2);
            String email = record.get(3);
            LocalDate birthDate = Converter.stringToLocalDate(record.get(4));

            return new cinema.data.Client(id, firstName, lastName, email, birthDate);
        }

        @Override
        public void parse(CSVPrinter printer, Identifiable object) throws IOException {
            cinema.data.Client here = (cinema.data.Client) object;
            printer.printRecord(
                    here.getId(),
                    here.getFirstName(),
                    here.getLastName(),
                    here.getEmail(),
                    Converter.localDateToString(here.getBirthDate())
            );
        }
    }

    public static class Employee implements Converter {
        @Override
        public cinema.data.Employee convert(CSVRecord record) {
            long id = Long.parseLong(record.get(0));
            String firstName = record.get(1);
            String lastName = record.get(2);
            String email = record.get(3);
            LocalDate birthDate = Converter.stringToLocalDate(record.get(4));
            LocalDate hireDate = Converter.stringToLocalDate(record.get(5));
            double salary = Double.parseDouble(record.get(6));

            return new cinema.data.Employee(id, firstName, lastName, email, birthDate, hireDate, salary);
        }

        @Override
        public void parse(CSVPrinter printer, Identifiable object) throws IOException {
            cinema.data.Employee here = (cinema.data.Employee) object;
            printer.printRecord(
                    here.getId(),
                    here.getFirstName(),
                    here.getLastName(),
                    here.getEmail(),
                    Converter.localDateToString(here.getBirthDate()),
                    Converter.localDateToString(here.getHireDate()),
                    here.getSalary()
            );
        }
    }

    public static class Food implements Converter {
        @Override
        public cinema.data.Food convert(CSVRecord record) {
            long id = Long.parseLong(record.get(0));
            String name = record.get(1);
            double price = Double.valueOf(record.get(1));

            return new cinema.data.Food(id, name, price);
        }

        @Override
        public void parse(CSVPrinter printer, Identifiable object) throws IOException {
            cinema.data.Food food = (cinema.data.Food) object;
            printer.printRecord(
                    food.getId(),
                    food.getName(),
                    food.getPrice()
            );
        }
    }


    public static class FoodPurchase implements Converter {
        @Override
        public cinema.data.FoodPurchase convert(CSVRecord record) {
            long purchaseId = Long.parseLong(record.get(0));
            long clientId = Long.parseLong(record.get(1));
            LocalDate purchaseDate = Converter.stringToLocalDate(record.get(2));
            long foodProductId = Long.parseLong(record.get(3));
            double price = Double.parseDouble(record.get(4));

            return new cinema.data.FoodPurchase(purchaseId, clientId, purchaseDate, foodProductId, price);
        }

        @Override
        public void parse(CSVPrinter printer, Identifiable object) throws IOException {
            cinema.data.FoodPurchase here = (cinema.data.FoodPurchase) object;
            printer.printRecord(
                    here.getId(),
                    here.getClientId(),
                    Converter.localDateToString(here.getPurchaseDate()),
                    here.getFoodProductId(),
                    here.getPrice()
            );
        }
    }


    public static class Movie implements Converter {
        @Override
        public cinema.data.Movie convert(CSVRecord record) {
            long id = Long.parseLong(record.get(0));
            String name = record.get(1);
            int duration = Integer.parseInt(record.get(2));

            return new cinema.data.Movie(id, name, duration);
        }

        @Override
        public void parse(CSVPrinter printer, Identifiable object) throws IOException {
            cinema.data.Movie here = (cinema.data.Movie) object;
            printer.printRecord(
                    here.getId(),
                    here.getName(),
                    here.getDurationInMinutes()
            );
        }
    }


    public static class Screening implements Converter {
        @Override
        public cinema.data.Screening convert(CSVRecord record) {
            long id = Long.parseLong(record.get(0));
            long movieId = Long.parseLong(record.get(1));
            long auditoriumId = Long.parseLong(record.get(2));
            double price = Double.parseDouble(record.get(3));
            LocalDate startTime = Converter.stringToLocalDate(record.get(4));
            int hour = Integer.parseInt(record.get(5));
            long technicianId = Long.parseLong(record.get(6));

            return new cinema.data.Screening(id, movieId, auditoriumId, price, startTime, hour, technicianId);
        }

        @Override
        public void parse(CSVPrinter printer, Identifiable object) throws IOException {
            cinema.data.Screening here = (cinema.data.Screening) object;
            printer.printRecord(
                    here.getId(),
                    here.getMovieId(),
                    here.getAuditoriumId(),
                    here.getPrice(),
                    Converter.localDateToString(here.getStartTime()),
                    here.getHour(),
                    here.getTechnicianId()
            );
        }
    }


    public static class TicketPurchase implements Converter {
        @Override
        public cinema.data.TicketPurchase convert(CSVRecord record) {
            long purchaseId = Long.parseLong(record.get(0));
            long clientId = Long.parseLong(record.get(1));
            LocalDate purchaseDate = Converter.stringToLocalDate(record.get(2));
            long screeningId = Long.parseLong(record.get(3));
            int seatNumber = Integer.parseInt(record.get(4));

            return new cinema.data.TicketPurchase(purchaseId, clientId, purchaseDate, screeningId, seatNumber);
        }

        @Override
        public void parse(CSVPrinter printer, Identifiable object) throws IOException {
            cinema.data.TicketPurchase here = (cinema.data.TicketPurchase) object;
            printer.printRecord(
                    here.getId(),
                    here.getClientId(),
                    Converter.localDateToString(here.getPurchaseDate()),
                    here.getScreeningId(),
                    here.getSeatNumber()
            );
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
