package com.ecom.backend.order.model;

import com.ecom.backend.common.AbstractEntity;
import com.ecom.backend.product.model.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class OrderItem extends AbstractEntity {

    @OneToOne
    private Product product;

    private double total_productPrice;

    private int productQuantity;
    @ManyToOne
    private Order order;
}
