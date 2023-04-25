package apsproject.src.database.scripts;

import apsproject.src.database.connection.ConnectionDb;

public class ClearTable {
    

    public ClearTable() {

        ConnectionDb apsProjectDb = new ConnectionDb();

        String sqlQuery = "DELETE FROM RACE";

        apsProjectDb.openConnection();
        apsProjectDb.executeUpdate(sqlQuery);
        apsProjectDb.closeConnection();

    }

}
