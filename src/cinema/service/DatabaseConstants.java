package cinema.service;

public interface DatabaseConstants {
    String AUDITORIUM_FILE = "CSV/auditorium.csv";
    String SCREENING_FILE = "CSV/screening.csv";
    String CATEGORY_FILE = "CSV/category.csv";
    String MOVIE_FILE = "CSV/movie.csv";
    String MOVIE_CATEGORY_FILE = "CSV/movie_category.csv";
    String FOOD_FILE = "CSV/food.csv";
    String CLIENT_FILE = "CSV/client.csv";
    String EMPLOYEE_FILE = "CSV/employee.csv";
    String SCREENING_EMPLOYEE_FILE = "CSV/screening_employee.csv";
    String TICKET_PURCHASE_FILE = "CSV/ticket_purchase.csv";
    String FOOD_PURCHASE_FILE = "CSV/food_purchase.csv";

    Object[] AUDITORIUM_HEADER = {"ID", "NUMBER_OF_SEATS"};
    Object[] SCREENING_HEADER = {"ID", "MOVIE_ID", "AUDITORIUM_ID", "PRICE", "START_TIME", "HOUR", "TECHNICIAN_ID"};
    Object[] CATEGORY_HEADER = {"ID", "NAME", "MINIMUM_AGE"};
    Object[] MOVIE_HEADER = {"ID", "NAME", "DURATION"};
    Object[] MOVIE_CATEGORY_HEADER = {"ID", "MOVIE_ID", "CATEGORY_ID"};
    Object[] FOOD_HEADER = {"ID", "NAME", "PRICE"};
    Object[] CLIENT_HEADER = {"ID", "FIRST_NAME", "LAST_NAME", "EMAIL", "BRITH_DATE", "FUNDS"};
    Object[] EMPLOYEE_HEADER = {"ID", "FIRST_NAME", "LAST_NAME", "EMAIL", "BIRTH_DATE", "HIRE_DATE", "SALARY"};
    Object[] SCREENING_EMPLOYEE_HEADER = {"ID", "SCREENING_ID", "EMPLOYEE_ID"};
    Object[] TICKET_PURCHASE_HEADER = {"ID", "CLIENT_ID", "PURCHASE_DATE", "SCREENING_ID", "SEAT_NUMBER"};
    Object[] FOOD_PURCHASE_HEADER = {"ID", "CLIENT_ID", "PURCHASE_DATE", "FOOD_PRODUCT_ID", "PRICE"};
}
