package com.example.jpastudy.persistence;

import com.example.jpastudy.domain.Store;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface StoreRepository extends JpaRepository<Store, Long> {

    @Query("SELECT distinct s   "
        + "FROM Store s "
        + "JOIN FETCH s.products "
        + "JOIN FETCH s.employees")
    List<Store> findAllWithFetchJoin();
}
