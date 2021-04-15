package com.cwmf.ecommerceservice.ecommerce.service;

import com.cwmf.ecommerceservice.ecommerce.exception.ProductException;
import com.cwmf.ecommerceservice.ecommerce.model.Product;
import com.cwmf.ecommerceservice.ecommerce.repo.ProductRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    //CRUD
    //create new product
    public Product createProduct(Product product){
        if(StringUtils.isNotBlank(product.getId())){
            throw new ProductException("product can not come with an id");
        }
        if(StringUtils.isBlank(product.getProductName())){
            throw new ProductException("product must come with a name");
        }
        if(product.getCost() < 0){
            throw new ProductException("product Cost must be positive");
        }
        if(product.getRetailPrice()<0){
            throw new ProductException("product retail price must be positive");
        }
        if(product.getCategory()==null){
            throw new ProductException("product must have a category name");
        }
        product.setId(null);
        Product saved=productRepository.save(product);
        return saved;
    }

    //Read Product
    public Optional<Product> getProductById(String productId){
        Optional<Product> find=productRepository.findById(productId);
        return find;
    }
    //getallProduct
    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }

    //Update Product
    public Product updateProduct(Product product){
        if(StringUtils.isBlank(product.getId())){
            throw new ProductException("product must come with id");
        }
        if(StringUtils.isBlank(product.getProductName())){
            throw new ProductException("product must come with a name");
        }
        if(product.getCost() < 0){
            throw new ProductException("product Cost must be positive");
        }
        if(product.getRetailPrice()<0){
            throw new ProductException("product retail price must be positive");
        }
        if(product.getCategory()==null){
            throw new ProductException("product must have a category name");
        }
        Product saved=productRepository.save(product);
        return saved;
    }

    //Delete Product
    public boolean deleteProduct(String id){
        if(productRepository.existsById(id)){
            productRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
