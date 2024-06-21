package com.pizzahouse.Service;

import com.pizzahouse.Entity.Category;
import com.pizzahouse.Entity.Food;
import com.pizzahouse.Payload.Request.FoodRequest;
import com.pizzahouse.Repository.CategoryRepository;
import com.pizzahouse.Repository.FoodRepository;
import com.pizzahouse.Service.imp.FileServiceImp;
import com.pizzahouse.Service.imp.FoodServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;


@Service
public class FoodService implements FoodServiceImp {

    @Autowired
    FoodRepository foodRepository;

    @Autowired
    FileServiceImp fileServiceImp;

    @Autowired
    CategoryRepository CategoryRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public boolean createFood(FoodRequest foodRequest) {
        boolean success = false;
        try{
            boolean saveFile = fileServiceImp.saveFile(foodRequest.getImage());
                if(saveFile){
                    Food food = new Food();
                    food.setName(foodRequest.getName());
                    food.setImage(foodRequest.getImage().getOriginalFilename());
                    food.setDescription(foodRequest.getDescription());
                    food.setPrice(foodRequest.getPrice());

                    Optional<Category> categoryOptional = categoryRepository.findById(foodRequest.getCategoryId());

                    if(categoryOptional.isPresent()){
                        Category category = categoryOptional.get();
                        food.setCategory(category);
                        System.out.println("category id: " + categoryOptional.get().getId());
                        foodRepository.save(food);
                        return true;
                    }else{
                        System.out.println("Category not found");
                    }
                }

        } catch (Exception e) {
            System.out.println("Error create food:"+e.getMessage());
        }
        return success;
    }

    @Override
    public boolean updateFood(int foodId, FoodRequest foodRequest) {

        boolean success = false;
        try {
            Optional<Food> optionalFood = foodRepository.findById(foodId);
            if(optionalFood.isPresent()){
                Food food = optionalFood.get();
                boolean saveFile = fileServiceImp.saveFile(foodRequest.getImage());
                if(saveFile){
                    food.setName(foodRequest.getName());
                    food.setImage(foodRequest.getImage().getOriginalFilename());
                    food.setDescription(foodRequest.getDescription());
                    food.setPrice(foodRequest.getPrice());

                    Optional<Category> categoryOptional = categoryRepository.findById(foodRequest.getCategoryId());
                    if(categoryOptional.isPresent()){
                        Category category = categoryOptional.get();
                        food.setCategory(category);
                        System.out.println("category id: " + categoryOptional.get().getId());
                        foodRepository.save(food);
                        return true;
                    }else{
                        System.out.println("Error update food: Category does not exist.");
                    }
                }
            }else{
                System.out.println("Error update food: Food does not exist.");
            }
        }catch (Exception e){
            System.out.println("Error update food:"+e.getMessage());
        }
        return success;
    }

    @Override
    public boolean deleteFood(int foodId) {
        if(foodRepository.existsById(foodId)){
            foodRepository.deleteById(foodId);
            return true;
        }
        return false;
    }


}
