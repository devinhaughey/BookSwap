package org.haughey.backend.Database;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Class to establish connection to the postgresql database. It uses the
 * provided url and port number to connect to the databse along with the
 * given username and password. These settings should be kept somewhere else.
 *
 * @author dhaugh
 */
public class DatabaseConnection {

    public Connection getDBConnection() {
        Connection connection = null;

        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager
                    .getConnection("jdbc:postgresql://localhost:5432/postgres","postgres","test123");
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("DB Connection opened");
        return connection;
    }

}
