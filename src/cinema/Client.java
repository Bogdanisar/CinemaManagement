package cinema;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Client extends Person {
    protected List<Purchase> purchaseList = new ArrayList<>();
    protected double funds;

    public Client(Long id, String firstName, String lastName, String email, LocalDate birthDate) {
        super(id, firstName, lastName, email, birthDate);
        this.funds = 0;
    }

    protected double getFunds() {
        return funds;
    }

    public int getAge() {
        return getAgeAt(LocalDate.now());
    }

    public int getAgeAt(LocalDate now) {
        int thenYear = birthDate.getYear(), nowYear = LocalDate.now().getYear();

        int age = nowYear - thenYear;
        LocalDate thenDate = birthDate.plusYears(age);
        if (thenDate.compareTo(now) > 0) {
            age -= 1;
        }
//        System.out.println(thenDate.getYear() + " " + thenDate.getMonthValue() + " " + thenDate.getDayOfMonth());

        return age;
    }
}
