package org.gxstar.employee.service.request;

public record EmployeeSaveRequest(
        String firstName,
        String lastName,
        String email
){
}
