package com.proyecto.aplicada.conectados;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Registro extends AppCompatActivity implements View.OnClickListener {

    private Button btnRegistrarme;
    private Button btnCancelar;
    private SqlConexion conexion;

    private EditText txtNombreCompleto,txtUsuario,txtPassword,txtCarnet,txtCorreo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        btnRegistrarme=(Button)findViewById(R.id.idBtnRegistrarme);
        btnCancelar=(Button)findViewById(R.id.idBtnCancelar);

        txtNombreCompleto=(EditText)findViewById(R.id.txtNombreCompleto);
        txtUsuario=(EditText)findViewById(R.id.txtNombreUsuario);
        txtPassword=(EditText)findViewById(R.id.txtPassword);
        txtCarnet=(EditText)findViewById(R.id.txtCarnet);
        txtCorreo=(EditText)findViewById(R.id.txtCorreo);

        btnCancelar.setOnClickListener(this);
        btnRegistrarme.setOnClickListener(this);

        conexion= new SqlConexion(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.idBtnRegistrarme:
                boolean estadoUsuario=false; //para verificar que no haya otro usuario igual
                boolean estadoCorreo=false; //para verificar que no haya otro correo igual

                String nombreCompleto = txtNombreCompleto.getText().toString();
                String usuario = txtUsuario.getText().toString();
                String password = txtPassword.getText().toString();
                String carnet = txtCarnet.getText().toString();
                String correo = txtCorreo.getText().toString();

                ContentValues valores = new ContentValues();
                valores.put("nombreCompleto", nombreCompleto);
                valores.put("password", password);
                valores.put("usuario", usuario );
                valores.put("estado",0);
                valores.put("carnet", carnet);
                valores.put("correo", correo);

                if(!nombreCompleto.equalsIgnoreCase("")&& !usuario.equalsIgnoreCase("")
                        && !password.equalsIgnoreCase("")&& !carnet.equalsIgnoreCase("")
                        && !correo.equalsIgnoreCase("")) {
                    String query = " SELECT * FROM usuario";
                    Cursor cursor = conexion.mConsultarUser(query, null);

                    if (cursor.moveToFirst()) {
                        //Recorremos el cursor hasta que no haya más registros
                        do {
                            if (usuario.equalsIgnoreCase(cursor.getString(1))) {
                                estadoUsuario = true;
                            }
                            if (correo.equalsIgnoreCase(cursor.getString(5))) {
                                estadoCorreo = true;
                            }
                        } while (cursor.moveToNext());
                    }
                    if (estadoCorreo == false || estadoUsuario == false) {
                        conexion.mInsertarUsuario(valores, "usuario");
                        Toast.makeText(this, "Registro exitoso", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(Registro.this, Inicio.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(this, "Correo o usuario ya registrado", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(this, "Favor llenar los campos necesarios", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.idBtnCancelar:
                Intent intent = new Intent(Registro.this,Inicio.class);
                startActivity(intent);
                break;

        }
    }
}
