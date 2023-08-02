package org.gxstar.employee.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"org.gxstar.employee.service"})
public class EmployeeServiceConfig {
}
