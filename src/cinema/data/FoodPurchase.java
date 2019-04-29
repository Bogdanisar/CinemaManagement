package cinema.data;

import cinema.service.GetterService;

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
        Food food = GetterService.getFood(foodProductId);

        return "FoodPurchase Purchase: " + food.getName() + ", price: " + price;
    }

}
