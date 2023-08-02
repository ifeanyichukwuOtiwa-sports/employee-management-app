package org.gxstar.employee.persistence.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class Employee {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;

    public static Employee toSave(final String firstName, final String lastName, final String email) {
        return new Employee(null, firstName, lastName, email);
    }
}
