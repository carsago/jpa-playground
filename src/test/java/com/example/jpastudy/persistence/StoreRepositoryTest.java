package com.example.jpastudy.persistence;

import com.example.jpastudy.domain.Employee;
import com.example.jpastudy.domain.Product;
import com.example.jpastudy.domain.Store;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DisplayNameGeneration(ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
@DataJpaTest
class StoreRepositoryTest {

    @Autowired
    StoreRepository storeRepository;

    @Autowired
    EntityManager em;

    @Test
    void 일반_조인으로_쿼리_여러방() {
        // given
        Long storeId = setUp();

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
    void fetch_join_으로_한방() {
        // given
        Long storeId = setUp();

        // when
        Store findStore = storeRepository.findByIdWithFetchJoin(storeId).get();
        for (Employee employee : findStore.getEmployees()) {
            System.out.println("employee.getName() = " + employee.getName());
        }
        for (Product product : findStore.getProducts()) {
            System.out.println("product.getName() = " + product.getName());
        }
    }

    private Long setUp() {
        Store store = new Store("우리 가게");
        store.addProducts(new Product("양념통닭", 100));
        store.addProducts(new Product("고추바사삭", 20));
        store.hireEmployee(new Employee("장병규"));
        store.hireEmployee(new Employee("오타니"));
        storeRepository.save(store);
        em.clear();
        return store.getId();
    }
}
