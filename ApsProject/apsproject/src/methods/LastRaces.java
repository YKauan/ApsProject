package apsproject.src.methods;

import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

import apsproject.src.languages.LanguageManager;

public class LastRaces {

    private static LastRaces instance;

    private static ArrayList<String> lastRaces;

    public LastRaces(){

        lastRaces = new ArrayList<String>();

    }

    //=> Metodo responsavel por adicionar a ultima corrida ao meu array que contem as ultimas corridas
    public void addLastRaces(String lastRace){

        lastRaces.add(lastRace);

    }

    //=> Metodo responsaver por retornar uma unica instancia da classe LastRaces para toda a aplicacao
    public static LastRaces getInstance() {
        if (instance == null) {
            instance = new LastRaces();
        }
        return instance;
    }

    //=> Metodo responsavel por exibir as ultimas corridas feitas
    public void showLastRaces(JFrame screen){

        LanguageManager languageManager = LanguageManager.getInstance();

        try{

            StringBuilder sb = new StringBuilder();

            for (Object value : lastRaces) {
                sb.append(value.toString());
                sb.append("\n");
            }

            String message = sb.toString();

            String okText = languageManager.getString("langOptionOk");
            UIManager.put("OptionPane.okButtonText", okText);

            if(!message.equals("")){

                JOptionPane.showMessageDialog(screen, message, languageManager.getString("joPane"), JOptionPane.INFORMATION_MESSAGE);
            
            }else{

                JOptionPane.showMessageDialog(screen, languageManager.getString("joPaneE"), languageManager.getString("joPane"), JOptionPane.INFORMATION_MESSAGE);
            
            }

        }catch(NullPointerException e){
        
            JOptionPane.showMessageDialog(screen, languageManager.getString("joPaneE"), languageManager.getString("joPane"), JOptionPane.INFORMATION_MESSAGE);
        
        }

    }

}
