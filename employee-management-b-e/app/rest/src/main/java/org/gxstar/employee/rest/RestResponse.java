package org.gxstar.employee.rest;

import java.util.Objects;

public record RestResponse<T>(
        T employeeItem,
        boolean hasItem
) {
    public static <T> RestResponse<T> of(final T item) {
        return new RestResponse<>(item, Objects.nonNull(item));
    }
}
