package com.example.jpastudy.persistence;

import com.example.jpastudy.domain.Product;
import com.example.jpastudy.domain.Store;
import jakarta.persistence.EntityManager;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import org.junit.jupiter.api.Test;
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
    private ProductRepository productRepository;

    @Autowired
    private EntityManager em;

    private AtomicInteger atomicInteger = new AtomicInteger(1);

    @Test
    void 찾아오기_JpaRepository() {
        // given
        setUp("레이스");
        setUp("다이노스");

        List<Store> stores = storeRepository.findAllWithFetchJoinProduct();
        for (Store store : stores) {
            System.out.println("store = " + store);
        }
        // when

        // then
    }

    @Test
    void 찾아오기_entityManager() {
        // given
        setUp("레이스");
        setUp("다이노스");

        List<Store> stores = em.createQuery("SELECT s "
            + "FROM Store s "
            + "JOIN FETCH s.products ", Store.class)
            .getResultList();
        System.out.println("stores.size() = " + stores.size());
        for (Store store : stores) {
            System.out.println("store = " + store);
        }
        // when

        // then
    }

    private Long setUp(String storeName) {
        Store store = new Store(storeName);
        store.addProducts(new Product("양념통닭", 100));
        store.addProducts(new Product("치킨", 100));
        storeRepository.save(store);
        return store.getId();
    }
}
