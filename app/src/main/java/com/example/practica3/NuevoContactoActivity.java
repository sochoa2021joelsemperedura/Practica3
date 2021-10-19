package com.example.practica3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NuevoContactoActivity extends AppCompatActivity {


    public final static int OPTION_REQUEST_NOMBRE = 0;
    public final static int OPTION_REQUEST_APELLIDO = 1;
    public final static int OPTION_REQUEST_EMPRESA = 2;

    /*Estas views se tendran que enviar a la main activity y si las clicas te llevaran
        a introduce introduceValorActivity (Ponerlas en clicable true)
     */
    TextView tvNombreContacto;
    TextView tvApellidos;
    TextView tvEmpresa;
    //El Seekbar, la textView representa a values de la barra (maximo establecido en modo diseño)
    SeekBar skbEdad;
    TextView tvEdadSkb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo_contacto);

        //Inicio las views
        iniciaViews();

        //el clic en cualquiera de las text view llama a la actividad introduceValorActivity y ademas se identifica a si misma para recibir una string de regreso si se da OK.
        tvNombreContacto.setOnClickListener(e->{
            iniciaActivityIntroducir(OPTION_REQUEST_NOMBRE,IntroduceValorActivity.EXTRA_NOMBRE_CONTACTO,tvNombreContacto.getText().toString());

        });
        tvApellidos.setOnClickListener(e->{
            iniciaActivityIntroducir(OPTION_REQUEST_APELLIDO,IntroduceValorActivity.EXTRA_APELLIDO_CONTACTO,tvApellidos.getText().toString());
        });
        tvEmpresa.setOnClickListener(e->{
            iniciaActivityIntroducir(OPTION_REQUEST_EMPRESA,IntroduceValorActivity.EXTRA_EMPRESA_CONTACTO,tvEmpresa.getText().toString());
        });

        skbEdad.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                //añade en la textView de la edad el valor del seekbar segun desplazas la barra
                tvEdadSkb.setText(getString(R.string.stEdad)+" "+i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
    //Inicia las views
    private void iniciaViews(){
        tvNombreContacto = findViewById(R.id.tvNombreContacto);
        tvApellidos = findViewById(R.id.tvApellidos);
        tvEmpresa = findViewById(R.id.tvEmpresa);
        skbEdad = findViewById(R.id.skbEdad);
        tvEdadSkb = findViewById(R.id.tvEdadSkb);
    }
    //Metodo que recibe un intent y identifica que textView hace la llamada y establece el texto recibido de la otra actividad
    private void iniciaActivityIntroducir(int optionRequest,String extra,String texto){
        Intent intent = new Intent(NuevoContactoActivity.this,IntroduceValorActivity.class);
        startActivityForResult(intent,optionRequest);
        intent.putExtra(extra,texto);
    }
}