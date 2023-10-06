package com.example.jpastudy.application;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BService {

    @Transactional
    public void a() {
        System.out.println("method a");
    }
}
