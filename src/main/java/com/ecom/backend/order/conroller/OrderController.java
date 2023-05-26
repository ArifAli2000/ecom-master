package com.ecom.backend.order.conroller;

import com.ecom.backend.order.dto.OrderDto;
import com.ecom.backend.order.service.OrderService;
import com.ecom.backend.payload.OrderRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;


@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;
    //create order

    @PostMapping
    public ResponseEntity<OrderDto> createOrder(@RequestBody OrderRequest orderReq, Principal principal) {
        String username=principal.getName();
        OrderDto order=this.orderService.orderCreate(orderReq,username);
        return new ResponseEntity<OrderDto>(order,HttpStatus.CREATED);
    }
}