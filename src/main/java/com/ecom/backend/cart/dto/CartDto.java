package com.ecom.backend.cart.dto;

import com.ecom.backend.User.dto.UserDto;
import com.ecom.backend.common.AbstractEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CartDto extends AbstractEntity {

    private Set<CartItemDto> items = new HashSet<>();
    private UserDto user;
}