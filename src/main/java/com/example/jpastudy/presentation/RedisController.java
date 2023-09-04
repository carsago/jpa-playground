package com.example.jpastudy.presentation;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/redis")
public class RedisController {

    private final RedisTemplate<String, String> redisTemplate;
    private final ThreadLocalRandom random = ThreadLocalRandom.current();
    private AtomicInteger atomicInteger = new AtomicInteger(1);

    @GetMapping
    public ResponseEntity<Void> doSomething() {
        ZSetOperations<String, String> zSetOperations = redisTemplate.opsForZSet();
        String formattedTime = LocalDateTime.now().minusHours(random.nextInt(0, 12))
            .format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));

        double doubleTime = Double.parseDouble(formattedTime);
        zSetOperations.add("time",
            String.valueOf(atomicInteger.incrementAndGet()),
            doubleTime);
        return ResponseEntity.ok().build();
    }
}
