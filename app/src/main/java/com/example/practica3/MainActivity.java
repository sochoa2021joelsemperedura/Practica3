package com.example.practica3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    //Constante para identificar los valores Extra que se enviaran a la activity
    public final static String EXTRA_CONTACTOS_ACTUALES = "MainActivity.CONTACTOS";
    TextView tvContactosActuales;
    Button btnNuevo;
    Button btnSalir;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //inicio las views
        iniciaViews();

        //Abre la activity Nuevo Contacto
       btnNuevo.setOnClickListener(view -> {
            Intent intent = new Intent (MainActivity.this, NuevoContactoActivity.class);
            startActivity(intent);
        });

       //Sale del programa
       btnSalir.setOnClickListener(v->{
           finish();
       });

    }

    //metodo identificador de objetos
    void iniciaViews(){
        tvContactosActuales = findViewById(R.id.tvContactosActuales);
        btnNuevo = findViewById(R.id.btnNuevo);
        btnSalir = findViewById(R.id.btnSalir);

    }

}