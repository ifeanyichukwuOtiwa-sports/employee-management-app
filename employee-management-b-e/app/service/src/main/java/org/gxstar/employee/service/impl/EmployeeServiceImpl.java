package org.gxstar.employee.service.impl;

import java.util.List;

import org.gxstar.employee.persistence.model.Employee;
import org.gxstar.employee.persistence.repository.EmployeeRepository;
import org.gxstar.employee.service.api.EmployeeService;
import org.gxstar.employee.service.dto.EmployeeDto;
import org.gxstar.employee.service.request.EmployeeSaveRequest;
import org.gxstar.employee.service.request.EmployeeUpdateRequest;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;
    @Override
    public List<EmployeeDto> getEmployees() {
        return employeeRepository.findAll().stream().map(this::mapToDto).toList();
    }

    private EmployeeDto mapToDto(final Employee employee) {
        return new EmployeeDto(
                employee.getFirstName(),
                employee.getLastName(),
                employee.getEmail()
        );
    }

    @Override
    public EmployeeDto getEmployeeById(final String id) {
        return employeeRepository.findById(Integer.parseInt(id)).map(this::mapToDto).orElseThrow();
    }

    @Override
    public EmployeeDto createEmployee(final EmployeeSaveRequest request) {
        final Employee savedEmployee = employeeRepository.save(
                Employee.toSave(
                        request.firstName(),
                        request.lastName(),
                        request.email()
                )
        );
        return mapToDto(savedEmployee);
    }

    @Override
    public EmployeeDto updateEmployee(final String id, final EmployeeUpdateRequest request) {
        return employeeRepository.findById(Integer.parseInt(id)).map(this::mapToDto).orElseThrow();
    }

    @Override
    public void deleteEmployee(final String id) {
        employeeRepository.deleteById(Long.parseLong(id));
    }
}
