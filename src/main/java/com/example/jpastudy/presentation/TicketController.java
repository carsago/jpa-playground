package com.example.jpastudy.presentation;


import com.example.jpastudy.application.ticketing.MemberTicketServiceFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
public class TicketController {

    private final MemberTicketServiceFacade ticketService;

    @GetMapping("/ticket")
    public ResponseEntity<Void> ticketing() {
        ticketService.doit(1L);
        return ResponseEntity.ok().build();
    }
}
