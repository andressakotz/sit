package com.example.cnec.sit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class perfilActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        TextView nome = findViewById(R.id.nome);
        nome.setText("José Eduardo Leischtweis");

        TextView nasc = findViewById(R.id.nasc);
        nasc.setText("31/05/1985");

        TextView email = findViewById(R.id.email);
        email.setText("josedl33@hotmail.com");

        TextView senha = findViewById(R.id.senha);
        senha.setText("joseedle1958");
    }

    public void alterar (View v) {

        Toast.makeText(perfilActivity.this, "Alterado com sucesso!", Toast.LENGTH_SHORT).show();
    }

    public void excluir (View v) {

        Toast.makeText(perfilActivity.this, "Excluido com sucesso!", Toast.LENGTH_SHORT).show();
    }

    /*public void excluirConta(View v) {
        TextView nome = findViewById(R.id.nome);

        excluirContaActivity excluirConta = new excluirContaActivity();

        excluirConta.execute(nome.getText().toString());

    }

    public void editarConta(View v) {
        TextView nome = findViewById(R.id.nome);
        TextView data_nasc = findViewById(R.id.nasc);
        TextView email = findViewById(R.id.email);
        TextView senha = findViewById(R.id.senha);

        edicarContaActivity editarConta = new edicarContaActivity();

        editarConta.execute(nome.getText().toString(),
                data_nasc.getText().toString(),
                email.getText().toString(),
                senha.getText().toString());

    }

    /*public void exibirListagem(String o) {
        try {
            JSONArray jsonArray = new JSONArray(o);

            TextView nome = new TextView(this);
            //nome.setText("Nome");
            nome.setTextSize(30);

            TextView data_nasc = new TextView(this);
            //data_nasc.setText("data_nasc");
            data_nasc.setTextSize(30);

            TextView email = new TextView(this);
            //email.setText("email");
            email.setTextSize(30);

            TextView senha = new TextView(this);
            //senha.setText("senha");
            senha.setTextSize(30);

            TableRow tr = new TableRow(this);

            tr.addView(nome);
            tr.addView(data_nasc);
            tr.addView(email);
            tr.addView(senha);


            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject JSONConta = jsonArray.getJSONObject(i);

                nome = new TextView(this);
                nome.setText(JSONConta.get("nome").toString());
                nome.setTextSize(30);

                data_nasc = new TextView(this);
                data_nasc.setText(JSONConta.get("data_nasc").toString());
                data_nasc.setTextSize(30);

                email = new TextView(this);
                email.setText(JSONConta.get("email").toString());
                email.setTextSize(30);

                senha = new TextView(this);
                senha.setText(JSONConta.get("senha").toString());
                senha.setTextSize(30);

                tr = new TableRow(this);

                tr.addView(nome);
                tr.addView(data_nasc);
                tr.addView(email);
                tr.addView(senha);
//                Log.d("Nome:", JSONLivro.get("nome").toString());
//                Log.d("Ano:", JSONLivro.get("ano").toString());
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }*/

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
                startActivity(new Intent(perfilActivity.this, projetoActivity.class));
                return true;
            case R.id.item3:
                startActivity(new Intent(perfilActivity.this, contatoActivity.class));
                return true;
            case R.id.item4:
                startActivity(new Intent(perfilActivity.this, feedbackActivity.class));
                return true;
            case R.id.item5:
                startActivity(new Intent(perfilActivity.this, loginActivity.class));
                return true;
            case R.id.item6:
                startActivity(new Intent(perfilActivity.this, perfilActivity.class));
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
