package com.example.practica3;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.practica3.POJO.Comparar;
import com.example.practica3.POJO.Contacto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.TreeSet;

public class MainActivity extends AppCompatActivity {
    //Constante para identificar los valores Extra que se enviaran a la activity

    public final int CONTACTO_NUEVO = 1;
    TextView tvContactosActuales;
    Button btnNuevo;
    Button btnSalir;

    //opcional 3
    Contacto contacto;
    Set<Contacto> contactosSinDuplicar = new TreeSet<Contacto>(Comparator.comparing(Contacto::toString)); //se entiende como duplicado un objeto exactamente igual
    List<Contacto> contactoList;

    //Opcional 4
    RadioGroup rgOrdenar;
    RadioButton rbNombreOrd;
    RadioButton rbApellidosOrd;
    RadioButton rbEdadOrd;
    RadioButton rbTelefonoOrd;

    Comparar metodoOrdenacion = Comparar.NOMBRE;



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

        //Lo que hace el cada radioButton cuando se le clica
        rgOrdenar.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.rbNombreOrd:
                        metodoOrdenacion = Comparar.NOMBRE;
                        break;
                    case R.id.rbApellidosOrd:
                       metodoOrdenacion = Comparar.APELLIDO;
                        break;
                    case R.id.rbEdadOrd:
                        metodoOrdenacion = Comparar.EDAD;

                        break;
                    case R.id.rbTelefonoOrd:
                        metodoOrdenacion = Comparar.TELEFONO; // cambia el comparador
                        break;
                }
                tvContactosActuales.setText(devuelveUnaLista(contactosSinDuplicar)); //actualiza la lista
            }
        });

    }

    //metodo identificador de objetos
    void iniciaViews(){
        tvContactosActuales = findViewById(R.id.tvContactosActuales);
        btnNuevo = findViewById(R.id.btnNuevo);
        btnSalir = findViewById(R.id.btnSalir);
        //Opcional 4
        rgOrdenar = findViewById(R.id.rgOrdenarLista);
        rbApellidosOrd = findViewById(R.id.rbApellidosOrd);
        rbNombreOrd = findViewById(R.id.rbNombreOrd);
        rbEdadOrd = findViewById(R.id.rbEdadOrd);
        rbTelefonoOrd = findViewById(R.id.rbTelefonoOrd);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && data != null){

            if (resultCode != RESULT_CANCELED){

                Contacto contactoNuevo = data.getParcelableExtra((NuevoContactoActivity.EXTRA_CONTACTOS_ACTUALES));
               //añado objeto al set
                contactosSinDuplicar.add(contactoNuevo);
                //recibe una string de la lista ya ordenada por apellidos
                tvContactosActuales.setText(devuelveUnaLista(contactosSinDuplicar));
            }
        }
    }
    //Lista a partir del set que mantiene los contactos
    private String devuelveUnaLista(Set<Contacto> contactos) {
        String salida="";
        //lista sin duplicidades
        contactoList = new ArrayList<>(contactos); //se recrea una nueva lista para evitar duplicados cada vez que se añade contacto

        //Orden
        contactoList.sort(metodoOrdenacion.getComparator());

        //pero los contactos estan 'permanentes' en el set
        for (Contacto contacto: contactoList) {
            salida+=contacto.toString()+"\n";
        }
        return salida;

    }

}