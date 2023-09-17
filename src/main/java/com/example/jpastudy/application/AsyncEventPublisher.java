package com.example.jpastudy.application;

import com.example.jpastudy.domain.EventSender;
import com.example.jpastudy.domain.Product;
import com.example.jpastudy.persistence.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
@RequiredArgsConstructor
public class AsyncEventPublisher {

    private final ProductRepository productRepository;

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    @Transactional(propagation = Propagation.REQUIRES_NEW)
//    @Async
    public void doLongLogic(EventSender sender) {
        System.out.println("---- 진입-------");
        productRepository.save(new Product("안에서 저장", 100));
//        try {
//            Thread.sleep(10000);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
    }
}
