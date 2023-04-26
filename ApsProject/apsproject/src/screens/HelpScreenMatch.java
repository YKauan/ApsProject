package apsproject.src.screens;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SpringLayout;
import javax.swing.SwingUtilities;

import apsproject.src.languages.LanguageManager;
import apsproject.src.methods.Buttons;
import apsproject.src.methods.LoadImages;
import apsproject.src.methods.Styles;

public class HelpScreenMatch extends JPanel {

    private Screen screen;
    private Image backgroundImage;
    private BufferedImage imgBg;
    private LoadImages images;

    private JRadioButton img0;
    private JRadioButton img1;
    private JRadioButton img2;
    private JRadioButton img3;

    private ButtonGroup  radImgGroup;

    private JButton nextButton;
    private JButton previousButton;
    private JButton exitButton;

    private JLabel help1Txt1;
    private JLabel help2Txt1;
    private JLabel help2Txt2;
    private JLabel help2Txt3;
    private JLabel help3Txt1;
    private JLabel help3Txt2;
    private JLabel help3Txt3;
    private JLabel help3Txt4;
    private JLabel help3Txt5;

    private int count;
    private int index;

    private LanguageManager languageManager;

    private static HelpScreenMatch instance;
    
    private SpringLayout layout;

    private Styles style;

    public HelpScreenMatch() {

        //Uso o SwingUtilities para que minha GUI na seja criada na thread de eventos (EDT)
        SwingUtilities.invokeLater(() -> {

            screen = new Screen(2);

            style = new Styles();

            index = 0;

            languageManager = LanguageManager.getInstance();

            images = new LoadImages();
            backgroundImage = images.getMainHelpImg();

            //=> Criando os JButtons
            nextButton     = new JButton();
            previousButton = new JButton();
            exitButton     = new JButton();

            //=> Criando os JRadioButton
            img0 = new JRadioButton();
            img1 = new JRadioButton();
            img2 = new JRadioButton();
            img3 = new JRadioButton();

            //=> Definindo os estilos dos JRadButtons
            style.styleRadButton(img0);
            style.styleRadButton(img1);
            style.styleRadButton(img2);
            style.styleRadButton(img3);

            //=> Criando um ButtonGroup para os JRadButtons
            radImgGroup = new ButtonGroup();
            radImgGroup.add(img0);
            radImgGroup.add(img1);
            radImgGroup.add(img2);
            radImgGroup.add(img3);
            
            //=> Adicionando os Action Listenner aos botoes/radbuttons
            img0.addActionListener(e -> radAction(e, 0));
            img1.addActionListener(e -> radAction(e, 1));
            img2.addActionListener(e -> radAction(e, 2));
            img3.addActionListener(e -> radAction(e, 3));
            nextButton.addActionListener(e -> {Buttons.helpButtonFunctions(e, previousButton, nextButton, index);paintImage();});
            previousButton.addActionListener(e -> {Buttons.helpButtonFunctions(e, previousButton, nextButton, index);paintImage();});
            exitButton.addActionListener(e -> Buttons.exitButtonHM(e, screen));

            //=> Definindo o JRadButton img0 como selecionado
            img0.setSelected(true);

            //=> Desabilitando o previousButton
            previousButton.setEnabled(false);

            //=> Definindo os estilos dos JButtons
            style.styleJButtonsNextButton(nextButton);
            style.styleJButtonsPreviousButton(previousButton);
            style.styleJButtonsExitButton(exitButton);

            //=> Criando e adicionando o modelo de layout
            layout = new SpringLayout();
            setLayout(layout);

            //=> Adicionando os componentes ao painel
            add(img0);
            add(img1);
            add(img2);
            add(img3);
            add(previousButton);
            add(nextButton);
            add(exitButton);

            //=> Definindo as posicoes dos componentes na tela 
            layout.putConstraint(SpringLayout.WEST  , img0 , 700 , SpringLayout.WEST   , this);
            layout.putConstraint(SpringLayout.NORTH , img0 , 405 , SpringLayout.NORTH  , this);
            layout.putConstraint(SpringLayout.WEST  , img1 , 725 , SpringLayout.WEST   , this);
            layout.putConstraint(SpringLayout.NORTH , img1 , 405 , SpringLayout.NORTH  , this);
            layout.putConstraint(SpringLayout.WEST  , img2 , 750 , SpringLayout.WEST   , this);
            layout.putConstraint(SpringLayout.NORTH , img2 , 405 , SpringLayout.NORTH  , this);
            layout.putConstraint(SpringLayout.WEST  , img3 , 775 , SpringLayout.WEST   , this);
            layout.putConstraint(SpringLayout.NORTH , img3 , 405 , SpringLayout.NORTH  , this);

            layout.putConstraint(SpringLayout.WEST  , previousButton , 005 , SpringLayout.WEST   , this);
            layout.putConstraint(SpringLayout.NORTH , previousButton , 225 , SpringLayout.NORTH  , this);
            layout.putConstraint(SpringLayout.WEST  , nextButton     , 782 , SpringLayout.WEST   , this);
            layout.putConstraint(SpringLayout.NORTH , nextButton     , 225 , SpringLayout.NORTH  , this);
            layout.putConstraint(SpringLayout.WEST  , exitButton     , 806 , SpringLayout.WEST   , this);
            layout.putConstraint(SpringLayout.NORTH , exitButton     , 014 , SpringLayout.NORTH  , this);

            //=> Adicionando o painel ao JFrame
            screen.add(this);

        });
    }

    //=> Sobreescrevendo o metodo paintComponent do JPanel
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (backgroundImage != null) {
            Dimension dim = this.getSize();
            int dWidth = (int) dim.getWidth();
            int dHeight = (int) dim.getHeight();

            imgBg = new BufferedImage(dWidth, dHeight, BufferedImage.TYPE_INT_RGB);
            Graphics2D g2 = imgBg.createGraphics();

            g2.drawImage(backgroundImage, 0, 0, dWidth, dHeight, null);
            g2.dispose();

            g.drawImage(imgBg, 0, 0, null);
        }
    }
    
    //=> Metodo responsavel por atualizar as imagens de fundo do Painel
    private void setImgBackground(int imageIndex) {
        switch (imageIndex) {
            case 0:
                backgroundImage = images.getMainHelpImg();
                break;
            case 1:
                backgroundImage = images.getHelpMatch1();
                break;
            case 2:
                backgroundImage = images.getHelpMatch2();
                break;
            case 3:
                backgroundImage = images.getHelpMatch3();
                break;
            default:
                break;
        }
        repaint();
    }

    //=> Metodo responsavel por validar quais imagens/textos devem ser exibidos
    private void paintImage() {

        //=> Pegando o valor do count para definir qual imagem deve ser pintada
        count = Buttons.getCountHelp();

        if (count == 0) {

            setImgBackground(0);
            img0.setSelected(true);

            labelsS0();

        }

        if (count == 1) {

            setImgBackground(1);
            img1.setSelected(true);

            labelsS1();

        }

        if (count == 2) {

            setImgBackground(2);
            img2.setSelected(true);

            labelsS2();
            
        }

        if (count == 3) {

            setImgBackground(3);
            img3.setSelected(true);

            labelsS3();

        }

    }
    
    //=> Metodo responsavel por validar quais imagens/textos devem ser exibidos
    private void radAction(ActionEvent e, int index) {
        

        if (index == 0) {

            setImgBackground(0);
            Buttons.setCountHelp(0);

            previousButton.setEnabled(false);
            nextButton.setEnabled(true);

            labelsS0();

        }

        if (index == 1) {

            setImgBackground(1);  
            Buttons.setCountHelp(1);

            previousButton.setEnabled(true);
            nextButton.setEnabled(true);

            labelsS1();

        }

        if (index == 2) {

            setImgBackground(2);
            Buttons.setCountHelp(2);

            previousButton.setEnabled(true);
            nextButton.setEnabled(true);

            labelsS2();

        }

        if (index == 3) {

            setImgBackground(3);
            Buttons.setCountHelp(3);

            previousButton.setEnabled(true);
            nextButton.setEnabled(false);

            labelsS3();

        }

    }

    //=> Metodo responsavel por atualizar os textos da tela
    private void labelsS0(){

        //=> Para cada componente deste painel
        for (Component component : this.getComponents()) {
            //=> Verifica se e instacia de um JLabel e o remove
            if (component instanceof JLabel) {
                this.remove(component);
            }
        }
        
        //=> Repintando e revalidando o painel
        this.repaint();
        this.revalidate();

    }

    //=> Metodo responsavel por atualizar os textos da tela
    private void labelsS1(){

        //=> Para cada componente deste painel
        for (Component component : this.getComponents()) {
            //=> Verifica se e instacia de um JLabel e o remove
            if (component instanceof JLabel) {
                this.remove(component);
            }
        }

        //=> Adicionando os novos textos a tela 
        help1Txt1 = new JLabel(languageManager.getString("helpM1Txt1"));
        help1Txt1.setForeground(Color.WHITE);
        add(help1Txt1);

        layout.putConstraint(SpringLayout.WEST, help1Txt1, 450, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, help1Txt1, 222, SpringLayout.NORTH, this);

        //=> Repintando e revalidando o painel
        this.repaint();
        this.revalidate();

    }

    //=> Metodo responsavel por atualizar os textos da tela
    private void labelsS2(){

        //=> Para cada componente deste painel
        for (Component component : this.getComponents()) {
            //=> Verifica se e instacia de um JLabel e o remove
            if (component instanceof JLabel) {
                this.remove(component);
            }
        }

        //=> Adicionando os novos textos a tela 
        help2Txt1 = new JLabel(languageManager.getString("helpM2Txt1"));
        help2Txt2 = new JLabel(languageManager.getString("helpM2Txt2"));
        help2Txt3 = new JLabel(languageManager.getString("helpM2Txt3"));
        
        help2Txt1.setForeground(Color.WHITE);
        help2Txt2.setForeground(Color.WHITE);
        help2Txt3.setForeground(Color.WHITE);
        
        add(help2Txt1);
        add(help2Txt2);
        add(help2Txt3);

        layout.putConstraint(SpringLayout.WEST, help2Txt1, 590, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, help2Txt1, 98, SpringLayout.NORTH, this);

        layout.putConstraint(SpringLayout.WEST, help2Txt2, 590, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, help2Txt2, 251, SpringLayout.NORTH, this);

        layout.putConstraint(SpringLayout.WEST, help2Txt3, 590, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, help2Txt3, 368, SpringLayout.NORTH, this);

        //=> Repintando e revalidando o painel
        this.repaint();
        this.revalidate();

    }

    //=> Metodo responsavel por atualizar os textos da tela
    private void labelsS3(){

        //=> Para cada componente deste painel
        for (Component component : this.getComponents()) {
            //=> Verifica se e instacia de um JLabel e o remove
            if (component instanceof JLabel) {
                this.remove(component);
            }
        }

        //=> Adicionando os novos textos a tela
        help3Txt1 = new JLabel(languageManager.getString("helpM3Txt1"));
        help3Txt2 = new JLabel(languageManager.getString("helpM3Txt2"));
        help3Txt3 = new JLabel(languageManager.getString("helpM3Txt3"));
        help3Txt4 = new JLabel(languageManager.getString("helpM3Txt4"));
        help3Txt5 = new JLabel(languageManager.getString("helpM3Txt5"));

        help3Txt1.setForeground(Color.WHITE);
        help3Txt2.setForeground(Color.WHITE);
        help3Txt3.setForeground(Color.WHITE);
        help3Txt4.setForeground(Color.WHITE);
        help3Txt5.setForeground(Color.WHITE);

        add(help3Txt1);
        add(help3Txt2);
        add(help3Txt3);
        add(help3Txt4);
        add(help3Txt5);

        layout.putConstraint(SpringLayout.WEST, help3Txt1, 550, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, help3Txt1, 108, SpringLayout.NORTH, this);

        layout.putConstraint(SpringLayout.WEST, help3Txt2, 550, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, help3Txt2, 210, SpringLayout.NORTH, this);

        layout.putConstraint(SpringLayout.WEST, help3Txt3, 550, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, help3Txt3, 266, SpringLayout.NORTH, this);

        layout.putConstraint(SpringLayout.WEST, help3Txt4, 550, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, help3Txt4, 316, SpringLayout.NORTH, this);

        layout.putConstraint(SpringLayout.WEST, help3Txt5, 550, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, help3Txt5, 370, SpringLayout.NORTH, this);

        //=> Repintando e revalidando o painel
        this.repaint();
        this.revalidate();

    }

    //=> Metodo responsavel por retornar uma unica instancia da classe HelpScreenMatch para toda a aplicacao.
    public static HelpScreenMatch getInstance() {

        if (instance == null) {
            instance = new HelpScreenMatch();
        }
        return instance;
    }

    //=> Metodo responsavel por permitir abrir a tela apos ser fechada
    public void close() {

        instance = null;
    }

}
