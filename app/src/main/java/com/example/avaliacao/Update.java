package com.example.avaliacao;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class Update extends AppCompatActivity {
    EditText input_nome, input_ano;
    Button btn_update,btn_delete;
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
                finish();
            }
        });
        btn_delete = findViewById(R.id.bt_delete_update);
        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            confirmDialog();//Chama método da caixa de dialogo que contem o método de apagar linha ds BD
            }
        });


    }
    //Método recupera os dados do item selecionado para serem mostrados no ecrã de update
    void getAndSetIntentData() {
        if (getIntent().hasExtra("id_equipa") && getIntent().hasExtra("nome_equipa") && getIntent().hasExtra("ano_campeonato")) {
            //Pega dados do Intent
            id_equipa = getIntent().getStringExtra("id_equipa");
            nome_equipa = getIntent().getStringExtra("nome_equipa");
            ano_campeonato = getIntent().getStringExtra("ano_campeonato");
            //Define dados no EditText
            input_nome.setText(nome_equipa);
            input_ano.setText(ano_campeonato);
            Log.d("stev",nome_equipa+""+ano_campeonato);
        } else {
            Toast.makeText(this, "Erro ao recuperar dados", Toast.LENGTH_SHORT).show();
        }
    }


    //Caixa de dialogo para confirmação da intenção de apagar item
    void confirmDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Apagar " + nome_equipa + "?");
        builder.setMessage("Tem a certeza que quer apagar " + nome_equipa + "?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                MySQL myDB = new MySQL(Update.this);
                myDB.apagaUmaColuna(id_equipa); // Método que apaga a linha da BD
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.create().show();
    }
}
