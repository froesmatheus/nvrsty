package com.nvrsty.activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TimePicker;

import com.nvrsty.R;
import com.nvrsty.models.HorarioAula;

import java.text.NumberFormat;
import java.util.Calendar;
import java.util.TimeZone;

public class CadastrarHorarioActivity extends AppCompatActivity {
    private TimePickerDialog horarioInicialTimePicker;
    private TimePickerDialog horarioFinalTimePicker;
    private int diaDaSemana;
    private EditText etHoraInicial, etHoraFinal, etDiaSemana, etBloco, etSala;
    private Calendar dataAtual, horaInicio, horaFim;
    private NumberFormat format = NumberFormat.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_horario);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.title_activity_cadastrar_horario);
        toolbar.setBackgroundColor(ContextCompat.getColor(this, R.color.primary));
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setNavigationIcon(R.mipmap.ic_action_navigation_close);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        dataAtual = Calendar.getInstance();
        horaInicio = Calendar.getInstance(TimeZone.getDefault());
        horaFim = Calendar.getInstance(TimeZone.getDefault());


        format.setMinimumIntegerDigits(2);
        etBloco = (EditText) findViewById(R.id.et_bloco);
        etSala = (EditText) findViewById(R.id.et_sala);
        etDiaSemana = (EditText) findViewById(R.id.et_dia_semana);
        etHoraInicial = (EditText) findViewById(R.id.et_hora_inicial);
        etHoraFinal = (EditText) findViewById(R.id.et_hora_final);

        etDiaSemana.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(CadastrarHorarioActivity.this);

                final ArrayAdapter<String> listAdapter = new ArrayAdapter<>(CadastrarHorarioActivity.this, android.R.layout.simple_list_item_1);
                listAdapter.add("Segunda-feira");
                listAdapter.add("Terça-feira");
                listAdapter.add("Quarta-feira");
                listAdapter.add("Quinta-feira");
                listAdapter.add("Sexta-feira");
                listAdapter.add("Sábado");
                listAdapter.add("Domingo");

                builder.setAdapter(listAdapter, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        etDiaSemana.setText(listAdapter.getItem(which));
                        diaDaSemana = which;
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();

            }
        });

        etHoraInicial.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    horarioInicialTimePicker = new TimePickerDialog(CadastrarHorarioActivity.this, new TimePickerDialog.OnTimeSetListener() {
                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                            horaInicio.set(Calendar.HOUR_OF_DAY, hourOfDay);
                            horaInicio.set(Calendar.MINUTE, minute);
                            horaInicio.set(Calendar.DAY_OF_WEEK, diaDaSemana);

                            String horaInicialStr = format.format(hourOfDay) + ":" + format.format(minute);
                            etHoraInicial.setText(horaInicialStr);
                        }
                    }, dataAtual.get(Calendar.HOUR_OF_DAY), dataAtual.get(Calendar.MINUTE), true);

                    horarioInicialTimePicker.setTitle(R.string.escolher_horario_inicial);
                    horarioInicialTimePicker.show();
                }
            }
        });

//        etHoraInicial.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                horarioInicialTimePicker = new TimePickerDialog(CadastrarHorarioActivity.this, new TimePickerDialog.OnTimeSetListener() {
//                    @Override
//                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
//                        horaInicio.set(Calendar.HOUR_OF_DAY, hourOfDay);
//                        horaInicio.set(Calendar.MINUTE, minute);
//                        etHoraInicial.setText(format.format(hourOfDay) + ":" + format.format(minute));
//                    }
//                }, dataAtual.get(Calendar.HOUR_OF_DAY), dataAtual.get(Calendar.MINUTE), true);
//
//                horarioInicialTimePicker.setTitle("Escolha um horário inicial");
//                horarioInicialTimePicker.show();
//            }
//        });

        etHoraFinal.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    horarioFinalTimePicker = new TimePickerDialog(CadastrarHorarioActivity.this, new TimePickerDialog.OnTimeSetListener() {
                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                            horaFim.set(Calendar.HOUR_OF_DAY, hourOfDay);
                            horaFim.set(Calendar.MINUTE, minute);
                            horaFim.set(Calendar.DAY_OF_WEEK, diaDaSemana);

                            String horaFinalStr = format.format(hourOfDay) + ":" + format.format(minute);
                            etHoraFinal.setText(horaFinalStr);
                        }
                    }, dataAtual.get(Calendar.HOUR_OF_DAY), dataAtual.get(Calendar.MINUTE), true);

                    horarioFinalTimePicker.setTitle(R.string.escolher_horario_final);
                    horarioFinalTimePicker.show();
                }
            }
        });
//
//        etHoraFinal.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                horarioFinalTimePicker = new TimePickerDialog(CadastrarHorarioActivity.this, new TimePickerDialog.OnTimeSetListener() {
//                    @Override
//                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
//                        horaFim.set(Calendar.HOUR_OF_DAY, hourOfDay);
//                        horaFim.set(Calendar.MINUTE, minute);
//                        etHoraFinal.setText(format.format(hourOfDay) + ":" + format.format(minute));
//                    }
//                }, dataAtual.get(Calendar.HOUR_OF_DAY), dataAtual.get(Calendar.MINUTE), true);
//
//                horarioFinalTimePicker.setTitle("Escolha um horário final");
//                horarioFinalTimePicker.show();
//            }
//        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_cadastrar_horario, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.action_confirmar:
                if (validarCampos()) {
                    String bloco = etBloco.getText().toString();
                    String sala = etSala.getText().toString();
                    HorarioAula horario = new HorarioAula(horaInicio, horaFim, diaDaSemana, bloco, sala);

                    Intent intent = new Intent();
                    intent.putExtra("HORARIO", horario);

                    setResult(Activity.RESULT_OK, intent);
                    finish();
                    return true;
                }
            case android.R.id.home:
                onBackPressed();
                return true;

        }

        return false;
    }

    private boolean validarCampos() {
        boolean valido = true;

        if (etDiaSemana.getText().toString().isEmpty()) {
            valido = false;
        } else {
        }

        if (etHoraInicial.getText().toString().isEmpty()) {
            valido = false;
        } else {
        }

        if (etHoraFinal.getText().toString().isEmpty()) {
            valido = false;
        } else {

        }

        return valido;
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(CadastrarHorarioActivity.this);

        builder.setTitle(R.string.confirmar_cancelamento);
        builder.setMessage(R.string.confirmar_cancelar_cadastro_horario);

        builder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                CadastrarHorarioActivity.this.finish();
                dialog.dismiss();
            }
        });

        builder.setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        builder.create().show();
    }
}
