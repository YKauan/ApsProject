package apsproject.src.methods;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class ButtonSaveSimu {

    private JButton saveButton;

    private JTextField[] playerName;
    private JTextField[] lap1;
    private JTextField[] lap2;

    private int length;

    private Color purpleSt = Color.decode("#8A2BE2");
    
    //=> Metodo responsavel por validar o botao save da tela de simulacao
    public ButtonSaveSimu(JButton saveButton, JTextField[] playerName, JTextField[] lap1, JTextField[] lap2, int length) {

        this.saveButton = saveButton;
        this.playerName = playerName;
        this.lap1       = lap1;
        this.lap2       = lap2;
        this.length     = length;

        DocumentListener listener = new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                updateButtonState();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                updateButtonState();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                updateButtonState();
            }
        };

        //=> Adiciono o DocumentListener a cada campo de texto
        for (int i = 0; i < length; i++) {
            playerName[i].getDocument().addDocumentListener(listener);
            lap1[i].getDocument().addDocumentListener(listener);
            lap2[i].getDocument().addDocumentListener(listener);
        }

        //=> Atualiza o estado do botão pela primeira vez
        updateButtonState();
    }

    //=> Metodo responsavel por atualizar o estado do botao
    private void updateButtonState(){

        //=> Verifique se há algum campo de texto vazio
        for (int i = 0; i < length; i++) {
            if (playerName[i].getText().isEmpty() || lap1[i].getText().isEmpty() || lap2[i].getText().isEmpty()) {
                saveButton.setBackground(Color.GRAY);
                saveButton.setEnabled(false);
                break;
            } else{
                saveButton.setBackground(purpleSt);
                saveButton.setEnabled(true);
            }
        }     
    }

    //=> Metodo responsavel por quantas vezes o for deve ser rodado
    public void setComboValueSelected(int length) {
        this.length = length;
    }
}
