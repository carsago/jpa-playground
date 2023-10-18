package com.example.jpastudy.persistence;

import com.example.jpastudy.domain.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TicketRepository extends JpaRepository<Ticket, Long> {

    @Modifying(flushAutomatically = true)
    @Query("update Ticket t set t.isOutOfStock = true where t.id = :ticketId")
    void updateToOutOfStockById(@Param("ticketId") Long ticketId);
}
