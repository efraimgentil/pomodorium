package me.efraimgentil.pomodorium.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import me.efraimgentil.pomodorium.model.Tarefa;

/**
 * Created by efraimgentil on 29/05/16.
 */
public class TarefaDao extends GenericDao<Tarefa> {

    public TarefaDao(Context context) {
        super(context);
    }

    @Override
    public void insert(Tarefa tarefa) {
        final SQLiteDatabase db = writableDB();
        ContentValues values = new ContentValues();
        values.put( Tarefa.NOME , tarefa.getNome() );
        values.put( Tarefa.DESCRICAO , tarefa.getDescricao() );
        values.put( Tarefa.CICLOS , tarefa.getCiclos() );
        values.put( Tarefa.CONCLUIDO , tarefa.isConcluido() ? true : false );
        final long insert = db.insert(Tarefa.TABELA, null, values);
        if(insert < 0 )  throw new RuntimeException("Não foi possivel inserir o registro");
        db.close();
    }

    @Override
    public void update(Tarefa tarefa) {
        final SQLiteDatabase db = writableDB();
        ContentValues values = new ContentValues();
        values.put( Tarefa.NOME , tarefa.getNome() );
        values.put( Tarefa.DESCRICAO , tarefa.getDescricao() );
        values.put( Tarefa.CICLOS , tarefa.getCiclos() );
        values.put( Tarefa.CONCLUIDO , tarefa.isConcluido() ? true : false );
        db.update( Tarefa.TABELA , values , "_id = ?" , new String[]{ String.valueOf(tarefa.getId() ) });
        db.close();
    }

    @Override
    public void delete(Tarefa tarefa) {
        final SQLiteDatabase db = writableDB();
        db.delete(  Tarefa.TABELA , "_id = ?" , new String[]{ String.valueOf(tarefa.getId() ) } );
        db.close();
    }

    @Override
    public Tarefa find(int id) {
        return null;
    }

    @Override
    public List<Tarefa> findAll() {
        Cursor cursor;
        String[] campos = { Tarefa.ID , Tarefa.NOME , Tarefa.DESCRICAO , Tarefa.CICLOS , Tarefa.CONCLUIDO };
        final SQLiteDatabase db = readableDB();
        cursor = db.query(Tarefa.TABELA, campos, null, null, null, null, null);

        List<Tarefa> tarefas = new ArrayList<>();
        while( cursor.moveToNext() ){
            Tarefa tarefa = new Tarefa();
            tarefa.setId( cursor.getInt( cursor.getColumnIndexOrThrow( Tarefa.ID ) ) );
            tarefa.setNome( cursor.getString( cursor.getColumnIndexOrThrow( Tarefa.NOME ) ) );
            tarefa.setDescricao( cursor.getString( cursor.getColumnIndexOrThrow( Tarefa.DESCRICAO) ) );
            tarefa.setCiclos( cursor.getInt( cursor.getColumnIndexOrThrow( Tarefa.CICLOS ) ) );
            tarefa.setConcluido( cursor.getInt( cursor.getColumnIndexOrThrow( Tarefa.CONCLUIDO ) ) == 0 ? false : true );
            tarefas.add(tarefa);
        }
        db.close();
        return tarefas;
    }

    public List<Tarefa> findAllNaoConcluido() {
        Cursor cursor;
        String[] campos = { Tarefa.ID , Tarefa.NOME , Tarefa.DESCRICAO , Tarefa.CICLOS , Tarefa.CONCLUIDO };
        final SQLiteDatabase db = readableDB();
        cursor = db.query(Tarefa.TABELA, campos, "concluido = ?", new String[]{ "0" }, null, null, null);

        List<Tarefa> tarefas = new ArrayList<>();
        while( cursor.moveToNext() ){
            Tarefa tarefa = new Tarefa();
            tarefa.setId( cursor.getInt( cursor.getColumnIndexOrThrow( Tarefa.ID ) ) );
            tarefa.setNome( cursor.getString( cursor.getColumnIndexOrThrow( Tarefa.NOME ) ) );
            tarefa.setDescricao( cursor.getString( cursor.getColumnIndexOrThrow( Tarefa.DESCRICAO) ) );
            tarefa.setCiclos( cursor.getInt( cursor.getColumnIndexOrThrow( Tarefa.CICLOS ) ) );
            tarefa.setConcluido( cursor.getInt( cursor.getColumnIndexOrThrow( Tarefa.CONCLUIDO ) ) == 0 ? false : true );
            tarefas.add(tarefa);
        }
        db.close();
        return tarefas;
    }
}

