package apsproject.src.methods;

import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;

public class BlockKeys implements KeyEventDispatcher {
  
  //=> Sobreescrevendo o metodo dispatchKeyEvent da interface KeyEventDispatcher
  @Override
  public boolean dispatchKeyEvent(KeyEvent e) {

    if (e.getKeyCode() == KeyEvent.VK_PRINTSCREEN) {

      //=> Criando uma selecao de texto para ser adicionado a area de transferencia
      String randomText = "Hoje nao Nelson!"; //=> Texto que sera copiado para a aerea de transferencia 
      StringSelection stringSelection = new StringSelection(randomText); //=> Criando uma selecao de texto

      //=> Pegando a area de transferencia e colando o texto na mesma
      Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
      clipboard.setContents(stringSelection, null);

    }
    return false;
  }
  
  //=> Metodo responsavel por adicionar o dispatchKeyEvent ao KeyboardFocusManager
  public static void blockPrints() {

    //=> Instanciando a classe BlockKeys
    BlockKeys blockPrint = new BlockKeys();

    //=> Adicionando o meu dispatchKeyEvent ao KeyboardFocusManager
    KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(blockPrint);

  }
}