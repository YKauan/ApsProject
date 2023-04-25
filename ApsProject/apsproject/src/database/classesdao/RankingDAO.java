package apsproject.src.database.classesdao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLDataException;
import java.util.ArrayList;

import apsproject.src.database.connection.ConnectionDb;

public class RankingDAO {
    
    private final ConnectionDb conn;

    private static RankingDAO instance;

    private String sqlQuery = "";

    private String rcType;
    private String rcCod;
    private String rcPlayer;
    private String rcEquip;
    private String rcTotal;

    private Double rcLap1;
    private Double rcLap2;
    
    private ResultSet rs;
    private PreparedStatement ps;

    private static ArrayList<String> typeArr   = new ArrayList<String>();
    private static ArrayList<String> codArr    = new ArrayList<String>();
    private static ArrayList<String> playerArr = new ArrayList<String>();
    private static ArrayList<String> equipArr  = new ArrayList<String>();
    private static ArrayList<String> lap1Arr   = new ArrayList<String>();
    private static ArrayList<String> lap2Arr   = new ArrayList<String>();
    private static ArrayList<String> totalArr  = new ArrayList<String>();

    public RankingDAO() {

        this.conn = new ConnectionDb();

    }

    public void searchValues(String type, String cod, String player, String equip, String lap1, String lap2, String total) throws SQLDataException {

        //=> Montando a minha Query/Consulta
        if(!cod.isEmpty()){

            sqlQuery = "SELECT * FROM RACE WHERE RC_COD = '" + cod + "'";

        }else if(cod.isEmpty()){

            sqlQuery += "SELECT * FROM RACE WHERE RC_COD != ''";

            if(!type.isEmpty()){

                sqlQuery += " AND RC_TYPE = '" + type + "'";
    
            }
            
        } 
        
        if(!player.isEmpty()){

            sqlQuery += " AND RC_PLAYER = '" + player + "'";

        }
        
        if(!equip.isEmpty()){

            sqlQuery += " AND RC_EQUIP = '" + equip + "'";

        }
        
        if(!lap1.isEmpty()){

            sqlQuery += " AND RC_LAP1 = '" + Integer.parseInt(lap1) + "'";

        }
        
        if(!lap2.isEmpty()){

            sqlQuery += " AND RC_LAP2 = '" + Integer.parseInt(lap2) + "'";

        }
        
        if(!total.isEmpty()){

            sqlQuery += " AND RC_TOTAL = '" + total + "'";

        }

        sqlQuery += " ORDER BY RC_TOTAL";

        try {
            
            //=> Preparando a minha Query/Consulta
            ps = conn.openConnection().prepareStatement(sqlQuery);

            //=> Executando a minha Query/Consulta e armazenando o resultset
            rs = ps.executeQuery();

            //=> Enquando tiver uma linha no resultado da Query/Consulta continua fazendos os processos abaixo
            while (rs.next()) {

                //=> Pegando os dados das colunas minha consulta
                rcType   = rs.getString("RC_TYPE");
                rcCod    = rs.getString("RC_COD");
                rcPlayer = rs.getString("RC_PLAYER");
                rcEquip  = rs.getString("RC_EQUIP");
                rcLap1   = rs.getDouble("RC_LAP1");
                rcLap2   = rs.getDouble("RC_LAP2");
                rcTotal  = rs.getString("RC_TOTAL");

                //=> Adicionando os dados aos meus arrays
                typeArr.add(rcType);
                codArr.add(rcCod);
                playerArr.add(rcPlayer);
                equipArr.add(rcEquip);
                lap1Arr.add(Double.toString(rcLap1));
                lap2Arr.add(Double.toString(rcLap2));
                totalArr.add(rcTotal);
            }

            //=> Fechando as conexoes se as mesmas estiverem abertas
            if (ps != null) {
                ps.close();
            }
            if (rs != null) {
                rs.close();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        sqlQuery = "";

    }

    //=> Metodo responsavel por retornar o meu array contendo o tipo da corrida
    public ArrayList<String> getType(){

        return typeArr;

    }

    //=> Metodo responsavel por retornar o meu array contendo o codigo da corrida
    public ArrayList<String> getCod(){

        return codArr;

    }

    //=> Metodo responsavel por retornar o meu array contendo os Jogadores da corrida
    public ArrayList<String> getPlayer(){

        return playerArr;

    }

    //=> Metodo responsavel por retornar o meu array contendo as Equipes da corrida
    public ArrayList<String> getEquip(){

        return equipArr;

    }

    //=> Metodo responsavel por retornar o meu array contendo o tempo da Volta 1 de cada jogador
    public ArrayList<String> getLap1(){

        return lap1Arr;

    }

    //=> Metodo responsavel por retornar o meu array contendo o tempo da Volta 2 de cada jogador
    public ArrayList<String> getLap2(){

        return lap2Arr;

    }

    //=> Metodo responsavel por retornar o meu array contendo o total de cada jogador
    public ArrayList<String> getTotal(){

        return totalArr;

    }

    //=> Metodo responsavel por limpar o meu ArrayList
    public ArrayList<String> clearArraysList(ArrayList<String> array){

        array.clear();
        
        return array;

    }

    //=> Metodo responsavel por retornar a instancia da minha classe
    public static RankingDAO getInstance() {
        if (instance == null) {
            instance = new RankingDAO();
        }
        return instance;
    }

    //=> Metodo responsavel por fechar a minha conexao com o DB
    public void closeConnection() {

        conn.closeConnection();

    }

}
