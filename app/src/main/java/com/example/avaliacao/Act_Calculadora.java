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
    Button btmais, btmenos, btvezes, btdividir, btapaga, btvirgula, btigual,btapaga_ultima_entrada,btpos_neg;
    TextView txt_visor, txt_visor2;
    Double num1, num2, resultado;//Variaveis armazenam os numeros a serem utilizados na operação assim como o resultado da operação
    String operador; // variavel armazena o operador escolhido pelo usuario
    boolean erro,nova_operacao = false,continua_operacao = false,pos_neg = false;


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
        btapaga = findViewById(R.id.bt_apagaTudo_mycalculadora);
        btapaga.setOnClickListener(this);
        btvirgula = findViewById(R.id.bt_virgula_mycalculadora);
        btvirgula.setOnClickListener(this);
        btigual = findViewById(R.id.bt_igual_mycalculadora);
        btigual.setOnClickListener(this);
        btapaga_ultima_entrada = findViewById(R.id.bt_apagaUltimaEntrada_mycalculadora);
        btapaga_ultima_entrada.setOnClickListener(this);
        btpos_neg = findViewById(R.id.bt_pos_neg_mycalculadora);
        btpos_neg.setOnClickListener(this);
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
        String visor1 = txt_visor.getText().toString(); // Passa o texto inserido na textview1 para String
        String visor2 = txt_visor2.getText().toString();// Passa o texto inserido na textview2 para String

        //region Lógica dos botões
        if (v.getId() == R.id.bt_mais_mycalculadora) {
            if (!continua_operacao) {
                escreveOperador("+");
                continua_operacao = true;
            } else {
                continuaOperacao("+");

            }
        } else if (v.getId() == R.id.bt_menos_mycalculadora) {
            if (!continua_operacao) {
                escreveOperador("-");
                continua_operacao = true;
            } else {
                continuaOperacao("-");

            }
        } else if (v.getId() == R.id.bt_vezes_mycalculadora) {
            if (!continua_operacao) {
                escreveOperador("*");
                continua_operacao = true;
            } else {
                continuaOperacao("*");

            }
        } else if (v.getId() == R.id.bt_dividir_mycalculadora) {
            if (!continua_operacao) {
                escreveOperador("/");
                continua_operacao = true;
            } else {
                continuaOperacao("/");

            }
        } else if (v.getId() == R.id.bt_apagaTudo_mycalculadora) {
            apagaTudo();
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
           botaoPonto();
        } else if (v.getId() == R.id.bt_igual_mycalculadora) {
           mostraResultado();
        } else if (v.getId() == R.id.bt_apagaUltimaEntrada_mycalculadora) {
            apagaUltimaEntrada();
        } else if (v.getId() == R.id.bt_pos_neg_mycalculadora) {

        }
        //endregion


    }

    public void mensagem(View v) {
        Toast.makeText(this, "Saindo...", Toast.LENGTH_LONG).show();
        finish();
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
            default://default é executado quando nenhum dos casos existentes é executado, sendo a ação considerada como um erro de input do utilizador
                throw new IllegalArgumentException("Operador Inválido: " + operador);
        }

    }


    public void escreveOperador(String p_operador) {
        String visor2 = txt_visor2.getText().toString();//variavel recebe o conteudo do visor
        if (!visor2.isEmpty()) {//Se o visor nao estiver vazio, adiciona o sinal de operação
            operador = p_operador; //Atribui o operador clickado à variavel operador q irá ser utilizada para o calculo
            continua_operacao = true;
            buscaNum1(Double.valueOf(txt_visor2.getText().toString()));//Pega o numero inserido na textview e atribui à variavel num1
            txt_visor.append(num1.toString());//Mostra no visor 1 o numero digitado no visor2 para o usuario ver
            txt_visor.append(p_operador);//Mostra no visor1 o operador que o usuario escolheu
            limpaVisor2();//Limpa o visor2 para que o usuario possa inserir o segundo numero da operacao
        } else if (nova_operacao) { //Caso seja uma nova operaçao o metodo permite mostrar no visor1 o sinal da operacao e atualiza a variavel operador para o calculo
            operador = p_operador;
            txt_visor.append(operador);
            nova_operacao = false;
        } else if (continua_operacao) {
            novaOperacao();
            txt_visor.append(p_operador);
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

    public void novaOperacao (){
        nova_operacao = true;
        num1 = resultado;
        txt_visor.setText(null);
        txt_visor2.setText(null);
        txt_visor.append(num1.toString());
        num2 = null;
        resultado = null;
    }
    public void continuaOperacao (String p_operador){
        if (!(resultado == null)) {
            num1 = resultado;
            operador = p_operador;
            resultado = null;
            num2 = null;
            txt_visor.setText(null);
            txt_visor2.setText(null);
            txt_visor.append(num1.toString());
            txt_visor.append(p_operador);
        }
    }

    public void mostraResultado(){
        //TryCatch verifica se foi lançada uma exceção, caso tenha, mostra mensagem de erro no visor
        try {
            if (resultado == null) {
                buscaNum2(Double.valueOf(txt_visor2.getText().toString())); //Pega o numero inserido na textview2 e atribui à variavel num2
                resultado = calcula(num1, num2, operador); // Executa o metodo calcula passando o num1, num2 e o operador como parametros para que o calculo seja realizado, e atribui o resultado à variavel resultado
                txt_visor.setText(null);//Limpa o visor 1
                txt_visor.append(num1 + "" + operador + "" + num2 + "" + "=");//Mostra a operação completa no visor 1 para o usuario
                txt_visor2.setText(null);//Limpa o visor 2
                txt_visor2.append(String.valueOf(resultado)); // Mostra o resultado da operacao no visor2
                num1 = null; //Limpa o conteudo da variavel num1
                num2 = null;//Limpa o conteudo da variavel num2
                operador = null;//Limpa o conteudo da variavel resultado
            } else if (nova_operacao) { //Se o botao = é clickado quando já existe uma operação feita, pega o resultado da operação e passa para num1 para dar inicio a outra operacao
                novaOperacao();
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

    public void botaoPonto(){
        String visor2 = txt_visor2.getText().toString();
        boolean temPonto = false;
        if (!(visor2.isEmpty())) {
            char[] ch = visor2.toCharArray();
            for (int i = 0; i < ch.length; i++) { //Percorre a String de acordo com o seu comprimento
                if (ch[i] == '.') { // Caso haja um '.' na string, modifica a variavel temPonto para true
                    temPonto = true;
                }
            }
            if (!temPonto){// Caso a a string nao tenha '.' é permitido adicionar o '.'
                txt_visor2.append(".");
            }else { // Caso a string já tenha '.', nao permite a colocação de outro '.' e mostra uma mensagem de operacao invalida
                Toast.makeText(this,"Operação Inválida",Toast.LENGTH_SHORT).show();
            }

        }else {
            Toast.makeText(this,"Insira um número",Toast.LENGTH_SHORT).show(); //Caso o visor esteja vazio, pede ao usuario para inserir um numero
        }
    }

    //Botao que apaga tudo dos visores
    public void apagaTudo() {
        //Limpa os 2 visores
        txt_visor.setText(null);
        txt_visor2.setText(null);
        resultado = null;
        num1 = null;
        num2 = null;
        nova_operacao = false;
        continua_operacao = false;
    }

    public void apagaUltimaEntrada(){
        String visor2 = txt_visor2.getText().toString();//Pega o conteudo do visor2 e passa para uma String
        if (!visor2.isEmpty()) {//Se o visor2 estiver preenchifo executa o codigo
            //O metodo substring cria uma outra string a partir da original, de acordo com o index inserido como parâmetro,
            //neste caso começa no 0, e passo o tamanho final da string -1 para que a ultima posicao seja apagada, e guardo
            // na varivel posApagada, que é printada no visor e mostra o numero atualizado.
            //Ex: String visor2 = 855, executa o metodo -> Substring posApagada 85.
            String posApagada = visor2.substring(0,visor2.length()-1);
            txt_visor2.setText(null);
            txt_visor2.append(posApagada);
        }else {
            //Evita de crashar XD
        }
    }

    public void negativoOuPositivo(){
        String visor2 = txt_visor2.getText().toString();
        if (!visor2.isEmpty()) {
            double num = Double.parseDouble((visor2));
            num=(-1);
            txt_visor2.setText(null);
            txt_visor2.append(String.valueOf(num));

        }
    }
}