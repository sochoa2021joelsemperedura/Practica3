package com.example.practica3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NuevoContactoActivity extends AppCompatActivity {
    //Los extras que identificaran los datos que recibira de IntroduceValorActivity
    //SON OPTION REQUEST MIRAR EN GITHUB EL STARTACTIVITYFORREQUEST Y RECTIFICAR
    public final static String EXTRA_NOMBRE_CONTACTO = "NuevoContactoActivity.NOMBRE";
    public final static String EXTRA_APELLIDO_CONTACTO = "NuevoContactoActivity.APELLIDOS";
    public final static String EXTRA_EMPRESA_CONTACTO = "NuevoContactoActivity.EMPRESA";

    /*Estas views se tendran que enviar a la main activity y si las clicas te llevaran
        a introduce introduceValorActivity (Ponerlas en clicable true)
     */
    TextView tvNombreContacto;
    TextView tvApellidos;
    TextView tvEmpresa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo_contacto);

        //Inicio las views
        iniciaViews();
    }
    //Inicia las views
    private void iniciaViews(){
        tvNombreContacto = findViewById(R.id.tvNombreContacto);
        tvApellidos = findViewById(R.id.tvApellidos);
        tvEmpresa = findViewById(R.id.tvEmpresa);
        //RELLENAR PERO HACER PRIMERO LOS OBJETOS EN LA CLASE
    }

    private void iniciaActivityIntroducir(){
        Intent intent = new Intent(NuevoContactoActivity.this,IntroduceValorActivity.class);
        startActivity(intent);
    }
}