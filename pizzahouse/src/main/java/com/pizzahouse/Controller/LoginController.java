package com.pizzahouse.Controller;


import com.pizzahouse.Entity.User;
import com.pizzahouse.Payload.Request.UserRequest;
import com.pizzahouse.Payload.ResponseData;
import com.pizzahouse.Security.JwtToken;
import com.pizzahouse.Service.imp.LoginServiceImp;

import com.pizzahouse.Service.imp.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private LoginServiceImp loginServiceImp;

    @Autowired
    private UserServiceImp userServiceImp;

    @Autowired
    private JwtToken jwtToken;



    @PostMapping("/signin")
    public ResponseEntity<?> signin(@RequestParam String email, @RequestParam String password) {
        ResponseData responseData = new ResponseData();

        if (loginServiceImp.signin(email, password)) {

            String token = jwtToken.generateToken(email);
            responseData.setStatusCode(200);
            responseData.setMessage("Login Successful");
//            responseData.setContent();
            responseData.setToken(token);
            return new ResponseEntity<>(responseData, HttpStatus.OK);
        }else {
            responseData.setStatusCode(401);
            responseData.setMessage("Login fail");
            return new ResponseEntity<>(responseData, HttpStatus.UNAUTHORIZED);
        }
    }
    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody UserRequest userRequest) {
        ResponseData responseData = new ResponseData();
        boolean success = userServiceImp.addUser(userRequest);
        if (success) {
            responseData.setStatusCode(200);
            responseData.setMessage("User Added Successfully");
            return new ResponseEntity<>(responseData, HttpStatus.OK);
        }else {
            responseData.setStatusCode(400);
            responseData.setMessage("Email already exists");
            return new ResponseEntity<>(responseData, HttpStatus.BAD_REQUEST);
        }
    }
}
