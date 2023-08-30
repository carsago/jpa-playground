package com.example.jpastudy.support;

import com.example.jpastudy.domain.Something;
import com.example.jpastudy.domain.SomethingAuto;
import com.example.jpastudy.persistence.SomethingAutoRepository;
import com.example.jpastudy.persistence.SomethingRepository;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/init")
public class initController {

    private final SomethingRepository somethingRepository;
    private final SomethingAutoRepository somethingAutoRepository;
    private final StoreService storeService;

    @PostMapping("/store/{tryCount}")
    public ResponseEntity<Void> store(@PathVariable Integer tryCount) {
        LocalDateTime start = LocalDateTime.now();
        System.out.println("start = " + start);
        storeService.init(tryCount);
        System.out.println("end = " + Duration.between(start, LocalDateTime.now()));
        return ResponseEntity.ok().build();
    }

    @PostMapping("/table/{tryCount}")
    public ResponseEntity<Void> init(@PathVariable Integer tryCount) {
        LocalDateTime start = LocalDateTime.now();
        System.out.println("start = " + start);
        List<Something> somethings = new ArrayList<>();
        for (Integer i = 0; i < tryCount; i++) {
            somethings.add(new Something());
        }
        somethingRepository.saveAll(somethings);
        System.out.println("end = " + Duration.between(start, LocalDateTime.now()));
        return ResponseEntity.ok().build();
    }

    @PostMapping("/auto/{tryCount}")
    public ResponseEntity<Void> initAuto(@PathVariable Integer tryCount) {
        LocalDateTime start = LocalDateTime.now();
        System.out.println("start = " + start);
        List<SomethingAuto> somethings = new ArrayList<>();
        for (Integer i = 0; i < tryCount; i++) {
            somethings.add(new SomethingAuto());
        }
        somethingAutoRepository.saveAll(somethings);
        System.out.println("end = " + Duration.between(start, LocalDateTime.now()));
        return ResponseEntity.ok().build();
    }
}
