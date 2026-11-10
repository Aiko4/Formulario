package com.example.formulario;

import static android.view.View.VISIBLE;

import android.content.Intent;
import android.os.Bundle;

import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn = findViewById(R.id.btn);
        Spinner spinner = findViewById(R.id.spinner);

        String[] consultas = new String[] {"Consulta","Información","Presupuesto","Otro"};
        ArrayAdapter<String> AA = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, consultas);
        spinner.setAdapter(AA);
        btn.setOnClickListener(v -> {

            EditText nombre = findViewById(R.id.nombre);
            String name = nombre.getText().toString();
            EditText correo = findViewById(R.id.correo);
            String mail = correo.getText().toString();
            EditText descripcion = findViewById(R.id.descripcionMensaje);
            String desc = descripcion.getText().toString();

            TextView error = findViewById(R.id.error);

            CheckBox checkCopia = findViewById(R.id.checkCopia);
            boolean copiado = checkCopia.isChecked();

            if (name.isEmpty() || mail.isEmpty() || desc.isEmpty()) {
                error.setVisibility(VISIBLE);
                error.setText("Campos vacíos, es necesario rellenar todos los campos");
            } else {
                Intent intentEmail = new Intent(Intent.ACTION_SEND);
                intentEmail.setType("plain/text");

                intentEmail.putExtra(android.content.Intent.EXTRA_EMAIL, new String[]{mail});
                if (copiado) {
                    intentEmail.putExtra(android.content.Intent.EXTRA_CC,
                            new String[]{mail});
                }

                intentEmail.putExtra(android.content.Intent.EXTRA_SUBJECT, spinner.getSelectedItem().toString());
                intentEmail.putExtra(android.content.Intent.EXTRA_TEXT, desc);

                startActivity(intentEmail);
            }
        });
    }
}