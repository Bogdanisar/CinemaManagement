package cinema.service;

public interface DatabaseConstants {
    String AUDITORIUM_TABLE = "auditorium";
    String SCREENING_TABLE = "screening";
    String CATEGORY_TABLE = "category";
    String MOVIE_TABLE = "movie";
    String MOVIE_CATEGORY_TABLE = "movie_category";
    String FOOD_TABLE = "food";
    String CLIENT_TABLE = "client";
    String EMPLOYEE_TABLE = "employee";
    String SCREENING_EMPLOYEE_TABLE = "screening_employee";
    String TICKET_PURCHASE_TABLE = "ticket_purchase";
    String FOOD_PURCHASE_TABLE = "food_purchase";


    String SEPARATOR = ", ";
    String[] AUDITORIUM_HEADER = {"ID", "NUMBER_OF_SEATS"};
    String[] SCREENING_HEADER = {"ID", "MOVIE_ID", "AUDITORIUM_ID", "PRICE", "START_TIME", "HOUR", "TECHNICIAN_ID"};
    String[] CATEGORY_HEADER = {"ID", "NAME", "MINIMUM_AGE"};
    String[] MOVIE_HEADER = {"ID", "NAME", "DURATION"};
    String[] MOVIE_CATEGORY_HEADER = {"ID", "MOVIE_ID", "CATEGORY_ID"};
    String[] FOOD_HEADER = {"ID", "NAME", "PRICE"};
    String[] CLIENT_HEADER = {"ID", "FIRST_NAME", "LAST_NAME", "EMAIL", "BRITH_DATE", "FUNDS"};
    String[] EMPLOYEE_HEADER = {"ID", "FIRST_NAME", "LAST_NAME", "EMAIL", "BIRTH_DATE", "HIRE_DATE", "SALARY"};
    String[] SCREENING_EMPLOYEE_HEADER = {"ID", "SCREENING_ID", "EMPLOYEE_ID"};
    String[] TICKET_PURCHASE_HEADER = {"ID", "CLIENT_ID", "PURCHASE_DATE", "SCREENING_ID", "SEAT_NUMBER"};
    String[] FOOD_PURCHASE_HEADER = {"ID", "CLIENT_ID", "PURCHASE_DATE", "FOOD_PRODUCT_ID", "PRICE"};
}
