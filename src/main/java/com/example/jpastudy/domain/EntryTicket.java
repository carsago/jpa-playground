package com.example.jpastudy.domain;

import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public class EntryTicket {

    private final Long id;
    private final LocalDateTime entryTime;
    private final Long memberTicketId;

    public EntryTicket(Long id, LocalDateTime entryTime, Long memberTicketId) {
        this.id = id;
        this.entryTime = entryTime;
        this.memberTicketId = memberTicketId;
    }

    public boolean canEntry(LocalDateTime now) {
        return now.isAfter(entryTime);
    }
}
