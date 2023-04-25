package apsproject.src.methods;

import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class UpdateFieldsMatch implements DocumentListener {

    private JTextField[] lapTime1;
    private JTextField[] lapTime2;
    private JTextField[] totalTimes;
    private int i;
    private boolean isUpadating;

    //=> Construtor que recebe os arrays de tempos da volta 1 e 2 tempos totais e o indice da volta atual.
    public UpdateFieldsMatch(JTextField[] lapTime1, JTextField[] lapTime2, JTextField[] totalTimes, int i) {
        this.i           = i;
        this.lapTime1    = lapTime1; 
        this.lapTime2    = lapTime2;
        this.totalTimes  = totalTimes;
        this.isUpadating = false;
    }

    public void changedUpdate(DocumentEvent e) {

    }

    //=> Metodo que e responsavel por atualizar os campos quando algo e digitado
    public void insertUpdate(DocumentEvent e) {
        if(!isUpadating){
            isUpadating = true;
            totalEvent(e);
            isUpadating = false;
        }  
    }

    //=> Metodo que e responsavel por atualizar os campos quando algo e apagado
    public void removeUpdate(DocumentEvent e) {
        Runnable threadUpdater = new Runnable() {
            @Override
            public void run() {
                if(!isUpadating){
                    isUpadating = true;
                    totalEvent(e);
                    isUpadating = false;
                }
            };
        };SwingUtilities.invokeLater(threadUpdater); 
    }

    //=> Metodo responsavel por atualizar o tempo total de uma volta ou mais
    private void totalEvent(DocumentEvent e) {

        //=> Instanciando minha classe CalculaTimes
        CalculateTimes  calculator = new CalculateTimes();

        //=> Verifico se os campos estao vazios, se sim defino como 0.0, se nao converto o valor para double
        double lap1Time = lapTime1[i].getText().isEmpty() ? 0.0 : Double.parseDouble(lapTime1[i].getText());
        double lap2Time = lapTime2[i].getText().isEmpty() ? 0.0 : Double.parseDouble(lapTime2[i].getText());
        
        //=> Variavel que recebe o valor total calculado em formato de string 
        String totalTimesResult = calculator.calculateTimes(lap1Time, lap2Time);
        
        //=> Defino o conteudo do meu campo total com o resultado do calculo
        totalTimes[i].setText(totalTimesResult);
 

    }

}
