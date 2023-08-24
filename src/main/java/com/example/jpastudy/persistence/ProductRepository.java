package com.example.jpastudy.persistence;

import com.example.jpastudy.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
