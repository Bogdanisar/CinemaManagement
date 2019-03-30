package cinema;

import java.time.LocalDateTime;

public final class TicketPurchase extends Purchase {
    Screening screening;
    int seatNumber;

    public TicketPurchase(LocalDateTime date, Screening screening, int seatNumber) {
        super(date);
        this.screening = screening;

        int maxSeat = screening.getAuditorium().getNumber_of_seats();
        if (!(0 < seatNumber && seatNumber <= screening.auditorium.getNumber_of_seats())) {
            throw new IllegalArgumentException("The seat id " + seatNumber + " is more than " + maxSeat);
        }

        this.seatNumber = seatNumber;
    }

    @Override
    public double getPrice() {
        return screening.getPrice(); ///// sau ceva
    }

    @Override
    public String getName() { //// sau ceva
        String movieName = screening.getMovie().getName();
        double price = screening.getPrice();
        return "TicketPurchase Purchase: " + movieName + ", price: " + price;
    }
}
