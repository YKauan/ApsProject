package apsproject.src.methods;

import java.awt.event.ActionEvent;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import apsproject.src.database.classesdao.MatchDAO;
import apsproject.src.database.classesdao.RankingDAO;
import apsproject.src.database.classesdao.SimulationDAO;
import apsproject.src.languages.LanguageManager;
import apsproject.src.screens.HelpScreenMatch;
import apsproject.src.screens.HelpScreenRanking;
import apsproject.src.screens.HelpScreenSimu;
import apsproject.src.screens.MatchScreen;
import apsproject.src.screens.RankingScreen;
import apsproject.src.screens.SimulationScreen;
import apsproject.src.screens.StartScreen;

public class Buttons {

    private static int count = 0;

    //=> Metodo responsavel pela funcao do botao voltar
    static public void backButton(ActionEvent e, JFrame thisScreen, JFrame previousScreen) {

        SwingUtilities.invokeLater(() -> {

            thisScreen.dispose();
            previousScreen.setVisible(true);

        });

    }

    //=> Metodo responsavel pelo botao sair
    static public void exitButton(ActionEvent e, JFrame exitScreen) {
        exitScreen.dispose();
    }

    //=> Metodo responsavel pelo botao sair da classe HelpScreenMatch
    static public void exitButtonHM(ActionEvent e, JFrame exitScreen) {
        exitScreen.dispose();
        HelpScreenMatch.getInstance().close();

    }

    //=> Metodo responsavel pelo botao sair da classe HelpScreenRanking
    static public void exitButtonHR(ActionEvent e, JFrame exitScreen) {
        exitScreen.dispose();
        HelpScreenRanking.getInstance().close();

    }

    //=> Metodo responsavel pelo botao sair da classe HelpScreenSimu
    static public void exitButtonHS(ActionEvent e, JFrame exitScreen) {
        exitScreen.dispose();
        HelpScreenSimu.getInstance().close();

    }

    //=> Metodo responsavel por instanciar a minha tela start
    static public void startButton(ActionEvent e, JFrame thisScreen) {
        new StartScreen(thisScreen);
        thisScreen.dispose();
    
    }

    //=> Metodo responsavel por instanciar a minha tela match
    static public void matchButton(ActionEvent e, JFrame thisScreen) {
        new MatchScreen(thisScreen);
        thisScreen.dispose();

    }

    //=> Metodo responsavel por instanciar a minha tela simulation
    static public void simulationButton(ActionEvent e, JFrame thisScreen) {
        new SimulationScreen(thisScreen);
        thisScreen.dispose();

    }

    //=> Metodo responsavel por instanciar a minha tela ranking
    static public void rankingButton(ActionEvent e, JFrame thisScreen) {
        new RankingScreen(thisScreen);
        thisScreen.dispose();

    }

    //=> Metodo responsavel por instanciar a minha tela help
    static public void helpButtonRank(ActionEvent e) {

        HelpScreenRanking helpScreen = HelpScreenRanking.getInstance();

        //=> Se a minha tela tiver sido instanciada eu seto o meu count como 0;
        if(helpScreen != null){
            
            Buttons.setCountHelp(0);

        }

    }

    //=> Metodo responsavel por instanciar a minha tela help
    static public void helpButtonMatch(ActionEvent e) {

        HelpScreenMatch helpScreenMatch = HelpScreenMatch.getInstance();

        //=> Se a minha tela tiver sido instanciada eu seto o meu count como 0;
        if(helpScreenMatch != null){

            Buttons.setCountHelp(0);

        }

    }

    //=> Metodo responsavel por instanciar a minha tela help
    static public void helpButtonSimu(ActionEvent e) {

        HelpScreenSimu helpScreenSimu = HelpScreenSimu.getInstance();

        //=> Se a minha tela tiver sido instanciada eu seto o meu count como 0;
        if(helpScreenSimu != null){

            Buttons.setCountHelp(0);

        }

    }

    //=> Metodo responsavel pelas funcoes dos botoes das telas de help
    static public int helpButtonFunctions(ActionEvent ev, JButton previousButton, JButton nextButton, int index) {

        //=> Verifico qual botao disparou o evento
        if (ev.getSource() == nextButton && count < 3) {
            count++;
        }
        else if (ev.getSource() == previousButton && count > 0) {
            count--;
        }

        //=> Validando quando os botoes devem ser desabilitados
        if(count == 3){
            nextButton.setEnabled(false);
            previousButton.setEnabled(true);
        }else if (count == 1 || count == 2){
            nextButton.setEnabled(true);
            previousButton.setEnabled(true);
        }else{
            nextButton.setEnabled(true);
            previousButton.setEnabled(false);
        }

        return count;

    }

    //=> Metodo responsavel por pegar o valor da minha variavel count
    public static int getCountHelp(){
        return count;
    }

    //=> Metodo responsavel por alterar o valor da minha variavel count
    public static int setCountHelp(int count){
        Buttons.count = count;
        return count;
    }

    //=> Metodo responsavel por alterar o idioma do meu programa
    static public void optionsButton(ActionEvent e, JButton sB, JButton rB, JButton oB, JButton eB, JFrame screen) {

        LanguageManager languageManager = LanguageManager.getInstance();

        //=> Array contendo as opcoes do comboBox
        String[] languages = {"日本", "English", "Español", "Português"};

        //=> Criando o comboBox com o meu array
        JComboBox<String> comboBox = new JComboBox<>(languages);

        JPanel panel = new JPanel();
        panel.add(comboBox);

        //=> Criando variavels que vou conter os textos dos botoes ok, cancel do JOptionPane
        String okText = languageManager.getString("langOptionOk");
        String cancelText = languageManager.getString("langOptionCancel");

        //=> Alterando o texto dos botoes do JOptionPane de acordo com o idioma selecionado
        UIManager.put("OptionPane.okButtonText", okText);
        UIManager.put("OptionPane.cancelButtonText", cancelText);

        //=> Pegando o resultado do JOptionPane
        int result = JOptionPane.showConfirmDialog(screen, panel, languageManager.getString("languageOption"), JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        
        if(result == JOptionPane.OK_OPTION){

            String selectedLang   = "en";
            String selectedLocale = "en";

            switch(comboBox.getSelectedIndex()) {
                case 0: // 日本
                    selectedLang = "jp";
                    selectedLocale = "jp";
                    break;
                case 1: // English
                    selectedLang = "en";
                    selectedLocale = "en";
                    break;
                case 2: // Español
                    selectedLang = "es";
                    selectedLocale = "es";
                    break;
                case 3: // Português
                    selectedLang = "br";
                    selectedLocale = "pt_BR";
                    break;
            } 
            
            //=> Atualizando o idioma de acordo com a opcao que o usuario selecionar
            languageManager.updateLanguage(selectedLang, selectedLocale);

            //=> Atualizando os textos que ja foram criados, com o novo idioma
            sB.setText(languageManager.getString("startButton"));
            rB.setText(languageManager.getString("rankingButton"));
            oB.setText(languageManager.getString("optionsButton"));
            eB.setText(languageManager.getString("exitButton"));
        }

    }

    //=> Metodo responsavel por salvar a simulacao
    static public void saveButtonSimu(ActionEvent e, String cod, JTextField[] participantName, JTextField[] lap1Value, JTextField[] lap2Value, JTextField[] totalValue, int lenght) {

        final SimulationDAO simuDao = new SimulationDAO();

        String type  = "S";

        for (int i = 0; i < lenght; i++) {

            //=> Atribuindo os valores dos meus arrays para variaveis
            String playerName = participantName[i].getText();
            Double lap1       = Double.parseDouble(lap1Value[i].getText());
            Double lap2       = Double.parseDouble(lap2Value[i].getText());
            String total      = totalValue[i].getText();

            //=> Tentando salvar os dados no DB
            try {
                simuDao.insertSimulationValues(cod, playerName, lap1, lap2, total, type);
            } catch (SQLException ex) {
                System.out.println("Falha ao salvar os dados");
                ex.printStackTrace();
            }
        }

        //Fechando a conexao com o DB
        simuDao.closeConnection();
    }

    //=> Metodo responsavel por salvar a minha partida
    static public void saveButtonMatch(ActionEvent e, String cod, JTextField[] participantName, JTextField[] equipsName, JTextField[] lap1Value, JTextField[] lap2Value, JTextField[] totalValue, int lenght) {

        final MatchDAO matchDao = new MatchDAO();

        String type  = "M";

        for (int i = 0; i < lenght; i++) {

            //=> Atribuindo os valores dos meus arrays para variaveis
            String playerName = participantName[i].getText();
            String equipName = equipsName[i].getText();
            Double lap1 = Double.parseDouble(lap1Value[i].getText());
            Double lap2 = Double.parseDouble(lap2Value[i].getText());
            String total = totalValue[i].getText();

            //=> Tentando salvar os dados no DB
            try {
                matchDao.insertSimulationValues(cod, playerName, equipName, lap1, lap2, total, type);
            } catch (SQLException ex) {
                System.out.println("Falha ao salvar os dados");
                ex.printStackTrace();
            }
        }
        
        //Fechando a conexao com o DB
        matchDao.closeConnection();
    }

    //=> Metodo responsavel por buscar os dados de acordo com os parametros passados
   static public void searchButton(ActionEvent e, String type, JTextField cod, JTextField player, JTextField equip, JTextField lap1, JTextField lap2, JTextField total){

        final RankingDAO rankingDao = RankingDAO.getInstance();
        
        String types   = type;
        String cods    = cod.getText();
        String players = player.getText();
        String equips  = equip.getText();
        String lap1s   = lap1.getText();
        String lap2s   = lap2.getText();
        String totals  = total.getText();

        //=> Tentando efetuar a consulta no DB
        try {
            rankingDao.searchValues(types, cods, players, equips, lap1s, lap2s, totals);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
}