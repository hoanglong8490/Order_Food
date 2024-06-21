package com.pizzahouse.Repository;

import com.pizzahouse.Entity.OrderDetail;
import com.pizzahouse.Entity.key.KeyOrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, KeyOrderDetail> {
    List<OrderDetail> findByOrderUserId(int orderId);
    void deleteByOrderId(int orderId);
}
