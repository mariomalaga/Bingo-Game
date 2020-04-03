package com.example.mario.bingoandroid_17;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class Juego extends AppCompatActivity implements View.OnClickListener{
    private ArrayAdapter<String> adaptadorCartones;
    private ListView listCartones;
    private RelativeLayout layout;
    private ArrayList<String> cartones;
    private ArrayList <Integer> comprobante = new ArrayList<>();
    private ArrayList<Integer> numerosSacados = new ArrayList<>();
    private Button sacarBola;
    private Spinner bolas;
    private ArrayAdapter<CharSequence> adaptadorBolas;
    private ArrayList <TextView> textos = new ArrayList<>();
    private ArrayList <Integer> contadores = new ArrayList<>();
    private ArrayList <Integer> jugador1 = new ArrayList<>();
    private ArrayList <Integer> jugador2 = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.juego);
        cartones = new ArrayList<>();
        bolas = findViewById(R.id.numeros);
        layout = findViewById(R.id.juego);
        sacarBola = findViewById(R.id.sacarBola);
        sacarBola.setOnClickListener(this);
        rellenarTextView();
        cantidadCartones();
        adaptadorBolas = new ArrayAdapter(this, android.R.layout.simple_spinner_item, numerosSacados);
        bolas.setAdapter(adaptadorBolas);
        adaptadorCartones = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, cartones);
        listCartones = findViewById(R.id.listCartones);
        listCartones.setAdapter(adaptadorCartones);
        adaptadorCartones.notifyDataSetChanged();
    }
    public String numerosDeCartones(ArrayList jugador){
        int numeroMaximo = Integer.parseInt(getString(R.string.numeroMaximo));
        String numeros = "";
        boolean comprobar;
        Random r = new Random();
        int numeroRandom;
        for (int i = 0; i < numeroMaximo; i++) {
            do{
                comprobar = false;
                numeroRandom = r.nextInt(90 - 1) + 1;
                if(comprobante.isEmpty()){
                }
                else{
                    for (int j = 0; j < comprobante.size(); j++) {
                        if(numeroRandom == comprobante.get(j)){
                            comprobar = true;
                        }
                    }
                }
            }
            while(comprobar == true);
            numeros += ""+numeroRandom+ " ";
            comprobante.add(numeroRandom);
            jugador.add(numeroRandom);
        }
        comprobante.clear();
        return numeros;
    }
    public void cantidadCartones(){
        int numeroCartones = Integer.parseInt(getString(R.string.numeroCartones));
        String carton;
        for (int i = 0; i < numeroCartones; i++) {
            if(i == 0){
                carton = numerosDeCartones(jugador1);
            }
            else{
                carton = numerosDeCartones(jugador2);
            }

            cartones.add(carton);
            contadores.add(0);
        }
    }

    public void rellenarTextView(){
        for( int i = 0; i < layout.getChildCount(); i++ ) {
            if (layout.getChildAt(i) instanceof TextView) {
                textos.add((TextView) layout.getChildAt(i));
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.sacarBola:
                Random r = new Random();
                boolean comprobar;
                int numeroRandom;
                do{
                    comprobar = false;
                    numeroRandom = r.nextInt(90 - 1) + 1;
                    if(numerosSacados.isEmpty()){
                    }
                    else{
                        for (int i = 0; i < numerosSacados.size(); i++) {
                            if(numeroRandom == numerosSacados.get(i)){
                                comprobar = true;
                            }
                        }
                    }
                }
                while(comprobar == true);
                for (int i = 0; i < jugador1.size(); i++) {
                    if(jugador1.get(i) == numeroRandom){
                        contadores.set(0,contadores.get(0) + 1);
                    }
                    else if(jugador2.get(i) == numeroRandom){
                        contadores.set(1,contadores.get(1) + 1);
                    }
                }
                for (int i = 0; i < contadores.size(); i++) {
                    textos.get(i).setText(getString(R.string.numeroAciertos) + " " + contadores.get(i));
                    if(contadores.get(i) > Integer.parseInt(getString(R.string.numeroLinea)) && contadores.get(i) < Integer.parseInt(getString(R.string.numeroBingo))){
                        textos.get(i).setText(getString(R.string.numeroAciertos) + " " + contadores.get(i) + ", " +getString(R.string.Linea) );
                    }
                    else if(contadores.get(i) == Integer.parseInt(getString(R.string.numeroBingo))){
                        textos.get(i).setText(getString(R.string.numeroAciertos) + " " + contadores.get(i) + ", " +getString(R.string.Bingo) );
                        textos.get(4).setText(getString(R.string.elementosSpinner) + " " + bolas.getAdapter().getCount());
                        sacarBola.setEnabled(false);
                    }
                }
                numerosSacados.add(numeroRandom);
                textos.get(3).setText(getString(R.string.numeroSacado)+ " " +numeroRandom);
                adaptadorBolas.notifyDataSetChanged();
                break;
        }
    }
}
