package com.example.jpastudy.application;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.jpastudy.application.ticketing.MemberTicketServiceFacade;
import com.example.jpastudy.domain.MemberTicket;
import com.example.jpastudy.domain.Ticket;
import com.example.jpastudy.persistence.MemberTicketRepository;
import com.example.jpastudy.persistence.TicketRepository;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import org.junit.jupiter.api.RepeatedTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@DisplayNameGeneration(ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
@SpringBootTest
class TicketingTest {

    @Autowired
    private MemberTicketServiceFacade ticketService;

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private MemberTicketRepository memberTicketRepository;

    @RepeatedTest(500)
    void 테스트() {
        // given
        Ticket ticket = ticketRepository.save(new Ticket(20, false));
        int tryCount = 40;
        ExecutorService executor = Executors.newFixedThreadPool(5);

        // when
        List<CompletableFuture<Void>> futures = IntStream.range(0, tryCount)
            .mapToObj(i -> CompletableFuture.runAsync(() -> {
                ticketService.doit(ticket.getId());
            }, executor).exceptionally(e -> null))
            .toList();
        futures.forEach(CompletableFuture::join);
        // when

        // then
        List<MemberTicket> memberTickets = memberTicketRepository.findAllByTicket(ticket).stream()
            .filter(MemberTicket::isCanUse)
            .toList();
        assertThat(memberTickets).hasSize(20);
    }
}
