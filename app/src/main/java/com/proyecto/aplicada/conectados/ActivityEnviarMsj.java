package com.proyecto.aplicada.conectados;

import android.content.ContentValues;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class ActivityEnviarMsj extends AppCompatActivity implements View.OnClickListener{

    private EditText txtAsunto, txtDesc;
    TextView txtUsuarioPara;
    SqlConexion conexion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_msj_nuevo);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Button button = (Button) findViewById(R.id.btn_agregarM);
        button.setOnClickListener(this);

        txtAsunto = (EditText) findViewById(R.id.edit_txt_asunto);
        txtDesc = (EditText) findViewById(R.id.edit_txt_descripcion);
        txtUsuarioPara=(TextView) findViewById(R.id.fecha_label);
        conexion = new SqlConexion(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        finish();
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }



    @Override
    public void onClick(View v) {

    switch (v.getId()){
        case R.id.btn_agregarM:
            ContentValues valores = new ContentValues();
            valores.put("asunto", txtAsunto.getText().toString());
            valores.put("mensaje",  txtDesc.getText().toString());
            valores.put("usuarioDe", getIntent().getExtras().getString("usuario") );
            valores.put("usuarioPara", "Becas");
            valores.put("estadoMensaje",0);
            if(conexion.mInsertarUsuario(valores, "mensajes")){
                System.out.println("Agregó msj");
                String query = " SELECT * FROM mensajes";
                Cursor cursor = conexion.mConsultarUser(query, null);

                if (cursor.moveToFirst()) {
                    do {
                    //Recorremos el cursor hasta que no haya más registros
                   System.out.println("De " +cursor.getString(3));
                        System.out.println("Para " +cursor.getString(4));
                    } while (cursor.moveToNext());
                }
            }else{
                System.out.println("NO AGREGÓ MSJ");
            }
            break;
    }
    }
}

