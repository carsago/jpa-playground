package com.example.jpastudy.persistence;

import com.example.jpastudy.domain.Something;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SomethingRepository extends JpaRepository<Something, Long> {


}
