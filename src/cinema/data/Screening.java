package cinema.data;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class Screening implements Comparable<Screening> {
    protected long id;
    protected long movieId, auditoriumId;
    protected double price;
    protected LocalDateTime startTime, endTime;
    protected long technicianId;

    public Screening(long id, long movieId, long auditoriumId, double price, LocalDateTime startTime, LocalDateTime endTime, long technicianId) {
        this.id = id;
        this.movieId = movieId;
        this.auditoriumId = auditoriumId;
        this.price = price;
        this.startTime = startTime;
        this.endTime = endTime;
        this.technicianId = technicianId;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public long getMovieId() {
        return movieId;
    }

    public void setMovieId(long movieId) {
        this.movieId = movieId;
    }


    public long getAuditoriumId() {
        return auditoriumId;
    }

    public void setAuditoriumId(long auditoriumId) {
        this.auditoriumId = auditoriumId;
    }


    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }


    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }


    public long getTechnicianId() {
        return technicianId;
    }

    public void setTechnicianId(long technicianId) {
        this.technicianId = technicianId;
    }


    private static boolean doIntersect(Screening s1, Screening s2) {
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
