package com.example.avaliacao;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;
import java.util.Stack;
import java.util.function.DoubleBinaryOperator;

public class Act_Calculadora extends AppCompatActivity implements View.OnClickListener {

    Button bt0, bt1, bt2, bt3, bt4, bt5, bt6, bt7, bt8, bt9;
    Button btmais, btmenos, btvezes, btdividir, btapaga, btvirgula, btigual;
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
        btigual = findViewById(R.id.bt_igual_mycalculadora);
        btigual.setOnClickListener(this);
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
        //region Lógica dos botões
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
        } else if (v.getId() == R.id.bt_num1_mycalculadora) {
            txt_visor.append("1");
        } else if (v.getId() == R.id.bt_num2_mycalculadora) {
            txt_visor.append("2");
        } else if (v.getId() == R.id.bt_num3_mycalculadora) {
            txt_visor.append("3");
        } else if (v.getId() == R.id.bt_num4_mycalculadora) {
            txt_visor.append("4");
        } else if (v.getId() == R.id.bt_num5_mycalculadora) {
            txt_visor.append("5");
        } else if (v.getId() == R.id.bt_num6_mycalculadora) {
            txt_visor.append("6");
        } else if (v.getId() == R.id.bt_num7_mycalculadora) {
            txt_visor.append("7");
        } else if (v.getId() == R.id.bt_num8_mycalculadora) {
            txt_visor.append("8");
        } else if (v.getId() == R.id.bt_num9_mycalculadora) {
            txt_visor.append("9");
        } else if (v.getId() == R.id.bt_virgula_mycalculadora) {
            txt_visor.append(".");
        } else if (v.getId() == R.id.bt_igual_mycalculadora) {
            if (!visor.isEmpty()) {
                //TryCatch verifica se foi lançada uma exceção, caso tenha, mostra mensagem de erro no visor
                try {
                    double resultado = realizaCalculo(pegaExpressaoVisor(visor));
                    txt_visor.setText(String.valueOf(resultado));
                } catch (ArithmeticException | IllegalArgumentException e) {
                    txt_visor.setText(e.getMessage());
                }
            }
        }
        //endregion


    }

    public void menssagem(View v) {
        Toast.makeText(this, "Saindo...", Toast.LENGTH_LONG).show();
        finish();
    }

    public Stack<String> pegaExpressaoVisor(String expressao) {
        StringBuffer constroiNumero = new StringBuffer();
        Stack<String> elementosOperacao = new Stack<>();
        //Percorre cada elemento da string, um por um, que esta no visor
        for (int i = 0; i < expressao.length(); i++) {
            //variavel caractere recebe um caractere da string de acordo com o index
            char caractere = expressao.charAt(i);
            //Se o caractere for um digito (numero) ou um "." ele é adicionado ao construtor de numero
            if (Character.isDigit(caractere) || caractere == '.') {
                constroiNumero.append(caractere);
            }else{
                //Se o proximo elemento nao for um numero ou "." e o construtor estiver preenchido, insere o numero construido na stack
                if (constroiNumero.length() > 0){
                    elementosOperacao.push(constroiNumero.toString());
                    //reseta o construtor
                    constroiNumero.delete(0,constroiNumero.length());
                }
                //Caso o elemento for um sinal matematico, ele é adicionado à stack
                elementosOperacao.push(String.valueOf(caractere));
            }
            }
            //adiciona o ultimo numero criado pelo controiNumero à stack
            elementosOperacao.push(constroiNumero.toString());
        return elementosOperacao;
    }

    public double realizaCalculo(Stack<String> elementosOperacao) {
        //Enquanto a stack conter elementos, realiza o codigo
        double num1, num2, resultado;
        String operador = null;
        Stack<Double> operandos = new Stack<>();
        while (!elementosOperacao.isEmpty()) {
            //Se o elemento da stack for um sinal matematico, atribui à variável operador
            if (Objects.equals(elementosOperacao.peek(), "+") || Objects.equals(elementosOperacao.peek(), "-") || Objects.equals(elementosOperacao.peek(), "*") || Objects.equals(elementosOperacao.peek(), "/")) {
                operador = elementosOperacao.pop();
            } else {
                //Se o elemento da stack elementosOperacao for um numero, insere-os na stack operandos
                operandos.push(Double.valueOf(elementosOperacao.pop()));
            }
        }
        //Atribui os numeros da stack operandos às respectivas variaveis para realização do calculo
        num1 = operandos.pop();
        num2 = operandos.pop();
        //Chama o metodo calcula passando os numeros e o operador como parâmetro e atribui o resultado à variavel resultado
        resultado = calcula(num1, num2, operador);
        return resultado;
    }

    //Método Calcula executa o calculo entre o num1 e num2 de acordo com o operador utilizado.
    public double calcula(double num1, double num2, String operador) {
        switch (operador) {
            case "+":
                return num1 + num2; //Não é necessário o break no switch porque o return encerra o método executado para retornar o valor.
            case "-":
                return num1 - num2;
            case "*":
                return num1 * num2;
            case "/":
                //Verifica se foi feito uma divisão por 0, se sim lança uma exceção.
                if (num2 == 0) {
                    throw new ArithmeticException("Não é possível dividir por 0");
                }
                return num1 / num2;
            default:
                throw new IllegalArgumentException("Operador Inválido: " + operador);
        }

    }


}