package com.example.cnec.sit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Main6Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);
    }

        public void salvarCadastro (View v) {
            EditText rua = findViewById(R.id.editText);
            EditText bairro = findViewById(R.id.editText2);
            EditText info = findViewById(R.id.editText3);

            String cadastra = rua.getText().toString();
            String cadastra1 = bairro.getText().toString();
            String cadastra2 = info.getText().toString();

            /*rua.setText("");
            bairro.setText("");
            info.setText("");

            Toast.makeText(Main6Activity.this, "Parada cadastrada com sucesso!", Toast.LENGTH_SHORT).show();*/

            if(rua.length() > 0){
                if(bairro.length() > 0){
                    if(info.length() > 0){
                        Toast.makeText(Main6Activity.this, "Parada cadastrada com sucesso!", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(Main6Activity.this, "Insira uma informação", Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    Toast.makeText(Main6Activity.this, "Insira um bairro", Toast.LENGTH_SHORT).show();
                }
            }
            else{
                Toast.makeText(Main6Activity.this, "Insira uma rua", Toast.LENGTH_SHORT).show();
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
                startActivity(new Intent(Main6Activity.this, projetoActivity.class));
                return true;
            case R.id.item3:
                startActivity(new Intent(Main6Activity.this, contatoActivity.class));
                return true;
            case R.id.item4:
                startActivity(new Intent(Main6Activity.this, feedbackActivity.class));
                return true;
            case R.id.item5:
                startActivity(new Intent(Main6Activity.this, loginActivity.class));
                return true;
            case R.id.item6:
                startActivity(new Intent(Main6Activity.this, perfilActivity.class));
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

