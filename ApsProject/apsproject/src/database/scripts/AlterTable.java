package apsproject.src.database.scripts;

import apsproject.src.database.connection.ConnectionDb;

public class AlterTable {
    
    public AlterTable() {

        ConnectionDb apsProjectDb = new ConnectionDb();

        //String sqlQuery = "ALTER TABLE RACE MODIFY COLUMN RC_EQUIP VARCHAR(11)";
        //String sqlQuery = "ALTER TABLE RACE MODIFY RC_COD VARCHAR(6) NOT NULL";
        //String sqlQuery = "ALTER TABLE RACE MODIFY RC_COD VARCHAR(6) NOT NULL";
        String sqlQuery = "ALTER TABLE RACE DROP PRIMARY KEY";

        apsProjectDb.openConnection();
        apsProjectDb.executeUpdate(sqlQuery);
        apsProjectDb.closeConnection();

    }

}
