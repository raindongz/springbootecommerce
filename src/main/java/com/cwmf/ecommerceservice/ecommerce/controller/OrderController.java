package com.cwmf.ecommerceservice.ecommerce.controller;

import com.cwmf.ecommerceservice.ecommerce.model.Order;
import com.cwmf.ecommerceservice.ecommerce.service.OrderService;
import org.springframework.data.mongodb.core.aggregation.BooleanOperators;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/order")
public class OrderController {
    private final OrderService orderService;
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    //CRUD
    @PostMapping
    public Order createOrder(@RequestBody Order order, Principal principal){
        return this.orderService.createOrder(order, principal.getName());
    }

    @PutMapping
    public Order updateOrder(@RequestBody Order order){
        return this.orderService.updateOrder(order);
    }

    @DeleteMapping("/{id}")
    public boolean deleteOrder(@PathVariable String id){
        return this.orderService.deleteOrder(id);
    }

    @GetMapping("/{id}")
    public Optional<Order> getOrderById(@PathVariable String id){
        return this.orderService.getOrderById(id);
    }

    @GetMapping("/active")
    public List<Order> getActiveOrder(Principal principal){return this.orderService.getActiveOrder(principal.getName());}


    @GetMapping
    @PreAuthorize("hasAnyAuthority('CUSTOMER', 'EMPLOYEE')")
    public List<Order> getAllOrders(Principal principal){return this.orderService.getAllOrders(principal.getName());}
}
