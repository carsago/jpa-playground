package com.example.jpastudy.domain;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Employee {

    @Id
    @GeneratedValue
    private Long id;
    private String name;

    public Employee(String name) {
        this(null, name);
    }

    public Employee(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
