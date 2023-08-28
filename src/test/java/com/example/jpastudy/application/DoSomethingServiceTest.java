package com.example.jpastudy.application;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@DisplayNameGeneration(ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
@ExtendWith(MockitoExtension.class)
class DoSomethingServiceTest {

    @InjectMocks
    private DoSomethingService doSomethingService;

    @Mock
    private AsyncEventPublisher asyncEventPublisher;

    @Test
    void 비동기_호출을_테스트한다() {
        // when
        doSomethingService.doSomethingWithAsync();

        // then
        verify(asyncEventPublisher, times(1)).doLongLogic(any());
    }
}
