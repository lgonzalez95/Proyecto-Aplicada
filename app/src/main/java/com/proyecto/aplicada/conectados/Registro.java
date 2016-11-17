package com.proyecto.aplicada.conectados;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Registro extends AppCompatActivity implements View.OnClickListener {

    private Button btnRegistrarme;
    private Button btnCancelar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        btnRegistrarme=(Button)findViewById(R.id.idBtnRegistrarme);
        btnCancelar=(Button)findViewById(R.id.idBtnCancelar);

        btnCancelar.setOnClickListener(this);
        btnRegistrarme.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.idBtnRegistrarme:
                break;
            case R.id.idBtnCancelar:
                break;

        }
    }
}
