package me.efraimgentil.pomodorium.service;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;

import me.efraimgentil.pomodorium.model.Tarefa;

/**
 * Created by efraimgentil on 04/06/16.
 */
public class BoundServiceConnection implements ServiceConnection {


    private BoundService.LocalBinder binder;
    private ServiceNotifier serviceNotifier;
    private PomodoroObserver observer;
    private boolean connected;
    private Tarefa tarefa;

    public BoundServiceConnection(PomodoroObserver observer , Tarefa tarefa) {
        this.observer = observer;
        this.tarefa = tarefa;
    }

    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {
        binder = (BoundService.LocalBinder) service;
        serviceNotifier = binder.getService();
        serviceNotifier.add(observer);
        connected = true;
        binder.getService().startCounter( tarefa );
    }

    @Override
    public void onServiceDisconnected(ComponentName name) {
        binder.getService().stopCounter();
        connected = false;
    }

    public BoundService getService(){
        return binder.getService();
    }

    public boolean isConnected() {
        return connected;
    }

    public ServiceNotifier getServiceNotifier() {
        return serviceNotifier;
    }

}
