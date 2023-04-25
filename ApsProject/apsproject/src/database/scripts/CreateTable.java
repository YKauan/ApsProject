package apsproject.src.database.scripts;

import apsproject.src.database.connection.ConnectionDb;

public class CreateTable {
    
    public CreateTable() {

        ConnectionDb apsProjectDb = new ConnectionDb();

        String sqlQuery = "";

        sqlQuery += "CREATE TABLE RACE";
        sqlQuery += "(";
        sqlQuery += "  RC_TYPE CHAR(1),";
        sqlQuery += "  RC_COD INT(6) ZEROFILL PRIMARY KEY AUTO_INCREMENT,";
        sqlQuery += "  RC_PLAYER VARCHAR(11),";
        sqlQuery += "  RC_EQUIP VARCHAR(5),";
        sqlQuery += "  RC_LAP1 DECIMAL,";
        sqlQuery += "  RC_LAP2 DECIMAL,";
        sqlQuery += "  RC_TOTAL VARCHAR(15)";
        sqlQuery += ")";

        apsProjectDb.openConnection();
        apsProjectDb.executeUpdate(sqlQuery);
        apsProjectDb.closeConnection();

    }

}
