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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by efraimgentil on 29/05/16.
 */
public class MyDbHelper extends SQLiteOpenHelper {

    private static final String BANCO = "pomodorium.db";
    private static final int VERSAO = 2;

    private AssetManager assetManager;

    public MyDbHelper(Context context) {
        super(context,  BANCO , null, VERSAO );
        assetManager=  context.getAssets();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final List<String> commands = breakFileInCommands("db/create.sql");
        for ( String command : commands ) {
            db.execSQL( command + ";" );
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        final List<String> commands = breakFileInCommands("db/update_"+ newVersion +".sql");
        for ( String command : commands ) {
            db.execSQL( command + ";" );
        }
    }

    public List<String> breakFileInCommands(String fileAssetsPath ){
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(assetManager.open( fileAssetsPath )) ) )  {
            // do reading, usually loop until end of file reading
            StringBuilder sb = new StringBuilder();
            String mLine;
            while ((mLine = reader.readLine()) != null) {
                sb.append( mLine );
            }
            String fileContent = sb.toString();
            String[] split = fileContent.split(";");
            return Arrays.asList(split);
        } catch (IOException e) {
            Log.e("Database", "Erro ao ler arquivo de criacao do banco de dados" , e );
        }
        return Collections.EMPTY_LIST;
    }

}
