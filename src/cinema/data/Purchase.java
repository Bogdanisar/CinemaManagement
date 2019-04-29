package cinema.data;

import java.time.LocalDate;

public abstract class Purchase implements Identifiable {
    protected long id, clientId;
    protected LocalDate purchaseDate;

    public Purchase(long id, long clientId, LocalDate purchaseDate) {
        this.id = id;
        this.clientId = clientId;
        this.purchaseDate = purchaseDate;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public long getClientId() {
        return clientId;
    }

    public void setClientId(long clientId) {
        this.clientId = clientId;
    }


    public LocalDate getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(LocalDate purchaseDate) {
        this.purchaseDate = purchaseDate;
    }


    public abstract String getName();
    public abstract double getPrice();
}
