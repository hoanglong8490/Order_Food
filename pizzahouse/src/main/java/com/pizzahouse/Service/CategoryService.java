package com.pizzahouse.Service;

import com.pizzahouse.DTO.CategoryDTO;
import com.pizzahouse.DTO.FoodDTO;
import com.pizzahouse.Entity.Category;
import com.pizzahouse.Entity.Food;
import com.pizzahouse.Payload.Request.CategoryRequest;
import com.pizzahouse.Repository.CategoryRepository;
import com.pizzahouse.Service.imp.CategoryServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService implements CategoryServiceImp {

    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public boolean addCategory(CategoryRequest categoryRequest) {
       try{
           if(categoryRepository.findByName(categoryRequest.getName())!=null){
               throw new Exception("Category already exists");
           }
           Category category = new Category();
           category.setName(categoryRequest.getName());
           categoryRepository.save(category);
           return true;
       }catch (Exception e){
           return false;
       }
    }

    @Override
    public List<CategoryDTO> getCategories() {
        List<Category> categoryList = categoryRepository.findAll();
        List<CategoryDTO> categoryDTOList = new ArrayList<>();
        for (Category category : categoryList) {
            categoryDTOList.add(convertCategoryToCategoryDTO(category));
        }
        return categoryDTOList;
    }

    @Override
    public CategoryDTO getCategoryById(int id) {
        Optional<Category> categoryOptional = categoryRepository.findById(id);
      return categoryOptional.map(this::convertCategoryToCategoryDTO).orElse(null);
    }
    private CategoryDTO convertCategoryToCategoryDTO(Category category){
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setId(category.getId());
        categoryDTO.setName(category.getName());
        List<FoodDTO> foodDTOList = new ArrayList<>();
        for (Food food : category.getFoodList()) {
            FoodDTO foodDTO = new FoodDTO();
            foodDTO.setId(food.getId());
            foodDTO.setName(food.getName());
            foodDTO.setImage(food.getImage());
            foodDTO.setDescription(food.getDescription());
            foodDTO.setPrice(food.getPrice());
            foodDTOList.add(foodDTO);
        }
        categoryDTO.setFoodList(foodDTOList);
        return categoryDTO;
    }
    @Override
    public boolean updateCategory(int cateId, CategoryRequest categoryRequest) {
        Optional<Category> category = categoryRepository.findById(cateId);
        try {
            if (category.isPresent()) {
                Category categoryToUpdate = category.get();
                categoryToUpdate.setName(categoryRequest.getName());
                categoryRepository.save(categoryToUpdate);
            }else{
                System.out.println("Category not found with id: " + cateId);
                return false; // Danh mục không tồn tại
            }
            return true;

        } catch (Exception e) {
            System.out.println("Error while updating Category:"+e.getMessage());
            return false;
        }
    }

    @Override
    public boolean deleteCategory(int cateId) {
        if(categoryRepository.findById(cateId).isPresent()){
            categoryRepository.deleteById(cateId);
            return true;
        }
        return false;
    }


}
