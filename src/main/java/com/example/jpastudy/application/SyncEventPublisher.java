package com.example.jpastudy.application;

import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
public class SyncEventPublisher {

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void doLongLogic(Integer value) {
    }
}
