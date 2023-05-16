package com.ecom.backend.product.repository;

import com.ecom.backend.category.model.Category;
import com.ecom.backend.product.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, String> {
    List<Product> findByCategory(Category category);


}
