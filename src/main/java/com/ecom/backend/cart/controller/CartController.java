package com.ecom.backend.cart.controller;

import com.ecom.backend.cart.dto.CartDto;
import com.ecom.backend.cart.service.CartService;
import com.ecom.backend.payload.ItemRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

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
    @GetMapping
    public ResponseEntity<CartDto> getAllCart(Principal principal){
       CartDto getAllCart = this.cartService.getAllCart(principal.getName());
        return new ResponseEntity<CartDto>(getAllCart,HttpStatus.ACCEPTED);
    }

    @GetMapping("{cartId}")
    public ResponseEntity<CartDto> getCartById(@PathVariable String cartId){
       CartDto cartById = this.cartService.getCartById(cartId);
        return new ResponseEntity<CartDto>(cartById,HttpStatus.OK);
    }

    @DeleteMapping("{productId}")
    public ResponseEntity<CartDto>deleteCartItemFromCart(@PathVariable String productId,Principal principal){

        CartDto remove = this.cartService.removeCartItemFromCart(principal.getName(),productId);
        return new ResponseEntity<CartDto>(remove,HttpStatus.UPGRADE_REQUIRED);
    }

}