package me.efraimgentil.pomodorium.model;

import org.joda.time.DateTime;
import org.joda.time.Seconds;

import java.io.Serializable;
import java.util.Timer;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by efraimgentil on 04/06/16.
 */
public class Pomodoro implements Serializable {

    private Tarefa tarefa;
    private Integer maxCicles;
    private Integer currentCicle;
    private boolean interval;
    private Integer intervalSize = 5; //minutos
    private AtomicInteger timeInSeconds;

    public Pomodoro(Tarefa tarefa) {
        this.tarefa = tarefa;
        this.currentCicle = 1;
        maxCicles = tarefa.getCiclos();
        interval = false;
        timeInSeconds = new AtomicInteger( 60 * 25 ); // 25 minutos
    }

    public void prepareNewCicle(){
        if(interval){
            currentCicle++;
            timeInSeconds = new AtomicInteger( 60 * 25 ); // 25 minutos
        }else{
            interval = true;
            if(currentCicle % 3 == 0){
                timeInSeconds = new AtomicInteger( 60 * 15 ); //15 minutos de intervalo
            }else{
                timeInSeconds = new AtomicInteger( 60 * 5 );// 5 minutos de intervalo
            }
        }
    }

    public boolean isCiclesEnded(){
        return maxCicles.equals(currentCicle);
    }

    public String getCurrentCicleTime(){
        final int andDecrement = timeInSeconds.getAndDecrement();
        int minutos  = (andDecrement/ 60);
        int segundos = ( andDecrement % 60 );
        return String.format("%02d:%02d" , minutos , segundos );
    }

    public Tarefa getTarefa() {
        return tarefa;
    }

    public void setTarefa(Tarefa tarefa) {
        this.tarefa = tarefa;
    }

    public Integer getMaxCicles() {
        return maxCicles;
    }

    public void setMaxCicles(Integer maxCicles) {
        this.maxCicles = maxCicles;
    }

    public Integer getCurrentCicle() {
        return currentCicle;
    }

    public void setCurrentCicle(Integer currentCicle) {
        this.currentCicle = currentCicle;
    }

    public boolean isInterval() {
        return interval;
    }

    public void setInterval(boolean interval) {
        this.interval = interval;
    }

    public Integer getIntervalSize() {
        return intervalSize;
    }

    public void setIntervalSize(Integer intervalSize) {
        this.intervalSize = intervalSize;
    }
}
