package com.example.jpastudy.application;

import com.example.jpastudy.domain.EventSender;
import com.example.jpastudy.domain.Product;
import com.example.jpastudy.persistence.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
@RequiredArgsConstructor
public class AsyncEventPublisher {

    private final ProductRepository productRepository;

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    @Async
    public void doLongLogic(EventSender sender) {
        System.out.println("---- 진입-------");
        productRepository.save(new Product("async에서 저장", 100));
    }

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    @Async
    public void update(UpdateEvent sender) {
        System.out.println("----update 진입-------");
        productRepository.deleteById(2L);
    }
}
