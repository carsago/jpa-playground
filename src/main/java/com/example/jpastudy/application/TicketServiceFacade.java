package com.example.jpastudy.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class TicketServiceFacade {

    private final TicketService ticketService;
    private final MemberTicketMaker memberTicketMaker;


    public void doit(Long ticketId) {
        TicketEvent ticketEvent = ticketService.doit(ticketId);
        memberTicketMaker.doSomething(ticketEvent);
        ticketService.check(ticketEvent.memberTicketId());
    }
}
