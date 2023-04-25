package apsproject.src.methods;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.Border;

import apsproject.src.languages.LanguageManager;

public class Styles {

    private Color purpleSt = Color.decode("#8A2BE2");
    private Color purpleEx = Color.decode("#6B3FA0");

    private LanguageManager languageManager = LanguageManager.getInstance();

    //=> Metodo responsavel por definir os estilos dos meus JButtons
    public void styleJButtons(JButton button) {

        addHoverEffectButtons(button);

        if (!button.getText().equals(languageManager.getString("exitButton"))) {
            
            button.setBackground(purpleSt);
            button.setForeground(Color.WHITE);
            button.setFocusPainted(false);
            button.setBorderPainted(false);
            
        }else{

            button.setBackground(Color.RED);
            button.setForeground(Color.WHITE);
            button.setFocusPainted(false);
            button.setBorderPainted(false);

        }
    }

    //=> Metodo responsavel por definir os estilos dos meus JButtons help
    public void styleJButtonsHelp(JButton button) {

        button.setIcon(new ImageIcon("./apsproject/src/assets/help.png"));
        button.setBackground(null);
        button.setBorder(null);
        button.setBorderPainted(false);
        button.setOpaque(true);
        button.setFocusPainted(false);
        button.setContentAreaFilled(false);

    }

    //=> Metodo responsavel por definir os estilos dos meus JButtons next Button
    public void styleJButtonsNextButton(JButton button) {

        button.setIcon(new ImageIcon("./apsproject/src/assets/rightArrow.png"));
        button.setBorder(null);
        button.setBorderPainted(false);
        button.setOpaque(true);
        button.setFocusPainted(false);
        button.setContentAreaFilled(false);

        if(!button.isEnabled()){
            
            button.setBackground(Color.GRAY);
        }

    }

    //=> Metodo responsavel por definir os estilos dos meus JButtons previous Button
    public void styleJButtonsPreviousButton(JButton button) {

        button.setIcon(new ImageIcon("./apsproject/src/assets/leftArrow.png"));
        button.setBorder(null);
        button.setBorderPainted(false);
        button.setOpaque(true);
        button.setFocusPainted(false);
        button.setContentAreaFilled(false);

        if(!button.isEnabled()){
            
            button.setBackground(Color.GRAY);
        }

    }

    //=> Metodo responsavel por definir os estilos dos meus JButtons exit Button
    public void styleJButtonsExitButton(JButton button) {

        button.setIcon(new ImageIcon("./apsproject/src/assets/close.png"));
        button.setBorder(null);
        button.setBorderPainted(false);
        button.setOpaque(true);
        button.setFocusPainted(false);
        button.setContentAreaFilled(false);

    }

    //=> Metodo responsavel por definir os estilos dos meus JRadioButton
    public void styleRadButton(JRadioButton radButton) {

        ImageIcon radSelected   = new ImageIcon("./apsproject/src/assets/selected.png");
        ImageIcon radNoSelected = new ImageIcon("./apsproject/src/assets/noSelected.png");
        
        Image selectedImage    = radSelected.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        Image notSelectedImage = radNoSelected.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);

        ImageIcon radSelectedIcon   = new ImageIcon(selectedImage);
        ImageIcon radNoSelectedIcon = new ImageIcon(notSelectedImage);

        radButton.setSelectedIcon(radSelectedIcon);
        radButton.setIcon(radNoSelectedIcon);
        radButton.setOpaque(false);
        radButton.setFocusPainted(false);
        radButton.setBorder(null);
        radButton.setForeground(Color.WHITE);
    }

    //=> Metodo responsavel por definir o efeito de hover dos JButtons
    public void addHoverEffectButtons(JButton button) {

        if(!button.getText().equals(languageManager.getString("exitButton"))){

            button.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseEntered(MouseEvent e) {
                        if(button.isEnabled()){
                            button.setBackground(purpleEx); 
                            button.setForeground(Color.BLACK);
                        }
                    }
            
                    @Override
                    public void mouseExited(MouseEvent e) {

                        if(button.isEnabled()){
                            button.setBackground(purpleSt); 
                            button.setForeground(Color.WHITE);
                        }
                    }
            });

        }else {

            button.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    if(button.isEnabled()){
                        button.setBackground(Color.WHITE); 
                        button.setForeground(Color.BLACK);
                    }
                }
        
                @Override
                public void mouseExited(MouseEvent e) {

                    if(button.isEnabled()){
                        button.setBackground(Color.RED); 
                        button.setForeground(Color.WHITE);
                    }
                }
            });

        }
        
    }

    //=> Metodo responsavel pelos estilos do JTextField com foco
    public void styleJTextFieldFocus(JTextField field) {

        Border defaultBorder = field.getBorder();
        Border focusBorder   = BorderFactory.createLineBorder(Color.WHITE, 2);

        field.addFocusListener(new FocusListener() {

            @Override
            public void focusGained(FocusEvent e) {
                field.setBorder(focusBorder);
                field.setBackground(purpleEx);
            }

            @Override
            public void focusLost(FocusEvent e) {
                field.setBorder(defaultBorder);
                field.setBackground(Color.WHITE);
            } 
        });
    }
}