package cinema;

import java.time.LocalDateTime;

public final class FoodPurchase extends Purchase {
    Food foodProduct;

    public FoodPurchase(Food foodProduct, LocalDateTime date) {
        super(date);
        this.foodProduct = foodProduct;
    }

    @Override
    public double getPrice() {
        return foodProduct.getPrice();
    }

    @Override
    public String getName() {
        return "FoodPurchase Purchase: " + foodProduct.getName() + ", price: " + foodProduct.getPrice();
    }
}
