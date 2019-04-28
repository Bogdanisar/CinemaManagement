package cinema.data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Client extends Person {
    protected double funds;

    public Client(Long id, String firstName, String lastName, String email, LocalDate birthDate) {
        super(id, firstName, lastName, email, birthDate);
        this.funds = 0;
    }

    public double getFunds() {
        return funds;
    }

    public void setFunds(double funds) {
        this.funds = funds;
    }


    public int getAgeNow() {
        return getAgeAt(LocalDate.now());
    }

    public int getAgeAt(LocalDate now) {
        int thenYear = birthDate.getYear(), nowYear = LocalDate.now().getYear();

        int age = nowYear - thenYear;
        LocalDate thenDate = birthDate.plusYears(age);
        if (thenDate.compareTo(now) > 0) {
            age -= 1;
        }

        return age;
    }
}
