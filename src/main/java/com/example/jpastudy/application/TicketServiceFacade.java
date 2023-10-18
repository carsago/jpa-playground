package com.example.jpastudy.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class TicketServiceFacade {

    private final TicketService ticketService;
    private final MemberTicketMaker memberTicketMaker;


    public void doit(Long ticketId) {
        TicketRegisterDto ticketRegisterDto = ticketService.register(ticketId);
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        memberTicketMaker.update(ticketRegisterDto);
    }
}
