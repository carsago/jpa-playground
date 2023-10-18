package com.example.jpastudy.application;

import com.example.jpastudy.domain.MemberTicket;
import com.example.jpastudy.domain.Ticket;
import com.example.jpastudy.persistence.MemberTicketRepository;
import com.example.jpastudy.persistence.TicketRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Slf4j
@Service
public class TicketService {

    private final TicketRepository ticketRepository;
    private final MemberTicketRepository memberTicketRepository;

    @Transactional
    public TicketEvent doit(Long ticketId) {
        log.info("직전 메소드 호출");
        Ticket ticket = ticketRepository.findById(ticketId).get();
        if (ticket.isOutOfStock()) {
            throw new IllegalArgumentException();
        }
        MemberTicket memberTicket = memberTicketRepository.save(new MemberTicket(ticket, false));
        return new TicketEvent(memberTicket.getId(), ticket.getId(), ticket.getMaxAmount());
    }

    @Transactional(readOnly = true)
    public void check(Long memberTicketId) {
        MemberTicket memberTicket = memberTicketRepository.findById(memberTicketId).get();
        if (!memberTicket.isCanUse()) {
            throw new IllegalArgumentException();
        }

    }
}
