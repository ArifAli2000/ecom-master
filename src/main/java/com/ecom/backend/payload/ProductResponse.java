package com.ecom.backend.payload;

import com.ecom.backend.product.dto.ProductDto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ProductResponse {
    private List<ProductDto> content;
    private int pageNumber;
    private int pageSize;
    private int totalPages;
    private boolean lastPage;

}
