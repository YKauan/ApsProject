package apsproject.src.screens;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;

import apsproject.src.database.classesdao.RankingDAO;
import apsproject.src.languages.LanguageManager;
import apsproject.src.methods.ButtonSearchRank;
import apsproject.src.methods.Buttons;
import apsproject.src.methods.LastRaces;
import apsproject.src.methods.Limits;
import apsproject.src.methods.Styles;
import apsproject.src.methods.Table;
import apsproject.src.panels.leftpanel.LeftPanelMS;
import apsproject.src.panels.rankingscreenpanels.RightPanel;

public class RankingScreen {

    private Screen screen;

    private SpringLayout layout;

    private LeftPanelMS leftPanel;
    private RightPanel rankingPanel;

    private JButton backButton;
    private JButton exitButton;
    private JButton searchButton;
    private JButton helpButton;
    private JButton matchButton;
    private JButton simuButton;
    private JButton lastButton;

    private JRadioButton raceRadButton;

    private JComboBox<String> typeOfRace;

    private JTextField codRace;
    private JTextField player;
    private JTextField equip;
    private JTextField lap1;
    private JTextField lap2;
    private JTextField total;

    private JLabel typeOfRaceTxt;
    private JLabel codRaceTxt;
    private JLabel playerTxt;
    private JLabel equipTxt;
    private JLabel lap1Txt;
    private JLabel lap2Txt;
    private JLabel totalTxt;
    private JLabel rcPLayer;
    private JLabel rcEquip;
    private JLabel rcLap1;
    private JLabel rcLap2;
    private JLabel rcTotal;

    private Table table;

    private JScrollPane js;

    private ButtonSearchRank searchValid;

    private RankingDAO rankDAO;

    private String[] comboOption;

    private Font JTextFieldFont;

    private LastRaces lastRaces;

    private Styles styles;

    private Color purple = Color.decode("#6B3FA0");

    private Border border = BorderFactory.createLineBorder(purple, 2);

    private LanguageManager languageManager;

    private static ArrayList<JLabel> arr = new ArrayList<>();

    public RankingScreen(JFrame previousScreen){

        SwingUtilities.invokeLater(() -> {

            styles = new Styles();

            languageManager = LanguageManager.getInstance();

            JTextFieldFont = new Font("Courier New", Font.ITALIC + Font.BOLD, 12);

            screen = new Screen(1);

            //=> Criando os paineis 
            leftPanel    = new LeftPanelMS();
            rankingPanel = new RightPanel();

            //=> Criando os JButtons
            searchButton = new JButton(languageManager.getString("searchButton"));
            simuButton   = new JButton(languageManager.getString("simulationButton"));
            matchButton  = new JButton(languageManager.getString("matchButton"));
            lastButton   = new JButton(languageManager.getString("lastRacesTxt"));
            backButton   = new JButton(languageManager.getString("backButton"));
            exitButton   = new JButton(languageManager.getString("exitButton"));
            helpButton   = new JButton();

            raceRadButton = new JRadioButton();

            lastRaces = LastRaces.getInstance();

            //=> Criando o comboBox com os tipos de corrida
            comboOption = new String[] {"M","S"};
            typeOfRace = new JComboBox<>(comboOption);

            //=> Criando os JTextField
            codRace = new JTextField();
            player  = new JTextField();
            equip   = new JTextField();
            lap1    = new JTextField();
            lap2    = new JTextField();
            total   = new JTextField();

            //=> Criando os JLabels
            typeOfRaceTxt = new JLabel(languageManager.getString("typeOfRace"));
            codRaceTxt    = new JLabel(languageManager.getString("codOfRaceTxt"));
            playerTxt     = new JLabel(languageManager.getString("playerTxt"));
            equipTxt      = new JLabel(languageManager.getString("equipTxt"));
            lap1Txt       = new JLabel(languageManager.getString("lap1Txt"));
            lap2Txt       = new JLabel(languageManager.getString("lap2Txt"));
            totalTxt      = new JLabel(languageManager.getString("totalTxt"));

            typeOfRace.setForeground(purple);

            //=> Definindo os Limites/Regras dos JTextField
            codRace.setDocument(new Limits(6,1));
            player.setDocument(new Limits(11,1));
            equip.setDocument(new Limits(11,1));
            lap1.setDocument(new Limits(5,0));
            lap2.setDocument(new Limits(5,0));
            total.setDocument(new Limits(15,1));

            //=> Definindo a borda dos JTextField
            codRace.setBorder(border);
            player.setBorder(border);
            equip.setBorder(border);
            lap1.setBorder(border);
            lap2.setBorder(border);
            total.setBorder(border);

            //=> Definindo a Fonte dos JTextField
            codRace.setFont(JTextFieldFont);
            player.setFont(JTextFieldFont);
            equip.setFont(JTextFieldFont);
            lap1.setFont(JTextFieldFont);
            lap2.setFont(JTextFieldFont);
            total.setFont(JTextFieldFont);

            //=> Definindo a cor do texto dos JTextField
            codRace.setForeground(Color.BLACK);
            player.setForeground(Color.BLACK);
            equip.setForeground(Color.BLACK);
            lap1.setForeground(Color.BLACK);
            lap2.setForeground(Color.BLACK);
            total.setForeground(Color.BLACK);

            //=> Adicionando o toolTip ao JTextField total
            total.setToolTipText(languageManager.getString("toolTipJTxt") + " 00:00:00 ");

            styles.styleRadButton(raceRadButton);

            styles.styleJButtonsHelp(helpButton);

            raceRadButton.setSelected(true);

            //=> Desabilitando os JTextFiel, deixando apenas o do codigo
            codRace.setEnabled(true);
            player.setEnabled(false);
            equip.setEnabled(false);
            lap1.setEnabled(false);
            lap2.setEnabled(false);
            total.setEnabled(false);
            typeOfRace.setEnabled(false);

            //=> Definindo a cor de fundo dos JTextField desabilitados
            player.setBackground(Color.GRAY);
            equip.setBackground(Color.GRAY);
            lap1.setBackground(Color.GRAY);
            lap2.setBackground(Color.GRAY);
            total.setBackground(Color.GRAY);

            //=> Definindo o estilos dos JTextField quando em foco
            styles.styleJTextFieldFocus(codRace);
            styles.styleJTextFieldFocus(player);
            styles.styleJTextFieldFocus(equip);
            styles.styleJTextFieldFocus(lap1);
            styles.styleJTextFieldFocus(lap2);
            styles.styleJTextFieldFocus(total);
            
            //=> Definindo a cor do texto das JLabels
            typeOfRaceTxt.setForeground(Color.WHITE);
            codRaceTxt.setForeground(Color.WHITE);
            playerTxt.setForeground(Color.WHITE);
            equipTxt.setForeground(Color.WHITE);
            lap1Txt.setForeground(Color.WHITE);
            lap2Txt.setForeground(Color.WHITE);
            totalTxt.setForeground(Color.WHITE);

            //=> Definindo o tamanho dos JTextField
            codRace.setPreferredSize(new Dimension(100,25));
            player.setPreferredSize(new Dimension(100,25));
            equip.setPreferredSize(new Dimension(100,25));
            lap1.setPreferredSize(new Dimension(100,25));
            lap2.setPreferredSize(new Dimension(100,25));
            total.setPreferredSize(new Dimension(100,25));

            //=> Definindo os estilos dos JButtons
            styles.styleJButtons(matchButton);
            styles.styleJButtons(simuButton);
            styles.styleJButtons(lastButton);
            styles.styleJButtons(searchButton);
            styles.styleJButtons(backButton);
            styles.styleJButtons(exitButton);

            //=> Adicionando os Action Listenner ao botoes
            typeOfRace.addActionListener(e -> comboAction(e));
            raceRadButton.addActionListener(e -> radAction(e));
            lastButton.addActionListener(e -> lastRaces.showLastRaces(screen));
            helpButton.addActionListener(e -> Buttons.helpButtonRank(e));
            searchButton.addActionListener(e -> {Buttons.searchButton(e, (String) typeOfRace.getSelectedItem(), codRace, player, equip, lap1, lap2, total);searchAction(e);});
            matchButton.addActionListener(e -> Buttons.matchButton(e, screen));
            simuButton.addActionListener(e -> Buttons.simulationButton(e, screen));
            backButton.addActionListener(e -> Buttons.backButton(e, screen, previousScreen));
            exitButton.addActionListener(e -> Buttons.exitButton(e, screen));
            
            //=> Definindo o Tamanho dos paineis
            leftPanel.setPreferredSize(new Dimension(400, screen.getHeight() - 015));
            rankingPanel.setPreferredSize(new Dimension(screen.getWidth() - 200, screen.getHeight() - 015));

            //=> Definindo o layout
            layout = new SpringLayout();
            rankingPanel.setLayout(layout);
            leftPanel.setLayout(layout);

            screen.setLayout(new BorderLayout());

            //=> Solicitando o foco para o JTextField codRace
            codRace.requestFocus();

            //=> Adicionando os componentes ao leftPanel
            leftPanel.add(raceRadButton);
            leftPanel.add(typeOfRace);
            leftPanel.add(typeOfRaceTxt);
            leftPanel.add(codRace);
            leftPanel.add(codRaceTxt);
            leftPanel.add(player);
            leftPanel.add(playerTxt);
            leftPanel.add(equip);
            leftPanel.add(equipTxt);
            leftPanel.add(lap1);
            leftPanel.add(lap1Txt);
            leftPanel.add(lap2);
            leftPanel.add(lap2Txt);
            leftPanel.add(total);
            leftPanel.add(totalTxt);
            leftPanel.add(helpButton);
            leftPanel.add(lastButton);
            leftPanel.add(matchButton);
            leftPanel.add(simuButton);
            leftPanel.add(searchButton);
            leftPanel.add(backButton);
            leftPanel.add(exitButton);

            //=> Posicionando os componentes no leftPanel
            layout.putConstraint(SpringLayout.WEST  , typeOfRace    , 150 , SpringLayout.WEST  , leftPanel);
            layout.putConstraint(SpringLayout.NORTH , typeOfRace    , 150 , SpringLayout.NORTH , leftPanel);
            layout.putConstraint(SpringLayout.WEST  , typeOfRaceTxt , 015 , SpringLayout.WEST  , leftPanel);
            layout.putConstraint(SpringLayout.NORTH , typeOfRaceTxt , 150 , SpringLayout.NORTH , leftPanel);
            layout.putConstraint(SpringLayout.WEST  , codRace       , 150 , SpringLayout.WEST  , leftPanel);
            layout.putConstraint(SpringLayout.NORTH , codRace       , 200 , SpringLayout.NORTH , leftPanel);
            layout.putConstraint(SpringLayout.WEST  , codRaceTxt    , 015 , SpringLayout.WEST  , leftPanel);
            layout.putConstraint(SpringLayout.NORTH , codRaceTxt    , 200 , SpringLayout.NORTH , leftPanel);
            layout.putConstraint(SpringLayout.WEST  , raceRadButton , 275 , SpringLayout.WEST  , leftPanel);
            layout.putConstraint(SpringLayout.NORTH , raceRadButton , 200 , SpringLayout.NORTH , leftPanel);
            layout.putConstraint(SpringLayout.WEST  , player        , 150 , SpringLayout.WEST  , leftPanel);
            layout.putConstraint(SpringLayout.NORTH , player        , 250 , SpringLayout.NORTH , leftPanel);
            layout.putConstraint(SpringLayout.WEST  , playerTxt     , 015 , SpringLayout.WEST  , leftPanel);
            layout.putConstraint(SpringLayout.NORTH , playerTxt     , 250 , SpringLayout.NORTH , leftPanel);
            layout.putConstraint(SpringLayout.WEST  , equip         , 150 , SpringLayout.WEST  , leftPanel);
            layout.putConstraint(SpringLayout.NORTH , equip         , 300 , SpringLayout.NORTH , leftPanel);
            layout.putConstraint(SpringLayout.WEST  , equipTxt      , 015 , SpringLayout.WEST  , leftPanel);
            layout.putConstraint(SpringLayout.NORTH , equipTxt      , 300 , SpringLayout.NORTH , leftPanel);
            layout.putConstraint(SpringLayout.WEST  , lap1          , 150 , SpringLayout.WEST  , leftPanel);
            layout.putConstraint(SpringLayout.NORTH , lap1          , 350 , SpringLayout.NORTH , leftPanel);
            layout.putConstraint(SpringLayout.WEST  , lap1Txt       , 015 , SpringLayout.WEST  , leftPanel);
            layout.putConstraint(SpringLayout.NORTH , lap1Txt       , 350 , SpringLayout.NORTH , leftPanel);
            layout.putConstraint(SpringLayout.WEST  , lap2          , 150 , SpringLayout.WEST  , leftPanel);
            layout.putConstraint(SpringLayout.NORTH , lap2          , 400 , SpringLayout.NORTH , leftPanel);
            layout.putConstraint(SpringLayout.WEST  , lap2Txt       , 015 , SpringLayout.WEST  , leftPanel);
            layout.putConstraint(SpringLayout.NORTH , lap2Txt       , 400 , SpringLayout.NORTH , leftPanel);
            layout.putConstraint(SpringLayout.WEST  , total         , 150 , SpringLayout.WEST  , leftPanel);
            layout.putConstraint(SpringLayout.NORTH , total         , 450 , SpringLayout.NORTH , leftPanel);
            layout.putConstraint(SpringLayout.WEST  , totalTxt      , 015 , SpringLayout.WEST  , leftPanel);
            layout.putConstraint(SpringLayout.NORTH , totalTxt      , 450 , SpringLayout.NORTH , leftPanel);
            layout.putConstraint(SpringLayout.WEST  , helpButton    , 373 , SpringLayout.WEST   , leftPanel);
            layout.putConstraint(SpringLayout.NORTH , helpButton    , 004 , SpringLayout.NORTH  , leftPanel);
            layout.putConstraint(SpringLayout.WEST  , matchButton   , 180 , SpringLayout.WEST  , leftPanel);
            layout.putConstraint(SpringLayout.NORTH , matchButton   , 550 , SpringLayout.NORTH , leftPanel);
            layout.putConstraint(SpringLayout.WEST  , simuButton    , 280 , SpringLayout.WEST  , leftPanel);
            layout.putConstraint(SpringLayout.NORTH , simuButton    , 550 , SpringLayout.NORTH , leftPanel);
            layout.putConstraint(SpringLayout.WEST  , searchButton  , 285 , SpringLayout.WEST  , leftPanel);
            layout.putConstraint(SpringLayout.NORTH , searchButton  , 500 , SpringLayout.NORTH , leftPanel);
            layout.putConstraint(SpringLayout.WEST  , lastButton    , 015 , SpringLayout.WEST  , leftPanel);
            layout.putConstraint(SpringLayout.NORTH , lastButton    , 500 , SpringLayout.NORTH , leftPanel);
            layout.putConstraint(SpringLayout.WEST  , backButton    ,  85 , SpringLayout.WEST  , leftPanel);
            layout.putConstraint(SpringLayout.NORTH , backButton    , 550 , SpringLayout.NORTH , leftPanel);
            layout.putConstraint(SpringLayout.WEST  , exitButton    , 010 , SpringLayout.WEST  , leftPanel);
            layout.putConstraint(SpringLayout.NORTH , exitButton    , 550 , SpringLayout.NORTH , leftPanel);

            //=> Adicionando os paineis a tela
            screen.add(leftPanel    , BorderLayout.WEST);
            screen.add(rankingPanel , BorderLayout.CENTER);

            //=> Verifica se o radButton esta selecionando
            if(raceRadButton.isSelected()){

                //=> Valida se pode ativar o botao de busca
                searchValid = new ButtonSearchRank(searchButton, codRace);

            }

        });
    }

    //=> Metodo responsavel por validar o tipo de corrida
    private void comboAction(ActionEvent e) {

        String type = (String) typeOfRace.getSelectedItem();

        //=> Se o tipo for igual a S desabilita o JTextField de equipe
        if(type == "S"){
            equip.setEnabled(false);
            equip.setBackground(Color.GRAY);
            equip.setText("");
        }else{
            equip.setEnabled(true);
            equip.setBackground(Color.WHITE);
        }

    }

    //=> Metodo responsavel pela validacao do radButton
    private void radAction(ActionEvent e) {

        if(raceRadButton.isSelected()){

            //=> Valida se o botao de busca pode ser habilitado
            searchValid = new ButtonSearchRank(searchButton, codRace);

            codRace.setEnabled(true);
            player.setEnabled(false);
            equip.setEnabled(false);
            lap1.setEnabled(false);
            lap2.setEnabled(false);
            total.setEnabled(false);
            typeOfRace.setEnabled(false);

            codRace.setBackground(Color.WHITE);
            player.setBackground(Color.GRAY);
            equip.setBackground(Color.GRAY);
            lap1.setBackground(Color.GRAY);
            lap2.setBackground(Color.GRAY);
            total.setBackground(Color.GRAY);

            codRace.setText("");
            player.setText("");
            equip.setText("");
            lap1.setText("");
            lap2.setText("");
            total.setText("");

        } else {

            //=> remove o documentListenner do campo codigo da corrida
            searchValid.removeDocumentListener();

            //=> Desabilita o campo do codigo da corrida
            codRace.setEnabled(false);
            codRace.setBackground(Color.GRAY);
            codRace.setText("");

            //=> Habilita os outros campos
            player.setEnabled(true);
            equip.setEnabled(true);
            lap1.setEnabled(true);
            lap2.setEnabled(true);
            total.setEnabled(true);
            typeOfRace.setEnabled(true);

            player.setBackground(Color.WHITE);
            equip.setBackground(Color.WHITE);
            lap1.setBackground(Color.WHITE);
            lap2.setBackground(Color.WHITE);
            total.setBackground(Color.WHITE);

        }

    }


    
    private void searchAction(ActionEvent e){

        rankDAO = RankingDAO.getInstance();


        Integer[] rcPLayerColuns  = {375,545,200,715,060};
        Integer[] rcPLayerLines   = {205,260,290,326,366};

        Integer[] rcEquipColuns   = {375,545,200,715,060};
        Integer[] rcEquipLines    = {236,292,321,357,398};
        
        Integer[] rcLap1Coluns    = {375,545,200,715,060};
        Integer[] rcLap1Lines     = {270,325,356,391,431};

        Integer[] rcLap2Coluns    = {375,545,200,715,060};
        Integer[] rcLap2Lines     = {302,357,386,423,463};

        Integer[] rcTotalColuns   = {375,545,200,715,060};
        Integer[] rcTotalLines = { 333, 389, 418, 455, 495 };
        
        for (Component component : rankingPanel.getComponents()) {
            if (component instanceof JLabel) {
                rankingPanel.remove(component);
            }
        }
        
        rankingPanel.repaint();
        rankingPanel.revalidate();
        
        if (raceRadButton.isSelected()) {

            if(table != null){

                rankingPanel.remove(js);
                rankingPanel.remove(table);                 
                rankingPanel.revalidate();
                rankingPanel.repaint();
                rankingPanel.setLayout(layout);
            }       

            for (int i = 0; i < rankDAO.getCod().size(); i++) {

                rcPLayer = new JLabel();
                rcEquip  = new JLabel();
                rcLap1   = new JLabel();
                rcLap2   = new JLabel();
                rcTotal  = new JLabel();

                arr.add(rcPLayer);
                arr.add(rcEquip);
                arr.add(rcLap1);
                arr.add(rcLap2);
                arr.add(rcTotal);

                rcPLayer.setText((rankDAO.getPlayer().get(i)));
                rcEquip.setText((rankDAO.getEquip().get(i)));
                rcLap1.setText((rankDAO.getLap1().get(i)));
                rcLap2.setText((rankDAO.getLap2().get(i)));
                rcTotal.setText((rankDAO.getTotal().get(i)));

                rcPLayer.setForeground(Color.WHITE);
                rcEquip.setForeground(Color.WHITE);
                rcLap1.setForeground(Color.WHITE);
                rcLap2.setForeground(Color.WHITE);
                rcTotal.setForeground(Color.WHITE);

                rankingPanel.add(rcPLayer);
                rankingPanel.add(rcEquip);
                rankingPanel.add(rcLap1);
                rankingPanel.add(rcLap2);
                rankingPanel.add(rcTotal);

                layout.putConstraint(SpringLayout.WEST  , rcPLayer , rcPLayerColuns[i] , SpringLayout.WEST  , rankingPanel);
                layout.putConstraint(SpringLayout.NORTH , rcPLayer , rcPLayerLines[i]  , SpringLayout.NORTH , rankingPanel);
                layout.putConstraint(SpringLayout.WEST  , rcEquip  , rcEquipColuns[i]  , SpringLayout.WEST  , rankingPanel);
                layout.putConstraint(SpringLayout.NORTH , rcEquip  , rcEquipLines[i]   , SpringLayout.NORTH , rankingPanel);
                layout.putConstraint(SpringLayout.WEST  , rcLap1   , rcLap1Coluns[i]   , SpringLayout.WEST  , rankingPanel);
                layout.putConstraint(SpringLayout.NORTH , rcLap1   , rcLap1Lines[i]    , SpringLayout.NORTH , rankingPanel);
                layout.putConstraint(SpringLayout.WEST  , rcLap2   , rcLap2Coluns[i]   , SpringLayout.WEST  , rankingPanel);
                layout.putConstraint(SpringLayout.NORTH , rcLap2   , rcLap2Lines[i]    , SpringLayout.NORTH , rankingPanel);
                layout.putConstraint(SpringLayout.WEST  , rcTotal  , rcTotalColuns[i]  , SpringLayout.WEST  , rankingPanel);
                layout.putConstraint(SpringLayout.NORTH , rcTotal  , rcTotalLines[i]   , SpringLayout.NORTH , rankingPanel);
        
            }


            rankingPanel.paintImage(1);

            codRace.setText("");

        }else {

            table = Table.getInstance();
            table.updateTableData();

            if(arr.size() != 0){

                for(int i = 0; i < arr.size(); i++){

                    rankingPanel.remove(arr.get(i));

                }

            }

            rankingPanel.setLayout(new BorderLayout());

            js = table.getJScrollPane();

            table.revalidate();

            rankingPanel.add(BorderLayout.CENTER, js);
            rankingPanel.paintImage(0);
            
            player.setText("");
            equip.setText("");
            lap1.setText("");
            lap2.setText("");
            total.setText("");

        }

        rankingPanel.revalidate();

        rankDAO.clearArraysList(rankDAO.getPlayer());
        rankDAO.clearArraysList(rankDAO.getEquip());
        rankDAO.clearArraysList(rankDAO.getLap1());
        rankDAO.clearArraysList(rankDAO.getLap2());
        rankDAO.clearArraysList(rankDAO.getTotal());
        rankDAO.clearArraysList(rankDAO.getCod());
        rankDAO.clearArraysList(rankDAO.getType());

    }
}