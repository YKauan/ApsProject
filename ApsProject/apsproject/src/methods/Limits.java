package apsproject.src.methods;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public class Limits extends PlainDocument{
    
    private int max;
    private int valid;

    public Limits(int max, int valid) {
        this.max = max;
        this.valid = valid;
    }

    //=> Sobrescrevendo o metodo insertString
    @Override
    public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException {
        if (str == null) {
            return;
        }

        //=> Verifica se a string contem somente digitos numericos
        if (valid != 1) {
            for (char c : str.toCharArray()) {
                if (!Character.isDigit(c) && c != '.') {
                    return;
                }
            }
        }

        //=> Verifica se a string a ser inserida contem um ponto decimal ou virgula
        if (str.contains(".") || str.contains(",")) {
            if (getText(0, getLength()).contains(".") || getText(0, getLength()).contains(",")) {
                return;
            }
        }

        //=> Verifica se o tamanho maximo permitido para o texto ainda nao foi alcançado
        if ((getLength() + str.length()) <= max) {
            super.insertString(offset, str, attr);
        }
    }


}
