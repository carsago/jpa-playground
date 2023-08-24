package com.example.jpastudy.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Store {

    @Id
    @GeneratedValue
    private Long id;
    private String name;

    @OneToMany(fetch = FetchType.LAZY)
    private List<Product> products = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY)
    private List<Employee> employees = new ArrayList<>();

    public Store(String name) {
        this(null, name);
    }

    public Store(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
