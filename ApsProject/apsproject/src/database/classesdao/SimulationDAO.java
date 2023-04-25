package apsproject.src.database.classesdao;

import java.sql.PreparedStatement;
import java.sql.SQLDataException;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

import apsproject.src.database.connection.ConnectionDb;
import apsproject.src.languages.LanguageManager;

public class SimulationDAO {

    private final ConnectionDb conn;

    private String sqlQuery;

    private boolean isSaveOk = true;

    private static JFrame screen;

    public SimulationDAO() {

        this.conn = new ConnectionDb();

    }

    public void insertSimulationValues(String cod, String participantName, double lap1Value, double lap2Value, String totalValue, String type) throws SQLDataException {

        //=> Query contendo a consulta responsavel por adicionar os valores obtidos na minha simulacao
        sqlQuery = "INSERT INTO RACE (RC_TYPE, RC_COD, RC_PLAYER, RC_LAP1, RC_LAP2, RC_TOTAL) VALUES (?,?,?,?,?,?)";

        try {
            
            //=> Preparando a consulta para receber os valores dos parametros que serao inseridos
            PreparedStatement st = conn.openConnection().prepareStatement(sqlQuery);

            //=> Setando os meus parametros
            st.setString(1, type);
            st.setString(2, cod);
            st.setString(3, participantName);
            st.setDouble(4, lap1Value);
            st.setDouble(5, lap2Value);
            st.setString(6, totalValue);
            st.executeUpdate();
            
            //=> Se o JOptionPane ja tiver sido exibido, o mesmo nao sera exibido novamente
            if(isSaveOk){
                String okText = LanguageManager.getInstance().getString("langOptionOk");
                UIManager.put("OptionPane.okButtonText", okText);
                JOptionPane.showMessageDialog(screen, LanguageManager.getInstance().getString("saveSimu"),"",JOptionPane.INFORMATION_MESSAGE);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        isSaveOk = false;
    }

    //=> Metodo que uso para pegar a tela que vou adicionar o JOptionPane
    public static JFrame getScreen(JFrame screen){
        SimulationDAO.screen = screen;
        return SimulationDAO.screen;

    }
    
    //=> Metodo responsavel por fechar a conexao com o DB
    public void closeConnection() {

        conn.closeConnection();

    }
}
