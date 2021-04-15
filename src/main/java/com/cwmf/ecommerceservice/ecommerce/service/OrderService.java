package com.cwmf.ecommerceservice.ecommerce.service;

import com.cwmf.ecommerceservice.ecommerce.exception.OrderException;
import com.cwmf.ecommerceservice.ecommerce.model.*;
import com.cwmf.ecommerceservice.ecommerce.repo.OrderRepository;
import com.cwmf.ecommerceservice.ecommerce.repo.ProductRepository;
import com.cwmf.ecommerceservice.ecommerce.repo.UserEntityRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final UserEntityRepository userEntityRepository;
    public OrderService(OrderRepository orderRepository, ProductRepository productRepository, UserEntityRepository userEntityRepository) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
        this.userEntityRepository = userEntityRepository;
    }

    public Order createOrder(Order order, String email){
        //check if id is null and if customer id exist and create new order with status ACTIVE
        if(StringUtils.isNotBlank(order.getId())){
            throw new OrderException("Order must come with empty id");
        }
        UserEntity user=userEntityRepository.findUserEntityByEmail(email).get();
        //handle empty String id passed in from frontend
        order.setId(null);
        order.setCustomerId(user.getId());
        //set order status to ACTIVE so user will be able to edit this order
        order.setStatus(OrderStatus.ACTIVE);
        Order saved=orderRepository.save(order);
        return saved;
    }

    public Order updateOrder(Order order){
        //when user click place order, the order status will become PLACED and call backend to check stock availability
        //if item no longer exist or product quantity out of stock, order status will change back to ACTIVE and throw an exception
        if(order.getStatus()==OrderStatus.PLACED) {
            //check customer id is present
            if(StringUtils.isBlank(order.getCustomerId())){
                order.setStatus(OrderStatus.ACTIVE);
                throw new OrderException("Order must come with customer id");
            }
            //check if shipping address is present
            if(StringUtils.isBlank(order.getShippingAddress())){
                order.setStatus(OrderStatus.ACTIVE);
                throw new OrderException("Order must come with a shipping address");
            }
            if(order.getOrderItemsList().isEmpty()){
                order.setStatus(OrderStatus.ACTIVE);
                throw new OrderException("Order must have items in it");
            }
            //list of products which store history products detail.
            List<Product> historyProductList = new ArrayList<>();
            List<Product> productList = new ArrayList<>();

            //check if product is in stock
            //iterate the map to check if all order items are in stock && exist in database
        /*    for (Map.Entry<String, OrderItems> entry : order.getOrderItemsList().entrySet()) {
                //get info of product in database with corresponding order item
                Optional<Product> product = productRepository.findById(entry.getKey());
                //check if product still exist in database
                if (product.isEmpty()) {
                    //error happen, order can not be execute, reset order status to active so customer can edit order
                    order.setStatus(OrderStatus.ACTIVE);
                    throw new OrderException("Product " + entry.getKey() + " is no longer exist");
                }
                //check if product is in stock
                if (product.get().getQuantity() <= entry.getValue().getQuantity()) {
                    //error happen, order can not be execute, reset order status to active so customer can edit order
                    order.setStatus(OrderStatus.ACTIVE);
                    throw new OrderException("Product " + entry.getKey() + " only have " + product.get().getQuantity() + " left");
                }

                //order is ready to be execute, deduct the amount the corresponding product quantity, and will later update product repository
                product.get().setQuantity(productRepository.findById(entry.getKey()).get().getQuantity() - entry.getValue().getQuantity());

                //add corresponding product to order product history list
                productList.add(product.get());

           }

         */

            for (int i=0; i<order.getOrderItemsList().size(); i++) {
                //get info of product in database with corresponding order item
                Optional<Product> product = productRepository.findById(order.getOrderItemsList().get(i).getProductId());
                //check if product still exist in database
                if (product.isEmpty()) {
                    //error happen, order can not be execute, reset order status to active so customer can edit order
                    order.setStatus(OrderStatus.ACTIVE);
                    throw new OrderException("Product " + order.getOrderItemsList().get(i).getProductId() + " is no longer exist");
                }
                //check if product is in stock
                if (product.get().getQuantity() <= order.getOrderItemsList().get(i).getQuantity()) {
                    //error happen, order can not be execute, reset order status to active so customer can edit order
                    order.setStatus(OrderStatus.ACTIVE);
                    throw new OrderException("Product " + order.getOrderItemsList().get(i).getProductId()  + " only have " + product.get().getQuantity() + " left");
                }

                //order is ready to be execute, deduct the amount the corresponding product quantity, and will later update product repository
                product.get().setQuantity(product.get().getQuantity() - order.getOrderItemsList().get(i).getQuantity());

                //add corresponding product to order product history list
                productList.add(product.get());
                Product historyProduct=new Product(product.get().getId(),product.get().getProductName(),order.getOrderItemsList().get(i).getQuantity(),product.get().getRetailPrice(),product.get().getCost(),product.get().getDescription(),product.get().getPictureURL(),product.get().getCategory());
                historyProductList.add(historyProduct);
            }
            //updated product quantity in database
            for (Product product : productList) {
                productRepository.save(product);
            }

            //set history product list
            order.setHistoryProductList(historyProductList);
            //order successfully executed, update this order in database
            Order saved = orderRepository.save(order);
            return saved;
        }
        for(int i=0; i<order.getOrderItemsList().size()-1; i++){
            for(int j=i+1; j<order.getOrderItemsList().size();j++){
                if(order.getOrderItemsList().get(i).getProductId().equals(order.getOrderItemsList().get(j).getProductId())){
                    order.getOrderItemsList().get(i).setQuantity(order.getOrderItemsList().get(i).getQuantity()+order.getOrderItemsList().get(j).getQuantity());
                    order.getOrderItemsList().remove(j);
                }
            }
        }
        //if order status not placed
        if(StringUtils.isBlank(order.getId())){
            throw new OrderException("Order must come with id");
        }
        if(StringUtils.isBlank(order.getCustomerId())){
            throw new OrderException("Order must come with customer id");
        }

        Order saved=orderRepository.save(order);
        return saved;
    }

    public boolean deleteOrder(String id){
        if(orderRepository.existsById(id)){
            orderRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public Optional<Order> getOrderById(String id){
        Optional<Order> find=orderRepository.findById(id);
        return find;
    }

    //get by userid and orderstatus
    //will not use this method
    public List<Order> getActiveOrder(String userEmail){
        UserEntity user=userEntityRepository.findUserEntityByEmail(userEmail).get();
        List<Order> orderList= orderRepository.findOrderByCustomerIdAndStatus(user.getId(), OrderStatus.ACTIVE);
        if(orderList.size()!=0){ return orderList; }
        Order newOrder=new Order();
        newOrder.setId(null);
        newOrder.setCustomerId(user.getId());
        newOrder.setShippingAddress("");
        newOrder.setStatus(OrderStatus.ACTIVE);
        newOrder.setOrderItemsList(new ArrayList<>());
        newOrder.setHistoryProductList(new ArrayList<>());
        Order saved=this.orderRepository.save(newOrder);
        orderList.add(saved);
        return orderList;
    }

    //getAllorders by specific user
    public List<Order> getAllOrders(String email){
        UserEntity user=userEntityRepository.findUserEntityByEmail(email).get();
        List<Order> orders=orderRepository.findByCustomerId(user.getId());
        List<Order> activeOrders=orderRepository.findOrderByCustomerIdAndStatus(user.getId(), OrderStatus.ACTIVE);
        if(activeOrders.size()!=0){return orders;}
      /*  OrderItems testOrderItems1=new OrderItems(1,2);
        OrderItems testOrderItems2=new OrderItems(1,2);
        Map<String,OrderItems> testMap=new HashMap<>();
        testMap.put("6073ad1440ab866e355e8367",testOrderItems1);
        testMap.put("607100662fb9380bb5ed847a",testOrderItems2);
        */
        Order newOrder=new Order();
        newOrder.setId(null);
        newOrder.setCustomerId(user.getId());
        newOrder.setShippingAddress("");
        newOrder.setStatus(OrderStatus.ACTIVE);
        newOrder.setOrderItemsList(new ArrayList<>());
        newOrder.setHistoryProductList(new ArrayList<>());
        Order saved=this.orderRepository.save(newOrder);
        orders.add(saved);
        return orders;
    }
}
