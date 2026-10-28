package com.example;

import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserServiceTest {

    @Test
    void shouldReturnUsernameForExistingUser() throws Exception {

        try (Connection connection =
                     DriverManager.getConnection("jdbc:h2:mem:testdb")) {

            try (Statement statement = connection.createStatement()) {
                statement.execute(
                    "CREATE TABLE users (id VARCHAR(50), username VARCHAR(100))"
                );

                statement.execute(
                    "INSERT INTO users VALUES ('1001', 'bharath')"
                );
            }

            UserService userService = new UserService();

            String username = userService.getUser(connection, "1001");

            assertEquals("bharath", username);
        }
    }
}
