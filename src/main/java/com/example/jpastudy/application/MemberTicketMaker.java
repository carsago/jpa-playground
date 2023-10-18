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
    public void update(TicketRegisterDto ticketRegisterDto) {
        Long memberTicketId = ticketRegisterDto.memberTicketId();
        int maxTicketAmount = ticketRegisterDto.maxTicketAmount();
        Long ticketId = ticketRegisterDto.ticketId();
        long count = memberTicketRepository.countMemberTicketByIdBeforeAndTicketId(memberTicketId, ticketId);
        if (count > maxTicketAmount) {
            return;
        }

        if (count == maxTicketAmount) {
            Ticket ticket = ticketRepository.findById(ticketId)
                .orElseThrow(IllegalArgumentException::new);
            ticket.setOutOfStock(true);
            return;
        }

        MemberTicket memberTicket = memberTicketRepository.findById(memberTicketId)
            .orElseThrow(IllegalArgumentException::new);
        memberTicket.setCanUse(true);
    }
}
