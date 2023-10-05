package com.example.jpastudy.presentation;

import com.example.jpastudy.application.DoSomethingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class TestController {

    private final DoSomethingService doSomethingService;

    @GetMapping("/async")
    public ResponseEntity<Void> doSomethingWithAsync() {
        doSomethingService.doSomethingWithAsync();
        return ResponseEntity.ok().build();
    }

    @PostMapping("/save")
    public ResponseEntity<Void> save() {
        doSomethingService.save();
        return ResponseEntity.ok().build();
    }

    @PostMapping("/update")
    public ResponseEntity<Void> update() {
        doSomethingService.update();
        return ResponseEntity.ok().build();
    }

    @GetMapping("/sync")
    public ResponseEntity<Void> doSomethingWithSync() {
        doSomethingService.doSomethingWithSync();
        return ResponseEntity.ok().build();
    }
}
