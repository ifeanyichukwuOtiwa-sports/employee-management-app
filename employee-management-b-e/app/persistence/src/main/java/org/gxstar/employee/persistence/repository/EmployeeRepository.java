package org.gxstar.employee.persistence.repository;

import org.gxstar.employee.persistence.model.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository {
    List<Employee> findAll();
    Employee save(Employee employee);
    Employee update(Employee employee);
    Optional<Employee> findById(long id);
    void deleteById(long id);
}
