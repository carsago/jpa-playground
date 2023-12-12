package com.example.jpastudy.domain;

import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@DisplayNameGeneration(ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
@SpringBootTest
class StoreRepositoryTest {

    @Autowired
    private StoreRepository storeRepository;

    @Test
    void 테스트() {
        // given
        storeRepository.save(new Store(
                List.of(new Item(), new Item()),
                Set.of(new Customer("현서"), new Customer("서현"))));

        // when
        storeRepository.findById(1L);
        // then
    }

}
