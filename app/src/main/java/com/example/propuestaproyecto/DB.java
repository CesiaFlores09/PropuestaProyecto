package com.example.propuestaproyecto;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class DB extends SQLiteOpenHelper {
    public static final String db_productos="db_producto";
    public static final int v=1;
    String sqlTabla="create table productos(idProductos integer primary key autoincrement,nombre text,codigo text,marca text,precio text)";

    public DB(Context context, String name, SQLiteDatabase.CursorFactory factory, int version){
        super(context,db_productos,factory,version);
    }


    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL(sqlTabla);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public void guardarProducto(String nom,String cod, String mar, String pre, String accion, String id){
        SQLiteDatabase db = getWritableDatabase();
        if(accion.equals("modificar")){
            db.execSQL("update productos set nombre='"+nom+"',codigo='"+cod+"',marca='"+mar+"',precio='"+pre+"' where idProductos='"+id+"'");
        }else {
            db.execSQL("insert into productos (nombre,codigo,marca,precio)values('"+nom+"','"+cod+"','"+mar+"','"+pre+"')");
        }
    }
    public void eliminarProducto(String id){
        SQLiteDatabase db= getWritableDatabase();
        db.execSQL("delete from productos where idProductos='"+id+"'");
    }
    public Cursor ConsultarProductos() {
        String sql = "select*from productos order by nombre asc";
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery(sql, null);
        return c;
    }
}
