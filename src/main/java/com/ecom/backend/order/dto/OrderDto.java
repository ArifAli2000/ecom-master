package com.ecom.backend.order.dto;

import com.ecom.backend.common.AbstractEntity;
import com.ecom.backend.user.dto.UserDto;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class OrderDto extends AbstractEntity {

    private String orderStatus;
    private String paymentStatus;
    private Date orderDelivered;
    private double orderAmount;
    private String billingAddress;

    private UserDto user;

    private Set<OrderItemDto> orderItem=new HashSet<>();
}
