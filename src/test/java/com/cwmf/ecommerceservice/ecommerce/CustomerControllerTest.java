/*package com.cwmf.ecommerceservice.ecommerce;
import static org.junit.jupiter.api.Assertions.*;
import com.cwmf.ecommerceservice.ecommerce.controller.CustomerController;
import com.cwmf.ecommerceservice.ecommerce.model.Customer;
import com.cwmf.ecommerceservice.ecommerce.service.CustomerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.util.Assert;

@WebMvcTest(CustomerController.class)
public class CustomerControllerTest {

    @MockBean
    private CustomerService customerService;

    @Test
    public void createCustomer() throws Exception{
       Customer customer = new Customer();
        customer.setEmail("1323");
        customer.setHasActiveOrder(false);
        customer.setPassword("Aaaa1!!");

        Customer newCustomer = customerService.createCustomer(customer);

        assertEquals(customer.getEmail(), newCustomer.getEmail());
        assertEquals(customer.getName(), newCustomer.getName());
        assertEquals(customer.getPassword(), newCustomer.getPassword());
    }
}


 */