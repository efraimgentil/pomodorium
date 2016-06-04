package me.efraimgentil.pomodorium;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import me.efraimgentil.pomodorium.activity.PomodoroActivity;
import me.efraimgentil.pomodorium.activity.TarefaActivity;
import me.efraimgentil.pomodorium.activity.TarefasFragment;
import me.efraimgentil.pomodorium.model.Tarefa;

public class MainActivity extends AppCompatActivity implements TarefasFragment.OnListFragmentInteractionListener {

    private TarefasFragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragment = new TarefasFragment( );
        final android.support.v4.app.FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add( R.id.tarefas_container , fragment ).commit();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        fragment.refreshList();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate( R.menu.main_menu , menu  );
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch ( id ) {
            case R.id.action_tarefas:
                Intent intent = new Intent(MainActivity.this, TarefaActivity.class);
                startActivity( intent );
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onListFragmentInteraction(Tarefa item) {
        Intent intent = new Intent(MainActivity.this , PomodoroActivity.class );
        intent.putExtra("tarefa" , item );
        startActivity( intent );
    }
}
