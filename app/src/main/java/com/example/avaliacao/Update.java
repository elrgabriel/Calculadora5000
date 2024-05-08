package com.example.avaliacao;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Update extends AppCompatActivity {
    EditText input_nome, input_ano;
    Button btn_update;
    String id_equipa, nome_equipa, ano_campeonato;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_update);

        input_nome = findViewById(R.id.edit_update_nomeEquipa);
        input_ano = findViewById(R.id.edit_update_anoCampeonato);
        getAndSetIntentData();
        btn_update = findViewById(R.id.bt_update_update);
        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nome_equipa = input_nome.getText().toString().trim();
                String ano_campeonato = input_ano.getText().toString().trim();
                MySQL myDB = new MySQL(Update.this);
                myDB.atualizarBD(id_equipa, nome_equipa, ano_campeonato);
            }
        });


    }

    void getAndSetIntentData() {
        if (getIntent().hasExtra("id_equipa") && getIntent().hasExtra("nome_equipa") && getIntent().hasExtra("ano_campeonato")) {
            //Pega dados do Intent
            id_equipa = getIntent().getStringExtra("id_equipa");
            nome_equipa = getIntent().getStringExtra("nome_equipa");
            ano_campeonato = getIntent().getStringExtra("ano_campeonato");
            //Define dados no EditText
            input_nome.setText(nome_equipa);
            input_ano.setText(ano_campeonato);
        } else {
            Toast.makeText(this, "Erro ao recuperar dados", Toast.LENGTH_SHORT).show();
        }
    }
}
