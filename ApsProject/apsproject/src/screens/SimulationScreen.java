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
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;

import apsproject.src.database.classesdao.SimulationDAO;
import apsproject.src.languages.LanguageManager;
import apsproject.src.methods.ButtonSaveSimu;
import apsproject.src.methods.Buttons;
import apsproject.src.methods.LastRaces;
import apsproject.src.methods.Limits;
import apsproject.src.methods.Styles;
import apsproject.src.methods.UpdateFieldsSimu;
import apsproject.src.panels.leftpanel.LeftPanelMS;
import apsproject.src.panels.simulationscreenpanels.SimuScreenRightPanel;

public class SimulationScreen {

    private Screen screen;

    private LeftPanelMS leftPanel;
    private SimuScreenRightPanel rightPanel;

    private JButton backButton;
    private JButton exitButton;
    private JButton saveButton;
    private JButton rankingButton;
    private JButton helpButton;

    private JRadioButton userOption;
    private JRadioButton autoOption;
    private ButtonGroup  radButtonsGroup;
    
    private JLabel title;
    private JLabel txt1;
    private JLabel txt2;
    private JLabel txt3;
    private JLabel participants;
    private JLabel lap1;
    private JLabel lap2;
    private JLabel codOfRace;

    private JTextField[] participantsName;
    private JTextField[] lap1Values;
    private JTextField[] lap2Values;
    private JTextField[] playersName;
    private JTextField[] lap1Time;
    private JTextField[] lap2Time;
    private JTextField[] totalTime;

    private ButtonSaveSimu saveValid;

    private Random random;

    private int num;

    private String cod;

    private Font JTextFieldFont;

    private JComboBox<Integer> comboBox;

    private LastRaces lastRaces;

    private SpringLayout layout;

    private Styles styles;

    private Color purple = Color.decode("#6B3FA0");

    private Border border = BorderFactory.createLineBorder(purple, 2);

    private LanguageManager languageManager;

    public SimulationScreen(JFrame previousScreen){

        SwingUtilities.invokeLater(() -> {

            JTextFieldFont = new Font("Courier New", Font.ITALIC + Font.BOLD, 12);

            languageManager = LanguageManager.getInstance();
        
            styles = new Styles();

            screen = new Screen(1);

            //=> Gerando o codigo da Corrida
            random = new Random();
            num = random.nextInt(90000) + 10000;
            cod = "S" + num;

            lastRaces = LastRaces.getInstance();

            codOfRace = new JLabel(cod);
            codOfRace.setForeground(Color.WHITE);

            //=> Criando os paineis
            leftPanel    = new LeftPanelMS();
            rightPanel   = new SimuScreenRightPanel();

            //=> Criando os JLabels
            title = new JLabel(languageManager.getString("titleAps"));
            txt1  = new JLabel(languageManager.getString("selectOptions"));
            txt2  = new JLabel(languageManager.getString("numberOfParticipants"));
            txt3  = new JLabel(languageManager.getString("nameOfParticipantes"));

            //=> Definindo a cro do texto do JLabel
            title.setForeground(Color.WHITE);
            txt1.setForeground(Color.WHITE);
            txt2.setForeground(Color.WHITE);
            txt3.setForeground(Color.WHITE);

            //=> Criando os Botoes
            saveButton    = new JButton(languageManager.getString("saveButton"));
            rankingButton = new JButton(languageManager.getString("rankingButton"));
            backButton    = new JButton(languageManager.getString("backButton"));
            exitButton    = new JButton(languageManager.getString("exitButton"));
            helpButton    = new JButton();

            //=> Definindo os estilos dos botoes
            styles.styleJButtonsHelp(helpButton);
            styles.styleJButtons(saveButton);
            styles.styleJButtons(rankingButton);
            styles.styleJButtons(backButton);
            styles.styleJButtons(exitButton);

            //=> Criando os rabutton e o ButtonGroup
            userOption = new JRadioButton(languageManager.getString("userOption"));
            autoOption = new JRadioButton(languageManager.getString("autoOption"));
            radButtonsGroup = new ButtonGroup();

            //=> Definindo os estilos dos radButtons
            styles.styleRadButton(userOption);
            styles.styleRadButton(autoOption);

            //=> Adicionando os radButtons ao ButtonGroup
            radButtonsGroup.add(userOption);
            radButtonsGroup.add(autoOption);
            
            //=> Setando o userOption como selecionado
            userOption.setSelected(true);

            //=> Criando o comboBox, com as opcoes de quantidade de participantes
            Integer[] comboOptions = {1,2,3,4,5};
            comboBox = new JComboBox<>(comboOptions);

            //=> Definindo o qual opcao do comboBOx vai estar selecionada de imediato
            comboBox.setSelectedIndex(4);
            comboBox.setForeground(purple);

            //=> Adicionando os ActionListenner aos botoes
            saveButton.addActionListener(e -> {Buttons.saveButtonSimu(e, cod, participantsName, lap1Values, lap2Values, totalTime, (int) comboBox.getSelectedItem());clearFields(e);});
            rankingButton.addActionListener(e -> Buttons.rankingButton(e, screen));
            helpButton.addActionListener(e -> Buttons.helpButtonSimu(e));
            userOption.addActionListener(e -> simulationAction(e));
            autoOption.addActionListener(e -> simulationAction(e));
            comboBox.addActionListener(e -> comboAction(e));
            backButton.addActionListener(e -> Buttons.backButton(e, screen, previousScreen));
            exitButton.addActionListener(e -> Buttons.exitButton(e, screen));

            //=> Definindo o tamanho dos paineis
            leftPanel.setPreferredSize(new Dimension(400, screen.getHeight() - 100));
            rightPanel.setPreferredSize(new Dimension(screen.getWidth() - 200, screen.getHeight() - 100));

            //=> Definindo o layout
            layout = new SpringLayout();
            rightPanel.setLayout(layout);
            leftPanel.setLayout(layout);

            screen.setLayout(new BorderLayout());

            //=> Adicionando os componentes aos paineis
            rightPanel.add(codOfRace);
            leftPanel.add(title);
            leftPanel.add(txt1);
            leftPanel.add(txt2);
            leftPanel.add(txt3);
            leftPanel.add(comboBox);
            leftPanel.add(userOption);
            leftPanel.add(autoOption);
            leftPanel.add(saveButton);
            leftPanel.add(rankingButton);
            leftPanel.add(helpButton);
            leftPanel.add(backButton);
            leftPanel.add(exitButton);

            //=> Arrays com os textos e posicoes dos Jlabels
            String[]  participantsArr = {languageManager.getString("player1"),languageManager.getString("player2"),languageManager.getString("player3"),languageManager.getString("player4"),languageManager.getString("player5")};
            Integer[] linePositions   = {260,310,360,410,460};

            //=> Instanciando os meu JLabels
            playersName      = new JTextField[5];
            lap1Time         = new JTextField[5];
            lap2Time         = new JTextField[5];
            totalTime        = new JTextField[5];
            participantsName = new JTextField[5];
            lap1Values       = new JTextField[5];
            lap2Values       = new JTextField[5];

            //=> Arrays com as posicoes das linhas e das colunas, dos componentes
            Integer[] playersNamesColuns  = { 80,650,350, 80,650};
            Integer[] playersNamesLines   = {100,100,275,400,400};

            Integer[] lap1Coluns          = { 80,650,350, 80,650};
            Integer[] lap1Lines           = {129,129,304,429,429};
            
            Integer[] lap2Coluns          = { 80,650,350, 80,650};
            Integer[] lap2Lines           = {158,158,333,458,458};

            Integer[] totalColuns         = { 80,650,350, 80,650};
            Integer[] totalLines          = {187,187,362,487,487};

            for (int i = 0; i < participantsArr.length; i++){

                //=> Inicializando o meu UpdateFieldsSimu que possui as regras de validacao dos JTextField 
                UpdateFieldsSimu updater = new UpdateFieldsSimu(lap1Values, lap2Values, totalTime, participantsName, playersName, lap1Time, lap2Time, i);

                //=====>LeftPanel<=====//

                //=> Criando os JLabels
                participants  = new JLabel(participantsArr[i]);
                lap1 = new JLabel("Lap1:");
                lap2 = new JLabel("Lap2:");

                //=> Definindo a cor do texto dos JLabels
                participants.setForeground(Color.WHITE);
                lap1.setForeground(Color.WHITE);
                lap2.setForeground(Color.WHITE);
    
                //=> Criando os JTextField
                participantsName[i] = new JTextField();
                participantsName[i].setForeground(Color.BLACK);
                participantsName[i].setFont(JTextFieldFont);
                participantsName[i].setBorder(border);
                participantsName[i].setPreferredSize(new Dimension(98,25));
                participantsName[i].setDocument(new Limits(11,1));//0: nao verificar se ha numeros no campo | 1: verifica se ha numeros no campo
                participantsName[i].getDocument().addDocumentListener(updater);
                styles.styleJTextFieldFocus(participantsName[i]);
                
                lap1Values[i] = new JTextField();
                lap1Values[i].setForeground(Color.BLACK);
                lap1Values[i].setFont(JTextFieldFont);
                lap1Values[i].setBorder(border);
                lap1Values[i].setPreferredSize(new Dimension(45,25));
                lap1Values[i].setDocument(new Limits(4,0));//0: nao verificar se ha numeros no campo | 1: verifica se ha numeros no campo
                lap1Values[i].getDocument().addDocumentListener(updater);
                styles.styleJTextFieldFocus(lap1Values[i]);
                
                lap2Values[i] = new JTextField();
                lap2Values[i].setForeground(Color.BLACK);
                lap2Values[i].setFont(JTextFieldFont);
                lap2Values[i].setBorder(border);
                lap2Values[i].setPreferredSize(new Dimension(45,25));
                lap2Values[i].setDocument(new Limits(4,0));//0: nao verificar se ha numeros no campo | 1: verifica se ha numeros no campo
                lap2Values[i].getDocument().addDocumentListener(updater);
                styles.styleJTextFieldFocus(lap2Values[i]);
    
                //=> Adicionando os componentes ao painel
                leftPanel.add(participants);
                leftPanel.add(participantsName[i]);
                leftPanel.add(lap1);
                leftPanel.add(lap2);
                leftPanel.add(lap1Values[i]);
                leftPanel.add(lap2Values[i]);
    
                //=> Posicionando os componentes
                layout.putConstraint(SpringLayout.WEST  , participants          , 15               , SpringLayout.WEST   , leftPanel);
                layout.putConstraint(SpringLayout.NORTH , participants          , linePositions[i] , SpringLayout.NORTH  , leftPanel);
                layout.putConstraint(SpringLayout.WEST  , participantsName[i]   , 95               , SpringLayout.WEST   , leftPanel);
                layout.putConstraint(SpringLayout.NORTH , participantsName[i]   , linePositions[i] , SpringLayout.NORTH  , leftPanel);
                layout.putConstraint(SpringLayout.WEST  , lap1                  , 200              , SpringLayout.WEST   , leftPanel);
                layout.putConstraint(SpringLayout.NORTH , lap1                  , linePositions[i] , SpringLayout.NORTH  , leftPanel);
                layout.putConstraint(SpringLayout.WEST  , lap2                  , 310              , SpringLayout.WEST   , leftPanel);
                layout.putConstraint(SpringLayout.NORTH , lap2                  , linePositions[i] , SpringLayout.NORTH  , leftPanel);
                layout.putConstraint(SpringLayout.WEST  , lap1Values[i]         , 245              , SpringLayout.WEST   , leftPanel);
                layout.putConstraint(SpringLayout.NORTH , lap1Values[i]         , linePositions[i] , SpringLayout.NORTH  , leftPanel);
                layout.putConstraint(SpringLayout.WEST  , lap2Values[i]         , 350              , SpringLayout.WEST   , leftPanel);
                layout.putConstraint(SpringLayout.NORTH , lap2Values[i]         , linePositions[i] , SpringLayout.NORTH  , leftPanel);

                //=====RightPanel<=====//

                //=> Criando os JTextField
                playersName[i] = new JTextField();
                playersName[i].setDisabledTextColor(Color.RED);
                playersName[i].setBorder(border);
                playersName[i].setPreferredSize(new Dimension(100,25));
                playersName[i].setDocument(new Limits(11,1));//0: verificar se ha numeros no campo | 1: nao verifica se ha numeros no campo
                playersName[i].getDocument().addDocumentListener(updater);
                playersName[i].setEnabled(false);
                
                lap1Time[i] = new JTextField();
                lap1Time[i].setDisabledTextColor(Color.RED);
                lap1Time[i].setBorder(border);
                lap1Time[i].setPreferredSize(new Dimension(100,25));
                lap1Time[i].setDocument(new Limits(11,1));//0: verificar se ha numeros no campo | 1: nao verifica se ha numeros no campo
                lap1Time[i].getDocument().addDocumentListener(updater);
                lap1Time[i].setEnabled(false);
                
                lap2Time[i] = new JTextField();
                lap2Time[i].setDisabledTextColor(Color.RED);
                lap2Time[i].setBorder(border);
                lap2Time[i].setPreferredSize(new Dimension(100,25));
                lap2Time[i].setDocument(new Limits(11,1));//0: verificar se ha numeros no campo | 1: nao verifica se ha numeros no campo
                lap2Time[i].getDocument().addDocumentListener(updater);
                lap2Time[i].setEnabled(false);
                
                totalTime[i] = new JTextField();
                totalTime[i].setDisabledTextColor(Color.RED);
                totalTime[i].setBorder(border);
                totalTime[i].setPreferredSize(new Dimension(100,25));
                totalTime[i].setDocument(new Limits(15,1));//0: verificar se ha numeros no campo | 1: nao verifica se ha numeros no campo
                totalTime[i].getDocument().addDocumentListener(updater);
                totalTime[i].setEnabled(false);

                //=> Adicionando os JTextField ao painel
                rightPanel.add(playersName[i]);
                rightPanel.add(lap1Time[i]);
                rightPanel.add(lap2Time[i]);
                rightPanel.add(totalTime[i]);

                //=> Posicionando os JTextField
                layout.putConstraint(SpringLayout.WEST  , playersName[i]      , playersNamesColuns[i] , SpringLayout.WEST   , rightPanel);
                layout.putConstraint(SpringLayout.NORTH , playersName[i]      , playersNamesLines[i]  , SpringLayout.NORTH  , rightPanel);
                layout.putConstraint(SpringLayout.WEST  , lap1Time[i]         , lap1Coluns[i]         , SpringLayout.WEST   , rightPanel);
                layout.putConstraint(SpringLayout.NORTH , lap1Time[i]         , lap1Lines[i]          , SpringLayout.NORTH  , rightPanel);
                layout.putConstraint(SpringLayout.WEST  , lap2Time[i]         , lap2Coluns[i]         , SpringLayout.WEST   , rightPanel);
                layout.putConstraint(SpringLayout.NORTH , lap2Time[i]         , lap2Lines[i]          , SpringLayout.NORTH  , rightPanel);
                layout.putConstraint(SpringLayout.WEST  , totalTime[i]        , totalColuns[i]        , SpringLayout.WEST   , rightPanel);
                layout.putConstraint(SpringLayout.NORTH , totalTime[i]        , totalLines[i]         , SpringLayout.NORTH  , rightPanel);
               
            }
            
            //=> Posicionando os componentes
            layout.putConstraint(SpringLayout.WEST  , title         , 050 , SpringLayout.WEST   , leftPanel);
            layout.putConstraint(SpringLayout.NORTH , title         , 050 , SpringLayout.NORTH  , leftPanel);
            layout.putConstraint(SpringLayout.WEST  , txt1          , 015 , SpringLayout.WEST   , leftPanel);
            layout.putConstraint(SpringLayout.NORTH , txt1          , 100 , SpringLayout.NORTH  , leftPanel);
            layout.putConstraint(SpringLayout.WEST  , userOption    , 015 , SpringLayout.WEST   , leftPanel);
            layout.putConstraint(SpringLayout.NORTH , userOption    , 130 , SpringLayout.NORTH  , leftPanel);
            layout.putConstraint(SpringLayout.WEST  , autoOption    , 125 , SpringLayout.WEST   , leftPanel);
            layout.putConstraint(SpringLayout.NORTH , autoOption    , 130 , SpringLayout.NORTH  , leftPanel);
            layout.putConstraint(SpringLayout.WEST  , txt2          , 015 , SpringLayout.WEST   , leftPanel);
            layout.putConstraint(SpringLayout.NORTH , txt2          , 175 , SpringLayout.NORTH  , leftPanel);
            layout.putConstraint(SpringLayout.WEST  , comboBox      , 325 , SpringLayout.WEST   , leftPanel);
            layout.putConstraint(SpringLayout.NORTH , comboBox      , 175 , SpringLayout.NORTH  , leftPanel);
            layout.putConstraint(SpringLayout.WEST  , txt3          , 015 , SpringLayout.WEST   , leftPanel);
            layout.putConstraint(SpringLayout.NORTH , txt3          , 220 , SpringLayout.NORTH  , leftPanel);
            layout.putConstraint(SpringLayout.WEST  , saveButton    , 295 , SpringLayout.WEST   , leftPanel);
            layout.putConstraint(SpringLayout.NORTH , saveButton    , 550 , SpringLayout.NORTH  , leftPanel);
            layout.putConstraint(SpringLayout.WEST  , rankingButton , 190 , SpringLayout.WEST   , leftPanel);
            layout.putConstraint(SpringLayout.NORTH , rankingButton , 550 , SpringLayout.NORTH  , leftPanel);
            layout.putConstraint(SpringLayout.WEST  , backButton    ,  95 , SpringLayout.WEST   , leftPanel);
            layout.putConstraint(SpringLayout.NORTH , backButton    , 550 , SpringLayout.NORTH  , leftPanel);
            layout.putConstraint(SpringLayout.WEST  , helpButton    , 373 , SpringLayout.WEST   , leftPanel);
            layout.putConstraint(SpringLayout.NORTH , helpButton    , 004 , SpringLayout.NORTH  , leftPanel);
            layout.putConstraint(SpringLayout.WEST  , exitButton    , 010 , SpringLayout.WEST   , leftPanel);
            layout.putConstraint(SpringLayout.NORTH , exitButton    , 550 , SpringLayout.NORTH  , leftPanel);
            layout.putConstraint(SpringLayout.WEST  , codOfRace     , 010 , SpringLayout.WEST   , rightPanel);
            layout.putConstraint(SpringLayout.NORTH , codOfRace     , 575 , SpringLayout.NORTH  , rightPanel);

            //=> Adicionando os paineis a tela
            screen.add(leftPanel   , BorderLayout.WEST);
            screen.add(rightPanel  , BorderLayout.CENTER);
        
            //=> Efetuando a validacao do botao salvar
            saveValid = new ButtonSaveSimu(saveButton, participantsName, lap1Values, lap2Values, (int) comboBox.getSelectedItem());

            //=> Pega o JFrame para ser usado no JOptionPane de confirmacao de salvamento
            SimulationDAO.getScreen(screen);

        });
    }

    //=> Metodo responsavel por validacoes dos radButton
    private void simulationAction(ActionEvent e) {

        if (userOption.isSelected()) {
            
            comboBox.setSelectedItem(5);
            
            for(int i = 0; i < participantsName.length; i++) {

                comboBox.setEnabled(true);
                participantsName[i].setEnabled(true);
                lap1Values[i].setEnabled(true);
                lap2Values[i].setEnabled(true);
            }

        } else if (autoOption.isSelected()) {

            comboBox.setSelectedItem(5);
            
            for(int i = 0; i < participantsName.length; i++) {

                int aleatoryNumberLap1 = (int) (Math.random() * (10000 - 1000)) + 1000;
                int aleatoryNumberLap2 = (int) (Math.random() * (10000 - 1000)) + 1000;

                String aleatoryPlayer = languageManager.getString("player") + aleatoryNumberLap1;

                comboBox.setEnabled(false);
                participantsName[i].setEnabled(false);
                participantsName[i].setText(aleatoryPlayer);
                participantsName[i].setDisabledTextColor(Color.BLACK);
                participantsName[i].setBackground(Color.GRAY);
                lap1Values[i].setEnabled(false);
                lap1Values[i].setText(Integer.toString(aleatoryNumberLap1));
                lap1Values[i].setDisabledTextColor(Color.BLACK);
                lap1Values[i].setBackground(Color.GRAY);
                lap2Values[i].setEnabled(false);
                lap2Values[i].setText(Integer.toString(aleatoryNumberLap2));
                lap2Values[i].setDisabledTextColor(Color.BLACK);
                lap2Values[i].setBackground(Color.GRAY);
            }

        }
            
    }

    //=> Metodo responsavel por validacoes do comboBox
    private void comboAction(ActionEvent e) {

        int participantsCount = (Integer) comboBox.getSelectedItem();
        
        for (int i = 0; i < participantsName.length; i++) {

            participantsName[i].setText("");
            participantsName[i].setBackground(Color.WHITE);
            lap1Values[i].setText("");
            lap1Values[i].setBackground(Color.WHITE);
            lap2Values[i].setText("");
            lap2Values[i].setBackground(Color.WHITE);

            playersName[i].setText("");
            lap1Time[i].setText("");
            lap2Time[i].setText("");
            totalTime[i].setText("");


            if (i < participantsCount) {
                participantsName[i].setEnabled(true);
                participantsName[i].setBackground(Color.WHITE);
                lap1Values[i].setEnabled(true);
                lap1Values[i].setBackground(Color.WHITE);
                lap2Values[i].setEnabled(true);
                lap2Values[i].setBackground(Color.WHITE);
            } else {
                participantsName[i].setEnabled(false);
                participantsName[i].setBackground(Color.GRAY);
                lap1Values[i].setEnabled(false);
                lap1Values[i].setBackground(Color.GRAY);
                lap2Values[i].setEnabled(false);
                lap2Values[i].setBackground(Color.GRAY);
            }
        }

        saveValid.setComboValueSelected(participantsCount);

    }

    //=> Metodo responsavel por limpar os campos apos afetuar o salvamento
    public void clearFields(ActionEvent e){

        for (int i = 0; i < participantsName.length; i++) {

            participantsName[i].setText("");
            lap1Values[i].setText("");
            lap2Values[i].setText("");
            lap1Time[i].setText("");
            playersName[i].setText("");
            lap1Time[i].setText("");
            lap2Time[i].setText("");
            totalTime[i].setText("");
        }

        //=> Adicionando o codigo da corrida ao meu array de ultimas corridas, e gerando o novo codigo
        lastRaces.addLastRaces(codOfRace.getText());
        num = random.nextInt(90000) + 10000;
        cod = "S" + num;
        codOfRace.setText(cod);

    }

}
