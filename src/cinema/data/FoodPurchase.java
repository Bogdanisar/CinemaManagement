package cinema.data;

import cinema.service.Converter;
import cinema.service.DatabaseConstants;
import cinema.service.GetterService;

import java.io.IOException;
import java.time.LocalDate;
import java.sql.*;

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
    public double getPrice(Connection conn) {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }


    @Override
    public String getName(Connection conn) {
        Food food = null;
        String name = null;
        try {
            food = (new GetterService(conn)).getFood(foodProductId);
            name = food.getName();
        }
        catch (Exception except) {
            name = "GetterService.getFood error: " + except.toString();
        }

        return "FoodPurchase Purchase: " + name + ", price: " + price;
    }

    @Override
    public String toString() {
        String ret = "";
        ret += id + DatabaseConstants.SEPARATOR;
        ret += clientId + DatabaseConstants.SEPARATOR;
        ret += Converter.localDateToString(purchaseDate) + DatabaseConstants.SEPARATOR;
        ret += foodProductId + DatabaseConstants.SEPARATOR;
        ret += price;

        return ret;
    }
}
