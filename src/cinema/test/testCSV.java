package cinema.test;

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

        FileWriter fileWriter = new FileWriter(path);
        CSVPrinter printer = new CSVPrinter(fileWriter, CSVFormat.DEFAULT);

        printer.printRecord(2, "te,\"s", new Integer[]{1,2,3}, 3.14);
        printer.printRecord();
        printer.println();
        printer.printRecord(1, 2, 3, 4);

        printer.close();




        FileReader fileReader = new FileReader(path);
        CSVParser parser = new CSVParser(fileReader, CSVFormat.DEFAULT.withHeader());

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
        parser.close();



//        List<? extends Number> list = new ArrayList<Integer>();

        Number num = Integer.valueOf(300);
        Integer i = testCSV.convert(num);
        System.out.println(i);
    }

    public static <T> T convert(Object obj) {
        return (T) obj;
    }
}
