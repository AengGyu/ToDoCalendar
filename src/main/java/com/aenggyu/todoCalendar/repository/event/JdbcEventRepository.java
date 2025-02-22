package com.aenggyu.todoCalendar.repository.event;

import com.aenggyu.todoCalendar.domain.event.Event;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public class JdbcEventRepository implements EventRepository {

    private final JdbcTemplate jdbcTemplate;

    public JdbcEventRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Event save(Event event) {
        String sql = "insert into event (title, description, start_date, end_date, member_id)" +
                " values (?, ?, ?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(sql, new String[]{"id"});
            ps.setString(1, event.getTitle());
            ps.setString(2, event.getDescription());
            ps.setObject(3, event.getStartDate());
            ps.setObject(4, event.getEndDate());
            ps.setLong(5, event.getMemberId());
            return ps;
        }, keyHolder);

        event.setId(keyHolder.getKey().longValue());
        return event;
    }

    @Override
    public List<Event> findByMemberId(Long memberId) {
        String sql = "select * from event where member_id = ? order by start_date";
        return jdbcTemplate.query(sql, eventRowMapper(), memberId);
    }

    @Override
    public List<Event> findAll() {
        String sql = "select * from event";
        return jdbcTemplate.query(sql, eventRowMapper());
    }

    @Override
    public Optional<Event> findById(Long id) {
        String sql = "select * from event where id = ?";
        try {
            Event event = jdbcTemplate.queryForObject(sql, eventRowMapper(), id);
            return Optional.of(event);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public void update(Event event) {
        String sql = "update event set title=?, description=?, start_date=?, end_date=? where id=?";
        jdbcTemplate.update(sql, event.getTitle(), event.getDescription(), event.getStartDate(), event.getEndDate(), event.getId());
    }

    @Override
    public void deleteById(Long id) {
        String sql = "delete from event where id = ?";
        jdbcTemplate.update(sql, id);
    }

    private RowMapper<Event> eventRowMapper() {
        return (rs, rowNum) -> {
            Event event = new Event();
            event.setId(rs.getLong("id"));
            event.setTitle(rs.getString("title"));
            event.setDescription(rs.getString("description"));
            event.setStartDate(rs.getObject("start_date", LocalDate.class));
            event.setEndDate(rs.getObject("end_date", LocalDate.class));
            event.setMemberId(rs.getLong("member_id"));
            return event;
        };
    }
}
