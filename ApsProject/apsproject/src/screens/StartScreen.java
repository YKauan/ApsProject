package apsproject.src.screens;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SpringLayout;
import javax.swing.SwingUtilities;

import apsproject.src.languages.LanguageManager;
import apsproject.src.methods.Buttons;
import apsproject.src.methods.Styles;
import apsproject.src.panels.leftpanel.LeftPanel;
import apsproject.src.panels.startscreenpanels.ImgPanelStart;

public class StartScreen {

    private Screen screen;

    private LeftPanel leftPanel;
    private ImgPanelStart imgPanel;

    private SpringLayout layout;

    private JButton simulationButton;
    private JButton matchButton;
    private JButton backButton;
    private JButton exitButton;

    private LanguageManager languageManager;

    private Styles styles;

    public StartScreen(JFrame previousScreen) {

        SwingUtilities.invokeLater(() -> {

            styles = new Styles();

            languageManager = LanguageManager.getInstance();
            
            screen = new Screen(1);

            //=> Criando os paineis 
            leftPanel  = new LeftPanel();
            imgPanel   = new ImgPanelStart();

            //=> Criando os JButtons
            matchButton      = new JButton(languageManager.getString("matchButton"));
            simulationButton = new JButton(languageManager.getString("simulationButton"));
            backButton       = new JButton(languageManager.getString("backButton"));
            exitButton       = new JButton(languageManager.getString("exitButton"));

            //=> Definindo os estilos dos JButtons
            styles.styleJButtons(matchButton);
            styles.styleJButtons(simulationButton);
            styles.styleJButtons(backButton);
            styles.styleJButtons(exitButton);

            //=> Adicionando o Mouse Listenner ao botoes
            matchButton.addMouseListener(imgPanel.new HoverEffects(matchButton,1));
            simulationButton.addMouseListener(imgPanel.new HoverEffects(matchButton,2));

            //=> Adicionando os ActionListenner aos botoes
            matchButton.addActionListener(e -> Buttons.matchButton(e, screen));
            simulationButton.addActionListener(e -> Buttons.simulationButton(e, screen));
            backButton.addActionListener(e -> Buttons.backButton(e, screen, previousScreen));
            exitButton.addActionListener(e -> Buttons.exitButton(e, screen));
            
            //=> Definindo o tamanho dos paineis
            leftPanel.setPreferredSize(new Dimension(400, screen.getHeight() - 100));
            imgPanel.setPreferredSize(new Dimension(screen.getWidth() - 200, screen.getHeight() - 100));

            //=> Definindo os layouts
            layout = new SpringLayout();
            leftPanel.setLayout(layout);

            screen.setLayout(new BorderLayout());

            //=> Adicionando os botoes ao painel
            leftPanel.add(matchButton);
            leftPanel.add(simulationButton);
            leftPanel.add(backButton);
            leftPanel.add(exitButton);

            //=> Posicionando os componentes no painel
            layout.putConstraint(SpringLayout.WEST  , matchButton       , 035 , SpringLayout.WEST  , leftPanel);
            layout.putConstraint(SpringLayout.NORTH , matchButton       , 125 , SpringLayout.NORTH , leftPanel);
            layout.putConstraint(SpringLayout.WEST  , simulationButton  , 035 , SpringLayout.WEST  , leftPanel);
            layout.putConstraint(SpringLayout.NORTH , simulationButton  , 225 , SpringLayout.NORTH , leftPanel);
            layout.putConstraint(SpringLayout.WEST  , backButton        , 035 , SpringLayout.WEST  , leftPanel);
            layout.putConstraint(SpringLayout.NORTH , backButton        , 325 , SpringLayout.NORTH , leftPanel);
            layout.putConstraint(SpringLayout.WEST  , exitButton        , 035 , SpringLayout.WEST  , leftPanel);
            layout.putConstraint(SpringLayout.NORTH , exitButton        , 425 , SpringLayout.NORTH , leftPanel);

            //=> Adicionando os paineis a tela
            screen.add(leftPanel , BorderLayout.WEST);
            screen.add(imgPanel  , BorderLayout.CENTER);
        });
    }
    
}
