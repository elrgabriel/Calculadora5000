package com.example.avaliacao;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class Act_Campeonatos extends AppCompatActivity implements View.OnClickListener {
    RecyclerView recyclerView;
    FloatingActionButton bt_adicionar;

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
    }

    @Override
    public void onClick(View v) {

    }
}
