package me.efraimgentil.pomodorium;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.android.ContextHolder;
import org.sqldroid.DroidDataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import me.efraimgentil.pomodorium.activity.TarefaActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        /*DroidDataSource dataSource = new DroidDataSource(getPackageName(), "db1");


        ContextHolder.setContext(this);
        Flyway flyway = new Flyway();
        flyway.setDataSource(dataSource);
        flyway.migrate();


        try {
            Connection connection = dataSource.getConnection();
            ResultSet resultSet = connection.prepareStatement("SELECT * FROM pessoas ").executeQuery();
            while( resultSet.next() ){
                System.out.println( resultSet.getInt("_id") );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }*/

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

}
