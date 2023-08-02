package org.gxstar.employee.core;

import org.gxstar.employee.config.EmployeeManagementConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(EmployeeManagementConfig.class)
public class EmployeeManagementApp {
    public static void main(String[] args) {
        SpringApplication.run(EmployeeManagementApp.class, args);
    }
}
