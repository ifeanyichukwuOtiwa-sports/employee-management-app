package org.gxstar.employee.service.request;

public record EmployeeUpdateRequest(
        String firstName,
        String lastName,
        String email
) {
}
