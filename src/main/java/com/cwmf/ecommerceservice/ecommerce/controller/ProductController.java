package com.cwmf.ecommerceservice.ecommerce.controller;

import com.cwmf.ecommerceservice.ecommerce.model.Product;
import com.cwmf.ecommerceservice.ecommerce.service.ProductService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/product")
public class ProductController {
    private final ProductService productService;
    public ProductController(ProductService productService) {
        this.productService = productService;
    }
    //CRUD

    //Create new Product
    @PostMapping
    @PreAuthorize("hasAuthority('EMPLOYEE')")
    public Product createProduct(@RequestBody Product product){
        return this.productService.createProduct(product);
    }

    @GetMapping("/{id}")
    public Optional<Product> getProductById(@PathVariable String id){
        return this.productService.getProductById(id);
    }

    @GetMapping
    @PreAuthorize("hasAnyAuthority('CUSTOMER', 'EMPLOYEE')")
    public List<Product> getAllProduct(){return this.productService.getAllProducts();}

    @PutMapping
    @PreAuthorize("hasAuthority('EMPLOYEE')")
    public Product updateProduct(@RequestBody Product product){
        return this.productService.updateProduct(product);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('EMPLOYEE')")
    public boolean deleteProduct(@PathVariable String id){
        return this.productService.deleteProduct(id);
    }
}
