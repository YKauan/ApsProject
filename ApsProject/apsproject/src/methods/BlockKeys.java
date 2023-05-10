package apsproject.src.methods;

import java.awt.Color;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import apsproject.src.languages.LanguageManager;
import apsproject.src.screens.Screen;

public class BlockKeys implements KeyEventDispatcher {
  
  //=> Sobreescrevendo o metodo dispatchKeyEvent da interface KeyEventDispatcher
  @Override
  public boolean dispatchKeyEvent(KeyEvent e) {

    if (e.getKeyCode() == KeyEvent.VK_PRINTSCREEN) {

      //=> Instanciando minha classe Screen
      Screen programFrame = new Screen(1);
      programFrame.getContentPane().setBackground(Color.BLACK);
      
      //=> Pegando o JFrame principal do programa
      JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(programFrame);

      //=> Exibe o JOptionPane com a mensagem de block da tecla print
      JOptionPane.showMessageDialog(frame, LanguageManager.getInstance().getString("printBlock"),"", JOptionPane.INFORMATION_MESSAGE);

      //=> Criando uma selecao de texto para ser adicionado a area de transferencia 
      StringSelection text = new StringSelection("Hoje nao Nelson! -_-"); //=> Criando uma selecao de texto

      //=> Pegando a area de transferencia e colando o texto na mesma
      Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
      clipboard.setContents(text, null);

      //=> Encerrando o JFrame de bloqueio
      programFrame.dispose();
    }
    return false;
  }
  
  //=> Metodo responsavel por adicionar o dispatchKeyEvent ao KeyboardFocusManager
  public static void blockPrints() {
    //=> Adicionando o meu dispatchKeyEvent ao KeyboardFocusManager
    KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(new BlockKeys());

  }
}