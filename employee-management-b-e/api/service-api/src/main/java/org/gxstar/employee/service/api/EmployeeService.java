package org.gxstar.employee.service.api;

import java.util.List;

import org.gxstar.employee.service.dto.EmployeeDto;
import org.gxstar.employee.service.request.EmployeeSaveRequest;
import org.gxstar.employee.service.request.EmployeeUpdateRequest;

public interface EmployeeService {
    List<EmployeeDto> getEmployees();
    EmployeeDto getEmployeeById(String id);
    EmployeeDto createEmployee(EmployeeSaveRequest request);
    EmployeeDto updateEmployee(String id, EmployeeUpdateRequest request);
    void deleteEmployee(String id);
}
