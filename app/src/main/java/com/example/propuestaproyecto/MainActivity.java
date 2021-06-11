package com.example.propuestaproyecto;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    Bundle parametros = new Bundle();

    DB db;
    public Cursor c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       obtenerDatos();

        FloatingActionButton btnSalir1 = (FloatingActionButton) findViewById(R.id.btnSalir1);
        FloatingActionButton btnAgregar = (FloatingActionButton) findViewById(R.id.btnAgregar);



        btnSalir1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishAffinity();
                System.exit(0);
            }


        });
        btnAgregar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent Siguiente = new Intent(MainActivity.this,DatosProductos.class);
                startActivity(Siguiente);

            }
        });


    }
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        MenuInflater inflate = getMenuInflater();
        inflate.inflate(R.menu.menu, menu);

        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
        c.moveToPosition(info.position);
        menu.setHeaderTitle(c.getString(1));


    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.mnxModificar:
                try {
                    String user[] = {
                            c.getString(1),
                            c.getString(2),
                            c.getString(3),
                            c.getString(4),

                    };

                    Bundle bundle = new Bundle();
                    bundle.putString("accion", "modificar");
                    bundle.putString("id", c.getString(0));
                    bundle.putStringArray("user", user);

                    Intent iproducto = new Intent(MainActivity.this, DatosProductos.class);
                    iproducto.putExtras(bundle);
                    startActivity(iproducto);

                }
                catch (Exception e) {
                    Toast.makeText(MainActivity.this,"Error: "+ e.getMessage().
                            toString(),Toast.LENGTH_LONG).show();

                }
                return true;

            case R.id.mnxEliminar:

                androidx.appcompat.app.AlertDialog confirmacion= eliminar();
                confirmacion.show();
                return true;
            default:
                return super.onContextItemSelected(item);

        }
    }
    public AlertDialog eliminar(){
        AlertDialog.Builder confirmacion = new
                AlertDialog.Builder(MainActivity.this);

        confirmacion.setTitle(c.getString(1));
        confirmacion.setMessage("Estas seguro de eliminar este contacto?");
        confirmacion.setPositiveButton("Si", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                db.eliminarProducto(c.getString(0));
                dialog.cancel();
                Toast.makeText(MainActivity.this,"El producto se elimino " +
                        "correctamente.", Toast.LENGTH_LONG).show();
            }
        });

        confirmacion.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
                Toast.makeText(MainActivity.this,"Accion cancelada.", Toast.LENGTH_LONG).show();
            }
        });
        return confirmacion.create();

    }
    public  void obtenerDatos(){
        db=new DB(MainActivity.this,"", null,1);
        c=db.ConsultarProductos();
        if (c.moveToFirst()){

            ListView ItsUser=(ListView)findViewById(R.id.ltsProductos);

            final ArrayList<String>alusers= new ArrayList<String>();
            final ArrayAdapter<String> aaUsers=new
                    ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1,
                    alusers);
            ItsUser.setAdapter(aaUsers);

            do {
                alusers.add(c.getString(1));
            }while (c.moveToNext());
            aaUsers.notifyDataSetChanged();

            registerForContextMenu(ItsUser);

        }else {
            Toast.makeText(MainActivity.this,"No hay productos " +
                    "que mostrar ",Toast.LENGTH_LONG).show();

        }
    }
    public void registrar_productos(View view){
        Intent iagregar = new Intent(MainActivity. this, DatosProductos.class);
        startActivity(iagregar);

    }



}








