package com.example.practica3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class IntroduceValorActivity extends AppCompatActivity {
    //TODO SEGURAMENTE ESO TENGA QUE SER EXTRA DATO Y EXTRA RESULTADO, MIRAR EL GITHUB DEL MODULO
    //Los extras que identificaran los datos que recibira de IntroduceValorActivity
    public final static String EXTRA_NOMBRE_CONTACTO = "NuevoContactoActivity.NOMBRE";
    public final static String EXTRA_APELLIDO_CONTACTO = "NuevoContactoActivity.APELLIDOS";
    public final static String EXTRA_EMPRESA_CONTACTO = "NuevoContactoActivity.EMPRESA";

    EditText etValorNombreApellidos;
    Button btnCancelIntroduce;
    Button btnOkIntroduce;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_introduce_valor);

        //inicia las views
        iniciaViews();

        //Boton para salir de la actividad
        btnCancelIntroduce.setOnClickListener(e->{
            finish();
        });

        btnOkIntroduce.setOnClickListener(e->{
            //TODO Voy por aqui, revisar y solucionar, las constantes habra que cambiarlas
            Intent intent = new Intent(IntroduceValorActivity.this,NuevoContactoActivity.class);
            etValorNombreApellidos.getText().toString();

        });
    }

    private void iniciaViews(){
        etValorNombreApellidos = findViewById(R.id.etValorNombreApellidos);
        btnCancelIntroduce = findViewById(R.id.btnCancelIntroduce);
        btnOkIntroduce = findViewById(R.id.btnOkIntroduce);
    }
}