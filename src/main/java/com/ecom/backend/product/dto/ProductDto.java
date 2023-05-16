package com.ecom.backend.product.dto;

import com.ecom.backend.category.dto.CategoryDto;
import com.ecom.backend.common.AbstractEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto extends AbstractEntity {

    private String product_name;
    private double product_price;
    private int product_quantity;
    private boolean live;
    private String product_imageName;
    private String product_desc;
    private boolean stock;

    private CategoryDto category;


}
