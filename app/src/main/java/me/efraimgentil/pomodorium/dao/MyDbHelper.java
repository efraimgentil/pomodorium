package me.efraimgentil.pomodorium.dao;

import android.content.Context;
import android.content.res.AssetManager;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.MediaStore;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by efraimgentil on 29/05/16.
 */
public class MyDbHelper extends SQLiteOpenHelper {

    private static final String BANCO = "pomodorium.db";
    private static final int VERSAO = 1;

    private AssetManager assetManager;

    public MyDbHelper(Context context) {
        super(context,  BANCO , null, VERSAO );
        assetManager=  context.getAssets();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(assetManager.open("db/create.sql")) ) )  {
            // do reading, usually loop until end of file reading
            StringBuilder sb = new StringBuilder();
            String mLine;
            while ((mLine = reader.readLine()) != null) {
                sb.append( mLine );
            }
            String fileContent = sb.toString();
            String[] split = fileContent.split(";");
            for ( String command : split ) {
                db.execSQL( command + ";" );
            }
        } catch (IOException e) {
            Log.e("Database", "Erro ao ler arquivo de criacao do banco de dados" , e );
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
