package me.efraimgentil.pomodorium.service;

import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.v7.widget.ViewUtils;
import android.util.Log;

import java.util.*;
import java.util.Observer;

import me.efraimgentil.pomodorium.MainActivity;
import me.efraimgentil.pomodorium.model.Pomodoro;
import me.efraimgentil.pomodorium.model.Tarefa;

/**
 * Created by efraimgentil on 04/06/16.
 */
public class BoundService extends Service implements ServiceNotifier{

    private IBinder binder;
    private boolean stop;
    private boolean isCountStarted;
    private List<PomodoroObserver> observers = new ArrayList<>();

    public BoundService() {
        this.stop = false;
        this.isCountStarted = false;
        this.binder = new LocalBinder();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    public void startCounter(final Tarefa tarefa) {

        final Pomodoro pomodoro = new Pomodoro(tarefa);
        isCountStarted = true;
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (!stop) {
                    try {
                        if (isCountStarted) {
                            final String currentCicleTime = pomodoro.getCurrentCicleTime();
                            if ("00:00".equals(currentCicleTime)) {
                                if(pomodoro.isCiclesEnded()){
                                    notifyEvent(pomodoro, currentCicleTime, PomodoroEvent.COMPLETE );
                                }else {
                                    notifyEvent(pomodoro, currentCicleTime, PomodoroEvent.END_CICLE);
                                }
                            } else {
                                notifyEvent(pomodoro, currentCicleTime, PomodoroEvent.UPDATE);
                            }
                        }
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                stop = false;
                isCountStarted = false;
            }
        }).start();
    }

    @Override
    public void add(PomodoroObserver observer) {
        observers.add(observer);
    }

    @Override
    public void notifyEvent(Pomodoro pomodoro, String newTime, PomodoroEvent event) {
        for (PomodoroObserver observer: observers ) {
            observer.receiveNewValue(pomodoro , newTime , event );
        }
    }

    public void stopCounter() {
        this.stop = true;
    }

    public void closeService() {
        stopCounter();
        stopSelf();
    }

    public class LocalBinder extends Binder {
        public BoundService getService() {
            return BoundService.this;
        }
    }

}
