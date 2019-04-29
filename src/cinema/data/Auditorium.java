package cinema.data;

public class Auditorium implements Identifiable {
    protected long id;
    protected int number_of_seats;

    public Auditorium(Long id, int number_of_seats) {
        this.number_of_seats = number_of_seats;
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public int getNumber_of_seats() {
        return number_of_seats;
    }

    public void setNumber_of_seats(int number_of_seats) {
        this.number_of_seats = number_of_seats;
    }
}
