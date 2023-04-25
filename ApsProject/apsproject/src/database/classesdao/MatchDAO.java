package apsproject.src.database.classesdao;

import java.sql.PreparedStatement;
import java.sql.SQLDataException;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

import apsproject.src.database.connection.ConnectionDb;
import apsproject.src.languages.LanguageManager;

public class MatchDAO {

    private final ConnectionDb conn;

    private String sqlQuery;

    private static JFrame screen;

    private boolean isSaveOk = true;

    public MatchDAO() {

        this.conn = new ConnectionDb();

    }

    public void insertSimulationValues(String cod,String participantName, String equipName, double lap1Value, double lap2Value, String totalValue, String type) throws SQLDataException {

        //=> Query contendo a consulta responsavel por adicionar os valores obtidos na minha Partida
        sqlQuery = "INSERT INTO RACE (RC_TYPE, RC_COD, RC_EQUIP, RC_PLAYER, RC_LAP1, RC_LAP2, RC_TOTAL) VALUES (?,?,?,?,?,?,?)";

        try {
            
            //=> Preparando a consulta para receber os valores dos parametros que serao inseridos
            PreparedStatement st = conn.openConnection().prepareStatement(sqlQuery);

            //=> Setando os meus parametros
            st.setString(1, type);
            st.setString(2, cod);
            st.setString(3, equipName);
            st.setString(4, participantName);
            st.setDouble(5, lap1Value);
            st.setDouble(6, lap2Value);
            st.setString(7, totalValue);
            st.executeUpdate();

            //=> Se o JOptionPane ja tiver sido exibido, o mesmo nao sera exibido novamente
            if(isSaveOk){
                String okText = LanguageManager.getInstance().getString("langOptionOk");
                UIManager.put("OptionPane.okButtonText", okText);
                JOptionPane.showMessageDialog(screen, LanguageManager.getInstance().getString("saveMatch"),"",JOptionPane.INFORMATION_MESSAGE);
            }

            isSaveOk = false;

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    //=> Metodo que uso para pegar a tela que vou adicionar o JOptionPane
    public static JFrame getScreen(JFrame screen){
        MatchDAO.screen = screen;
        return MatchDAO.screen;

    }
    
    //=> Metodo responsavel por fechar a conexao com o DB
    public void closeConnection() {

        conn.closeConnection();

    }
}

