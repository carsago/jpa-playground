package com.example.jpastudy.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.TableGenerator;
import lombok.Getter;

@Getter
@Entity
@TableGenerator(
    name = "seq_generator",
    table = "sequences",
    pkColumnName = "charger_seq",
    allocationSize = 1000
)
public class Something {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "seq_generator")
    private Long id;

    public Something() {
    }

    public Something(Long id) {
        this.id = id;
    }
}
