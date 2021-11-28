package com.example.practica3;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    //Constante para identificar los valores Extra que se enviaran a la activity

    public final int CONTACTO_NUEVO = 1;
    TextView tvContactosActuales;
    Button btnNuevo;
    Button btnSalir;

    //****Parte Opcional 2****/
    RadioGroup rgOrdenar;
    RadioButton rbNombreOrd;
    RadioButton rbApellidoOrd;



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

       //Si se clica un radiobutton u otro
        rgOrdenar.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.rbNombreOrd:
                        tvContactosActuales.setText(listaContactos((String)tvContactosActuales.getText(),0));

                        break;
                    case R.id.rbApellidoOrd:
                        tvContactosActuales.setText(listaContactos((String)tvContactosActuales.getText(),1));
                        break;

                }
            }
        });



    }

    //metodo identificador de objetos
    void iniciaViews(){
        tvContactosActuales = findViewById(R.id.tvContactosActuales);
        btnNuevo = findViewById(R.id.btnNuevo);
        btnSalir = findViewById(R.id.btnSalir);
        //
        rgOrdenar = findViewById(R.id.rgOrdenar);
        rbNombreOrd = findViewById(R.id.rbNombreOrd);
        rbApellidoOrd = findViewById(R.id.rbApellidoOrd);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK){
            //Contactos actuales es igual a el texto de los contactos actuales más el string extra del intent que recupero.
            tvContactosActuales.setText(tvContactosActuales.getText().toString()+data.getStringExtra(NuevoContactoActivity.EXTRA_CONTACTOS_ACTUALES)+"\n");
        }
    }

    //Convertir una string en una lista de lineas
    public String listaContactos(String cadena,int campo){
      List<String>contactos;
      String salida="";
      if (campo >= 0 || campo < 2){ //me protejo para que no me de error ya que solo comparo por nombre o apellido (0,1)

      contactos = Arrays.asList(cadena.split("\n")); //dividimos por salto de linea
        Collections.sort(contactos, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.split(" ")[campo].compareTo(o2.split(" ")[campo]);
            }
        });//ordenamos asignando que parte es la que se va a comparar

        for (String contacto:contactos //recorremos y añadimos a la string que se mostrara por pantalla
             ) {
            salida+=contacto.toLowerCase(Locale.ROOT)+"\n"; //un poco chapuza lo se, pero lo paso a minuscula y asi trata todas las letras como iguales
        }
      }else{
          salida = tvContactosActuales.getText().toString();
      }
        return salida;
    }
}