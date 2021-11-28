package com.example.practica3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;

import com.example.practica3.POJO.Contacto;

public class NuevoContactoActivity extends AppCompatActivity implements View.OnClickListener {

    public final static String EXTRA_CONTACTOS_ACTUALES = "NuevoContactoActivity.CONTACTOS";
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

    Button btnOk;
    Button btnCancel;
    EditText etTelefono;

    //Contacto que recibe la actividad principal
    Contacto nuevoContacto;

    //imagen que cambiara
    ImageView ivEmpresaOParticular;
    ImageView ivSexo;
    ImageView ivRecordarLlamar;
    ImageView ivFavoritos;
    //radiogroup y elementos
    RadioGroup rgTipoContacto;
    RadioButton rbEmpresa;
    RadioButton rbParticular;
    RadioGroup rgSexo;
    RadioButton rbHombre;
    RadioButton rbMujer;
    //Checkbox
    CheckBox cbxRecordarLlamar;
    //Switch
    Switch swFavoritos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo_contacto);

        //Inicio las views
        iniciaViews();

        //Manejaremos el evento del botón en el mismo Listener. Para ello fijaros como la activity implementa View.OnClickListener
        tvNombreContacto.setOnClickListener(this);
        tvApellidos.setOnClickListener(this);
        tvEmpresa.setOnClickListener(this);

        //cambio de imagen segun la seleccion del radiobutton, pd: el por defecto esta señalado en el xml de la actividad
        rgTipoContacto.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.rbEmpresa:
                        ivEmpresaOParticular.setImageResource(R.drawable.img_company);
                        break;
                    case R.id.rbParticular:
                        ivEmpresaOParticular.setImageResource(R.drawable.img_user);
                        break;
                }
            }
        });
        //En este caso no hay seleccion por defecto ya que uno puede no querer identificar su genero.
        rgSexo.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.rbHombre:
                        ivSexo.setImageResource(R.drawable.img_male);
                        break;
                    case R.id.rbMujer:
                        ivSexo.setImageResource(R.drawable.img_female);
                        break;
                }
            }
        });

        //Detecta si el checkbox esta marcado, en caso de estarlo da visibilidad a una imageview
        cbxRecordarLlamar.setOnCheckedChangeListener(
                new CheckBox.OnCheckedChangeListener(){

                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if(isChecked) {
                            ivRecordarLlamar.setVisibility(View.VISIBLE);
                        }else{
                            //No me gusta el gone, prefiero que siga manteniendo su espacio aun sin visibilidad
                            ivRecordarLlamar.setVisibility(View.INVISIBLE);
                            }
                    }
                }
        );
        /*En el ejercicio te pide el uso del visibility , pero ya lo he utilizado y me parece más correcto sustituir la imagen
            Sustituye la imagen comprobando si esta marcado o lo el switch
         */
        swFavoritos.setOnCheckedChangeListener(
                new CheckBox.OnCheckedChangeListener(){

                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if(isChecked) {
                            ivFavoritos.setImageResource(R.drawable.img_star);
                        }else{
                            ivFavoritos.setImageResource(R.drawable.img_star_empty);
                        }
                    }
                }
        );

        skbEdad.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                //añade en la textView de la edad el valor del seekbar segun desplazas la barra
                tvEdadSkb.setText(getString(R.string.stEdad) + " " + i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        //Boton que cierra la actividad
        btnCancel.setOnClickListener(e -> {
            finish();
        });
        //btn que envia las strings de los datos del usuario y su numero de telefono
        btnOk.setOnClickListener(e -> {
            Intent iBack = getIntent();
            //pongo la informacion que recibira la mainactivity en la string textodelmain
            nuevoContacto = new Contacto(tvNombreContacto.getText().toString(),tvApellidos.getText().toString(),
                    Integer.parseInt(etTelefono.getText().toString() ));
            iBack.putExtra(EXTRA_CONTACTOS_ACTUALES, nuevoContacto);
            setResult(RESULT_OK, iBack);
            finish();
        });

    }
    /**
     * Maneja el click de los botones de la Activity
     * @param view: Boton que recibe el click
     */
    @Override
    public void onClick(View view) {
        Intent i;
        //creamos el intent para llamar a la acitividad
        i=new Intent(this,IntroduceValorActivity.class);
        switch (view.getId()) {
            case R.id.tvNombreContacto:
                //enviamos el nombre actual
                i.putExtra(IntroduceValorActivity.EXTRA_DATOS,tvNombreContacto.getText().toString());
                //llamamos a la actividad a la espera de recibir el resultado
                //indicando el código de llamada
                startActivityForResult(i,OPTION_REQUEST_NOMBRE);
                break;
            case R.id.tvApellidos:
                //enviamos el apellido actual
                i.putExtra(IntroduceValorActivity.EXTRA_DATOS,tvApellidos.getText().toString());
                //llamamos a la actividad a la espera de recibir el resultado
                //indicando el código de llamada
                startActivityForResult(i,OPTION_REQUEST_APELLIDO);
                break;
            case R.id.tvEmpresa:
                //enviamos la empresa actual
                i.putExtra(IntroduceValorActivity.EXTRA_DATOS,tvEmpresa.getText().toString());
                //llamamos a la actividad a la espera de recibir el resultado
                //indicando el código de llamada
                startActivityForResult(i,OPTION_REQUEST_EMPRESA);
                break;
        }
    }

    /**
     * Cuando la actividad EntradaDatos termine, esta actividad llamará a este evento, donde
     * podremos obtener el resultado devuelto.
     * @param requestCode: si fue llamada para NOMBRE on APELLIDO
     * @param resultCode: Si el usuario pulsó ACEPTAR o CANCELAR
     * @param data: Datos devueltos
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // Comprobamos si el resultado de la segunda actividad no es  "RESULT_CANCELED".
        if (resultCode != RESULT_CANCELED) {
            // De lo contrario, recogemos el resultado de la segunda actividad.
            String resultado = data.getStringExtra(IntroduceValorActivity.EXTRA_DATOS_RESULTADO);
            // Y tratamos el resultado en función de si se lanzó para rellenar el
            // nombre o el apellido.
            switch (requestCode) {
                case OPTION_REQUEST_NOMBRE:
                    tvNombreContacto.setText(resultado);
                    break;
                case OPTION_REQUEST_APELLIDO:
                    tvApellidos.setText(resultado);
                    break;
                case OPTION_REQUEST_EMPRESA:
                    tvEmpresa.setText(resultado);
                    break;

            }
        }
    }

    //Inicia las views
    private void iniciaViews(){
        tvNombreContacto = findViewById(R.id.tvNombreContacto);
        tvApellidos = findViewById(R.id.tvApellidos);
        tvEmpresa = findViewById(R.id.tvEmpresa);
        skbEdad = findViewById(R.id.skbEdad);
        tvEdadSkb = findViewById(R.id.tvEdadSkb);
        btnCancel = findViewById(R.id.btnCancel);
        btnOk = findViewById(R.id.btnOk);
        etTelefono= findViewById(R.id.etTelefono);
        ivEmpresaOParticular = findViewById(R.id.ivEmpresaOParticular);
        rgTipoContacto = findViewById(R.id.rgTipoContacto);
        rbEmpresa = findViewById(R.id.rbEmpresa);
        rbParticular = findViewById(R.id.rbParticular);
        ivSexo = findViewById(R.id.ivSexo);
        rgSexo = findViewById(R.id.rgSexo);
        rbHombre = findViewById(R.id.rbHombre);
        rbMujer = findViewById(R.id.rbMujer);
        cbxRecordarLlamar = findViewById(R.id.cbxRecordarLlamar);
        ivRecordarLlamar = findViewById(R.id.ivRecordarLlamar);
        swFavoritos = findViewById(R.id.swFavoritos);
        ivFavoritos = findViewById(R.id.ivFavoritos);
    }

}