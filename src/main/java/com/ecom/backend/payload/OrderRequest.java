package com.ecom.backend.payload;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderRequest {

    private String orderAddress;
    private String cartId;
}
