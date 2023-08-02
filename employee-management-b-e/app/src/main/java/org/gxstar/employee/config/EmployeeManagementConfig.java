package org.gxstar.employee.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan(basePackages = {"org.gxstar.employee.core"})
@Import(EmployeePersistenceConfig.class)
public class EmployeeManagementConfig {
}
