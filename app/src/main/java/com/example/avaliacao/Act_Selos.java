package com.example.avaliacao;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Act_Selos extends AppCompatActivity implements View.OnClickListener {
    EditText txt_visor;
    TextView txt_selo5, txt_selo3;
    Button bt_calculaSelo, bt_limpa, bt_sair;
    int valor = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_selos);
        txt_visor = findViewById(R.id.txt_visor_selos);
        txt_selo3 = findViewById(R.id.txt_resultado_selos3);
        txt_selo5 = findViewById(R.id.txt_resultado_selos5);

        bt_calculaSelo = findViewById(R.id.bt_calculaSelos_selos);
        bt_calculaSelo.setOnClickListener(this);
        bt_limpa = findViewById(R.id.bt_limpaSelos_selos);
        bt_limpa.setOnClickListener(this);
        bt_sair = findViewById(R.id.bt_sair_selos);
        bt_sair.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.bt_calculaSelos_selos) {
            String visor = txt_visor.getText().toString();

            if (!visor.isEmpty()) {
                valor = Integer.parseInt(visor);
                logicaSelos();
                txt_visor.setEnabled(false);//desativa a textview para input do usuario
                bt_calculaSelo.setEnabled(false); //desativa o botão de calcular selo
            }
        } else if (v.getId() == R.id.bt_limpaSelos_selos) {
            //limpa os visores
            txt_visor.setText(null);
            txt_selo3.setText(null);
            txt_selo5.setText(null);
            txt_visor.setEnabled(true);//ativa a textview para input do usuario
            bt_calculaSelo.setEnabled(true);//ativa o botão de calcular selo
            
        } else if (v.getId() == R.id.bt_sair_selos) {
            finish();
        }
    }

    public void logicaSelos() {
        int quoc, r, s5 = 0, s3 = 0;
        String selo5, selo3;

        if (valor >= 8) {

            quoc = valor / 8;
            r = valor % 8;
            switch (r) {
                case 0:
                    s3 = quoc;
                    s5 = quoc;
                    break;
                case 1:
                    s3 = quoc + 2;
                    s5 = quoc - 1;
                    break;
                case 2:
                    s3 = quoc - 1;
                    s5 = quoc + 1;
                    break;
                case 3:
                    s3 = quoc + 1;
                    s5 = quoc;
                    break;
                case 4:
                    s3 = quoc + 3;
                    s5 = quoc - 1;
                    break;
                case 5:
                    s3 = quoc;
                    s5 = quoc + 1;
                    break;
                case 6:
                    s3 = quoc + 2;
                    s5 = quoc;
                    break;
                case 7:
                    s3 = quoc - 1;
                    s5 = quoc + 2;
                    break;

            }

        } else {

            if (valor == 3) {
                s3 = 1;
                s5 = 0;
            } else if (valor == 5) {
                s3 = 0;
                s5 = 1;
            } else if (valor == 6) {
                s3 = 2;
                s5 = 0;
            } else {
                txt_visor.setText("nº Invalido");
            }

        }

        selo5 = String.valueOf(s5);
        txt_selo5.append(selo5);
        selo3 = String.valueOf(s3);
        txt_selo3.append(selo3);
    }

}
