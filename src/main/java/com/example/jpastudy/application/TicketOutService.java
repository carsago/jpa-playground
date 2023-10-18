package com.example.jpastudy.application;

import com.example.jpastudy.domain.Ticket;
import com.example.jpastudy.persistence.TicketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Component
public class TicketOutService {

    private final TicketRepository ticketRepository;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void out(Long ticketId) {
        Ticket ticket = ticketRepository.findById(ticketId)
            .orElseThrow(IllegalArgumentException::new);

    }
}
