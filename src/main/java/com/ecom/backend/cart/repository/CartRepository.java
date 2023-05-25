package com.ecom.backend.cart.repository;

import com.ecom.backend.user.model.User;
import com.ecom.backend.cart.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart,Integer> {
    public Optional<Cart> findByUser(User user);
}
