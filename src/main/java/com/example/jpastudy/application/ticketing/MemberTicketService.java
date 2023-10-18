package com.example.jpastudy.application.ticketing;


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
public class MemberTicketService {

    private final TicketRepository ticketRepository;
    private final MemberTicketRepository memberTicketRepository;

    public TicketDto issued(Long ticketId) {
        Ticket ticket = getTicket(ticketId);
        if (ticket.isOutOfStock()) {
            throw new IllegalArgumentException();
        }

        MemberTicket savedMemberTicket = memberTicketRepository.save(new MemberTicket(ticket, false));
        return new TicketDto(savedMemberTicket.getId(), ticket.getId(), ticket.getMaxAmount());
    }

    @Transactional
    public void update(TicketDto ticketDto) {
        Long memberTicketId = ticketDto.memberTicketId();
        int maxTicketAmount = ticketDto.maxTicketAmount();
        Long ticketId = ticketDto.ticketId();
        long count = memberTicketRepository.countMemberTicketByIdBeforeAndTicketId(memberTicketId, ticketId);
        if (count > maxTicketAmount) {
            return;
        }

        if (count == maxTicketAmount) {
            Ticket ticket = getTicket(ticketId);
            ticket.setOutOfStock(true);
            return;
        }

        MemberTicket memberTicket = memberTicketRepository.findById(memberTicketId)
            .orElseThrow(IllegalArgumentException::new);
        memberTicket.setCanUse(true);
    }

    private Ticket getTicket(Long ticketId) {
        return ticketRepository.findById(ticketId)
            .orElseThrow(IllegalArgumentException::new);
    }
}
