package me.efraimgentil.pomodorium.activity;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import me.efraimgentil.pomodorium.R;
import me.efraimgentil.pomodorium.dao.TarefaDao;
import me.efraimgentil.pomodorium.model.Intention;
import me.efraimgentil.pomodorium.model.Tarefa;

public class TarefaActivity extends AppCompatActivity implements ListTarefaFragment.OnListFragmentInteractionListener {

    TarefaDao tarefaDao;
    private ListTarefaFragment listFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tarefa);
        setTitle("Lista Tarefas");

        listFragment = new ListTarefaFragment( );
        final android.support.v4.app.FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add( R.id.teste , listFragment ).commit();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate( R.menu.menu_tarefas , menu  );
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        final int itemId = item.getItemId();
        switch ( itemId){
            case R.id.menu_item_nova_tarefa:
                final Intent intent = new Intent(TarefaActivity.this, FormTarefaActivity.class);
                startActivity( intent );
                break;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onRestart() {
        super.onRestart();
        listFragment.refreshList();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onListFragmentInteraction(View v, Tarefa item , Intention intention) {
        switch (intention){
            case CLICK:
                break;
            case EDTIAR:
                final Intent intent = new Intent(TarefaActivity.this, FormTarefaActivity.class);
                intent.putExtra("tarefa" , item );
                startActivity( intent );
                break;
            case REMOVER:
                listFragment.getTarefaDao().delete( item );;
                listFragment.refreshList();
                break;
        }
        Log.i("Teste", item.toString() );
    }

}
