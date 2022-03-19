package com.example.fastfood1;

public class FoodItem {
    private String foodName;
    private int foodImg;
    private int price;

    public FoodItem(String foodName, int foodImg, int price) {
        this.foodName = foodName;
        this.foodImg = foodImg;
        this.price = price;
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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
