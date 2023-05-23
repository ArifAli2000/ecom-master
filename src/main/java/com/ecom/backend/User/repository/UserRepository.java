package com.ecom.backend.User.repository;

import com.ecom.backend.User.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface  UserRepository extends JpaRepository<User,String> {
    public Optional<User> findByEmail(String email);

    //Optional<Object> findByEmail(String email);
}
