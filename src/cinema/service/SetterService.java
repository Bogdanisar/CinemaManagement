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
    public static long update(String pathName, Converter converter, Identifiable updateObject) throws IOException {
        FileReader reader = new FileReader(pathName);
        CSVParser parser = new CSVParser(reader, CSVFormat.DEFAULT);

        List<Identifiable> list = new ArrayList<>();
        boolean matchFound = false;
        for (CSVRecord record : parser) {
            Identifiable currentIdentifiable = converter.convert(record);

            Identifiable nextIdentifiable = currentIdentifiable;
            if (currentIdentifiable.getId() == updateObject.getId()) {
                if (matchFound) {
                    parser.close();
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

        long retId = -1;
        if (matchFound == false) {
            if (updateObject.getId() != -1) {
                throw new IllegalArgumentException("Object with id: " + updateObject.getId() + " not found");
            }

            updateObject.setId(list.size() + 1);
            retId = updateObject.getId();
            list.add(updateObject);
        }

        CSVPrinter printer = new CSVPrinter(new FileWriter(pathName), CSVFormat.DEFAULT);
        for (Identifiable currIdentifiable : list) {
            converter.parse(printer, currIdentifiable);
        }
        printer.close();
        
        return retId;
    }


    public static long updateMovieCategory(cinema.data.AssociativeEntry object) throws IOException {
        return SetterService.update(DatabaseConstants.MOVIE_CATEGORY_FILE, new Converter.AssociativeEntry(), object);
    }
    public static long updateScreeningClient(cinema.data.AssociativeEntry object) throws IOException {
        return SetterService.update(DatabaseConstants.SCREENING_CLIENT_FILE, new Converter.AssociativeEntry(), object);
    }
    public static long updateScreeningEmployee(cinema.data.AssociativeEntry object) throws IOException {
        return SetterService.update(DatabaseConstants.SCREENING_EMPLOYEE_FILE, new Converter.AssociativeEntry(), object);
    }

    public static long update(cinema.data.Auditorium object) throws IOException {
        return SetterService.update(DatabaseConstants.AUDITORIUM_FILE, new Converter.Auditorium(), object);
    }

    public static long update(cinema.data.Category object) throws IOException {
        return SetterService.update(DatabaseConstants.CATEGORY_FILE, new Converter.Category(), object);
    }

    public static long update(cinema.data.Client object) throws IOException {
        return SetterService.update(DatabaseConstants.CLIENT_FILE, new Converter.Client(), object);
    }

    public static long update(cinema.data.Employee object) throws IOException {
        return SetterService.update(DatabaseConstants.EMPLOYEE_FILE, new Converter.Employee(), object);
    }

    public static long update(cinema.data.Food object) throws IOException {
        return SetterService.update(DatabaseConstants.FOOD_FILE, new Converter.Food(), object);
    }

    public static long update(cinema.data.FoodPurchase object) throws IOException {
        return SetterService.update(DatabaseConstants.FOOD_PURCHASE_FILE, new Converter.FoodPurchase(), object);
    }

    public static long update(cinema.data.Movie object) throws IOException {
        return SetterService.update(DatabaseConstants.MOVIE_FILE, new Converter.Movie(), object);
    }

    public static long update(cinema.data.Screening object) throws IOException {
        return SetterService.update(DatabaseConstants.SCREENING_FILE, new Converter.Screening(), object);
    }

    public static long update(cinema.data.TicketPurchase object) throws IOException {
        return SetterService.update(DatabaseConstants.TICKET_PURCHASE_FILE, new Converter.TicketPurchase(), object);
    }
}
