package com.ecom.backend.cart.controller;

import com.ecom.backend.cart.dto.CartDto;
import com.ecom.backend.cart.service.CartService;
import com.ecom.backend.payload.ItemRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/cart")
public class CartController{

    @Autowired
    private CartService cartService;

    @PostMapping
    public ResponseEntity<CartDto> addtoCart(@RequestBody ItemRequest itemRequest, Principal principal){
        String email=principal.getName();
        System.out.println(email);
        CartDto addItem = this.cartService.addItem(itemRequest,principal.getName());
        System.out.println(principal.getName());
        return new ResponseEntity<CartDto>(addItem, HttpStatus.OK);
    }


    //create method for getting cart
}
