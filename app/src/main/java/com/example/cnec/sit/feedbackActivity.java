package com.example.cnec.sit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class feedbackActivity extends AppCompatActivity {

    private String[] categoria = new String[]{"Ônibus",
            "Paradas de Ônibus ",
            "Atendimento ",
            "Outros "};

    private String[] categoria2 = new String[]{"Elogio ",
            "Crítica ",
            "Sugestão ",
            "Outros "};

    private Spinner sp3;
    private Spinner sp4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        //SPINNER 1

        ArrayAdapter<String> adapter3 =  new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, categoria);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        sp3 = (Spinner) (findViewById(R.id.sAssunto));
        sp3.setAdapter(adapter3);

        sp3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        //SPINNER 2

        ArrayAdapter<String> adapter4 =  new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, categoria2);
        adapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        sp4 = (Spinner) (findViewById(R.id.sCategoria));
        sp4.setAdapter(adapter4);

        sp4.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    /*public void enviarFeedback (View v) {

        Toast.makeText(feedbackActivity.this, "Feedback enviado com sucesso!", Toast.LENGTH_SHORT).show();

    }*/


    public void enviarFeedback (View v) {
        EditText campoFeedback = (EditText) findViewById(R.id.campoFeedback);

        String nomeSP3 = (String) sp3.getSelectedItem();
        long idSP3 = sp3.getSelectedItemId();
        int posicaoSP3 = sp3.getSelectedItemPosition();

        String nomeSP4 = (String) sp4.getSelectedItem();
        long idSP4 = sp4.getSelectedItemId();
        int posicaoSP4 = sp4.getSelectedItemPosition();

        if(campoFeedback.length() > 0){
            Toast.makeText(feedbackActivity.this, "Seu feedback sobre: " + nomeSP3 + ", " + nomeSP4 + " foi enviado com sucesso!", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(feedbackActivity.this, "Preencha o campo de feedback", Toast.LENGTH_SHORT).show();
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item1:
                startActivity(new Intent(feedbackActivity.this, projetoActivity.class));
                return true;
            case R.id.item3:
                startActivity(new Intent(feedbackActivity.this, contatoActivity.class));
                return true;
            case R.id.item4:
                startActivity(new Intent(feedbackActivity.this, feedbackActivity.class));
                return true;
            case R.id.item5:
                startActivity(new Intent(feedbackActivity.this, loginActivity.class));
                return true;
            case R.id.item6:
                startActivity(new Intent(feedbackActivity.this, perfilActivity.class));
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void vaiParaInicio(View v) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
