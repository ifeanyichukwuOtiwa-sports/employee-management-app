package org.gxstar.employee.persistence.repository;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

import java.util.Objects;
import java.util.Optional;
import java.util.function.Predicate;

import org.assertj.core.api.Condition;
import org.gxstar.employee.config.EmployeePersistenceConfig;
import org.gxstar.employee.persistence.model.Employee;
import org.gxstar.test.containers.BootStrapContainers;
import org.jetbrains.annotations.Nullable;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.jdbc.Sql;


@BootStrapContainers(value = {EmployeePersistenceConfig.class})
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class EmployeeRepositoryTest {
    @Autowired
    private EmployeeRepository repository;

    @Autowired
    private NamedParameterJdbcOperations jdbcOperations;

    private static Predicate<Employee> getEmployeePredicate() {
        final Predicate<Employee> emailPredicate = x -> Objects.equals("ikechi@ch.com", x.getEmail());
        final Predicate<Employee> firstNamePredicate = x -> Objects.equals("ikechi", x.getFirstName());
        final Predicate<Employee> lastNamePredicate = x -> Objects.equals("China", x.getLastName());
        return emailPredicate.and(firstNamePredicate).and(lastNamePredicate);
    }

    private static RowMapper<Employee> mapperRow() {
        return (rs, n) -> new Employee(
                rs.getLong("id"),
                rs.getString("first_name"),
                rs.getString("last_name"),
                rs.getString("email")
        );
    }

    @Test
    @Sql(value = "classpath:inserts.sql")
    void findAll() {

        assertThat(repository.findAll()).hasSize(10);
    }

    @Test
    void save() {
        final Employee saved = repository.save(Employee.toSave("Ikechi", "China", "ikechi@ch.com"));
        final var employeePredicate = getEmployeePredicate();
        assertThat(saved.getId()).isNotNull();
        assertThat(saved.getFirstName()).isEqualTo("Ikechi");
        assertThat(saved.getEmail()).isEqualTo("ikechi@ch.com");
        assertThat(saved.getLastName()).isEqualTo("China");
    }

    @Test
    @Sql("classpath:inserts.sql")
    void update() {
        final Employee employee = findEmployee("John");

        final Employee actual = new Employee(employee.getId(), employee.getFirstName(), employee.getLastName(), employee.getEmail());

        employee.setEmail("john.doe@cgc.com");

        final Employee update = repository.update(employee);

        assertThat(update.getId()).isEqualTo(actual.getId());
        assertThat(update.getEmail()).isEqualTo("john.doe@cgc.com");


    }

    @Nullable
    private Employee findEmployee(final String firstName) {
        final String sql = """
                SELECT id, first_name, last_name, email
                FROM employee e WHERE e.first_name = :firstName;
                """;
        final SqlParameterSource params = new MapSqlParameterSource().addValue("firstName", firstName);
        return jdbcOperations.query(sql, params, mapperRow()).stream().findFirst().orElse(null);
    }

    @Test
    @Sql("classpath:inserts.sql")
    void findById() {
        final Employee actual = findEmployee("John");
        final Optional<Employee> employee = repository.findById(actual.getId());
        assertThat(employee).isNotEmpty().contains(actual);
    }

    @Test
    @Sql("classpath:inserts.sql")
    void deleteById() {
        final Employee actual = findEmployee("James");

        repository.deleteById(actual.getId());

        final Optional<Employee> employee = repository.findById(actual.getId());
        assertThat(employee).isEmpty();
    }
}