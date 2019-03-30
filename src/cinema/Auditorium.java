package cinema;

import java.util.TreeSet;
import java.util.logging.Logger;

public class Auditorium {
    protected final long id;
    protected final int number_of_seats;
    protected TreeSet<Screening> screeningTreeSet;

    public Auditorium(Long id, int number_of_seats) {
        this.number_of_seats = number_of_seats;
        this.id = id;
        screeningTreeSet = new TreeSet<>();
    }

    public long getId() {
        return id;
    }

    public int getNumber_of_seats() {
        return number_of_seats;
    }
}
