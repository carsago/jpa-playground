package com.example.jpastudy.persistence;

import com.example.jpastudy.domain.Product;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProductRepository extends JpaRepository<Product, Long> {


    @Modifying(flushAutomatically = true)
    @Query("update Product p set p.quantity = :quantities where p.id in :productIds")
    void updateQuantities(@Param("productIds") List<Long> productIds,
                          @Param("quantities") List<Integer> quantities);

    @Query("select p from Product p where p.name like %:param%")
    List<Product> findByParam(@Param("param") String param);
}
