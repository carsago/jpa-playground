package com.example.jpastudy.application;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class AService {

    private final BService bService;

    public void a() {
        System.out.println("method a");
        bService.a();
    }
}
