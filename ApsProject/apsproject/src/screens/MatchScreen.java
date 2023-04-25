package apsproject.src.screens;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;

import apsproject.src.database.classesdao.MatchDAO;
import apsproject.src.languages.LanguageManager;
import apsproject.src.methods.ButtonSaveMatch;
import apsproject.src.methods.Buttons;
import apsproject.src.methods.LastRaces;
import apsproject.src.methods.Limits;
import apsproject.src.methods.Styles;
import apsproject.src.methods.UpdateFieldsMatch;
import apsproject.src.panels.leftpanel.LeftPanelMS;
import apsproject.src.panels.matchscreenpanels.MatchScreenRighPanel;

public class MatchScreen {

    private Screen screen;

    private LeftPanelMS leftPanel;
    private MatchScreenRighPanel rightPanel;

    private JLabel title;
    private JLabel txt1;
    private JLabel codOfRace;
    private JLabel players;
    private JLabel equips;
    private JLabel lap1;
    private JLabel lap2;
    private JLabel totalTxt;

    private JTextField[] playersName;
    private JTextField[] playersEquip; 
    private JTextField[] lapTime1;
    private JTextField[] lapTime2;
    private JTextField[] totalTimes;

    private JButton simulationButton;
    private JButton rankingButton;
    private JButton saveButton;
    private JButton backButton;
    private JButton exitButton;
    private JButton helpButton;

    private JRadioButton oneParticipant;
    private JRadioButton twoParticipants;
    private JRadioButton threeParticipants;
    private JRadioButton fourParticipants;
    private JRadioButton fiveParticipants;
    private ButtonGroup  radParticipantsGroup;

    private ButtonSaveMatch saveValid;

    private Random random;

    private String cod;

    private int num;
    private int radValueSelected;

    private LastRaces lastRaces;

    private SpringLayout layout;

    private Font JTextFieldFont;

    private Styles styles;

    private Color purple = Color.decode("#6B3FA0");

    private Border border = BorderFactory.createLineBorder(purple, 2);

    private LanguageManager languageManager;
    
    public MatchScreen(JFrame previousScreen){

        SwingUtilities.invokeLater(() -> {

            JTextFieldFont = new Font("Courier New", Font.ITALIC + Font.BOLD, 12);

            languageManager = LanguageManager.getInstance();
            
            styles = new Styles();

            screen = new Screen(1);

            //=> Gerando o codigo da corrida
            random = new Random();
            num    = random.nextInt(90000) + 10000;
            cod    = "M" + num;

            lastRaces = LastRaces.getInstance();

            codOfRace = new JLabel(cod);
            codOfRace.setForeground(Color.WHITE);

            //=> Criando os Paineis 
            leftPanel  = new LeftPanelMS();
            rightPanel = new MatchScreenRighPanel();

            title = new JLabel(languageManager.getString("titleAps"));
            txt1  = new JLabel(languageManager.getString("numberOfParticipants"));
            
            title.setForeground(Color.WHITE);
            txt1.setForeground(Color.WHITE);

            //=> Criando os JButtons
            simulationButton = new JButton(languageManager.getString("simulationButton"));
            rankingButton    = new JButton(languageManager.getString("rankingButton"));
            saveButton       = new JButton(languageManager.getString("saveButton"));
            backButton       = new JButton(languageManager.getString("backButton"));
            exitButton       = new JButton(languageManager.getString("exitButton"));
            helpButton       = new JButton();

            //=> Definindo os estilos dos JButtons
            styles.styleJButtonsHelp(helpButton);
            styles.styleJButtons(simulationButton);
            styles.styleJButtons(rankingButton);
            styles.styleJButtons(saveButton);
            styles.styleJButtons(backButton);
            styles.styleJButtons(exitButton);

            //=> Criando os JRadioButtons
            oneParticipant    = new JRadioButton("1");
            twoParticipants   = new JRadioButton("2");
            threeParticipants = new JRadioButton("3");
            fourParticipants  = new JRadioButton("4");
            fiveParticipants  = new JRadioButton("5");

            //=> Definindo os estilos dos JRadButtons
            styles.styleRadButton(oneParticipant);
            styles.styleRadButton(twoParticipants);
            styles.styleRadButton(threeParticipants);
            styles.styleRadButton(fourParticipants);
            styles.styleRadButton(fiveParticipants);

            //=> Definindo o fiveParticipants como selecionando
            fiveParticipants.setSelected(true);

            //=> Criando um ButtonGroup com os Radbuttons
            radParticipantsGroup = new ButtonGroup();
            radParticipantsGroup.add(oneParticipant);
            radParticipantsGroup.add(twoParticipants);
            radParticipantsGroup.add(threeParticipants);
            radParticipantsGroup.add(fourParticipants);
            radParticipantsGroup.add(fiveParticipants);

            //=> Adicionando os ActionListnner aos botoes e radbuttons
            oneParticipant.addActionListener(e -> radAction(e));
            twoParticipants.addActionListener(e -> radAction(e));
            threeParticipants.addActionListener(e -> radAction(e));
            fourParticipants.addActionListener(e -> radAction(e));
            fiveParticipants.addActionListener(e -> radAction(e));
            simulationButton.addActionListener(e -> Buttons.simulationButton(e, screen));
            rankingButton.addActionListener(e -> Buttons.rankingButton(e, screen));
            saveButton.addActionListener(e -> {Buttons.saveButtonMatch(e, cod, playersName, playersEquip, lapTime1, lapTime2, totalTimes,radValueSelected);clearFields(e);});
            helpButton.addActionListener(e -> Buttons.helpButtonMatch(e));
            backButton.addActionListener(e -> Buttons.backButton(e, screen, previousScreen));
            exitButton.addActionListener(e -> Buttons.exitButton(e, screen));

            //=> Definindo o tamanho dos paineies 
            leftPanel.setPreferredSize(new Dimension(400, screen.getHeight() - 100));
            rightPanel.setPreferredSize(new Dimension(screen.getWidth() - 200, screen.getHeight() - 100));

            //=> Definindo o layout da tela e paineis
            layout = new SpringLayout();
            rightPanel.setLayout(layout);
            leftPanel.setLayout(layout);

            screen.setLayout(new BorderLayout());

            //=> Adicionando os componentes aos leftPanel 
            leftPanel.add(title);
            leftPanel.add(txt1);
            leftPanel.add(oneParticipant);
            leftPanel.add(twoParticipants);
            leftPanel.add(threeParticipants);
            leftPanel.add(fourParticipants);
            leftPanel.add(fiveParticipants);
            leftPanel.add(simulationButton);
            leftPanel.add(rankingButton);
            leftPanel.add(saveButton);
            leftPanel.add(backButton);
            leftPanel.add(helpButton);
            leftPanel.add(exitButton);
            rightPanel.add(codOfRace);

            //=> Inicializando os meu JTextField[]
            playersName  = new JTextField[5];
            playersEquip = new JTextField[5];
            lapTime1     = new JTextField[5];
            lapTime2     = new JTextField[5];
            totalTimes   = new JTextField[5];

            //=> Criando os arrays que vao ser responsaveis pelos JLabels
            String[] playersNameArr = {languageManager.getString("player1"),languageManager.getString("player2"),languageManager.getString("player3"),languageManager.getString("player4"),languageManager.getString("player5")};
            String[] equipsArr      = {languageManager.getString("team1"),languageManager.getString("team2"),languageManager.getString("team3"),languageManager.getString("team4"),languageManager.getString("team5")};
            String[] lapTimeTxt1Arr = {languageManager.getString("lap1Player1"),languageManager.getString("lap1Player2"),languageManager.getString("lap1Player3"),languageManager.getString("lap1Player4"),languageManager.getString("lap1Player5")};
            String[] lapTimeTxt2Arr = {languageManager.getString("lap2Player1"),languageManager.getString("lap2Player2"),languageManager.getString("lap2Player3"),languageManager.getString("lap2Player4"),languageManager.getString("lap2Player5")};
            String[] totalTimeArr   = {languageManager.getString("totalTimeP1"),languageManager.getString("totalTimeP2"),languageManager.getString("totalTimeP3"),languageManager.getString("totalTimeP4"),languageManager.getString("totalTimeP5")};

            //=> Arrays contendo as posicoes de coluna e linhas do meu JTextField e JLabels
            Integer[] nameColuns        = { 80, 80, 80, 80, 80};
            Integer[] nameLines         = {100,200,300,400,500};
            Integer[] playersColuns     = {180,180,180,180,180};
            Integer[] playersLines      = {100,200,300,400,500};
            
            Integer[] equipsNamesColuns = { 80, 80, 80, 80, 80};
            Integer[] equipsNameLines   = {135,235,335,435,535};
            Integer[] equipsColuns      = {180,180,180,180,180};
            Integer[] equipsLines       = {127,227,327,427,527};

            Integer[] txtLap1Coluns     = {360,360,360,360,360};
            Integer[] txtLap1Lines      = {100,200,300,400,500};
            Integer[] lapTime1Coluns    = {510,510,510,510,510};
            Integer[] lapTime1Lines     = {100,200,300,400,500};

            Integer[] txtLap2Coluns     = {360,360,360,360,360};
            Integer[] txtLap2LInes      = {125,225,325,425,525};
            Integer[] lapTime2Coluns    = {510,510,510,510,510};
            Integer[] lapTime2Lines     = {127,227,327,427,527};

            Integer[] txtTotalColuns    = {650,650,650,650,650};
            Integer[] txtTotalLines     = {100,200,300,400,500};
            Integer[] totalTimesColuns  = {675,675,675,675,675};
            Integer[] totalTimesLines   = {125,225,325,425,525};

            for (int i = 0; i < 5; i++){

                //=> Inicializando o meu UpdateFieldsMatch que possui as regras de validacao dos JTextField 
                UpdateFieldsMatch updater = new UpdateFieldsMatch(lapTime1, lapTime2, totalTimes, i);

                //=> Criando os JLabels 
                players  = new JLabel(playersNameArr[i]);
                equips   = new JLabel(equipsArr[i]);
                lap1     = new JLabel(lapTimeTxt1Arr[i]);
                lap2     = new JLabel(lapTimeTxt2Arr[i]);
                totalTxt = new JLabel(totalTimeArr[i]);

                //=> Definindo a cor do texto dos JLabels
                players.setForeground(Color.WHITE);
                equips.setForeground(Color.WHITE);
                lap1.setForeground(Color.WHITE);
                lap2.setForeground(Color.WHITE);
                totalTxt.setForeground(Color.WHITE);
    
                //=> Criando os JTextField
                playersName[i] = new JTextField();
                playersName[i].setForeground(Color.BLACK);
                playersName[i].setFont(JTextFieldFont);
                playersName[i].setBorder(border);
                playersName[i].setPreferredSize(new Dimension(100,25));
                playersName[i].setDocument(new Limits(11,1));//0: verificar se ha numeros no campo | 1: nao verifica se ha numeros no campo
                styles.styleJTextFieldFocus(playersName[i]);
                
                playersEquip[i] = new JTextField();
                playersEquip[i].setForeground(Color.BLACK);
                playersEquip[i].setFont(JTextFieldFont);
                playersEquip[i].setBorder(border);
                playersEquip[i].setPreferredSize(new Dimension(100,25));
                playersEquip[i].setDocument(new Limits(11,1));//0: verificar se ha numeros no campo | 1: nao verifica se ha numeros no campo
                styles.styleJTextFieldFocus(playersEquip[i]);

                lapTime1[i] = new JTextField("0.0");
                lapTime1[i].setForeground(Color.BLACK);
                lapTime1[i].setFont(JTextFieldFont);
                lapTime1[i].setBorder(border);
                lapTime1[i].setPreferredSize(new Dimension(100,25));
                lapTime1[i].setDocument(new Limits(5,0));//0: verificar se ha numeros no campo | 1: nao verifica se ha numeros no campo
                lapTime1[i].getDocument().addDocumentListener(updater);
                styles.styleJTextFieldFocus(lapTime1[i]);

                lapTime2[i] = new JTextField("0.0");
                lapTime2[i].setForeground(Color.BLACK);
                lapTime2[i].setFont(JTextFieldFont);
                lapTime2[i].setBorder(border);
                lapTime2[i].setPreferredSize(new Dimension(100,25));
                lapTime2[i].setDocument(new Limits(5,0));//0: verificar se ha numeros no campo | 1: nao verifica se ha numeros no campo
                lapTime2[i].getDocument().addDocumentListener(updater);
                styles.styleJTextFieldFocus(lapTime2[i]);

                totalTimes[i] = new JTextField("0.0");
                totalTimes[i].setForeground(Color.BLACK);
                totalTimes[i].setFont(JTextFieldFont);
                totalTimes[i].setBorder(border);
                totalTimes[i].setPreferredSize(new Dimension(100,25));
                totalTimes[i].setDisabledTextColor(Color.RED);
                totalTimes[i].setBackground(Color.GRAY);
                totalTimes[i].setEnabled(false);
                totalTimes[i].setDocument(new Limits(15,1));//0: verificar se ha numeros no campo | 1: nao verifica se ha numeros no campo
                totalTimes[i].getDocument().addDocumentListener(updater);
    
                //=> Adicionando os JTextField e JLabels ao rightPanel
                rightPanel.add(players);
                rightPanel.add(equips);
                rightPanel.add(playersName[i]);
                rightPanel.add(playersEquip[i]);
                rightPanel.add(lap1);
                rightPanel.add(lap2);
                rightPanel.add(lapTime1[i]);
                rightPanel.add(lapTime2[i]);
                rightPanel.add(totalTxt);
                rightPanel.add(totalTimes[i]);
    
                //=> Posicionando os componentes no rightPanel
                layout.putConstraint(SpringLayout.WEST  , players          , nameColuns[i]         , SpringLayout.WEST   , rightPanel);
                layout.putConstraint(SpringLayout.NORTH , players          , nameLines[i]          , SpringLayout.NORTH  , rightPanel);
                layout.putConstraint(SpringLayout.WEST  , equips           , equipsNamesColuns[i]  , SpringLayout.WEST   , rightPanel);
                layout.putConstraint(SpringLayout.NORTH , equips           , equipsNameLines[i]    , SpringLayout.NORTH  , rightPanel);
                layout.putConstraint(SpringLayout.WEST  , playersName[i]   , playersColuns[i]      , SpringLayout.WEST   , rightPanel);
                layout.putConstraint(SpringLayout.NORTH , playersName[i]   , playersLines[i]       , SpringLayout.NORTH  , rightPanel);
                layout.putConstraint(SpringLayout.WEST  , playersEquip[i]  , equipsColuns[i]       , SpringLayout.WEST   , rightPanel);
                layout.putConstraint(SpringLayout.NORTH , playersEquip[i]  , equipsLines[i]        , SpringLayout.NORTH  , rightPanel);
                layout.putConstraint(SpringLayout.WEST  , lap1             , txtLap1Coluns[i]      , SpringLayout.WEST   , rightPanel);
                layout.putConstraint(SpringLayout.NORTH , lap1             , txtLap1Lines[i]       , SpringLayout.NORTH  , rightPanel);
                layout.putConstraint(SpringLayout.WEST  , lap2             , txtLap2Coluns[i]      , SpringLayout.WEST   , rightPanel);
                layout.putConstraint(SpringLayout.NORTH , lap2             , txtLap2LInes[i]       , SpringLayout.NORTH  , rightPanel);
                layout.putConstraint(SpringLayout.WEST  , lapTime1[i]      , lapTime1Coluns[i]     , SpringLayout.WEST   , rightPanel);
                layout.putConstraint(SpringLayout.NORTH , lapTime1[i]      , lapTime1Lines[i]      , SpringLayout.NORTH  , rightPanel);
                layout.putConstraint(SpringLayout.WEST  , lapTime2[i]      , lapTime2Coluns[i]     , SpringLayout.WEST   , rightPanel);
                layout.putConstraint(SpringLayout.NORTH , lapTime2[i]      , lapTime2Lines[i]      , SpringLayout.NORTH  , rightPanel);
                layout.putConstraint(SpringLayout.WEST  , totalTxt         , txtTotalColuns[i]     , SpringLayout.WEST   , rightPanel);
                layout.putConstraint(SpringLayout.NORTH , totalTxt         , txtTotalLines[i]      , SpringLayout.NORTH  , rightPanel);
                layout.putConstraint(SpringLayout.WEST  , totalTimes[i]    , totalTimesColuns[i]   , SpringLayout.WEST   , rightPanel);
                layout.putConstraint(SpringLayout.NORTH , totalTimes[i]    , totalTimesLines[i]    , SpringLayout.NORTH  , rightPanel);
            }

            //=> Posicionando os componentes no leftpanel
            layout.putConstraint(SpringLayout.WEST  , title             , 050 , SpringLayout.WEST  , leftPanel);
            layout.putConstraint(SpringLayout.NORTH , title             , 075 , SpringLayout.NORTH , leftPanel);
            layout.putConstraint(SpringLayout.WEST  , txt1              , 015 , SpringLayout.WEST  , leftPanel);
            layout.putConstraint(SpringLayout.NORTH , txt1              , 125 , SpringLayout.NORTH , leftPanel);
            layout.putConstraint(SpringLayout.WEST  , oneParticipant    , 020 , SpringLayout.WEST  , leftPanel);
            layout.putConstraint(SpringLayout.NORTH , oneParticipant    , 150 , SpringLayout.NORTH , leftPanel);
            layout.putConstraint(SpringLayout.WEST  , twoParticipants   , 066 , SpringLayout.WEST  , leftPanel);
            layout.putConstraint(SpringLayout.NORTH , twoParticipants   , 150 , SpringLayout.NORTH , leftPanel);
            layout.putConstraint(SpringLayout.WEST  , threeParticipants , 100 , SpringLayout.WEST  , leftPanel);
            layout.putConstraint(SpringLayout.NORTH , threeParticipants , 150 , SpringLayout.NORTH , leftPanel);
            layout.putConstraint(SpringLayout.WEST  , fourParticipants  , 140 , SpringLayout.WEST  , leftPanel);
            layout.putConstraint(SpringLayout.NORTH , fourParticipants  , 150 , SpringLayout.NORTH , leftPanel);
            layout.putConstraint(SpringLayout.WEST  , fiveParticipants  , 180 , SpringLayout.WEST  , leftPanel);
            layout.putConstraint(SpringLayout.NORTH , fiveParticipants  , 150 , SpringLayout.NORTH , leftPanel);
            layout.putConstraint(SpringLayout.WEST  , simulationButton  , 015 , SpringLayout.WEST  , leftPanel);
            layout.putConstraint(SpringLayout.NORTH , simulationButton  , 350 , SpringLayout.NORTH , leftPanel);
            layout.putConstraint(SpringLayout.WEST  , rankingButton     , 015 , SpringLayout.WEST  , leftPanel);
            layout.putConstraint(SpringLayout.NORTH , rankingButton     , 450 , SpringLayout.NORTH , leftPanel);
            layout.putConstraint(SpringLayout.WEST  , saveButton        , 250 , SpringLayout.WEST  , leftPanel);
            layout.putConstraint(SpringLayout.NORTH , saveButton        , 550 , SpringLayout.NORTH , leftPanel);
            layout.putConstraint(SpringLayout.WEST  , helpButton        , 373 , SpringLayout.WEST  , leftPanel);
            layout.putConstraint(SpringLayout.NORTH , helpButton        , 004 , SpringLayout.NORTH , leftPanel);
            layout.putConstraint(SpringLayout.WEST  , backButton        , 150 , SpringLayout.WEST  , leftPanel);
            layout.putConstraint(SpringLayout.NORTH , backButton        , 550 , SpringLayout.NORTH , leftPanel);
            layout.putConstraint(SpringLayout.WEST  , exitButton        , 015 , SpringLayout.WEST  , leftPanel);
            layout.putConstraint(SpringLayout.NORTH , exitButton        , 550 , SpringLayout.NORTH , leftPanel);
            layout.putConstraint(SpringLayout.WEST  , codOfRace         , 015 , SpringLayout.WEST  , rightPanel);
            layout.putConstraint(SpringLayout.NORTH , codOfRace         , 575 , SpringLayout.NORTH , rightPanel);

            //=> Adicionando os paineis a tela
            screen.add(leftPanel   , BorderLayout.WEST);
            screen.add(rightPanel  , BorderLayout.CENTER);

            radValueSelected = 5;
            
            //=> Inicializo o validdador do botao de salvar 
            saveValid = new ButtonSaveMatch(saveButton, playersName, playersEquip, lapTime1, lapTime2, radValueSelected);

            //=> Pegando a tela para passar paro o JOption pane de confirmacao de salvamento
            MatchDAO.getScreen(screen);

        });
    }

    //=> Metodo responsavel por atualizar os campos de acordo com o radButton selecionando
    private synchronized void radAction(ActionEvent e) {

        //=> Verificando qual o numero de participantes selecionados

        radValueSelected = oneParticipant.isSelected()    ? 1 :
                           twoParticipants.isSelected()   ? 2 :
                           threeParticipants.isSelected() ? 3 :
                           fourParticipants.isSelected()  ? 4 :
                           fiveParticipants.isSelected()  ? 5 : 0;

        //=> Pegando qual o valor que foi selecionando no radButton
        String pCount =  e.getActionCommand();
        int count = Integer.parseInt(pCount);

        //=> Validando quais os campos que devem estar desabilitados e habilitados
        for (int i = 0; i < playersName.length; i++) {

            playersName[i].setText("");
            playersName[i].setBackground(Color.WHITE);
            playersEquip[i].setText("");
            playersEquip[i].setBackground(Color.WHITE);
            lapTime1[i].setText("");
            lapTime1[i].setBackground(Color.WHITE);
            lapTime2[i].setText("");
            lapTime2[i].setBackground(Color.WHITE);

            if (i < count) {
                playersName[i].setEnabled(true);
                playersName[i].setBackground(Color.WHITE);
                playersEquip[i].setEnabled(true);
                playersEquip[i].setBackground(Color.WHITE);
                lapTime1[i].setEnabled(true);
                lapTime1[i].setBackground(Color.WHITE);
                lapTime2[i].setEnabled(true);
                lapTime2[i].setBackground(Color.WHITE);
            } else {
                playersName[i].setEnabled(false);
                playersName[i].setBackground(Color.GRAY);
                playersEquip[i].setEnabled(false);
                playersEquip[i].setBackground(Color.GRAY);
                lapTime1[i].setEnabled(false);
                lapTime1[i].setBackground(Color.GRAY);
                lapTime2[i].setEnabled(false);
                lapTime2[i].setBackground(Color.GRAY);
            }
        }

        saveValid.setRadValueSelected(radValueSelected);
    }
        
    //=> Metodo responsavel por limpar os campos
    public synchronized void clearFields(ActionEvent e){

        for (int i = 0; i < playersName.length; i++) {

            playersName[i].setText("");
            playersEquip[i].setText("");
            lapTime1[i].setText("");
            lapTime2[i].setText("");
            totalTimes[i].setText("");

        }

        //=> Adicionando o codigo da corrida ao meu array de ultimas corridas, e gerando o novo codigo
        lastRaces.addLastRaces(codOfRace.getText());
        num = random.nextInt(90000) + 10000;
        cod = "M" + num;
        codOfRace.setText(cod);

    }


}
