/*package com.cwmf.ecommerceservice.ecommerce.controller;

import com.cwmf.ecommerceservice.ecommerce.model.Customer;
import com.cwmf.ecommerceservice.ecommerce.service.CustomerService;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    private final CustomerService customerService;
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    public Customer createCustomer(@RequestBody Customer customer){return this.customerService.createCustomer(customer);}

    @DeleteMapping("/{id}")
    public boolean deleteCustomer(@PathVariable String id){
        return this.customerService.deleteCustomer(id);
    }

    @PutMapping
    public Customer updateCustomer(@RequestBody Customer customer){return this.customerService.updateCustomer(customer);}

    @GetMapping("/{id}")
    public Optional<Customer> getCustomerById(@PathVariable String id){return this.customerService.getCustomerById(id);}
}


 */