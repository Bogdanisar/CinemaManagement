package cinema.test;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class testCSV {
    public static void main(String[] args) throws IOException {
        String path = "test.csv";
        FileReader fileReader = new FileReader(path);
//        CSVParser parser = new CSVParser(fileReader, CSVFormat.DEFAULT.withIgnoreHeaderCase());
//        CSVParser parser = new CSVParser(fileReader, CSVFormat.DEFAULT);
//        CSVParser parser = new CSVParser(fileReader, CSVFormat.DEFAULT.withIgnoreHeaderCase(false));
//        CSVParser parser = new CSVParser(fileReader, CSVFormat.DEFAULT.withIgnoreHeaderCase(true));
        CSVParser parser = new CSVParser(fileReader, CSVFormat.EXCEL);

        System.out.println("HeaderMap:");
        System.out.println(parser.getHeaderMap());
        System.out.println();
        System.out.println();

        for (CSVRecord record : parser) {
            String all = "";
            for (String column : record) {
                all += column + ",";
            }

            System.out.println(all);
            System.out.println(record.toString());
            System.out.println(record.toMap());
            System.out.println();
        }
    }
}
