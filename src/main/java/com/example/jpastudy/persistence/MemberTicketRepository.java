package com.example.jpastudy.persistence;

import com.example.jpastudy.domain.MemberTicket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberTicketRepository extends JpaRepository<MemberTicket, Long> {

}
