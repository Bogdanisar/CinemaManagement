package cinema.data;

import cinema.service.Converter;
import cinema.service.DatabaseConstants;

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

    @Override
    public String getName() {
        return "Client: " + super.getName();
    }


    @Override
    public String toString() {
        String ret = "";
        ret += id + DatabaseConstants.SEPARATOR;
        ret += firstName + DatabaseConstants.SEPARATOR;
        ret += lastName + DatabaseConstants.SEPARATOR;
        ret += email + DatabaseConstants.SEPARATOR;
        ret += Converter.localDateToString(birthDate) + DatabaseConstants.SEPARATOR;
        ret += String.format("%.2f", this.getFunds());

        return ret;
    }
}
