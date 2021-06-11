package com.example.propuestaproyecto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class Principal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        FloatingActionButton btnEntrar=(FloatingActionButton) findViewById(R.id.btnEntrar);
        FloatingActionButton btnSalir=(FloatingActionButton) findViewById(R.id.btnSalir2);
         Button btnAcerca=(Button) findViewById(R.id.btnAcerca);

        btnSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();

            }
        });
        btnEntrar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent Siguiente = new Intent(Principal.this,MainActivity.class);
                startActivity(Siguiente);

            }
        });
        btnAcerca.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent Siguiente = new Intent(Principal.this,AcercaDe.class);
                startActivity(Siguiente);

            }
        });
}
}