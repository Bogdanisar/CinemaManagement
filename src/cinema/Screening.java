package cinema;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class Screening implements Comparable<Screening> {
    protected Movie movie;
    protected Auditorium auditorium;
    protected double price;
    protected LocalDateTime startTime, endTime;
    protected Employee technician;
    protected Set<Client> clients;
    protected Set<Employee> ushers;
    protected Set<Integer> seatSet;
    protected long id;

    public Screening(Long id, Movie movie, Auditorium auditorium, double price, LocalDateTime startTime, Employee technician) {
        this.id = id;
        this.movie = movie;
        this.auditorium = auditorium;
        this.price = price;
        this.startTime = startTime;
        this.endTime = startTime.plusMinutes(movie.getDurationInMinutes());
        this.technician = technician;
        this.clients = new TreeSet<>();
        this.ushers = new TreeSet<>();
        this.seatSet = new HashSet<>();
    }

    public Movie getMovie() {
        return movie;
    }

    public Auditorium getAuditorium() {
        return auditorium;
    }

    public double getPrice() {
        return price;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public Employee getTechnician() {
        return technician;
    }

    public long getId() {
        return id;
    }


    protected void addClient(Client client) {
        clients.add(client);
    }

    protected void addUsher(Employee usher) {
        ushers.add(usher);
    }

    protected static boolean doIntersect(Screening s1, Screening s2) {
        LocalDateTime maxLeft, minRight;
        LocalDateTime a = s1.startTime, b = s1.endTime;
        LocalDateTime x = s2.startTime, y = s2.endTime;
        if (a.compareTo(x) < 0) {
            maxLeft = x;
        }
        else {
            maxLeft = a;
        }

        if (b.compareTo(y) < 0) {
            minRight = b;
        }
        else {
            minRight = y;
        }

        if (maxLeft.compareTo(minRight) <= 0) {
            return true;
        }
        return false;
    }

    @Override
    public int compareTo(Screening other) {
        if (doIntersect(this, other)) {
            return 0;
        }

        if (this.endTime.compareTo(other.endTime) < 0) {
            return -1;
        }
        return 1;
    }
}
