package org.gxstar.employee.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"org.gxstar.employee.persistence"})
@EnableAutoConfiguration
public class EmployeePersistenceConfig {
}
