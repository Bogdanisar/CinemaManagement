package cinema;

import java.time.LocalDateTime;

public abstract class Purchase {
    private final LocalDateTime purchaseDate;

    public Purchase(LocalDateTime purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public LocalDateTime getPurchaseDate() {
        return purchaseDate;
    }

    public abstract String getName();
    public abstract double getPrice();
}
