package com.pizzahouse.Controller;


import com.pizzahouse.DTO.OrderDTO;
import com.pizzahouse.Entity.OrderDetail;
import com.pizzahouse.Payload.Request.OrderRequest;
import com.pizzahouse.Payload.ResponseData;
import com.pizzahouse.Service.imp.OrderServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("Order")
public class OrderController {

    @Autowired
    OrderServiceImp orderServiceImp;

    @PostMapping("/addOrder")
    public ResponseEntity<?>addOrder(@RequestBody OrderRequest orderRequest) {
        ResponseData responseData = new ResponseData( );
        boolean success = orderServiceImp.addOrder(orderRequest);
        if (success) {
            responseData.setStatusCode(200);
            responseData.setMessage("Order added successfully");
            return new ResponseEntity<>(responseData, HttpStatus.OK);
        }else{
            responseData.setStatusCode(400);
            responseData.setMessage("Error adding order");
            return new ResponseEntity<>(responseData, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getAllOrder")
    public ResponseEntity<?> getAllOrder() {
        ResponseData responseData = new ResponseData( );
        List<OrderDTO> orderDTOS = orderServiceImp.getOrderDetails();
        if (!orderDTOS.isEmpty()) {
            responseData.setStatusCode(200);
            responseData.setMessage("Order found");
            responseData.setContent(orderDTOS);
            return new ResponseEntity<>(responseData, HttpStatus.OK);
        }else{
            responseData.setStatusCode(400);
            responseData.setMessage("Error finding order");
            return new ResponseEntity<>(responseData, HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/getOrder/{userId}")
    public ResponseEntity<?> getOrder(@PathVariable int userId) {
        ResponseData responseData = new ResponseData( );
        List<OrderDTO> orderDTOS = orderServiceImp.getOrderByUserId(userId);
        if (!orderDTOS.isEmpty()) {
            responseData.setStatusCode(200);
            responseData.setMessage("Order found");
            responseData.setContent(orderDTOS);
            return new ResponseEntity<>(responseData, HttpStatus.OK);
        }else{
            responseData.setStatusCode(400);
            responseData.setMessage("Error finding order");
            return new ResponseEntity<>(responseData, HttpStatus.BAD_REQUEST);
        }
    }
    @DeleteMapping("/deleteOrder/{orderId}")
    public ResponseEntity<?> deleteOrder(@PathVariable int orderId) {
        ResponseData responseData = new ResponseData();
        boolean success = orderServiceImp.deleteOrder(orderId);
        if (success) {
            responseData.setStatusCode(200);
            responseData.setMessage("Order deleted successfully");
            return new ResponseEntity<>(responseData, HttpStatus.OK);
        }else{
            responseData.setStatusCode(400);
            responseData.setMessage("Error deleting order");
            return new ResponseEntity<>(responseData, HttpStatus.BAD_REQUEST);
        }
    }
}
