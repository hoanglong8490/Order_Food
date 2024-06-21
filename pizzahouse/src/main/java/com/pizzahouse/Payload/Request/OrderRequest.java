package com.pizzahouse.Payload.Request;

public class OrderRequest {
    private int userId;
    private int orderId;
    private int[] idFood;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int[] getIdFood() {
        return idFood;
    }

    public void setIdFood(int[] idFood) {
        this.idFood = idFood;
    }
}
