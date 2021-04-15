/*
package com.cwmf.ecommerceservice.ecommerce.service;

import com.cwmf.ecommerceservice.ecommerce.exception.CustomerException;
import com.cwmf.ecommerceservice.ecommerce.model.Customer;
import com.cwmf.ecommerceservice.ecommerce.repo.CustomerRepository;
import org.springframework.stereotype.Service;
import org.apache.commons.lang3.StringUtils;

import java.util.Optional;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    //create
    public Customer createCustomer(Customer customer){
        if(StringUtils.isNotBlank(customer.getId())){
            throw new CustomerException("Customer id must be empty");
        }
        if(StringUtils.isBlank(customer.getName())){
            throw new CustomerException("Customer must have a name");
        }
        if(!isPasswordSafe(customer.getPassword())){
            throw new CustomerException("Password too simple");
        }
        customer.setId(null);
        Customer saved=customerRepository.save(customer);
        return saved;
}
    //password complexity check helper method
    private boolean isPasswordSafe(String passWord){
        if(passWord.matches("(?=.*[0-9]).*") && passWord.matches("(?=.*[a-z]).*") && passWord.matches("(?=.*[A-Z]).*") && passWord.matches("(?=.*[~!@#$%^&*()_-]).*")){
            return true;
        }else{
            return false;
        }
    }

    //delete
    public boolean deleteCustomer(String id){
        if(customerRepository.existsById(id)){
            customerRepository.deleteById(id);
            return true;
        }
        return false;
    }

    //Update
    public Customer updateCustomer(Customer customer){
        if(StringUtils.isBlank(customer.getId())){
            throw new CustomerException("Customer id must not be empty");
        }
        if(StringUtils.isBlank(customer.getName())){
            throw new CustomerException("Customer must have a name");
        }
        if(!isPasswordSafe(customer.getPassword())){
            throw new CustomerException("Password too simple");
        }
        Customer saved=customerRepository.save(customer);
        return saved;
    }

    //Read
    public Optional<Customer> getCustomerById(String customerId){
        if(!customerRepository.existsById(customerId)){
            throw new CustomerException("customer" + customerId+" not exist");
        }
        Optional<Customer> find=customerRepository.findById(customerId);
        return find;
    }
}




 */