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
import java.sql.*;

public class SetterService {
    private Connection conn;
    public SetterService(Connection conn) {
        this.conn = conn;
    }

    public PreparedStatement getPreparedStatement(long id, String tableName, String[] columnNames) throws SQLException {
        String stmt = "";

        if (id == -1) {
            stmt = "INSERT INTO " + tableName + " ";
            stmt += "(" + String.join(", ", columnNames) + ") ";

            String questionMarks = new String(new char[columnNames.length]).replace("\0", "?, ");
            questionMarks = questionMarks.substring(0, questionMarks.length() - 2);

            stmt += "VALUES (" + questionMarks + ");";
        }
        else {
            stmt = "UPDATE " + tableName + " SET ";
            stmt += String.join(" = ?, ", columnNames) + " = ?; ";
            stmt += "WHERE id = " + id;
        }

        return conn.prepareStatement(stmt);
    }

    public long updateMovieCategory(cinema.data.AssociativeEntry object) throws IOException, SQLException {
        long id = object.getId();
        final String tableName = DatabaseConstants.MOVIE_CATEGORY_TABLE;
        final String[] columnNames = new String[] {
                "first_id",
                "second_id"
        };

        PreparedStatement pstmt = getPreparedStatement(id, tableName, columnNames);
        pstmt.setInt(1, (int)object.getFirstId());
        pstmt.setInt(2, (int)object.getSecondId());

        pstmt.execute();
        return pstmt.getGeneratedKeys().getLong("id");
    }
    public long updateScreeningEmployee(cinema.data.AssociativeEntry object) throws IOException, SQLException {
        long id = object.getId();
        final String tableName = DatabaseConstants.SCREENING_EMPLOYEE_TABLE;
        final String[] columnNames = new String[] {
                "first_id",
                "second_id"
        };

        PreparedStatement pstmt = getPreparedStatement(id, tableName, columnNames);
        pstmt.setInt(1, (int)object.getFirstId());
        pstmt.setInt(2, (int)object.getSecondId());

        pstmt.execute();
        return pstmt.getGeneratedKeys().getLong("id");
    }

    public long update(cinema.data.Auditorium object) throws IOException, SQLException {
        long id = object.getId();
        final String tableName = DatabaseConstants.AUDITORIUM_TABLE;
        final String[] columnNames = new String[] {
                "number_of_seats"
        };

        PreparedStatement pstmt = getPreparedStatement(id, tableName, columnNames);
        pstmt.setInt(1, (int)object.getNumber_of_seats());

        pstmt.execute();
        return pstmt.getGeneratedKeys().getLong("id");
    }

    public long update(cinema.data.Category object) throws IOException, SQLException {
        long id = object.getId();
        final String tableName = DatabaseConstants.CATEGORY_TABLE;
        final String[] columnNames = new String[] {
                "name",
                "minimum_age"
        };

        PreparedStatement pstmt = getPreparedStatement(id, tableName, columnNames);
        pstmt.setString(1, object.getName());
        pstmt.setInt(2, object.getMinimumAge());

        pstmt.execute();
        return pstmt.getGeneratedKeys().getLong("id");
    }

    public long update(cinema.data.Client object) throws IOException, SQLException {
        long id = object.getId();
        final String tableName = DatabaseConstants.CLIENT_TABLE;
        final String[] columnNames = new String[] {
                "first_name",
                "last_name",
                "email",
                "birth_date",
                "funds"
        };

        PreparedStatement pstmt = getPreparedStatement(id, tableName, columnNames);
        pstmt.setString( 1, object.getFirstName() );
        pstmt.setString( 2, object.getLastName() );
        pstmt.setString( 3, object.getEmail() );
        pstmt.setString( 4, Converter.localDateToString(object.getBirthDate()) );
        pstmt.setDouble( 5, object.getFunds() );

        pstmt.execute();
        return pstmt.getGeneratedKeys().getLong("id");
    }

    public long update(cinema.data.Employee object) throws IOException, SQLException {
        long id = object.getId();
        final String tableName = DatabaseConstants.EMPLOYEE_TABLE;
        final String[] columnNames = new String[] {
                "first_name",
                "last_name",
                "email",
                "birth_date",
                "hire_date",
                "salary"
        };

        PreparedStatement pstmt = getPreparedStatement(id, tableName, columnNames);
        pstmt.setString( 1, object.getFirstName() );
        pstmt.setString( 2, object.getLastName() );
        pstmt.setString( 3, object.getEmail() );
        pstmt.setString( 4, Converter.localDateToString(object.getBirthDate()) );
        pstmt.setString( 5, Converter.localDateToString(object.getHireDate()) );
        pstmt.setDouble( 6, object.getSalary() );

        pstmt.execute();
        return pstmt.getGeneratedKeys().getLong("id");
    }

    public long update(cinema.data.Food object) throws IOException, SQLException {
        long id = object.getId();
        final String tableName = DatabaseConstants.FOOD_TABLE;
        final String[] columnNames = new String[] {
                "name",
                "price"
        };

        PreparedStatement pstmt = getPreparedStatement(id, tableName, columnNames);
        pstmt.setString( 1, object.getName() );
        pstmt.setDouble( 2, object.getPrice() );

        pstmt.execute();
        return pstmt.getGeneratedKeys().getLong("id");
    }

    public long update(cinema.data.FoodPurchase object) throws IOException, SQLException {
        long id = object.getId();
        final String tableName = DatabaseConstants.FOOD_PURCHASE_TABLE;
        final String[] columnNames = new String[] {
                "client_id",
                "purchase_date",
                "food_product_id",
                "price"
        };

        PreparedStatement pstmt = getPreparedStatement(id, tableName, columnNames);
        pstmt.setLong( 1, object.getClientId() );
        pstmt.setString( 2, Converter.localDateToString(object.getPurchaseDate()) );
        pstmt.setLong( 3, object.getFoodProductId() );
        pstmt.setDouble( 4, object.getPrice(this.conn) );

        pstmt.execute();
        return pstmt.getGeneratedKeys().getLong("id");
    }

    public long update(cinema.data.Movie object) throws IOException, SQLException {
        long id = object.getId();
        final String tableName = DatabaseConstants.MOVIE_TABLE;
        final String[] columnNames = new String[] {
                "name",
                "duration"
        };

        PreparedStatement pstmt = getPreparedStatement(id, tableName, columnNames);
        pstmt.setString( 1, object.getName() );
        pstmt.setInt( 2, object.getDurationInMinutes() );

        pstmt.execute();
        return pstmt.getGeneratedKeys().getLong("id");
    }

    public long update(cinema.data.Screening object) throws IOException, SQLException {
        long id = object.getId();
        final String tableName = DatabaseConstants.SCREENING_TABLE;
        final String[] columnNames = new String[] {
                "movie_id",
                "auditorium_id",
                "price",
                "start_time",
                "hour",
                "technician_id"
        };

        PreparedStatement pstmt = getPreparedStatement(id, tableName, columnNames);
        pstmt.setLong( 1, object.getMovieId() );
        pstmt.setLong( 2, object.getAuditoriumId() );
        pstmt.setDouble( 3, object.getPrice() );
        pstmt.setString( 4, Converter.localDateToString(object.getStartTime()) );
        pstmt.setInt( 5, object.getHour() );
        pstmt.setLong( 6, object.getTechnicianId() );

        pstmt.execute();
        return pstmt.getGeneratedKeys().getLong("id");
    }

    public long update(cinema.data.TicketPurchase object) throws IOException, SQLException {
        long id = object.getId();
        final String tableName = DatabaseConstants.FOOD_PURCHASE_TABLE;
        final String[] columnNames = new String[] {
                "client_id",
                "purchase_date",
                "screening_id",
                "seat_number"
        };

        PreparedStatement pstmt = getPreparedStatement(id, tableName, columnNames);
        pstmt.setLong( 1, object.getClientId() );
        pstmt.setString( 2, Converter.localDateToString(object.getPurchaseDate()) );
        pstmt.setLong( 3, object.getScreeningId() );
        pstmt.setInt( 4, object.getSeatNumber() );

        pstmt.execute();
        return pstmt.getGeneratedKeys().getLong("id");
    }
}
