package cinema.data;

import java.time.LocalDate;

public class Employee extends Person {
    protected LocalDate hireDate;
    protected double salary;

    public Employee(Long id, String firstName, String lastName, String email, LocalDate birthDate, LocalDate hireDate, double salary) {
        super(id, firstName, lastName, email, birthDate);
        this.hireDate = hireDate;
        this.salary = salary;
    }


    public LocalDate getHireDate() {
        return hireDate;
    }

    public void setHireDate(LocalDate hireDate) {
        this.hireDate = hireDate;
    }


    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    @Override
    public String getName() {
        return "Employee: " + super.getName();
    }
}
