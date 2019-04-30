package cinema.data;

import cinema.service.GetterService;

import java.io.IOException;
import java.time.LocalDate;

public final class FoodPurchase extends Purchase {
    protected long foodProductId;
    double price;

    public FoodPurchase(long purchaseId, long clientId, LocalDate purchaseDate, long foodProductId, double price) {
        super(purchaseId, clientId, purchaseDate);
        this.foodProductId = foodProductId;
        this.price = price;
    }


    public Long getFoodProductId() {
        return foodProductId;
    }

    public void setFoodProductId(Long id) {
        this.foodProductId = id;
    }


    @Override
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }


    @Override
    public String getName() {
        Food food = null;
        String name = null;
        try {
            food = GetterService.getFood(foodProductId);
            name = food.getName();
        }
        catch (Exception except) {
            name = "GetterService.getFood error: " + except.toString();
        }

        return "FoodPurchase Purchase: " + name + ", price: " + price;
    }

}
