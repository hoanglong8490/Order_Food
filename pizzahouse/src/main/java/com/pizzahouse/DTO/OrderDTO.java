package com.pizzahouse.DTO;

public class OrderDTO {
    private int orderId;
    private int foodId;
    private String foodName;
    private double price;

    public OrderDTO(int orderId, int foodId, String foodName, double price) {
        this.orderId = orderId;
        this.foodId = foodId;
        this.foodName = foodName;
        this.price = price;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getFoodIdId() {
        return foodId;
    }

    public void setFoodId(int foodId) {
        this.foodId = foodId;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
