package org.gxstar.employee.persistence.repository.impl;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.gxstar.employee.persistence.model.Employee;
import org.gxstar.employee.persistence.repository.EmployeeRepository;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RequiredArgsConstructor
@Slf4j
@Repository
public class EmployeeRepositoryImpl implements EmployeeRepository {
    private final NamedParameterJdbcOperations namedParameterJdbcOperations;
    @Override
    public List<Employee> findAll() {
        final String sql = """
                SELECT id, first_name, last_name, email
                FROM
                employee;
                """;
        return namedParameterJdbcOperations.query(sql, mapToRow());
    }

    private RowMapper<Employee> mapToRow() {
        return (rs, rn) -> new Employee(
                rs.getLong("id"),
                rs.getString("first_name"),
                rs.getString("last_name"),
                rs.getString("email")
        );
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Employee save(final Employee employee) {
        final int key = doCreate(employee);
        return findById(key).orElseThrow();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Employee update(final Employee employee) {
        final String sql = """
                UPDATE employee e
                SET e.first_name = :firstName,
                e.last_name = :lastName,
                e.email = :email
                WHERE e.id = :id;
                """;
        final SqlParameterSource params = new MapSqlParameterSource()
                .addValue("id", employee.getId())
                .addValue(Field.FIRST_NAME.getName(), employee.getFirstName())
                .addValue(Field.LAST_NAME.getName(), employee.getLastName())
                .addValue(Field.EMAIL.getName(), employee.getEmail());
        namedParameterJdbcOperations.update(sql, params);
        return findById(employee.getId()).orElseThrow();
    }

    private int doCreate(final Employee employee) {
        final String sql = """
                INSERT INTO employee(first_name, last_name, email)
                VALUE(:firstName, :lastName, :email)
                """;
        final SqlParameterSource params = new MapSqlParameterSource()
                .addValue(Field.FIRST_NAME.getName(), employee.getFirstName())
                .addValue(Field.LAST_NAME.getName(), employee.getLastName())
                .addValue(Field.EMAIL.getName(), employee.getEmail());
        final GeneratedKeyHolder keyholder = new GeneratedKeyHolder();
        namedParameterJdbcOperations.update(sql, params, keyholder);
        return Objects.requireNonNull(keyholder.getKey()).intValue();
    }

    @Override
    public Optional<Employee> findById(final long id) {
        final String sql = """
                SELECT id, first_name, last_name, email
                FROM employee e
                WHERE e.id = :id;
                """;
        SqlParameterSource params = new MapSqlParameterSource().addValue("id", id);
        return namedParameterJdbcOperations.query(sql, params, mapToRow()).stream().findFirst();
    }

    @Override
    public void deleteById(final long id) {
        final String sql = """
                DELETE FROM employee e WHERE e.id = :id;
                """;
        SqlParameterSource params = new MapSqlParameterSource().addValue("id", id);
        namedParameterJdbcOperations.update(sql, params);
    }

    @RequiredArgsConstructor
    @Getter
    private enum Field{
        FIRST_NAME("firstName"),
        LAST_NAME("lastName"),
        EMAIL("email");

        private final String name;

    }
}
