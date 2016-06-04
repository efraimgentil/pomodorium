package me.efraimgentil.pomodorium.activity;

import android.content.Context;
import android.content.Intent;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import me.efraimgentil.pomodorium.R;
import me.efraimgentil.pomodorium.model.Pomodoro;
import me.efraimgentil.pomodorium.model.Tarefa;
import me.efraimgentil.pomodorium.service.BoundService;
import me.efraimgentil.pomodorium.service.BoundServiceConnection;
import me.efraimgentil.pomodorium.service.PomodoroEvent;
import me.efraimgentil.pomodorium.service.PomodoroObserver;

public class PomodoroActivity extends AppCompatActivity implements PomodoroObserver {

    private BoundServiceConnection serviceConnection;
    private Intent serviceIntent;
    private Tarefa tarefa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pomodoro);

        tarefa = (Tarefa) getIntent().getExtras().get("tarefa");

        serviceConnection = new BoundServiceConnection(this , tarefa );
        serviceIntent = new Intent(this, BoundService.class);
        startService(serviceIntent);

    }

    @Override
    protected void onStart() {
        super.onStart();
        bindService( serviceIntent, serviceConnection , Context.BIND_AUTO_CREATE  );

    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(serviceConnection.isConnected()){
            stopService(serviceIntent);
            unbindService(serviceConnection);
        }
    }

    @Override
    public void receiveNewValue(Pomodoro pomodoro, String newTime, PomodoroEvent event) {
            Log.i("pomodoro" , newTime );
    }
}
