package com.example.fastfood1;

public class FoodItem {
    String foodName;
    int foodImg;

    public FoodItem(String foodName, int foodImg) {
        this.foodName = foodName;
        this.foodImg = foodImg;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public int getFoodImg() {
        return foodImg;
    }

    public void setFoodImg(int foodImg) {
        this.foodImg = foodImg;
    }
}
