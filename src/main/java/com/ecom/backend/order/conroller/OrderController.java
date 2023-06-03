package com.ecom.backend.order.conroller;

import com.ecom.backend.order.dto.OrderDto;
import com.ecom.backend.order.service.OrderService;
import com.ecom.backend.payload.ApiResponse;
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

    @DeleteMapping("{orderId}")
    public ResponseEntity<?> cancelOrderById(@PathVariable String orderId) {
        this.orderService.cancelOrder(orderId);
        return new ResponseEntity<ApiResponse>(new ApiResponse("Order deleted",true),HttpStatus.OK);
    }

    @GetMapping("{orderId}")
    public ResponseEntity<OrderDto> findById(@PathVariable String orderId) {
       OrderDto orderDto = this.orderService.findById(orderId);
        return new ResponseEntity<OrderDto>(orderDto,HttpStatus.ACCEPTED);
    }
}


