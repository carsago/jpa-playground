package com.example.jpastudy.application.ticketing;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MemberTicketServiceFacade {

    private final MemberTicketService memberTicketService;


    public void doit(Long ticketId) {
        TicketDto ticketDto = memberTicketService.issued(ticketId);
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        memberTicketService.update(ticketDto);
    }
}
