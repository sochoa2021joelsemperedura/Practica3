package com.example.practica3;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    //Constante para identificar los valores Extra que se enviaran a la activity

    public final int CONTACTO_NUEVO = 1;
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
            startActivityForResult(intent,CONTACTO_NUEVO);
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK){
            //Contactos actuales es igual a el texto de los contactos actuales más el string extra del intent que recupero.
            tvContactosActuales.setText(tvContactosActuales.getText().toString()+data.getStringExtra(NuevoContactoActivity.EXTRA_CONTACTOS_ACTUALES)+"\n");
        }
    }
}