package es.joseljg.examen1_eva_recuperacion_joseluis;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Pantalla2Activity extends AppCompatActivity {

    private TextView txt_pantalla2_titulo2_numero1;
    private TextView txt_pantalla2_titulo3_numero1;

    private Button bt_pagar;
    private double total;

    private double saldo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla2);
        total = 0;
        saldo = 150;
        txt_pantalla2_titulo2_numero1 = (TextView) findViewById(R.id.txt_pantalla2_titulo2_numero1);
        txt_pantalla2_titulo3_numero1 = (TextView) findViewById(R.id.txt_pantalla2_titulo3_numero1);
        bt_pagar = (Button) findViewById(R.id.bt_pagar);
        //-----------------------------------------------------------------------------------------------
        Intent intent  = getIntent();
        if(intent != null) {
            total = intent.getDoubleExtra(MainActivity.EXTRA_TOTAL, 0);
            txt_pantalla2_titulo2_numero1.setText(String.valueOf(total));
        }
        txt_pantalla2_titulo3_numero1.setText(String.valueOf(saldo));
        if(saldo < total)
        {
            Toast.makeText(this,"no tienes suficiente saldo, carga la tarjeta",Toast.LENGTH_LONG).show();
            bt_pagar.setEnabled(false);
        }

    }
}