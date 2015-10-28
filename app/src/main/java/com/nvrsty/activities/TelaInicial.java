package com.nvrsty.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.nvrsty.R;
import com.nvrsty.db.DisciplinasDAO;

public class TelaInicial extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_inicial);

        ImageView btnMaterias = (ImageView) findViewById(R.id.btn_materias);
        btnMaterias.setOnClickListener(this);

        ImageView btnEventos = (ImageView) findViewById(R.id.btn_eventos);
        btnEventos.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Intent intent = null;

        switch (v.getId()) {

            case R.id.btn_materias:
                intent = new Intent(getApplicationContext(), ListaDisciplinas.class);
                break;
            case R.id.btn_eventos:
                intent = new Intent(getApplicationContext(), ListaEventos.class);
                break;
        }
        startActivity(intent);
    }
}