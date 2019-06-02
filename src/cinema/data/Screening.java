package cinema.data;

import cinema.service.Converter;
import cinema.service.DatabaseConstants;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class Screening implements Identifiable {
    protected long id, movieId, auditoriumId;
    protected double price;
    protected LocalDate startTime;
    protected int hour;
    protected long technicianId;

    public Screening(long id, long movieId, long auditoriumId, double price, LocalDate startTime, int hour, long technicianId) {
        this.id = id;
        this.movieId = movieId;
        this.auditoriumId = auditoriumId;
        this.price = price;
        this.startTime = startTime;
        this.hour = hour;
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


    public LocalDate getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDate startTime) {
        this.startTime = startTime;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public long getTechnicianId() {
        return technicianId;
    }

    public void setTechnicianId(long technicianId) {
        this.technicianId = technicianId;
    }


    @Override
    public String toString() {
        String ret = "";
        ret += id + DatabaseConstants.SEPARATOR;
        ret += movieId + DatabaseConstants.SEPARATOR;
        ret += auditoriumId + DatabaseConstants.SEPARATOR;
        ret += price + DatabaseConstants.SEPARATOR;
        ret += Converter.localDateToString(startTime) + DatabaseConstants.SEPARATOR;
        ret += hour + DatabaseConstants.SEPARATOR;
        ret += technicianId;

        return ret;
    }
}
