package apsproject.src.screens;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.SpringLayout;
import javax.swing.SwingUtilities;

import apsproject.src.languages.LanguageManager;
import apsproject.src.methods.Buttons;
import apsproject.src.methods.Styles;
import apsproject.src.panels.mainscreenpanels.ImgPanel;
import apsproject.src.panels.mainscreenpanels.LeftPanel;
import apsproject.src.panels.mainscreenpanels.UpPanel;

public class MainScreen {

    private Screen screen;
    private LeftPanel leftPanel;
    private UpPanel upPanel;
    private ImgPanel imgPanel;

    private JButton startButton;
    private JButton rankingButton;
    private JButton optionsButton;
    private JButton exitButton;

    private SpringLayout layout;

    private Styles styles;

    private LanguageManager languageManager;

    public MainScreen() {
    
        SwingUtilities.invokeLater(() -> {

            styles = new Styles();

            languageManager = LanguageManager.getInstance();

            screen    = new Screen(1);

            //=> Criando os Paineis
            leftPanel = new LeftPanel();
            upPanel   = new UpPanel();
            imgPanel  = new ImgPanel();

            //=> Criando os JButtons
            startButton   = new JButton(languageManager.getString("startButton"));
            rankingButton = new JButton(languageManager.getString("rankingButton"));
            optionsButton = new JButton(languageManager.getString("optionsButton"));
            exitButton    = new JButton(languageManager.getString("exitButton"));

            //=> Adicionando os estilos dos JButtons
            styles.styleJButtons(startButton);
            styles.styleJButtons(rankingButton);
            styles.styleJButtons(optionsButton);
            styles.styleJButtons(exitButton);

            //=> Adicionando o Mouse Listenner aos meus botoes
            startButton.addMouseListener(imgPanel.new HoverEffects(startButton, 1));
            rankingButton.addMouseListener(imgPanel.new HoverEffects(rankingButton,2));

            //=> Adicionando os Action LIstenner dos botoes
            startButton.addActionListener(e -> Buttons.startButton(e, screen));
            optionsButton.addActionListener(e -> Buttons.optionsButton(e, startButton, rankingButton, optionsButton, exitButton, screen));
            rankingButton.addActionListener(e -> Buttons.rankingButton(e, screen));
            exitButton.addActionListener(e -> Buttons.exitButton(e, screen));

            //=> Definindo o tamanho dos Paineis 
            upPanel.setPreferredSize(new Dimension(screen.getWidth(), 150));
            leftPanel.setPreferredSize(new Dimension(400, screen.getHeight() - 100));
            imgPanel.setPreferredSize(new Dimension(screen.getWidth() - 200, screen.getHeight() - 100));

            //=> Definindo os layouts da tela e dos paineis 
            layout = new SpringLayout();

            leftPanel.setLayout(layout);
            screen.setLayout(new BorderLayout());
            imgPanel.setLayout(new BorderLayout());

            //=> Adicionando os botoes ao leftPanel
            leftPanel.add(startButton);
            leftPanel.add(rankingButton);
            leftPanel.add(optionsButton);
            leftPanel.add(exitButton);

            //=> Posicionamento dos botoes no leftPanel
            layout.putConstraint(SpringLayout.WEST  , startButton   , 030 , SpringLayout.WEST  , leftPanel);
            layout.putConstraint(SpringLayout.NORTH , startButton   , 100 , SpringLayout.NORTH , leftPanel);
            layout.putConstraint(SpringLayout.WEST  , rankingButton , 030 , SpringLayout.WEST  , leftPanel);
            layout.putConstraint(SpringLayout.NORTH , rankingButton , 200 , SpringLayout.NORTH , leftPanel);
            layout.putConstraint(SpringLayout.WEST  , optionsButton , 030 , SpringLayout.WEST  , leftPanel);
            layout.putConstraint(SpringLayout.NORTH , optionsButton , 300 , SpringLayout.NORTH , leftPanel);
            layout.putConstraint(SpringLayout.WEST  , exitButton    , 030 , SpringLayout.WEST  , leftPanel);
            layout.putConstraint(SpringLayout.NORTH , exitButton    , 400 , SpringLayout.NORTH , leftPanel);

            //=> Adicionando os Paineis ao JFrame
            screen.add(upPanel   , BorderLayout.NORTH);
            screen.add(leftPanel , BorderLayout.WEST);
            screen.add(imgPanel  , BorderLayout.CENTER);
        });
    }
}