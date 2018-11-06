package com.example.cnec.sit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

public class projetoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_projeto);

        /*TextView tv = findViewById(R.id.textView);

        tv.setText("O projeto SIT foi criado em 2017, pelos acadêmicos do 3º e 4º semestre do Curso" +
                " Tecnologia em Análise e Desenvolvimento de Sistemas na Faculdade CNEC de Santo Ângelo" +
                " – Rio Grande do Sul como parte do Projeto Integrador, que visa facilitar as informações " +
                "sobre os cronogramas do transporte coletivo público da cidade de Santo Ângelo.  " +
                "Com objetivo de facilitar o acesso às informações aos usuários de uma maneira fácil, " +
                "rápida e segura para quem busca este meio de locomoção em Santo Ângelo. Atualmente," +
                " o mesmo está sendo continuado pelos acadêmicos do 4º Semestre de 2018/2 do curso.");*/
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
                startActivity(new Intent(projetoActivity.this, projetoActivity.class));
                return true;
            case R.id.item3:
                startActivity(new Intent(projetoActivity.this, contatoActivity.class));
                return true;
            case R.id.item4:
                startActivity(new Intent(projetoActivity.this, feedbackActivity.class));
                return true;
            case R.id.item5:
                startActivity(new Intent(projetoActivity.this, loginActivity.class));
                return true;
            case R.id.item6:
                startActivity(new Intent(projetoActivity.this, perfilActivity.class));
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

