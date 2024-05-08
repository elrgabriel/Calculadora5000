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

    private Context context;
    private static final String DB_NOME = "TabelaCampeonatos.db";
    private static final int DB_VERSAO = 1;

    private static final String TABELA_NOME = "campeonatos";
    private static final String COLUNA_ID = "_id";
    private static final String COLUNA_EQUIPAS = "nome_equipa";
    private static final String COLUNA_ANO = "ano_campeao";
    private static final String COLUNA_IMAGEM = "imagem";

    public MySQL(@Nullable Context context) {
        super(context, DB_NOME, null, DB_VERSAO);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABELA_NOME +
                " (" + COLUNA_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUNA_EQUIPAS + " TEXT, " +
                COLUNA_ANO + " INTEGER);";
        db.execSQL(query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABELA_NOME);
        onCreate(db);
    }

    void inserirEquipa(String equipa, int ano_campeonato) {
        SQLiteDatabase db = this.getWritableDatabase(); //método pertence à classe SQLOpenHelper serve para escrever dados na tabela
        ContentValues cv = new ContentValues(); //Armazena os dados da app para repassar para a base de dados.

        cv.put(COLUNA_EQUIPAS, equipa); //Método para inserir dados na coluna, recebendo o respectivo parâmetro
        cv.put(COLUNA_ANO, ano_campeonato);
        long result = db.insert(TABELA_NOME, null, cv); //Método insere os dados contidos na variável de ContentValues nas respectivas colunas da base de dados
        if (result == -1) {
            Toast.makeText(context, "Falhou", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Inserido com Sucesso", Toast.LENGTH_SHORT).show();
        }
    }
    Cursor lerBD(){
        String query = "SELECT * FROM " + TABELA_NOME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if (db != null) {
            cursor = db.rawQuery(query,null);
        }
        return cursor;
    }


}
