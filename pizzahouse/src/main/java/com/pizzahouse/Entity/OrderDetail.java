package com.pizzahouse.Entity;

import com.pizzahouse.Entity.key.KeyOrderDetail;
import jakarta.persistence.*;

import java.util.Date;


@Entity(name = "orders_detail")
public class OrderDetail {
    @EmbeddedId
    KeyOrderDetail keyOrderDetail;

    @ManyToOne
    @JoinColumn(name = "order_id", insertable = false, updatable = false)
    private Order order;

    @ManyToOne
    @JoinColumn(name = "food_id", insertable = false, updatable = false)
    private Food food;

    @Column(name = "create_date")
    private Date createDate;

    public KeyOrderDetail getKeyOrderDetail() {
        return keyOrderDetail;
    }

    public void setKeyOrderDetail(KeyOrderDetail keyOrderDetail) {
        this.keyOrderDetail = keyOrderDetail;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
