package com.example.practica3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class IntroduceValorActivity extends AppCompatActivity implements View.OnClickListener{
    //Los extras que identificaran los datos que recibira de IntroduceValorActivity
    public final static String EXTRA_DATOS_RESULTADO = "NuevoContactoActivity.DATOSRESULTADO";
    public final static String EXTRA_DATOS = "NuevoContactoActivity.DATOS";

    EditText etValorNombreApellidos;
    Button btnCancelIntroduce;
    Button btnOkIntroduce;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_introduce_valor);

        //inicia las views
        iniciaViews();
        //manejo del boton en el mismo listener
        btnOkIntroduce.setOnClickListener(this);
        btnCancelIntroduce.setOnClickListener(this);

        //Obtenemos el valor de la actividad y lo mostramos
        etValorNombreApellidos.setText(getIntent().getStringExtra(EXTRA_DATOS));
    }
    /**
     * Este método implementa el evento de los botones ok y cancel
     * @param view
     */
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnOkIntroduce:
                // Si el EditText no está vacío enviamos el resultado
                if(etValorNombreApellidos.getText().length()!=0) {
                    //guardamos el resultado en el Intent que llamó la actividad, aunque
                    //podríamos crear uno nuevo
                    Intent iBack = getIntent();

                    iBack.putExtra(EXTRA_DATOS_RESULTADO,etValorNombreApellidos.getText().toString());
                    //indicamos que se ha pulsado aceptar y enviamos el Intent
                    setResult(RESULT_OK,iBack);
                    //cerramos la actividad
                    finish();
                }
                break;
            case R.id.btnCancelIntroduce:
                //Indicamos que el usuario ha pulsado Cancelar
                setResult(RESULT_CANCELED);
                //cerramos la actividad
                finish();
                //si el usuario pulsa el botón de back también se devuelve Canceled
                break;
        }
    }
    private void iniciaViews(){
        etValorNombreApellidos = findViewById(R.id.etValorNombreApellidos);
        btnCancelIntroduce = findViewById((R.id.btnCancelIntroduce));
        btnOkIntroduce = findViewById(R.id.btnOkIntroduce);
    }
}