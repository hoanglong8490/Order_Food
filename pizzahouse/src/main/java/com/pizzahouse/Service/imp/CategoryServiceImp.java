package com.pizzahouse.Service.imp;


import com.pizzahouse.DTO.CategoryDTO;
import com.pizzahouse.Payload.Request.CategoryRequest;

import java.util.List;

public interface CategoryServiceImp {
    boolean addCategory(CategoryRequest categoryRequest);
    List<CategoryDTO> getCategories();
    CategoryDTO getCategoryById(int id);
    boolean updateCategory(int cateId, CategoryRequest categoryRequest);
    boolean deleteCategory(int cateId);
//    List<Food> getFoodsByCategory(int cateId);

}
