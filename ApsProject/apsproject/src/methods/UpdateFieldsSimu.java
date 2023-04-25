package apsproject.src.methods;

import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class UpdateFieldsSimu implements DocumentListener {

    private JTextField[] lapTime1;
    private JTextField[] lapTime2;
    private JTextField[] totalTimes;
    private JTextField[] playersName;
    private JTextField[] playersNameVisu;
    private JTextField[] lap1Visu;
    private JTextField[] lap2Visu;

    private int i;
    private boolean isUpadating;

    //=> Construtor que recebe os arrays de tempos da volta 1 e 2 tempos totais e o indice da volta atual.
    public UpdateFieldsSimu(JTextField[] lapTime1, JTextField[] lapTime2, JTextField[] totalTimes, JTextField[] playersName, JTextField[] playersNameVisu, JTextField[] lap1Visu, JTextField[] lap2Visu, int i) {
        this.i               = i;
        this.lapTime1        = lapTime1; 
        this.lapTime2        = lapTime2;
        this.totalTimes      = totalTimes;
        this.playersName     = playersName;
        this.playersNameVisu = playersNameVisu;
        this.lap1Visu        = lap1Visu;
        this.lap2Visu        = lap2Visu;
        this.isUpadating     = false;
    }
    public void changedUpdate(DocumentEvent e) {

    }

    //=> Metodo que e responsavel por atualizar os campos quando algo e digitado
    public void insertUpdate(DocumentEvent e) {
        if(!isUpadating){
            isUpadating = true;
            updaterEvent(e);
            isUpadating = false;
        }
            
    }

    public void removeUpdate(DocumentEvent e) {

    }

    //=> Metodo responsavel por atualizar os meus JTextField
    private void updaterEvent(DocumentEvent e) {

        //=> Instanciando minha classe CalculaTimes
        CalculateTimes  calculator = new CalculateTimes();

        //=> Verifico se os campos estao vazios, se sim defino como 0.0, se nao converto o valor para double
        double lap1Time = lapTime1[i].getText().isEmpty() ? 0.0 : Double.parseDouble(lapTime1[i].getText());
        double lap2Time = lapTime2[i].getText().isEmpty() ? 0.0 : Double.parseDouble(lapTime2[i].getText());
        
        //=> Capturando o nome do jogador
        String namePlayer = playersName[i].getText();
        
        //=> Variavel que recebe o valor total calculado em formato de string 
        String totalTimesResult = calculator.calculateTimes(lap1Time, lap2Time);

        //=> Verifico se os campos estao vazios, se sim defino como 0.0, se nao converto o valor para double
        double lap1TimeVisu = lapTime1[i].getText().isEmpty() ? 0.0 : Double.parseDouble(lapTime1[i].getText());
        double lap2TimeVisu = lapTime2[i].getText().isEmpty() ? 0.0 : Double.parseDouble(lapTime2[i].getText());

        lap1Visu[i].setText(Double.toString(lap1TimeVisu));
        lap2Visu[i].setText(Double.toString(lap2TimeVisu));
        playersNameVisu[i].setText(namePlayer);
        totalTimes[i].setText(totalTimesResult);
    }

}
