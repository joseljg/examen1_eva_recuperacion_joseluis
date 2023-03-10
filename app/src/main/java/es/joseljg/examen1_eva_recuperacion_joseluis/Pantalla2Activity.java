package es.joseljg.examen1_eva_recuperacion_joseluis;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Pantalla2Activity extends AppCompatActivity {

    private TextView txt_pantalla2_titulo2_numero1 = null;
    private TextView txt_pantalla2_titulo3_numero1 = null;

    private EditText edt_cargar_tarjeta = null;

    private Button bt_pagar;
    private double total;

    private double saldo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla2);
        total = 0;
        saldo = 0;
        txt_pantalla2_titulo2_numero1 = (TextView) findViewById(R.id.txt_pantalla2_titulo2_numero1);
        txt_pantalla2_titulo3_numero1 = (TextView) findViewById(R.id.txt_pantalla2_titulo3_numero1);
        edt_cargar_tarjeta = (EditText) findViewById(R.id.edt_cargar_tarjeta);

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

    public void cargar_saldo_tarjeta(View view) {
        String textocantidad = String.valueOf(edt_cargar_tarjeta.getText());
        if(textocantidad.isEmpty())
        {
            edt_cargar_tarjeta.setError("escribe una cantidad");
            return;
        }
        double cantidad = Double.valueOf(textocantidad);
        saldo = saldo + cantidad;
        if(saldo >= total)
        {
            bt_pagar.setEnabled(true);
        }
        txt_pantalla2_titulo3_numero1.setText(String.valueOf(saldo));
        Toast.makeText(this, "carga realizada correctamente", Toast.LENGTH_SHORT).show();
    }

    public void pagar_pedido(View view) {
        saldo = saldo - total;
        txt_pantalla2_titulo3_numero1.setText(String.valueOf(saldo));
        Toast.makeText(this,"pago realizado correctamente", Toast.LENGTH_LONG).show();
        bt_pagar.setEnabled(false);
    }
}