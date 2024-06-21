package com.pizzahouse.Controller;


import com.pizzahouse.Payload.Request.FoodRequest;
import com.pizzahouse.Payload.ResponseData;
import com.pizzahouse.Service.imp.FoodServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("Food")
public class FoodController {
    @Autowired
    FoodServiceImp foodServiceImp;

    @PostMapping("/addFood")
    public ResponseEntity<?> addFood(@ModelAttribute @RequestBody FoodRequest foodRequest) {
        ResponseData responseData = new ResponseData();
        boolean success = foodServiceImp.createFood(foodRequest);
        if (success) {
            responseData.setStatusCode(200);
            responseData.setMessage("Food added successfully");
            responseData.setContent(foodRequest);
            return new ResponseEntity<>(responseData, HttpStatus.OK);
        }else{
            responseData.setStatusCode(400);
            responseData.setMessage("Food not added successfully");
            return new ResponseEntity<>(responseData, HttpStatus.BAD_REQUEST);
        }
    }
    @PutMapping("/updateFood/{foodId}")
    public ResponseEntity<?> updateFood( @PathVariable("foodId") int foodId, @ModelAttribute FoodRequest foodRequest) {
        ResponseData responseData = new ResponseData();
        boolean success = foodServiceImp.updateFood(foodId, foodRequest);
        if (success) {
            responseData.setStatusCode(200);
            responseData.setMessage("Food updated successfully");
            return new ResponseEntity<>(responseData, HttpStatus.OK);
        }else{
            responseData.setStatusCode(400);
            responseData.setMessage("Food not updated successfully");
            return new ResponseEntity<>(responseData, HttpStatus.BAD_REQUEST);
        }
    }
    @DeleteMapping("/deleteFood/{foodId}")
    public ResponseEntity<?> deleteFood( @PathVariable("foodId") int foodId) {
        ResponseData responseData = new ResponseData();
        boolean success = foodServiceImp.deleteFood(foodId);
        if(success){
            responseData.setStatusCode(200);
            responseData.setMessage("Food deleted successfully");
            return new ResponseEntity<>(responseData, HttpStatus.OK);
        }else{
            responseData.setStatusCode(400);
            responseData.setMessage("Food not deleted successfully");
            return new ResponseEntity<>(responseData, HttpStatus.BAD_REQUEST);
        }
    }
}
