package apsproject.src.methods;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class BlockKeys implements KeyListener {

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_PRINTSCREEN && e.isShiftDown()) {
           e.consume(); // impede que a tecla seja processada
        }
     }
     public void keyReleased(KeyEvent e) {
        // não faz nada
     }
     public void keyTyped(KeyEvent e) {
        // não faz nada
     }
}