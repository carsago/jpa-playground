package com.example.jpastudy.persistence;

import com.example.jpastudy.domain.Product;
import jakarta.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DisplayNameGeneration(ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
@DataJpaTest
class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private EntityManager em;

    @Test
    void 업데이트() {
        // given
        List<Product> products = new ArrayList<>();
        List<Integer> addQuantities = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            products.add(productRepository.save(new Product("상품", 100)));
            addQuantities.add(i);
        }

        for (int i = 0; i < products.size(); i++) {
            products.get(i).addQuantity(addQuantities.get(i));

        }

        em.flush();
    }

}
