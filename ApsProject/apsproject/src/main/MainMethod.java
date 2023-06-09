package apsproject.src.main;

import javax.swing.SwingUtilities;

import apsproject.src.methods.BlockingPrintScreen;
import apsproject.src.screens.MainScreen;

public class MainMethod {
    
    public static void main(String[] args) {
        
        //=> Uso o SwingUtilities para que minha GUI seja criada na thread de eventos (EDT)
        SwingUtilities.invokeLater(() -> {
    
            new MainScreen();

            //=> Bloqueando a captura de tela
            BlockingPrintScreen.addKeyEventDispatcherForTheSystem();
     
        });

    }

}