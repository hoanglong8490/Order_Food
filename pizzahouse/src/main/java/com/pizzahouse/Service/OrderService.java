package com.pizzahouse.Service;

import com.pizzahouse.DTO.OrderDTO;
import com.pizzahouse.Entity.Food;
import com.pizzahouse.Entity.Order;
import com.pizzahouse.Entity.OrderDetail;
import com.pizzahouse.Entity.User;
import com.pizzahouse.Entity.key.KeyOrderDetail;
import com.pizzahouse.Payload.Request.OrderRequest;
import com.pizzahouse.Repository.OrderDetailRepository;
import com.pizzahouse.Repository.OrderRepository;
import com.pizzahouse.Repository.UserRepository;
import com.pizzahouse.Service.imp.OrderServiceImp;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Service
public class OrderService implements OrderServiceImp {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    OrderDetailRepository orderDetailRepository;

    @Autowired
    UserRepository userRepository;


    @Transactional
    @Override
    public boolean addOrder(OrderRequest orderRequest) {
        try{
            String userEmail = SecurityContextHolder.getContext().getAuthentication().getName();
            System.out.println("userEmail: " + userEmail);
            User authUser = userRepository.findByEmail(userEmail);
            if(authUser == null){
                throw new RuntimeException("User not found");
            }

            Order order = new Order();
            order.setUser(authUser);
            order.setId(orderRequest.getOrderId());
            orderRepository.save(order);

            List<OrderDetail> orderDetails = new ArrayList<>();
            for(int idFood : orderRequest.getIdFood()){
                Food food = new Food();
                food.setId(idFood);

                OrderDetail orderDetail = new OrderDetail();
                KeyOrderDetail keyOrderDetail = new KeyOrderDetail(order.getId(),idFood);
                orderDetail.setOrder(order);
                orderDetail.setFood(food);
                orderDetail.setKeyOrderDetail(keyOrderDetail);
                orderDetails.add(orderDetail);

            }
            orderDetailRepository.saveAll(orderDetails);


            return true;
        }catch (Exception e){
            System.out.println("Error adding order:"+e.getMessage());
            return false;
        }

    }

    @Override
    public List<OrderDTO> getOrderDetails() {
        List<OrderDetail> orderDetails = orderDetailRepository.findAll();
        List<OrderDTO> orderDTOs = new ArrayList<>();
        for(OrderDetail orderDetail : orderDetails){
            OrderDTO orderDTO = new OrderDTO(
            orderDetail.getOrder().getId(),
            orderDetail.getFood().getId(),
            orderDetail.getFood().getName(),
            orderDetail.getFood().getPrice()
            );
        orderDTOs.add(orderDTO);
        }

        return orderDTOs;
    }

    @Override
    public List<OrderDTO> getOrderByUserId(int userId) {
        List<OrderDetail> orderDetails = orderDetailRepository.findByOrderUserId(userId);
        List<OrderDTO> orderDTOs = orderDetails.stream()
                .map(orderDetail -> new OrderDTO(orderDetail.getOrder().getId(),orderDetail.getFood().getId(), orderDetail.getFood().getName(), orderDetail.getFood().getPrice()))
                .toList();
        return orderDTOs;
    }

    @Override
    public boolean deleteOrder(int orderId) {
        try{
            String userEmail = SecurityContextHolder.getContext().getAuthentication().getName();
            User authUser = userRepository.findByEmail(userEmail);
            if(authUser == null){
                throw new RuntimeException("User not found");
            }

            Order order = orderRepository.findById(orderId).orElse(null);
            if(order == null){
                throw new RuntimeException("Order not found");
            }

            if(order.getUser().getId() != authUser.getId()){
                throw new RuntimeException("You do not have permission to delete this order");
            }

            orderDetailRepository.deleteByOrderId(orderId);
            orderRepository.delete(order);
            return true;

        }catch (Exception e){
            System.out.println("Error deleting order:"+e.getMessage());
            return false;
        }
    }
}
