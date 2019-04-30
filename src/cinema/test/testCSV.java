package cinema.test;

import cinema.data.Category;
import cinema.service.GetterService;
import cinema.service.SetterService;
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

public class testCSV {
    public static void main(String[] args) throws IOException {
        String path = "test.csv";

//        FileWriter fileWriter = new FileWriter(path);
//        CSVPrinter printer = new CSVPrinter(fileWriter, CSVFormat.DEFAULT);
//
//        printer.printRecord(2, "te,\"s", new Integer[]{1,2,3}, 3.14);
//        printer.printRecord();
//        printer.println();
//        printer.printRecord(1, 2, 3, 4);
//
//        printer.close();
//
//
//
//
//        FileReader fileReader = new FileReader(path);
//        CSVParser parser = new CSVParser(fileReader, CSVFormat.DEFAULT.withHeader());
//
//        System.out.println("HeaderMap:");
//        System.out.println(parser.getHeaderMap());
//        System.out.println();
//        System.out.println();
//
//        for (CSVRecord record : parser) {
//            String all = "";
//            for (String column : record) {
//                all += column + ",";
//            }
//
//            System.out.println(all);
//            System.out.println(record.toString());
//            System.out.println(record.toMap());
//            System.out.println();
//        }
//        parser.close();


//        Category c1 = new Category(-1L, "Action", 12);
//        Category c2 = new Category(3L, "Fictionless", 120);
//        SetterService.update(c1);
//        SetterService.update(c2);

//        Category c3 = GetterService.getCategory(3);
//        System.out.println(c3.getName());
//        System.out.println(c3.getMinimumAge());
    }
}
