package cinema.data;

import java.time.LocalDateTime;

public abstract class Purchase {
    protected long id, clientId;
    protected LocalDateTime purchaseDate;

    public Purchase(long id, long clientId, LocalDateTime purchaseDate) {
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


    public LocalDateTime getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(LocalDateTime purchaseDate) {
        this.purchaseDate = purchaseDate;
    }


    public abstract String getName();
    public abstract double getPrice();
}
