package org.gxstar.employee.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public record ListResponse<T>(
        List<T> items,
        long count
) {
    public static <T> ListResponse<T> of(Collection<? extends T> items) {
        return new ListResponse<>(new ArrayList<>(items), items.size());
    }

    public static <T> ListResponse<T> empty() {
        return new ListResponse<>(new ArrayList<>(), 0);
    }
}
