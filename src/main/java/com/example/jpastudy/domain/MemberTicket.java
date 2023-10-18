package com.example.jpastudy.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class MemberTicket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Ticket ticket;

    private boolean canUse;

    public MemberTicket() {
    }

    public MemberTicket(Ticket ticket, boolean canUse) {
        this(null,ticket, canUse);
    }

    public MemberTicket(Long id, Ticket ticket, boolean canUse) {
        this.id = id;
        this.ticket = ticket;
        this.canUse = canUse;
    }

    public Long getId() {
        return id;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public boolean isCanUse() {
        return canUse;
    }

    public void setCanUse(boolean right) {
        this.canUse = right;
    }
}
