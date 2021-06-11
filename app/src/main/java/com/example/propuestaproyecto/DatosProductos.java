package com.example.propuestaproyecto;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONObject;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;


public class DatosProductos extends AppCompatActivity {
    DB productos;
    String accion= "nuevo";
    String id="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos_productos);
            mostrarDatos();


        FloatingActionButton bSalir2 = (FloatingActionButton) findViewById(R.id.btnSalir2);
        FloatingActionButton bAtras = (FloatingActionButton) findViewById(R.id.btnAtras);

        bAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }

        });
        bSalir2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishAffinity();
                System.exit(0);
            }
        });

    }
    public void mostrarDatos(){
        try {
            Bundle bundle= getIntent().getExtras();
            accion= bundle.getString("accion");
            if (accion.equals("moficar")){
                id= bundle.getString("id");

                String user[]= bundle.getStringArray("user");

                TextView tempval=(TextView)findViewById(R.id.txtNombre);
                tempval.setText(user[0].toString());

                tempval=(TextView)findViewById(R.id.txtCodigo);
                tempval.setText(user[1].toString());

                tempval=(TextView)findViewById(R.id.txtMarca);
                tempval.setText(user[2].toString());

                tempval=(TextView)findViewById(R.id.txtPrecio);
                tempval.setText(user[3].toString());

            }
        }catch (Exception e){
            Toast.makeText(DatosProductos.this,"Error: "+ e.getMessage().
                    toString(),Toast.LENGTH_LONG).show();

        }

    }
    public void  guardar_productos(View v){
        try {
            TextView tempval=(TextView)findViewById(R.id.txtNombre);
            String nom = tempval.getText().toString();

            tempval=(TextView)findViewById(R.id.txtCodigo);
            String cod = tempval.getText().toString();

            tempval=(TextView)findViewById(R.id.txtMarca);
            String mar = tempval.getText().toString();

            tempval=(TextView)findViewById(R.id.txtPrecio);
            String pre = tempval.getText().toString();

            productos= new DB(DatosProductos. this,"", null,1);
            productos.guardarProducto(nom, cod, mar,pre, accion, id);

            Toast.makeText(DatosProductos.this,"Producto registrado" +
                    " con exito", Toast.LENGTH_LONG).show();

            Intent imostrar= new Intent(DatosProductos. this,
                    MainActivity.class);
            startActivity(imostrar);

        }catch (Exception ex) {
            Toast.makeText(DatosProductos.this,"Error: "+
                    ex.getMessage().toString(),+Toast.LENGTH_LONG).show();
        }
    }
}

