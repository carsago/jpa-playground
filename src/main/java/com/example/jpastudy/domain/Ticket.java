package com.example.jpastudy.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer amount;
    private LocalDateTime entryTime;


    public Ticket(Integer amount, LocalDateTime entryTime) {
        this(null, amount, entryTime);
    }

    public Ticket(Long id, Integer amount, LocalDateTime entryTime) {
        this.id = id;
        this.amount = amount;
        this.entryTime = entryTime;
    }
}
