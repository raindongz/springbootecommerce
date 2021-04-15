package com.cwmf.ecommerceservice.ecommerce.repo;

import com.cwmf.ecommerceservice.ecommerce.model.Order;
import com.cwmf.ecommerceservice.ecommerce.model.OrderStatus;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends MongoRepository<Order, String>{
    List<Order> findOrderByCustomerIdAndStatus(String customerId, OrderStatus status);
    List<Order> findByCustomerId(String customerId);
}
