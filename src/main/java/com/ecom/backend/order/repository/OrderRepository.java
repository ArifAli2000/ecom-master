package com.ecom.backend.order.repository;

import com.ecom.backend.category.model.Category;
import com.ecom.backend.order.model.Order;
import com.ecom.backend.product.model.Product;
import com.ecom.backend.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order,String> {


}
