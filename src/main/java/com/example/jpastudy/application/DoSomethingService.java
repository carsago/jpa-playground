package com.example.jpastudy.application;

import com.example.jpastudy.domain.EventSender;
import com.example.jpastudy.domain.Product;
import com.example.jpastudy.persistence.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Transactional
public class DoSomethingService {

    private final ApplicationEventPublisher applicationEventPublisher;
    private final ProductRepository productRepository;

    public void doSomethingWithAsync() {
        productRepository.save(new Product("밖에서 저장", 100));
        applicationEventPublisher.publishEvent(new EventSender());
    }

    public void doSomethingWithSync() {
        productRepository.save(new Product("동기 상품", 100));
    }
}
