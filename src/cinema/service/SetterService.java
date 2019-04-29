package cinema.service;

import cinema.data.Identifiable;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SetterService {
    public static void update(String pathName, Converter converter, Identifiable updateObject) throws IOException {
        FileReader reader = new FileReader(pathName);
        CSVParser parser = new CSVParser(reader, CSVFormat.DEFAULT);

        List<Identifiable> list = new ArrayList<>();
        boolean matchFound = false;
        for (CSVRecord record : parser) {
            Identifiable currentIdentifiable = converter.convert(record);

            Identifiable nextIdentifiable = currentIdentifiable;
            if (currentIdentifiable.getId() == updateObject.getId()) {
                if (matchFound) {
                    throw new IllegalArgumentException("Two objects with the same id(" + updateObject.getId() + ") found in " + pathName);
                }
                else {
                    matchFound = true;
                    nextIdentifiable = updateObject;
                }
            }

            list.add(nextIdentifiable);
        }
        parser.close();

        if (matchFound == false) {
            list.add(updateObject);
        }

        CSVPrinter printer = new CSVPrinter(new FileWriter(pathName), CSVFormat.DEFAULT);
        for (Identifiable currIdentifiable : list) {
            converter.parse(printer, currIdentifiable);
        }
        printer.close();
    }

    public static void update(cinema.data.Auditorium object) throws IOException {
        SetterService.update(DatabaseConstants.AUDITORIUM_FILE, new Converter.Auditorium(), object);
    }

    public static void update(cinema.data.Category object) throws IOException {
        SetterService.update(DatabaseConstants.CATEGORY_FILE, new Converter.Category(), object);
    }

    public static void update(cinema.data.Client object) throws IOException {
        SetterService.update(DatabaseConstants.CLIENT_FILE, new Converter.Client(), object);
    }

    public static void update(cinema.data.Employee object) throws IOException {
        SetterService.update(DatabaseConstants.EMPLOYEE_FILE, new Converter.Employee(), object);
    }

    public static void update(cinema.data.Food object) throws IOException {
        SetterService.update(DatabaseConstants.FOOD_FILE, new Converter.Food(), object);
    }

    public static void update(cinema.data.FoodPurchase object) throws IOException {
        SetterService.update(DatabaseConstants.FOOD_PURCHASE_FILE, new Converter.FoodPurchase(), object);
    }

    public static void update(cinema.data.Movie object) throws IOException {
        SetterService.update(DatabaseConstants.MOVIE_FILE, new Converter.Movie(), object);
    }

    public static void update(cinema.data.Screening object) throws IOException {
        SetterService.update(DatabaseConstants.SCREENING_FILE, new Converter.Screening(), object);
    }

    public static void update(cinema.data.TicketPurchase object) throws IOException {
        SetterService.update(DatabaseConstants.TICKET_PURCHASE_FILE, new Converter.TicketPurchase(), object);
    }
}
