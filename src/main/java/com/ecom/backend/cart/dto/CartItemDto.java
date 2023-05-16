package com.ecom.backend.cart.dto;

import com.ecom.backend.common.AbstractEntity;
import com.ecom.backend.product.dto.ProductDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CartItemDto extends AbstractEntity {

    private int quantity;
    private double total_price;

    private CartDto cart;
    private ProductDto product;

}