package apsproject.src.main;

import javax.swing.SwingUtilities;

import apsproject.src.methods.BlockKeys;
import apsproject.src.screens.MainScreen;

public class MainMethod {
    
    public static void main(String[] args) {
        
        //=> Uso o SwingUtilities para que minha GUI seja criada na thread de eventos (EDT)
        SwingUtilities.invokeLater(() -> {
    
            //=> Instanciando minha tela principal.
            MainScreen main = new MainScreen();

            //=> Bloqueando a captura de tela
            BlockKeys.blockPrints();
     
        });

    }

}