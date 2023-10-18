package com.example.jpastudy.domain;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int maxAmount;
    private boolean isOutOfStock;

    public Ticket() {
    }

    public Ticket(int maxAmount, boolean isOutOfStock) {
        this(null, maxAmount, isOutOfStock);
    }

    public Ticket(Long id, int maxAmount, boolean isOutOfStock) {
        this.id = id;
        this.maxAmount = maxAmount;
        this.isOutOfStock = isOutOfStock;
    }

    public Long getId() {
        return id;
    }

    public int getMaxAmount() {
        return maxAmount;
    }

    public boolean isOutOfStock() {
        return isOutOfStock;
    }
}
