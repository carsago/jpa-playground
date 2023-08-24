package com.example.jpastudy.persistence;

import com.example.jpastudy.domain.Store;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface StoreRepository extends JpaRepository<Store, Long> {


    @Query("SELECT s "
        + "FROM Store s "
        + "JOIN FETCH s.employees e "
        + "JOIN FETCH s.products p "
        + "WHERE s.id = :id")
    Optional<Store> findByIdWithFetchJoin(Long id);
}
