package org.gxstar.employee.service.dto;

public record EmployeeDto(
        String id,
        String firstName,
        String lastName,
        String email
) {
}
