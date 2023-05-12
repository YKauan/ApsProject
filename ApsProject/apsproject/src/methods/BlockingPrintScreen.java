package apsproject.src.methods;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.Robot;

import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;

import apsproject.src.screens.Screen;

public class BlockingPrintScreen implements KeyEventDispatcher {
  
  //=> Sobreescrevendo o metodo dispatchKeyEvent da interface KeyEventDispatcher
  @Override
  public boolean dispatchKeyEvent(KeyEvent e) {

    if (e.getKeyCode() == KeyEvent.VK_PRINTSCREEN) {
      //=> Instanciando minha classe Screen 
      Screen programFrame = new Screen(1);
      programFrame.getContentPane().setBackground(Color.BLACK); 

      programFrame.addWindowFocusListener(new WindowFocusListener() {
        @Override
        public void windowGainedFocus(WindowEvent e) {
          try {

            Thread.sleep(1500);
            Robot robot = new Robot();
            Thread.sleep(1500);

            //=> Simulando pressionamento da tecla print
            robot.keyPress(KeyEvent.VK_PRINTSCREEN); //=> Simula o pressionamento da tecla PrintScreen
            robot.keyRelease(KeyEvent.VK_PRINTSCREEN); //=> Simula a liberacao da tecla PrintScreen

            //=> Removendo o meu dispatchKeyEvent
            KeyboardFocusManager.getCurrentKeyboardFocusManager().removeKeyEventDispatcher(BlockingPrintScreen.this);

            //=> Encerro  a tela caso ela esteja aberta
            if (programFrame != null) {
              programFrame.dispose();
            }
          } catch (AWTException | InterruptedException exc) {
            exc.printStackTrace();
          }
        }

        @Override
        public void windowLostFocus(WindowEvent e) {

          //=> Adicionando novamente o dispatchKeyEvent
          addKeyEventDispatcherForTheSystem();

          //=> Encerro  a tela caso ela esteja aberta
          if (programFrame != null) {
            programFrame.dispose();
          }
        }
    });

    }
    return false;
  }
  
  //=> Metodo responsavel por adicionar o dispatchKeyEvent ao KeyboardFocusManager
  public static void addKeyEventDispatcherForTheSystem() {
    //=> Adicionando o meu dispatchKeyEvent ao KeyboardFocusManager
    KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(new BlockingPrintScreen());

  }
}