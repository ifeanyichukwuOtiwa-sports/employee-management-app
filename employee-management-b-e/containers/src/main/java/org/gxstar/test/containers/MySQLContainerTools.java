package org.gxstar.test.containers;

import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.containers.Network;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.utility.DockerImageName;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MySQLContainerTools {
    @SuppressWarnings("resource")
    public static <T extends MySQLContainer<T>> MySQLContainer<T> mysql(final String schema, final String mysqlVersion) {
        return new MySQLContainer<T>(DockerImageName.parse("mysql:%s".formatted(mysqlVersion)).asCompatibleSubstituteFor("mysql"))
                .withDatabaseName(schema)
                .withUsername("test")
                .withPassword("test")
                .withNetwork(Network.newNetwork())
                .waitingFor(Wait.forLogMessage("*ready for connections*", 1));
    }
}
