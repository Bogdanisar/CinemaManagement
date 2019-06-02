package cinema.data;

import cinema.service.Converter;
import cinema.service.DatabaseConstants;
import cinema.service.GetterService;

import java.io.IOException;
import java.time.LocalDate;
import java.sql.Connection;

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


    private void retrieveData(Connection conn) {
        if (this.price == null || this.movieName == null) {
            Screening screening = null;
            Movie movie = null;

            try {
                screening = (new GetterService(conn)).getScreening(this.screeningId);
                movie = (new GetterService(conn)).getMovie(screening.getMovieId());

                this.price = screening.getPrice();
                this.movieName = movie.getName();
            }
            catch (Exception except) {
                this.price = -1D;
                this.movieName = "(new GetterService(conn)).getScreening or .getMovie error: " + except.toString();
            }
        }
    }


    @Override
    public double getPrice(Connection conn) {
        this.retrieveData(conn);

        return this.price;
    }

    @Override
    public String getName(Connection conn) {
        this.retrieveData(conn);

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


    @Override
    public String toString() {
        String ret = "";
        ret += id + DatabaseConstants.SEPARATOR;
        ret += clientId + DatabaseConstants.SEPARATOR;
        ret += Converter.localDateToString(purchaseDate) + DatabaseConstants.SEPARATOR;
        ret += screeningId + DatabaseConstants.SEPARATOR;
        ret += seatNumber;

        return ret;
    }
}
