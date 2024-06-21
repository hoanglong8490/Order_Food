package com.pizzahouse.Service.imp;

import com.pizzahouse.Entity.Food;
import com.pizzahouse.Payload.Request.FoodRequest;
import org.springframework.web.multipart.MultipartFile;

public interface FoodServiceImp {
//    boolean createFood(String name,MultipartFile file,String description,Double price,int categoryId);
    boolean createFood(FoodRequest  foodRequest);
    boolean updateFood(int foodId,FoodRequest foodRequest);
    boolean deleteFood(int foodId);
}
