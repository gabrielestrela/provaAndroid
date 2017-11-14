package br.com.forca.jogo.prova.jogoforca;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private int i, errors, rights;
    private String palavra = "teste";
    private ArrayList<Character> result;
    private String resultText;
    private char hifen = '_';
    private int hifenCount = 0;
    static private TextView hifens;
    static private TextView gameResult;
    static private Button reset;
    static private ArrayList<TextView> desenho;


    public String setWord(String word){

        this.errors = 0;
        this.rights = 0;
        desenho = new ArrayList<>();

        result = new ArrayList<>();

        desenho.add((TextView) findViewById(R.id.cabeca));
        desenho.add((TextView) findViewById(R.id.bracoE));
        desenho.add((TextView) findViewById(R.id.bracoDD));
        desenho.add((TextView) findViewById(R.id.corpo));
        desenho.add((TextView) findViewById(R.id.pernaE));
        desenho.add((TextView) findViewById(R.id.pernaDD));

        for(i = 0; i < desenho.size(); i++){
            desenho.get(i).setVisibility(View.INVISIBLE);
        }

        hifenCount = word.length();
        for(i = 0; i < hifenCount; i++){
            result.add('-');
        }

        return resultText = result.toString();
    }

    public void receiveChar(String input){
        boolean acertouLetra = false;

        for(i = 0; i < palavra.length(); i++) {
            if(palavra.charAt(i) == input.charAt(0)){
                result.set(i, input.charAt(0));
                rights++;
                acertouLetra = true;
            }
        }

        if(!acertouLetra){
            desenho.get(errors).setVisibility(View.VISIBLE);
            Log.i("ERRO NRO", "==>" + errors);
            errors++;
        }

        resultText = result.toString();
    }

    public boolean checkEndGame(){
        if(errors == palavra.length()){
            gameResult.setText("PERDEU");
            gameResult.setTextColor(Color.parseColor("#ff0000"));
            return true;
        }
        if(rights == palavra.length()) {
            gameResult.setText("GANHOU");
            gameResult.setTextColor(Color.parseColor("#00ff00"));
            return true;
        }

        return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        hifens = (TextView) findViewById(R.id.hifens);
        gameResult = (TextView) findViewById(R.id.result);
        final EditText inputChar = (EditText) findViewById(R.id.input);
        Button check = (Button) findViewById(R.id.check);
        reset = (Button) findViewById(R.id.reset);

        hifens.setText(setWord(this.palavra));

        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                receiveChar(inputChar.getText().toString());
                MainActivity.hifens.setText(resultText);
                if(checkEndGame()) {
                    reset.setVisibility(View.VISIBLE);
                }
            }
        });

    }
}
