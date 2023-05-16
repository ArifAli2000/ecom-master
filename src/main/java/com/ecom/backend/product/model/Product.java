package com.ecom.backend.product.model;

import com.ecom.backend.category.model.Category;
import com.ecom.backend.common.AbstractEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Getter
@Setter
@ToString
@Entity
@Table(name = "Product")
public class Product extends AbstractEntity {

    private String product_name;
    private double product_price;
    private int product_quantity;
    private boolean live;
    private String product_imageName;
    private String product_desc;
    private boolean stock=true;

    @ManyToOne(fetch = FetchType.EAGER)
    private Category category;


}
