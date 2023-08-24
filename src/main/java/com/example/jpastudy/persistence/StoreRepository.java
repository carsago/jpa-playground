package com.example.jpastudy.persistence;

import com.example.jpastudy.domain.Store;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface StoreRepository extends JpaRepository<Store, Long> {

    @Query("SELECT s "
        + "FROM Store s "
        + "JOIN FETCH s.products ")
    List<Store> findAllWithFetchJoinProduct();
}
