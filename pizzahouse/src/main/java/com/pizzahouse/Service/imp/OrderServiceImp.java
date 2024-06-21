package com.pizzahouse.Service.imp;

import com.pizzahouse.DTO.OrderDTO;
import com.pizzahouse.Entity.OrderDetail;
import com.pizzahouse.Payload.Request.OrderRequest;

import java.util.List;

public interface OrderServiceImp {
    boolean addOrder(OrderRequest orderRequest);
    List<OrderDTO> getOrderDetails();
    List<OrderDTO> getOrderByUserId(int userId);
    boolean deleteOrder(int orderId);
}
