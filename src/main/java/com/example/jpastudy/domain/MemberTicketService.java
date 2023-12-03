package com.example.jpastudy.domain;

import com.example.jpastudy.persistence.MemberTicketRepository;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberTicketService {

    private final MemberTicketRepository memberTicketRepository;

    public void validateEntry(Long memberTicketId, LocalDateTime now) {
        MemberTicket memberTicket = memberTicketRepository.findById(memberTicketId)
                .orElseThrow(IllegalArgumentException::new);
        if (!memberTicket.canEntry(now)) {
            throw new IllegalArgumentException();
        }
    }
}
