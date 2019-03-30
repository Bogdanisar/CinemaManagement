package cinema;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Employee extends Person {
    LocalDate hireDate;
    double salary;

    public Employee(Long id, String firstName, String lastName, String email, LocalDate birthDate, LocalDate hireDate, double salary) {
        super(id, firstName, lastName, email, birthDate);
        this.hireDate = hireDate;
        this.salary = salary;
    }
}
