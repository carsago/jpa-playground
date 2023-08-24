package com.example.jpastudy.persistence;

import com.example.jpastudy.domain.Member;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {


    Slice<Member> readAllBy(Pageable pageable);
}
