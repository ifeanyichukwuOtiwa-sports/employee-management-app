package org.gxstar.test.containers;

import java.util.Objects;

import org.testcontainers.containers.MySQLContainer;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MySqlSingletonContainer {
    public static final String MYSQL_VERSION = "8.0.33-oracle";

    public static final String SCHEMA = "employee_db";


    private static MySQLContainer<?> mySQLContainer;

    public static <T extends MySQLContainer<T>> MySQLContainer<T> getContainerInstance() {
        if (Objects.isNull(mySQLContainer)) {
            mySQLContainer = startedMySQLContainer();
        }
        //noinspection unchecked
        return (MySQLContainer<T>) mySQLContainer;
    }

    private static <T extends MySQLContainer<T>> MySQLContainer<T> startedMySQLContainer() {
        final MySQLContainer<T> target = MySQLContainerTools.mysql(SCHEMA, MYSQL_VERSION);
        target.start();
        return target;
    }
}
