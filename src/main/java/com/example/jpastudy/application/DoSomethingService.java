package com.example.jpastudy.application;

import java.time.Duration;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Transactional
public class DoSomethingService {

    private final AsyncEventPublisher asyncEventPublisher;
    private final SyncEventPublisher syncEventPublisher;

    public void doSomethingWithAsync() {
        LocalDateTime start = LocalDateTime.now();
        System.out.println(start);
        asyncEventPublisher.doLongLogic(null);
        System.out.println(Duration.between(start, LocalDateTime.now()));
    }

    public void doSomethingWithSync() {
        LocalDateTime start = LocalDateTime.now();
        System.out.println(start);
        syncEventPublisher.doLongLogic(null);
        System.out.println(Duration.between(start, LocalDateTime.now()));
    }
}
