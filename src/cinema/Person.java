package cinema;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Person implements Comparable<Person> {
    protected long id;
    protected String firstName, lastName, email;
    protected LocalDate birthDate;

    public Person(Long id, String firstName, String lastName, String email, LocalDate birthDate) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.birthDate = birthDate;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getName() {
        return lastName + " " + firstName;
    }

    public String getEmail() {
        return email;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    @Override
    public int compareTo(Person other) {
        if (this.id == other.id) {
            return 0;
        }

        if (this.id < other.id) {
            return -1;
        }
        return 1;
    }
}
