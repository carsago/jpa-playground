package com.example.jpastudy.persistence;

import com.example.jpastudy.domain.Employee;
import com.example.jpastudy.domain.Product;
import com.example.jpastudy.domain.Store;
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

    private AtomicInteger atomicInteger = new AtomicInteger(1);

    @Test
    void 일반_조인으로_쿼리_여러방() {
        // given
        Long storeId = setUp("우리 가게");

        // when
        Store findStore = storeRepository.findById(storeId).get();
        for (Employee employee : findStore.getEmployees()) {
            System.out.println("employee.getName() = " + employee.getName());
        }
        for (Product product : findStore.getProducts()) {
            System.out.println("product.getName() = " + product.getName());
        }
    }

    @Test
    void 그냥찾아오기() {
        // given
        setUp("현서 가게");
        setUp("얀디 가게");

        // when
        List<Store> stores = storeRepository.findAll();
        for (Store store : stores) {
            for (Employee employee : store.getEmployees()) {
                System.out.println("employee.getId() = " + employee.getId());
                System.out.println("employee.getName() = " + employee.getName());
            }
            for (Product product : store.getProducts()) {
                System.out.println("product.getId() = " + product.getId());
                System.out.println("product.getName() = " + product.getName());
            }
        }
    }

    @Test
    void fetch_join_으로_한방() {
        // given
        setUp("현서 가게");
        setUp("얀디 가게");

        // when
        List<Store> stores = storeRepository.findAllWithFetchJoin();
        for (Store store : stores) {
            System.out.println("store.getId() = " + store.getId());
            System.out.println("----------------------");
            for (Employee employee : store.getEmployees()) {
                System.out.println("employee.getId() = " + employee.getId());
                System.out.println("employee.getName() = " + employee.getName());
            }
            System.out.println("----------------------");

            for (Product product : store.getProducts()) {
                System.out.println("product.getId() = " + product.getId());
                System.out.println("product.getName() = " + product.getName());
            }

            System.out.println("----------------------");
        }
    }

    private Long setUp(String storeName) {
        Store store = new Store(storeName);
        store.addProducts(new Product("양념통닭" + atomicInteger.getAndIncrement(), 100));
        store.addProducts(new Product("고추바사삭" + atomicInteger.getAndIncrement(), 20));
        store.hireEmployee(new Employee("장병규" + atomicInteger.getAndIncrement()));
        store.hireEmployee(new Employee("오타니" + atomicInteger.getAndIncrement()));
        storeRepository.save(store);
        return store.getId();
    }
}
