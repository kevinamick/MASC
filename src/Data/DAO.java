package Data;

import java.sql.Connection;

public class DAO {
    protected Connection database;

    public DAO() {
        database = Database.connect();
    }
}
