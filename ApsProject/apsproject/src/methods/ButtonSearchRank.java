package apsproject.src.methods;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class ButtonSearchRank {

    private JButton searchButton;

    private JTextField codOfRace;

    private DocumentListener listener;

    private Color purpleSt = Color.decode("#8A2BE2");

    //=> Metodo responsavel por validar o botao de busca
    public ButtonSearchRank(JButton searchButton, JTextField codOfRace) {

        this.searchButton = searchButton;
        this.codOfRace = codOfRace;

        listener = new DocumentListener() {
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
                
            }
        };

        //=> Adiciono o DocumentListener a cada campo de texto
        for (int i = 0; i < 1; i++) {
            codOfRace.getDocument().addDocumentListener(listener);
        }

        //=> Atualiza o estado do botão pela primeira vez
        updateButtonState();
    }

    //=> Metodo responsavel por atualizar o estado do botao
    private void updateButtonState(){

        //=> Verifique se há algum campo de texto vazio
        for (int i = 0; i < 1; i++) {
            if (codOfRace.getText().isEmpty()) {
                searchButton.setEnabled(false);
                searchButton.setBackground(Color.GRAY);
                break;
            } else{
                searchButton.setBackground(purpleSt);
                searchButton.setEnabled(true);
            }
        }     
    }
    
    //=> Metodo responsavel por remover os Document Listenner
    public void removeDocumentListener(){

        codOfRace.getDocument().removeDocumentListener(listener);
        searchButton.setBackground(purpleSt);
        searchButton.setEnabled(true);

    }
}
