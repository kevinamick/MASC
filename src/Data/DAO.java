package Data;

import java.sql.Connection;
import java.sql.SQLException;

public class DAO {
    protected Connection database;

    public void open() {
        database = Database.connect();
    }

    public void close() {
        try {
            database.close();
        } catch (SQLException e) {
            System.err.println("Something went wrong when closing connection.");
            System.err.println(e.getMessage());
        }
    }
}
