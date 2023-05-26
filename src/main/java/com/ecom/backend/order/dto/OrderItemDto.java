package com.ecom.backend.order.dto;

import com.ecom.backend.common.AbstractEntity;
import com.ecom.backend.product.dto.ProductDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderItemDto extends AbstractEntity {

    private ProductDto product;

    private double total_productPrice;
    @JsonIgnore
    private OrderDto order;
}
