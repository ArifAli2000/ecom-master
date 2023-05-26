package com.ecom.backend.payload;

import com.ecom.backend.order.dto.OrderDto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OrderResponse {

    private	int pageSize;
    private	int pageNumber;
    private	int totalPage;
    private	Long totalElement;
    private List<OrderDto> content;
    private	boolean isLastPage;
}
