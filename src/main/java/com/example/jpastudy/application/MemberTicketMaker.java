package com.example.jpastudy.application;


import com.example.jpastudy.domain.MemberTicket;
import com.example.jpastudy.domain.Ticket;
import com.example.jpastudy.persistence.MemberTicketRepository;
import com.example.jpastudy.persistence.TicketRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Slf4j
@Component
public class MemberTicketMaker {

    private final TicketRepository ticketRepository;
    private final MemberTicketRepository memberTicketRepository;

    @Transactional
    public void doSomething(TicketEvent ticketEvent) {
        log.info("이벤트 호출");
        Long memberTicketId = ticketEvent.memberTicketId();
        int maxTicketAmount = ticketEvent.maxTicketAmount();
        Ticket ticket = ticketRepository.findById(ticketEvent.ticketId())
            .orElseThrow(IllegalArgumentException::new);
        long count = memberTicketRepository.countMemberTicketByIdBeforeAndTicket(memberTicketId, ticket);
        if (count >= maxTicketAmount) {
            ticket.setOutOfStock(true);
            return;
        }
        MemberTicket memberTicket = memberTicketRepository.findById(memberTicketId).get();
        memberTicket.setCanUse(true);
    }
}
