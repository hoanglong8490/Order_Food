package com.pizzahouse.Controller;


import com.pizzahouse.Entity.User;
import com.pizzahouse.Payload.Request.UserRequest;
import com.pizzahouse.Payload.ResponseData;
import com.pizzahouse.Service.imp.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("User")
public class UserController {

    @Autowired
    UserServiceImp userServiceImp;

    @GetMapping("/getAllUser")
    public ResponseEntity<ResponseData> getAllUser() {
        ResponseData responseData = new ResponseData();
        responseData.setMessage("200");
        responseData.setContent(userServiceImp.getAllUser());
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }

    @PostMapping("/addUser")
    public ResponseEntity<?> addUser(@RequestBody UserRequest userRequest) {
        ResponseData responseData = new ResponseData();
        boolean success = userServiceImp.addUser(userRequest);
        if (success) {
            responseData.setMessage("200");
            responseData.setMessage("User Added Successfully");
            return new ResponseEntity<>(responseData, HttpStatus.OK);
        }else{
            responseData.setStatusCode(400);
            responseData.setMessage("Email already exists");
            return new ResponseEntity<>(responseData, HttpStatus.BAD_REQUEST);

        }
    }
    @PutMapping("updateUser/{userId}")
    public ResponseEntity<?> updateUser(@PathVariable("userId") int userId ,@RequestBody UserRequest userRequest) {
        ResponseData responseData = new ResponseData();
        try{
            boolean success = userServiceImp.updateUser(userId,userRequest);
            if (success) {
                responseData.setStatusCode(200);
                responseData.setMessage("User Updated Successfully");
                responseData.setContent(userRequest);
                return new ResponseEntity<>(responseData, HttpStatus.OK);
            }else {
                responseData.setStatusCode(400);
                responseData.setMessage("Email not found");
                return new ResponseEntity<>(responseData, HttpStatus.NOT_FOUND);
            }
        }catch (Exception e){
            responseData.setStatusCode(400);
            responseData.setMessage("Email already exists");
            return new ResponseEntity<>(responseData, HttpStatus.BAD_REQUEST);
        }
    }
    @DeleteMapping("/deleteUser/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable("userId") int userId) {
        ResponseData responseData = new ResponseData();
        boolean success = userServiceImp.deleteUser(userId);
        if (success) {
            responseData.setStatusCode(200);
            responseData.setMessage("User Deleted Successfully");
            return new ResponseEntity<>(responseData, HttpStatus.OK);
        }else{
            responseData.setStatusCode(400);
            responseData.setMessage("Email not found");
            return new ResponseEntity<>(responseData, HttpStatus.NOT_FOUND);
        }
    }
}
