package com.example.jpastudy.persistence;

import com.example.jpastudy.domain.EntryTicket;
import com.example.jpastudy.domain.MemberTicket;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EntryTicketReader {

    private final MemberTicketRepository memberTicketRepository;

    public EntryTicket read(Long memberTicketId) {
        MemberTicket memberTicket = memberTicketRepository.findById(memberTicketId)
                .orElseThrow(IllegalArgumentException::new);
        return new EntryTicket(
                memberTicket.getId(),
                memberTicket.getTicket().getEntryTime(),
                memberTicket.getMemberId());
    }
}
