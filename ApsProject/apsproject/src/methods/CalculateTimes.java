package apsproject.src.methods;

import java.text.DecimalFormat;

public class CalculateTimes {

    private double lap1Time;
    private double lap2Time;
    
    //=> Metodo responsavel por calcular o tempo das voltas
    public String calculateTimes(double lap1Time, double lap2Time){

        this.lap1Time = lap1Time;
        this.lap2Time = lap2Time;

        double totalTimeSeconds = this.lap1Time + this.lap2Time;

        int seconds = (int) totalTimeSeconds % 60;
        int minutes = (int) (totalTimeSeconds / 60) % 60;
        int hours   = (int) totalTimeSeconds / 3600;

        DecimalFormat decimalFormat = new DecimalFormat("00");
        String totalTimeFormatted = decimalFormat.format(hours) + ":" + decimalFormat.format(minutes) + ":" + decimalFormat.format(seconds);

        return totalTimeFormatted;

    }

}
