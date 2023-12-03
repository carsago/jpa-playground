package com.example.jpastudy.domain;

import com.example.jpastudy.persistence.EntryTicketReader;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class EntryTicketService {

    private final EntryTicketReader entryTicketReader;

    public void validateEntry(Long memberTicketId, LocalDateTime now) {
        EntryTicket entryTicket = entryTicketReader.read(memberTicketId);
        if (!entryTicket.canEntry(now)) {
            throw new IllegalArgumentException();
        }
    }
}
