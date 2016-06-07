package me.efraimgentil.pomodorium.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import me.efraimgentil.pomodorium.R;
import me.efraimgentil.pomodorium.dao.TarefaDao;
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
    private Handler handler;
    private TarefaDao tarefaDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pomodoro);

        tarefaDao = new TarefaDao(PomodoroActivity.this);
        tarefa = (Tarefa) getIntent().getExtras().get("tarefa");

        serviceConnection = new BoundServiceConnection(this , tarefa );
        serviceIntent = new Intent(this, BoundService.class);
        startService(serviceIntent);

        handler= new Handler();
    }

    @Override
    protected void onStart() {
        super.onStart();
        bindService( serviceIntent, serviceConnection , Context.BIND_AUTO_CREATE  );

        ((TextView)findViewById(R.id.pomodoro_txt_title)).setText( tarefa.getNome() );
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(serviceConnection.isConnected()){
            stopService(serviceIntent);
            unbindService(serviceConnection);
            serviceConnection.getService().closeService();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void receiveNewValue(final Pomodoro pomodoro, final String newTime, final PomodoroEvent event) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                MediaPlayer mp;
                switch (event){
                    case UPDATE:
                        ((TextView)findViewById(R.id.pomodoro_txt_timer)).setText( newTime );
                        break;
                    case END_CICLE:
                        ((TextView)findViewById(R.id.pomodoro_txt_timer)).setText( newTime );

                        mp = MediaPlayer.create(PomodoroActivity.this, R.raw.alarm );
                        mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {

                            @Override
                            public void onCompletion(MediaPlayer mp) {
                                // TODO Auto-generated method stub
                                mp.release();
                            }

                        });
                        mp.start();

                        AlertDialog.Builder builder1 = new AlertDialog.Builder(PomodoroActivity.this);
                        if(pomodoro.isInterval()){
                            builder1.setMessage("Está na hora de voltar ao trabalho.");
                        }else {
                            builder1.setMessage("Está na hora do intervalo.");
                        }
                        builder1.setCancelable(false);
                        builder1.setPositiveButton(
                                "OK",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        serviceConnection.getService().startNewCicle();
                                        dialog.cancel();
                                    }
                                });
                        builder1.create().show();
                        break;
                    case COMPLETE:
                        mp = MediaPlayer.create(PomodoroActivity.this, R.raw.ff7bw );
                        mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {

                            @Override
                            public void onCompletion(MediaPlayer mp) {
                                // TODO Auto-generated method stub
                                mp.release();
                            }

                        });
                        mp.start();


                        AlertDialog.Builder builder = new AlertDialog.Builder(PomodoroActivity.this);
                        builder.setMessage("Vocẽ completou todos os pomodoros.");
                        builder.setCancelable(false);
                        builder.setPositiveButton(
                                "OK",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        tarefa.setConcluido(true);
                                        tarefaDao.update( tarefa );
                                        dialog.cancel();
                                        finish();
                                    }
                                });
                        builder.create().show();
                        break;
                }
            }
        });

    }
}
