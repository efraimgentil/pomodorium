package me.efraimgentil.pomodorium.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.List;

import me.efraimgentil.pomodorium.service.DatabaseHelper;

/**
 * Created by efraimgentil on 25/05/16.
 */
public abstract class GenericDao<T> implements IDatabase<T> {

    final MyDbHelper helper;
    SQLiteDatabase db;

    public GenericDao(Context context){
        helper = new MyDbHelper(context);
    }

    public SQLiteDatabase writableDB(){
        if( db == null || !db.isOpen() ){
            db = helper.getWritableDatabase();
        }
        return db;
    }

    public SQLiteDatabase readableDB(){
        if(  db == null || !db.isOpen() ){
            db = helper.getReadableDatabase();
        }
        return db;
    }

}
