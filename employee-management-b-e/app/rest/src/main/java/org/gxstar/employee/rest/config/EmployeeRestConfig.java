package org.gxstar.employee.rest.config;

import org.gxstar.employee.config.EmployeeServiceConfig;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan(basePackages = {"org.gxstar.employee.rest"})
@Import(value = {EmployeeServiceConfig.class})
public class EmployeeRestConfig {
}
