package com.example.jpastudy.application;

import java.sql.PreparedStatement;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class JdbcMemberTicketRepository {

    private static final String MEMBER_TICKET_INSERT_SQL = "INSERT INTO member_ticket (ticket_id, can_use) VALUES (?, ?)";
    private final JdbcTemplate jdbcTemplate;

    public Long save(Long ticketId) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(MEMBER_TICKET_INSERT_SQL, new String[] { "id" });
            ps.setLong(1, ticketId);
            ps.setBoolean(2, false);
            return ps;
        }, keyHolder);
        return keyHolder.getKey().longValue();
    }
}
