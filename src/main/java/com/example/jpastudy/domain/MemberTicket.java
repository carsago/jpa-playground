package com.example.jpastudy.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class MemberTicket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Ticket ticket;

    private Long memberId;

    public MemberTicket(Ticket ticket, Long memberId) {
        this(null, ticket, memberId);
    }

    public MemberTicket(Long id, Ticket ticket, Long memberId) {
        this.id = id;
        this.ticket = ticket;
        this.memberId = memberId;
    }
}
