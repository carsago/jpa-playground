package com.example.jpastudy.persistence;

import com.example.jpastudy.domain.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

    Page<Product> findAll(Pageable pageable);

//    Slice<Product> readAll(Pageable pageable);
}
