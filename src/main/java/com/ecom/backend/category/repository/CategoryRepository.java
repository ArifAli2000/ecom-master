package com.ecom.backend.category.repository;


import com.ecom.backend.category.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,String> {

}