package com.cwmf.ecommerceservice.ecommerce.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderItems {
    private String productId;
    private String productName;
    private int quantity;
    private double unitPrice;
}
