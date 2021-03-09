package com.jwtaut.demo.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Food")

public class Food {

    @Id
    private String id;
    private String foodName;
    private double foodPrice;

    public Food(String foodName, double foodPrice, boolean b) {
        this.foodName = foodName;
        this.foodPrice = foodPrice;
    }

    public String getId() {
        return id;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public double getFoodPrice() {
        return foodPrice;
    }

    public void setFoodPrice(double foodPrice) {
        this.foodPrice = foodPrice;
    }

}
