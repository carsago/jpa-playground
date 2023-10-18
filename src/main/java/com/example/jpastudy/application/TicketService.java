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

//    @Transactional
    public TicketRegisterDto register(Long ticketId) {
        Ticket ticket = ticketRepository.findById(ticketId)
            .orElseThrow(IllegalArgumentException::new);
        if (ticket.isOutOfStock()) {
            throw new IllegalArgumentException();
        }

        Long memberTicketId = memberTicketRepository.save(new MemberTicket(ticket, false)).getId();
        return new TicketRegisterDto(memberTicketId, ticket.getId(), ticket.getMaxAmount());
    }

    @Transactional(readOnly = true)
    public void checkSuccess(Long memberTicketId) {
        MemberTicket memberTicket = memberTicketRepository.findById(memberTicketId).get();
        if (!memberTicket.isCanUse()) {
            throw new IllegalArgumentException();
        }
    }
}
