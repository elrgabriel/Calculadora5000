package com.example.avaliacao;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class Act_Campeonatos extends AppCompatActivity implements View.OnClickListener {
    RecyclerView recyclerView;
    FloatingActionButton bt_adicionar;
    ArrayList<String> id_equipa,nome_equipa,ano_campeonato;
    RecycleViewAdapter rvAdapter;
    MySQL myDB;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_campeonatos);

        recyclerView = findViewById(R.id.recycleview_campeonatos);
        bt_adicionar = findViewById(R.id.rv_float_bt_adicionar);
        bt_adicionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Act_Campeonatos.this,Insert.class);
                startActivity(intent);
            }
        });

        myDB = new MySQL(Act_Campeonatos.this);
        id_equipa = new ArrayList<>();
        nome_equipa = new ArrayList<>();
        ano_campeonato = new ArrayList<>();

        storeDataInArrays();
        rvAdapter = new RecycleViewAdapter(Act_Campeonatos.this,id_equipa,nome_equipa,ano_campeonato);
        recyclerView.setAdapter(rvAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(Act_Campeonatos.this));
    }

    @Override
    public void onClick(View v) {

    }

    void storeDataInArrays(){
        Cursor cursor = myDB.lerBD();
        if (cursor.getCount() == 0) {
            Toast.makeText(this, "Nenhum registro encontrado", Toast.LENGTH_SHORT).show();
        }else {
            while (cursor.moveToNext()) {
                id_equipa.add(cursor.getString(0));
                nome_equipa.add(cursor.getString(1));
                ano_campeonato.add(cursor.getString(2));
            }
        }
    }
}
