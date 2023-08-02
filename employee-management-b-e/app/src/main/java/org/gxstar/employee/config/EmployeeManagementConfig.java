package org.gxstar.employee.config;

import org.gxstar.employee.rest.config.EmployeeRestConfig;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan(basePackages = {"org.gxstar.employee.core"})
@Import(value = {EmployeeRestConfig.class})
public class EmployeeManagementConfig {
}
