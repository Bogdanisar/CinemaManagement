package cinema.data;

import cinema.service.GetterService;

import java.time.LocalDateTime;

public final class TicketPurchase extends Purchase {
    protected long screeningId;
    protected int seatNumber;
    private Screening screening;
    private Movie movie;

    public TicketPurchase(long id, long clientId, LocalDateTime purchaseDate, long screeningId, int seatNumber) {
        super(id, clientId, purchaseDate);
        this.screeningId = screeningId;
        this.seatNumber = seatNumber;

        this.screening = null;
    }


    private void retrieveScreening() {
        if (this.screening == null) {
            this.screening = GetterService.getScreening(this.getScreeningId());
        }
    }

    private void retrieveMovie() {
        this.retrieveScreening();
        if (this.movie == null) {
            this.movie = GetterService.getMovie(this.screening.getMovieId());
        }
    }


    @Override
    public double getPrice() {
        this.retrieveScreening();

        return this.screening.getPrice();
    }

    @Override
    public String getName() {
        this.retrieveScreening();
        this.retrieveMovie();

        String movieName = this.movie.getName();
        double price = this.screening.getPrice();
        return "TicketPurchase Purchase: " + movieName + ", price: " + price;
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
