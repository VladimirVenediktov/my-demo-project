package ru.venediktov.testspringproject.repository.impl;

import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.venediktov.testspringproject.model.Developer;
import ru.venediktov.testspringproject.repository.CommonRepository;

@Repository
@RequiredArgsConstructor
public class DeveloperJdbcRepository implements CommonRepository<Developer> {

  private static final String SQL_INSERT = "insert into developer (id, name, created, modified, active)"
      + "values (:id,:name,:created,:modified,:active)";
  private static final String SQL_QUERY_FIND_ALL = "select id, name, created, modified, active from developer";
  private static final String SQL_QUERY_FIND_BY_ID = SQL_QUERY_FIND_ALL + " where id = :id";
  private static final String SQL_UPDATE = "update developer set name = :name, modified = :modified,"
      + "active = :active where id = :id";
  private static final String SQL_DELETE = "delete from developer where id = :id";

  private final NamedParameterJdbcTemplate jdbcTemplate;

  private RowMapper<Developer> toDoRowMapper = (ResultSet rs, int rowNum) -> {
    Developer developer = new Developer();
    developer.setId(rs.getString("id"));
    developer.setName(rs.getString("name"));
    developer.setModified(rs.getTimestamp("modified").toLocalDateTime());
    developer.setCreated(rs.getTimestamp("created").toLocalDateTime());
    developer.setActive(rs.getBoolean("active"));
    return developer;
  };

  @Override
  public Developer save(final Developer domain) {
    Developer result = findById(domain.getId());
    if (result != null) {
      result.setName(domain.getName());
      result.setActive(domain.isActive());
      result.setModified(LocalDateTime.now());
      return upsert(result, SQL_UPDATE);
    }
    return upsert(domain, SQL_INSERT);
  }

  private Developer upsert(final Developer developer, final String sql) {
    Map<String, Object> namedParameters = new HashMap<>();
    namedParameters.put("id", developer.getId());
    namedParameters.put("name", developer.getName());
    namedParameters.put("created", java.sql.Timestamp.valueOf(developer.getCreated()));
    namedParameters.put("modified", java.sql.Timestamp.valueOf(developer.getModified()));
    namedParameters.put("active", developer.isActive());

    this.jdbcTemplate.update(sql, namedParameters);

    return findById(developer.getId());
  }

  @Override
  public Iterable<Developer> save(Collection<Developer> domains) {
    domains.forEach(this::save);
    return findAll();
  }

  @Override
  public void delete(final Developer domain) {
    Map<String, String> namedParameters = Collections.singletonMap("id", domain.getId());
    this.jdbcTemplate.update(SQL_DELETE, namedParameters);
  }

  @Override
  public Developer findById(String id) {
    try {
      Map<String, String> namedParameters = Collections.singletonMap("id", id);
      return this.jdbcTemplate.queryForObject(SQL_QUERY_FIND_BY_ID, namedParameters, toDoRowMapper);
    } catch (EmptyResultDataAccessException ex) {
      return null;
    }
  }

  @Override
  public Iterable<Developer> findAll() {
    return this.jdbcTemplate.query(SQL_QUERY_FIND_ALL, toDoRowMapper);
  }

}