package com.example.practica3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class IntroduceValorActivity extends AppCompatActivity {
    EditText etValorNombreApellidos;
    Button btnCancel;
    Button btnOk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_introduce_valor);
    }

    private void iniciaViews(){
        etValorNombreApellidos = findViewById(R.id.etValorNombreApellidos);
        btnCancel = findViewById(R.id.btnCancel);
        btnOk = findViewById(R.id.btnOk);
    }
}