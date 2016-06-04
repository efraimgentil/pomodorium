package me.efraimgentil.pomodorium.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import me.efraimgentil.pomodorium.R;
import me.efraimgentil.pomodorium.dao.TarefaDao;
import me.efraimgentil.pomodorium.model.Tarefa;

public class FormTarefaActivity extends AppCompatActivity {

    TarefaDao tarefaDao;
    private FormTarefaFragment formFragment;
    Tarefa tarefa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tarefa);
        setTitle("Tarefa");


        tarefa = new Tarefa();
        final Bundle extras = getIntent().getExtras();
        if(extras != null ) {
            final Object t = extras.get("tarefa");
            if (t != null) {
                tarefa = (Tarefa) t;
            }
        }

        tarefaDao = new TarefaDao(FormTarefaActivity.this);
        formFragment = new FormTarefaFragment();
        final android.support.v4.app.FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        formFragment.setTarefa( tarefa );
        ft.add( R.id.teste , formFragment ).commit();
    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    public void executaAcao(View view){

        tarefa.setNome(  ((TextView)  findViewById(R.id.ipt_nome)).getText().toString()  );
        tarefa.setDescricao(  ((TextView)  findViewById(R.id.ipt_descricao)).getText().toString()  );
        tarefa.setCiclos(  new Integer( ((TextView)  findViewById(R.id.ipt_ciclos)).getText().toString() ) );
        if(tarefa.getId() != null){
            tarefaDao.update(tarefa);
        }else {
            tarefaDao.insert(tarefa);
        }
        finish();
    }


}
