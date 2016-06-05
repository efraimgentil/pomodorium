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

    private final int CICLE_TIME =  10; //60 * 25;// 25 minutos
    private final int BIG_INTERVAL = 60 * 15; //15 minutos de intervalo
    private final int SMALL_INTERVAL = 5; //60 * 5; // 5 minutos de intervalo

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
        timeInSeconds = new AtomicInteger(CICLE_TIME );
    }

    public void prepareNewCicle(){
        if(interval){
            interval= false;
            currentCicle++;
            timeInSeconds = new AtomicInteger( CICLE_TIME);
        }else{
            interval = true;
            if(currentCicle % 3 == 0){
                timeInSeconds = new AtomicInteger( BIG_INTERVAL);
            }else{
                timeInSeconds = new AtomicInteger( SMALL_INTERVAL );
            }
        }
    }

    public boolean isCiclesEnded(){
        return maxCicles.equals(currentCicle);
    }

    public int getTimeInSeconds(){
        return timeInSeconds.get();
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
