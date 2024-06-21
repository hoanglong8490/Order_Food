package com.pizzahouse.Controller;


import com.pizzahouse.DTO.CategoryDTO;
import com.pizzahouse.Entity.Category;
import com.pizzahouse.Payload.Request.CategoryRequest;
import com.pizzahouse.Payload.ResponseData;
import com.pizzahouse.Repository.CategoryRepository;
import com.pizzahouse.Service.CategoryService;
import com.pizzahouse.Service.imp.CategoryServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Category")
public class CategoryController {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CategoryServiceImp categoryServiceImp;
    @Autowired
    private CategoryService categoryService;

    @PostMapping("/addCategory")
    public ResponseEntity<?> addCategory(@RequestBody CategoryRequest categoryRequest) {
        ResponseData responseData = new ResponseData();
        boolean success = categoryServiceImp.addCategory(categoryRequest);
        if (success) {
            responseData.setStatusCode(200);
            responseData.setMessage("Category added successfully");
            return new ResponseEntity<>(responseData, HttpStatus.OK);
        }else{
            responseData.setStatusCode(400);
            responseData.setMessage("Category already exist");
            return new ResponseEntity<>(responseData, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/updateCategory/{cateId}")
    public ResponseEntity<?> updateCategory(@PathVariable("cateId") int cateId, @RequestBody CategoryRequest categoryRequest) {
        ResponseData responseData = new ResponseData();
        boolean success = categoryServiceImp.updateCategory(cateId,categoryRequest);
        try {
            if(success){
                responseData.setStatusCode(200);
                responseData.setMessage("Category updated successfully");
                return new ResponseEntity<>(responseData, HttpStatus.OK);
            }else{
                responseData.setStatusCode(400);
                responseData.setMessage("Category already exist");
                return new ResponseEntity<>(responseData, HttpStatus.BAD_REQUEST);
            }
        }catch (Exception e) {
            System.out.println("Error in updating Category: " + e.getMessage());
        }
        return new ResponseEntity<>(responseData,HttpStatus.OK);
    }

    @GetMapping("/getAllCategory")
    public ResponseEntity<?> getAllCategory() {
        ResponseData responseData = new ResponseData();
        responseData.setStatusCode(200);
        responseData.setMessage("Category list retrieved");
        responseData.setContent(categoryServiceImp.getCategories());
        return new ResponseEntity<>(responseData,HttpStatus.OK);
    }


    @GetMapping("/getAllCategory/{cateId}")
    public ResponseEntity<?> getCategoryById(@PathVariable("cateId") int cateId ){
        ResponseData responseData = new ResponseData();
        CategoryDTO category = categoryService.getCategoryById(cateId);
       if (category != null) {
           responseData.setStatusCode(200);
           responseData.setMessage("success");
           responseData.setContent(category);
           return  new ResponseEntity<>(responseData,HttpStatus.OK);
       } else{
            responseData.setStatusCode(400);
            responseData.setMessage("Category not found");
            return new ResponseEntity<>(responseData, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/categoryDelete/{cateId}")
    public ResponseEntity<?> deleteCategory(@PathVariable("cateId") int cateId) {
        ResponseData responseData = new ResponseData();
        boolean success = categoryServiceImp.deleteCategory(cateId);
        if (success) {
            responseData.setStatusCode(200);
            responseData.setMessage("Category deleted successfully");
            return new ResponseEntity<>(responseData, HttpStatus.OK);
        }else{
            responseData.setStatusCode(400);
            responseData.setMessage("Category not found");
            return new ResponseEntity<>(responseData, HttpStatus.BAD_REQUEST);
        }
    }


}
