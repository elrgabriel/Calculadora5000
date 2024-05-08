package com.example.avaliacao;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Insert extends AppCompatActivity implements View.OnClickListener {
    EditText equipa_input, ano_campeonato_input;
    Button bt_inserir;

    @Override
    public void onClick(View v) {

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_insert);
        equipa_input = findViewById(R.id.edit_insert_nomeEquipa);
        ano_campeonato_input = findViewById(R.id.edit_insert_anoCampeonato);
        bt_inserir = findViewById(R.id.bt_insert_insert);
        bt_inserir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MySQL myDB = new MySQL(Insert.this); //Cria objeto da classe MySQL capaz de usar os metodos de criação da BD
                myDB.inserirEquipa(equipa_input.getText().toString(),
                        Integer.parseInt(ano_campeonato_input.getText().toString())); //Passa os parâmetros necerrários do método InserirEquipa

            }
        });
    }
}
