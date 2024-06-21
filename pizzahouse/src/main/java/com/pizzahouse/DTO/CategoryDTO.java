package com.pizzahouse.DTO;

import com.pizzahouse.Entity.Food;

import java.util.List;

public class CategoryDTO {
    private int id;
    private String name;
    List<FoodDTO> foodList ;



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<FoodDTO> getFoodList() {
        return foodList;
    }

    public void setFoodList(List<FoodDTO> foodList) {
        this.foodList = foodList;
    }
}
