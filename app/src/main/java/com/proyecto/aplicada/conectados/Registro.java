package com.proyecto.aplicada.conectados;

import android.content.ContentValues;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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
                if(conexion.mInsertarUsuario(valores, "usuario")==true){
                    System.out.println("Agregado exitosamente");
                }else{
                    System.out.println("No se agregó");
                }
                break;
            case R.id.idBtnCancelar:
                break;

        }
    }
}
