package apsproject.src.methods;

import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;

public class BlockKeys implements KeyEventDispatcher {

    @Override
    public boolean dispatchKeyEvent(KeyEvent e) {
      System.out.println(e.getKeyCode());
      if (e.getKeyCode() == KeyEvent.VK_PRINTSCREEN) {

        String randomText = "Hoje nao Nelson!"; // gerando um texto aleatório de 20 caracteres
        StringSelection stringSelection = new StringSelection(randomText); // criando uma seleção de texto
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard(); // obtendo a área de transferência do sistema
        clipboard.setContents(stringSelection, null); // colocando a seleção na área de transferência

      }
        return false;
    }
    
    public static void blockPrints() {
      BlockKeys blockPrint = new BlockKeys();
        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(blockPrint);
    }
}