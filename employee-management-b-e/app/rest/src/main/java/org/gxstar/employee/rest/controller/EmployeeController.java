package org.gxstar.employee.rest.controller;

import org.gxstar.employee.rest.RestResponse;
import org.gxstar.employee.rest.config.RestMappings;
import org.gxstar.employee.service.ListResponse;
import org.gxstar.employee.service.api.EmployeeService;
import org.gxstar.employee.service.dto.EmployeeDto;
import org.gxstar.employee.service.request.EmployeeSaveRequest;
import org.gxstar.employee.service.request.EmployeeUpdateRequest;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(path = RestMappings.EMPLOYEE_API_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping("list")
    public ListResponse<EmployeeDto> getAllEmployees() {
        return ListResponse.of(employeeService.getEmployees());
    }

    @PostMapping("save")
    public RestResponse<EmployeeDto> createEmployee(@RequestBody final EmployeeSaveRequest request) {
        return RestResponse.of(employeeService.createEmployee(request));
    }

    @GetMapping(path = "{id}")
    public RestResponse<EmployeeDto> getEmployee(@PathVariable(value = "id") final String id) {
        return RestResponse.of(employeeService.getEmployeeById(id));
    }

    @PutMapping(path = "update/{id}")
    public RestResponse<EmployeeDto> updateEmployee(@PathVariable(value = "id") final String id, @RequestBody final EmployeeUpdateRequest request) {
        return RestResponse.of(employeeService.updateEmployee(id, request));
    }

    @DeleteMapping(path = "{id}")
    public void deleteEmployee(@PathVariable(value = "id") final String id) {
        employeeService.deleteEmployee(id);
    }

}
