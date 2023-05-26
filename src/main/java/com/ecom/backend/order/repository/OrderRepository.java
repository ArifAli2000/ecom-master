package com.ecom.backend.order.repository;

import com.ecom.backend.order.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order,String> {
}
