package es.joseljg.examen1_eva_recuperacion_joseluis;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    public static final String EXTRA_TOTAL = "es.joseljg.examen1_eva_recuperacion_joseluis.mainactivity.total";
    private Spinner sp_temporada;
    private EditText edt_cuantas_entradas;
    private String temporada;
    private int cantidadEntradas;
    private boolean quierePalomitas;

    private double total;
    private double precioEntrada;
    private double precioPalomitas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sp_temporada = (Spinner) findViewById(R.id.sp_temporada);
        edt_cuantas_entradas = (EditText) findViewById(R.id.edt_cantidad_entradas);

        temporada = "alta";
        quierePalomitas = false;
        // codigo para cargar el spinner con temporada baja, media, alta------------
        String[] temporadas ={"baja","media","alta"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, temporadas);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_temporada.setAdapter(adapter);
        sp_temporada.setOnItemSelectedListener(this);
        //---------------------------------------------------------------------------

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        temporada = adapterView.getItemAtPosition(i).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
         temporada = "alta";
    }

    public void ir_al_pago(View view) {
        // validar que has puesto una cantidad válida en edt_cuantas_entradas
        String textoCuantasEntradas = String.valueOf(edt_cuantas_entradas.getText());
        if(textoCuantasEntradas.isEmpty())
        {
            edt_cuantas_entradas.setError("debes poner la cantidad de entradas");
            return;
        }
        cantidadEntradas = Integer.valueOf(textoCuantasEntradas);
        if(cantidadEntradas > 10)
        {
            edt_cuantas_entradas.setError("maxima compra de 10 entradas");
            return;
        }
        // calcular el precio de la entrada según la temporada
        if(temporada.equalsIgnoreCase("baja"))
        {
            precioEntrada = 3.0;
        }
        else if(temporada.equalsIgnoreCase("media"))
        {
            precioEntrada = 4.0;
        }
        else if(temporada.equalsIgnoreCase("alta"))
        {
            precioEntrada = 5.0;
        }
        //-------------------------------------------------------------
        if(!quierePalomitas)
        {
            precioPalomitas = 0;
        }
        else{
            precioPalomitas = 4.5;
        }
        //-------------------------------------------------------------
        total = cantidadEntradas * precioEntrada + precioPalomitas;

        Intent intent = new Intent(this, Pantalla2Activity.class);
        intent.putExtra(EXTRA_TOTAL, total);
        startActivity(intent);
    }

    public void asignar_quiere_palomitas(View view)
    {
        RadioButton rb = (RadioButton) view;
        if(rb.isChecked())
        {
            switch(rb.getId())
            {
                case R.id.rb_palomitas_si:
                    quierePalomitas = true;
                    break;
                case R.id.rb_palomitas_no:
                    quierePalomitas = false;
                    break;
                default:
                    quierePalomitas = false;
            }
        }

    }
}