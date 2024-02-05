package com.example.avaliacao;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.function.DoubleBinaryOperator;

public class Act_Calculadora extends AppCompatActivity implements View.OnClickListener {

    Button bt0,bt1,bt2,bt3,bt4,bt5,bt6,bt7,bt8,bt9;
    Button btmais, btmenos, btvezes, btdividir, btapaga,btvirgula,btigual;
    TextView txt_visor;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_calculadora);
        txt_visor = findViewById(R.id.txt_visor_calculadora);
        //region Botões Operacionais
        btmais = findViewById(R.id.bt_mais_mycalculadora);
        btmais.setOnClickListener(this);
        btmenos = findViewById(R.id.bt_menos_mycalculadora);
        btmenos.setOnClickListener(this);
        btvezes = findViewById(R.id.bt_vezes_mycalculadora);
        btvezes.setOnClickListener(this);
        btdividir = findViewById(R.id.bt_dividir_mycalculadora);
        btdividir.setOnClickListener(this);
        btapaga = findViewById(R.id.bt_apaga_mycalculadora);
        btapaga.setOnClickListener(this);
        btvirgula = findViewById(R.id.bt_virgula_mycalculadora);
        btvirgula.setOnClickListener(this);
        //endregion

        //region Botões numéricos
        bt0 = findViewById(R.id.bt_num0_mycalculadora);
        bt0.setOnClickListener(this);
        bt1 = findViewById(R.id.bt_num1_mycalculadora);
        bt1.setOnClickListener(this);
        bt2 = findViewById(R.id.bt_num2_mycalculadora);
        bt2.setOnClickListener(this);
        bt3 = findViewById(R.id.bt_num3_mycalculadora);
        bt3.setOnClickListener(this);
        bt4 = findViewById(R.id.bt_num4_mycalculadora);
        bt4.setOnClickListener(this);
        bt5 = findViewById(R.id.bt_num5_mycalculadora);
        bt5.setOnClickListener(this);
        bt6 = findViewById(R.id.bt_num6_mycalculadora);
        bt6.setOnClickListener(this);
        bt7 = findViewById(R.id.bt_num7_mycalculadora);
        bt8 = findViewById(R.id.bt_num8_mycalculadora);
        bt8.setOnClickListener(this);
        bt8.setOnClickListener(this);
        bt9 = findViewById(R.id.bt_num9_mycalculadora);
        bt9.setOnClickListener(this);
        //endregion
    }


    @Override
    public void onClick(View v) {
        String visor = txt_visor.getText().toString(); // Passa o texto inserido na textview para String
        if (v.getId() == R.id.bt_mais_mycalculadora) {
            txt_visor.append("+");
        } else if (v.getId() == R.id.bt_menos_mycalculadora) {
            txt_visor.append("-");
        } else if (v.getId() == R.id.bt_vezes_mycalculadora) {
            txt_visor.append("*");
        } else if (v.getId() == R.id.bt_dividir_mycalculadora) {
            txt_visor.append("/");
        } else if (v.getId() == R.id.bt_apaga_mycalculadora) {
            txt_visor.setText(null);
        } else if (v.getId() == R.id.bt_num0_mycalculadora) {
            txt_visor.append("0");
        }else if (v.getId() == R.id.bt_num1_mycalculadora) {
            txt_visor.append("1");
        }else if (v.getId() == R.id.bt_num2_mycalculadora) {
            txt_visor.append("2");
        }else if (v.getId() == R.id.bt_num3_mycalculadora) {
            txt_visor.append("3");
        }else if (v.getId() == R.id.bt_num4_mycalculadora) {
            txt_visor.append("4");
        }else if (v.getId() == R.id.bt_num5_mycalculadora) {
            txt_visor.append("5");
        }else if (v.getId() == R.id.bt_num6_mycalculadora) {
            txt_visor.append("6");
        }else if (v.getId() == R.id.bt_num7_mycalculadora) {
            txt_visor.append("7");
        }else if (v.getId() == R.id.bt_num8_mycalculadora) {
            txt_visor.append("8");
        }else if (v.getId() == R.id.bt_num9_mycalculadora) {
            txt_visor.append("9");
        } else if (v.getId() == R.id.bt_virgula_mycalculadora) {
            txt_visor.append(".");
        }


    }
        public void menssagem (View v){
            Toast.makeText(this, "Saindo...", Toast.LENGTH_LONG).show();
            finish();
        }





    }