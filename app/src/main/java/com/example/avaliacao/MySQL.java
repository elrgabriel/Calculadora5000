package com.example.avaliacao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;


public class MySQL extends SQLiteOpenHelper {

    Context ctx;
    public static final String EQUIPAS = "EQUIPAS";
    public static final String PK = "PK";
    public static final String NOME_EQUIPAS = "NOME_EQUIPAS";
    public static final String CAMPEONATOS = "CAMPEONATOS";
    public static final String FK = "FK";
    public static final String ANO_CAMPEONATO = "ANO_CAMPEONATO";
    public static final String DATABASE = "CalculadoraDB.db";

    public MySQL(@Nullable Context context) {
        super(context, DATABASE, null, 1);
        ctx = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqlEquipas = "CREATE TABLE " + EQUIPAS + " ( " +
                "    " + PK + "         INTEGER   PRIMARY KEY," +
                "    " + NOME_EQUIPAS + " TEXT (30) " +
                ");";

        String sqlCampeonatos = "CREATE TABLE " + CAMPEONATOS + "(" +
                FK +           "  INTEGER      REFERENCES EQUIPAS (" + PK + "), " +
                ANO_CAMPEONATO + " INTEGER(10) " +
                ");";
        db.execSQL(sqlEquipas);
        db.execSQL(sqlCampeonatos);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql1 = "Drop table if exists " + EQUIPAS + " ; ";
        String sql2 = "Drop table if exists " + CAMPEONATOS + " ; ";
        db.execSQL(sql1);
        db.execSQL(sql2);
        onCreate(db);
    }
    public long inserirEquipa (Equipa equipa,Campeonatos campeonato){
        long resp = 0;
        SQLiteDatabase db = getWritableDatabase();
        try{
            db.beginTransaction();
            ContentValues cv = new ContentValues();
            cv.put(NOME_EQUIPAS,equipa.getNome_equipa());
            // Insert into EQUIPAS table
            long equipeId = db.insert(EQUIPAS, null, cv);
            // Insert into CAMPEONATOS table with the generated PK from EQUIPAS table
            if (equipeId != -1) {
                cv = new ContentValues();
                cv.put(FK, equipeId);
                cv.put(ANO_CAMPEONATO, campeonato.getAno_campeonato()); // Assuming you have a method to get the year of championship
                resp = db.insert(CAMPEONATOS, null, cv);
            }
            db.setTransactionSuccessful();
        } catch(SQLException erro){
            Toast.makeText(ctx,erro.getMessage(),Toast.LENGTH_LONG).show();
        } finally {
            db.endTransaction();
            db.close();
        }
        return resp;
    }
    public List<Equipa> carregaListaEquipas() {
        List<Equipa> equipas = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        String sql = "SELECT * FROM " + EQUIPAS + ";";
        Cursor cursor = db.rawQuery(sql, null);

        if (cursor != null && cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getInt(0));
                String nomeEquipas = cursor.getString(cursor.getInt(1));

                equipas.add(new Equipa(id, nomeEquipas));
            } while (cursor.moveToNext());
        }

        if (cursor != null) {
            cursor.close();
        }
        db.close();

        return equipas;
    }

    public List<Campeonatos> carregaListaCampeonatos() {
        List<Campeonatos> campeonatos = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        String sql = "SELECT * FROM " + CAMPEONATOS + ";";
        Cursor cursor = db.rawQuery(sql, null);

        if (cursor != null && cursor.moveToFirst()) {
            do {
                int equipeId = cursor.getInt(0);
                int anoCampeonato = cursor.getInt(1);

                campeonatos.add(new Campeonatos( equipeId, anoCampeonato));
            } while (cursor.moveToNext());
        }

        if (cursor != null) {
            cursor.close();
        }
        db.close();

        return campeonatos;
    }
    public int atualizarEquipa(int equipeId, String novoNome) {
        SQLiteDatabase db = getWritableDatabase();
        int rowsAffected = 0;

        try {
            ContentValues values = new ContentValues();
            values.put(NOME_EQUIPAS, novoNome);

            rowsAffected = db.update(EQUIPAS, values, PK + " = ?", new String[]{String.valueOf(equipeId)});
        } catch (SQLException erro) {
            Toast.makeText(ctx, erro.getMessage(), Toast.LENGTH_LONG).show();
        } finally {
            db.close();
        }

        return rowsAffected;
    }

    public int atualizarCampeonato(int equipeId, int novoAnoCampeonato) {
        SQLiteDatabase db = getWritableDatabase();
        int rowsAffected = 0;

        try {
            ContentValues values = new ContentValues();
            values.put(ANO_CAMPEONATO, novoAnoCampeonato);

            rowsAffected = db.update(CAMPEONATOS, values, FK + " = ?", new String[]{String.valueOf(equipeId)});
        } catch (SQLException erro) {
            Toast.makeText(ctx, erro.getMessage(), Toast.LENGTH_LONG).show();
        } finally {
            db.close();
        }

        return rowsAffected;
    }
    public int deletarEquipa(int equipeId) {
        SQLiteDatabase db = getWritableDatabase();
        int rowsAffected = 0;

        try {
            rowsAffected = db.delete(EQUIPAS, PK + " = ?", new String[]{String.valueOf(equipeId)});
            // If you want to delete associated championship records in CAMPEONATOS table as well,
            // you can add the following line:
            // db.delete(CAMPEONATOS, FK + " = ?", new String[]{String.valueOf(equipeId)});
        } catch (SQLException erro) {
            Toast.makeText(ctx, erro.getMessage(), Toast.LENGTH_LONG).show();
        } finally {
            db.close();
        }

        return rowsAffected;
    }

    public int deletarCampeonato(int equipeId) {
        SQLiteDatabase db = getWritableDatabase();
        int rowsAffected = 0;

        try {
            rowsAffected = db.delete(CAMPEONATOS, FK + " = ?", new String[]{String.valueOf(equipeId)});
        } catch (SQLException erro) {
            Toast.makeText(ctx, erro.getMessage(), Toast.LENGTH_LONG).show();
        } finally {
            db.close();
        }

        return rowsAffected;
    }


}
