package com.example.jpastudy.playground;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import org.junit.jupiter.api.Test;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(ReplaceUnderscores.class)
public class AnyTest {

    @Test
    void 테스트를_한다() {
        // when && then
        assertThat(1 + 3).isEqualTo(4);
    }

    @Test
    void 테스트를_한다_깨졌다유유() {
        // when && then
        assertThat(1 + 3).isEqualTo(2);
    }
}
