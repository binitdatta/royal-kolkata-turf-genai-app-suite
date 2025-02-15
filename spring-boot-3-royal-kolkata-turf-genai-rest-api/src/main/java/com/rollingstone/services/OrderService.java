package com.rollingstone.services;

import com.rollingstone.entity.Order;
import com.rollingstone.entity.OrderDetail;
import com.rollingstone.repository.OrderDetailRepository;
import com.rollingstone.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    // Order-related methods
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Optional<Order> getOrderById(Integer id) {
        return orderRepository.findById(id);
    }

    public Order createOrder(Order order) {
        return orderRepository.save(order);
    }

    public Order updateOrder(Integer id, Order order) {
        if (orderRepository.existsById(id)) {
            order.setOrderId(id);
            return orderRepository.save(order);
        }
        throw new RuntimeException("Order not found with id: " + id);
    }

    public void deleteOrder(Integer id) {
        if (orderRepository.existsById(id)) {
            orderRepository.deleteById(id);
        } else {
            throw new RuntimeException("Order not found with id: " + id);
        }
    }

    // OrderDetail-related methods
    public List<OrderDetail> getAllOrderDetails() {
        return orderDetailRepository.findAll();
    }

    public Optional<OrderDetail> getOrderDetailById(Integer id) {
        return orderDetailRepository.findById(id);
    }

    public OrderDetail createOrderDetail(OrderDetail orderDetail) {
        return orderDetailRepository.save(orderDetail);
    }

    public OrderDetail updateOrderDetail(Integer id, OrderDetail orderDetail) {
        if (orderDetailRepository.existsById(id)) {
            orderDetail.setOrderDetailId(id);
            return orderDetailRepository.save(orderDetail);
        }
        throw new RuntimeException("OrderDetail not found with id: " + id);
    }

    public void deleteOrderDetail(Integer id) {
        if (orderDetailRepository.existsById(id)) {
            orderDetailRepository.deleteById(id);
        } else {
            throw new RuntimeException("OrderDetail not found with id: " + id);
        }
    }
}
