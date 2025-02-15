package com.rollingstone.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "ORDER_DETAILS")
public class OrderDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ORDER_DETAIL_ID")
    private Integer orderDetailId;

    @ManyToOne
    @JoinColumn(name = "ORDER_ID", nullable = false)
    private Order order;

    @ManyToOne
    @JoinColumn(name = "ITEM_ID", nullable = false)
    private FoodAndDrink item;

    @Column(name = "QUANTITY", nullable = false)
    private Integer quantity;

    @Column(name = "ITEM_PRICE", nullable = false)
    private BigDecimal itemPrice;

    // Default constructor
    public OrderDetail() {
    }

    // Full constructor
    public OrderDetail(Integer orderDetailId, Order order, FoodAndDrink item, Integer quantity, BigDecimal itemPrice) {
        this.orderDetailId = orderDetailId;
        this.order = order;
        this.item = item;
        this.quantity = quantity;
        this.itemPrice = itemPrice;
    }

    // Getters and setters
    public Integer getOrderDetailId() {
        return orderDetailId;
    }

    public void setOrderDetailId(Integer orderDetailId) {
        this.orderDetailId = orderDetailId;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public FoodAndDrink getItem() {
        return item;
    }

    public void setItem(FoodAndDrink item) {
        this.item = item;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(BigDecimal itemPrice) {
        this.itemPrice = itemPrice;
    }

    // toString method
    @Override
    public String toString() {
        return "OrderDetail{" +
                "orderDetailId=" + orderDetailId +
                ", order=" + (order != null ? order.getOrderId() : "null") +
                ", item=" + (item != null ? item.getItemName() : "null") +
                ", quantity=" + quantity +
                ", itemPrice=" + itemPrice +
                '}';
    }
}
