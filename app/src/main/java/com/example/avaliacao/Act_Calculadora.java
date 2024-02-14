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
    TextView txt_visor, txt_visor2;
    Double num1, num2, resultado;//Variaveis armazenam os numeros a serem utilizados na operação assim como o resultado da operação
    String operador; // variavel armazena o operador escolhido pelo usuario
    boolean erro,nova_operacao = false;//Variavel diz se houve um erro ou nao


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_calculadora);
        txt_visor = findViewById(R.id.txt_visor_calculadora);
        txt_visor2 = findViewById(R.id.txt_visor2_calculadora);
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
        String visor1 = txt_visor.getText().toString(); // Passa o texto inserido na textview para String
        String visor2 = txt_visor2.getText().toString();

        //region Lógica dos botões
        if (v.getId() == R.id.bt_mais_mycalculadora) {
            escreveOperador("+");
        } else if (v.getId() == R.id.bt_menos_mycalculadora) {
            escreveOperador("-");
        } else if (v.getId() == R.id.bt_vezes_mycalculadora) {
            escreveOperador("*");
        } else if (v.getId() == R.id.bt_dividir_mycalculadora) {
            escreveOperador("/");
        } else if (v.getId() == R.id.bt_apaga_mycalculadora) {
            //Limpa os 2 visores
            txt_visor.setText(null);
            txt_visor2.setText(null);
            nova_operacao = false;
        } else if (v.getId() == R.id.bt_num0_mycalculadora) {
            escreveNumero("0");
        } else if (v.getId() == R.id.bt_num1_mycalculadora) {
            escreveNumero("1");
        } else if (v.getId() == R.id.bt_num2_mycalculadora) {
            escreveNumero("2");
        } else if (v.getId() == R.id.bt_num3_mycalculadora) {
            escreveNumero("3");
        } else if (v.getId() == R.id.bt_num4_mycalculadora) {
            escreveNumero("4");
        } else if (v.getId() == R.id.bt_num5_mycalculadora) {
            escreveNumero("5");
        } else if (v.getId() == R.id.bt_num6_mycalculadora) {
            escreveNumero("6");
        } else if (v.getId() == R.id.bt_num7_mycalculadora) {
            escreveNumero("7");
        } else if (v.getId() == R.id.bt_num8_mycalculadora) {
            escreveNumero("8");
        } else if (v.getId() == R.id.bt_num9_mycalculadora) {
            escreveNumero("9");
        } else if (v.getId() == R.id.bt_virgula_mycalculadora) {
            if (!(visor2.isEmpty())) {
                txt_visor2.append(".");
            }else {
                Toast.makeText(this,"Insira um número",Toast.LENGTH_SHORT).show();
            }
        } else if (v.getId() == R.id.bt_igual_mycalculadora) {
            //TryCatch verifica se foi lançada uma exceção, caso tenha, mostra mensagem de erro no visor
            try {
                if (resultado==null) {
                    buscaNum2(Double.valueOf(txt_visor2.getText().toString())); ////Pega o numero inserido na textview e atribui à variavel num2
                    resultado = calcula(num1, num2, operador); // Executa o metodo calcula passando o num1, num2 e o operador como parametros para que o calculo seja realizado, e atribui o resultado à variavel resultado
                    txt_visor.setText(null);//Limpa o visor 1
                    txt_visor.append(num1 + "" + operador + "" + num2 + "" + "=");//Mostra a operação completa no visor 1 para o usuario
                    txt_visor2.setText(null);//Limpa o visor 2
                    txt_visor2.append(String.valueOf(resultado)); // Mostra o resultado da operacao no visor2
                    num1 = null; //Limpa o conteudo da variavel num1
                    num2 = null;//Limpa o conteudo da variavel num2
                    operador = null;//Limpa o conteudo da variavel resultado
                } else { //Se o botao = é clickado quando já existe uma operação feita, pega o resultado da operação e passa para num1 para dar inicio a outra operacao
                    nova_operacao = true;
                    num1 = resultado;
                    txt_visor.setText(null);
                    txt_visor2.setText(null);
                    txt_visor.append(num1.toString());
                    num2 = null;
                    resultado = null;
                }
            } catch (ArithmeticException | IllegalArgumentException e) {
                erro = true;
                txt_visor.setText(null);
                txt_visor2.setText(null);
                txt_visor.append("ERRO");//Mostra menssagem de erro nos visores
                txt_visor2.append("ERRO");
                Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show(); // Mostra a mensagem da exceção em um Toast
            }
        }
        //endregion


    }

    public void menssagem(View v) {
        Toast.makeText(this, "Saindo...", Toast.LENGTH_LONG).show();
        finish();
    }

//    public Stack<String> pegaExpressaoVisor(String expressao) {
//        StringBuffer constroiNumero = new StringBuffer();
//        Stack<String> elementosOperacao = new Stack<>();
//        //Percorre cada elemento da string, um por um, que esta no visor
//        for (int i = 0; i < expressao.length(); i++) {
//            //variavel caractere recebe um caractere da string de acordo com o index
//            char caractere = expressao.charAt(i);
//            //Se o caractere for um digito (numero) ou um "." ele é adicionado ao construtor de numero
//            if (Character.isDigit(caractere) || caractere == '.') {
//                constroiNumero.append(caractere);
//            }else{
//                //Se o proximo elemento nao for um numero ou "." e o construtor estiver preenchido, insere o numero construido na stack
//                if (constroiNumero.length() > 0){
//                    elementosOperacao.push(constroiNumero.toString());
//                    //reseta o construtor
//                    constroiNumero.delete(0,constroiNumero.length());
//                }
//                //Caso o elemento for um sinal matematico, ele é adicionado à stack
//                elementosOperacao.push(String.valueOf(caractere));
//            }
//            }
//            //adiciona o ultimo numero criado pelo controiNumero à stack
//            elementosOperacao.push(constroiNumero.toString());
//        return elementosOperacao;
//    }

//    public double realizaCalculo(Stack<String> elementosOperacao) {
//        //Enquanto a stack conter elementos, realiza o codigo
//        double num1, num2, resultado;
//        String operador = null;
//        Stack<Double> operandos = new Stack<>();
//        while (!elementosOperacao.isEmpty()) {
//            //Se o elemento da stack for um sinal matematico, atribui à variável operador
//            if (Objects.equals(elementosOperacao.peek(), "+") || Objects.equals(elementosOperacao.peek(), "-") || Objects.equals(elementosOperacao.peek(), "*") || Objects.equals(elementosOperacao.peek(), "/")) {
//                operador = elementosOperacao.pop();
//            } else {
//                //Se o elemento da stack elementosOperacao for um numero, insere-os na stack operandos
//                operandos.push(Double.valueOf(elementosOperacao.pop()));
//            }
//        }
//        //Atribui os numeros da stack operandos às respectivas variaveis para realização do calculo
//        num1 = operandos.pop();
//        num2 = operandos.pop();
//        //Chama o metodo calcula passando os numeros e o operador como parâmetro e atribui o resultado à variavel resultado
//        resultado = calcula(num1, num2, operador);
//        return resultado;
//    }

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


    public void escreveOperador(String p_operador) {
        String visor2 = txt_visor2.getText().toString();//variavel recebe o conteudo do visor
        if (!visor2.isEmpty()) {//Se o visor nao estiver vazio, adiciona o sinal de operação
            operador = p_operador; //Atribui o operador clickado à variavel operador q irá ser utilizada para o calculo
            buscaNum1(Double.valueOf(txt_visor2.getText().toString()));//Pega o numero inserido na textview e atribui à variavel num1
            txt_visor.append(num1.toString());//Mostra no visor 1 o numero digitado no visor2 para o usuario ver
            txt_visor.append(p_operador);//Mostra no visor1 o operador que o usuario escolheu
            limpaVisor2();//Limpa o visor2 para que o usuario possa inserir o segundo numero da operacao
        } else if (nova_operacao) {
            operador = p_operador;
            txt_visor.append(operador);
            nova_operacao = false;
        } else {
            num1 = resultado;
            operador = p_operador;
            txt_visor.setText(null);
            txt_visor2.setText(null);
            txt_visor.append(num1.toString());
            txt_visor.append(p_operador);
            num2 = null;
            resultado = null;
        }
    }

    public void escreveNumero(String p_numero) {
        if (erro) { // Limpa a mensagem da textview caso haja menssagem de erro
            txt_visor.setText(null);
            txt_visor2.setText(null);
            erro = false; //muda variavel de controle de erro para falso
            txt_visor2.append(p_numero);
        } else {
            txt_visor2.append(p_numero); // mostra o numero desejado no visor
        }
    }

    public void buscaNum1(Double operando) {
        num1 = operando;
    }

    public void buscaNum2(Double operando) {
        num2 = operando;
    }

    public void limpaVisor2() {
        txt_visor2.setText(null);
    }

}