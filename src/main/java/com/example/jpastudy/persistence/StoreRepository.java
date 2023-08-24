package com.example.jpastudy.persistence;

import com.example.jpastudy.domain.Store;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository<Store, Long> {

}
