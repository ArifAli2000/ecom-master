package com.ecom.backend.cart.model;


import com.ecom.backend.common.AbstractEntity;
import com.ecom.backend.product.model.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CartItem extends AbstractEntity {

    private int quantity;
    private double total_price;
    //Relationship with other table

    @ManyToOne
    private Cart cart;
    @OneToOne
    private Product product;

}
