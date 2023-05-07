package apsproject.src.database.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConnectionDb {

    private final String URL  = "jdbc:mysql://localhost:3306/apsproject";
    private final String USER = "root";
    private final String PWD  = "Kauan123";

    private Connection conn = null;
    private PreparedStatement ps = null;
    
    //=> Metodo responsavel por abrir a conexao com o DB
    public Connection openConnection() {

        try {

            if (conn == null) {
                conn = DriverManager.getConnection(URL, USER, PWD);
                System.out.println("Conected");
                return conn;
            } else {
                return conn;
            }

        } catch (SQLException e) {
            System.out.println("Driver nao localizado");
            e.printStackTrace();
            return null;
        }

    }
    
    //=> Metodo responsavel por fechar a conexao com o DB
    public void closeConnection() {
        
        try {
            
            conn.close();
            if (ps != null) {
                ps.close();
            }

        } catch (SQLException e) {
           System.out.println("Nao foi possivel encerrar a conexao com o banco de daods!");
        }
       

    }

    //Metodo responsavel por executar uma Query/Consulta que me retorne dados
    public ResultSet executeQuery(String sqlQuery) {

        ResultSet rs = null;

        try {
            ps = conn.prepareStatement(sqlQuery);
            rs = ps.executeQuery();
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
        return rs;
    }

    //=> Metodo responsavel por executar updates
    public void executeUpdate(String sqlQuery) {

        try {
            ps = conn.prepareStatement(sqlQuery);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }

    }
}
