package cinema.data;

import cinema.service.GetterService;

import java.io.IOException;
import java.time.LocalDate;

public final class TicketPurchase extends Purchase {
    protected long screeningId;
    protected int seatNumber;

    private Double price;
    private String movieName;

    public TicketPurchase(long id, long clientId, LocalDate purchaseDate, long screeningId, int seatNumber) {
        super(id, clientId, purchaseDate);
        this.screeningId = screeningId;
        this.seatNumber = seatNumber;

        this.price = null;
        this.movieName = null;
    }


    private void retrieveData() {
        if (this.price == null || this.movieName == null) {
            Screening screening = null;
            Movie movie = null;

            try {
                screening = GetterService.getScreening(this.screeningId);
                movie = GetterService.getMovie(screening.getMovieId());

                this.price = screening.getPrice();
                this.movieName = movie.getName();
            }
            catch (Exception except) {
                this.price = -1D;
                this.movieName = "GetterService.getScreening or getMovie error: " + except.toString();
            }
        }
    }


    @Override
    public double getPrice() {
        this.retrieveData();

        return this.price;
    }

    @Override
    public String getName() {
        this.retrieveData();

        return "TicketPurchase Purchase: " + this.movieName + ", price: " + this.price;
    }


    public long getScreeningId() {
        return screeningId;
    }

    public void setScreeningId(long screeningId) {
        this.screeningId = screeningId;
    }


    public int getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(int seatNumber) {
        this.seatNumber = seatNumber;
    }
}
