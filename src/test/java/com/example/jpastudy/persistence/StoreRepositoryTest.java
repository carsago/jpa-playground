package com.example.jpastudy.persistence;

import com.example.jpastudy.domain.Product;
import com.example.jpastudy.domain.Store;
import jakarta.persistence.EntityManager;
import java.util.concurrent.atomic.AtomicInteger;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;

@DisplayNameGeneration(ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
@DataJpaTest
@TestPropertySource(properties = "spring.jpa.properties.hibernate.default_batch_fetch_size=1000")
class StoreRepositoryTest {

    @Autowired
    private StoreRepository storeRepository;

    @Autowired
    private EntityManager em;

    private AtomicInteger atomicInteger = new AtomicInteger(1);


    private Long setUp(String storeName) {
        Store store = new Store(storeName);
        store.addProducts(new Product("양념통닭" + atomicInteger.getAndIncrement(), 100));
        store.addProducts(new Product("고추바사삭" + atomicInteger.getAndIncrement(), 20));
        storeRepository.save(store);
        em.clear();
        return store.getId();
    }
}
