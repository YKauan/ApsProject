package apsproject.src.methods;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.KeyEvent;

import javax.swing.JDialog;
import javax.swing.JPanel;

import apsproject.src.screens.Screen;

public class BlockKeys implements KeyEventDispatcher {

   private static JDialog block;
   private JPanel pane;
   private Screen screen;

    @Override
    public boolean dispatchKeyEvent(KeyEvent e) {
      System.out.println(e.getKeyCode());
        if (e.getKeyCode() == KeyEvent.VK_PRINTSCREEN && e.getID() == KeyEvent.KEY_PRESSED) {
            pane = new JPanel();
            block = new JDialog(screen,"", true);
            block.setSize(new Dimension(1250, 600));
            block.setLocationRelativeTo(screen);
            pane.setBackground(Color.BLACK);
            block.add(pane);
            block.setVisible(true);
            e.consume();
            return true;
        }else if(e.getKeyCode() == KeyEvent.VK_PRINTSCREEN && e.getID() == KeyEvent.KEY_RELEASED){

            block.dispose();
            return true;

        }
        return false;
    }
    
    public static void blockPrints() {
      BlockKeys blockPrint = new BlockKeys();
        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(blockPrint);
    }
}