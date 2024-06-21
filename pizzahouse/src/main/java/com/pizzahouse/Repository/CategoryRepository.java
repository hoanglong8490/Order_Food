package com.pizzahouse.Repository;

import com.pizzahouse.DTO.FoodDTO;
import com.pizzahouse.Entity.Category;
import com.pizzahouse.Entity.Food;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    Category findByName(String name);
    Optional<Category> findById(int id);

}
