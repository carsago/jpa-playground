package com.example.jpastudy.persistence;

import com.example.jpastudy.domain.MemberTicket;
import com.example.jpastudy.domain.Ticket;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberTicketRepository extends JpaRepository<MemberTicket, Long> {

    long countMemberTicketByIdBeforeAndTicket(Long id, Ticket ticket);

    long countMemberTicketByIdBefore(Long memberTicketId);

    List<MemberTicket> findAllByTicket(Ticket ticket);
}
