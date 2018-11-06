package com.example.cnec.sit;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
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

public class MainActivity extends AppCompatActivity {

    private android.widget.Toast Toast;



    private String[] ruas = new String[]{"Rua do Rosário ",
            "Rua São Lourenço ",
            "Travessa Butia ",
            "Travessa Germano Mousquer ",
            "Rua Oscar Ernesto Jung ",
            "Travessa Fabiano Rosa de Paula ",
            "Rua Agostinho ",
            "Rua José Mendes ",
            "Rua Pedro Casarotto ",
            "Rua Silvia Conoratti ",
            "Travessa Rita de Cassia ",
            "Rua José Vieira ",
            "Avenida Sagrada Famí ",
            "Rua Luís Carlos Rocha"};

    private String[] ruas2 = new String[]{"Rua do Rosário ",
            "Rua São Lourenço ",
            "Travessa Butia ",
            "Travessa Germano Mousquer ",
            "Rua Oscar Ernesto Jung ",
            "Travessa Fabiano Rosa de Paula ",
            "Rua Agostinho ",
            "Rua José Mendes ",
            "Rua Pedro Casarotto ",
            "Rua Silvia Conoratti ",
            "Travessa Rita de Cassia ",
            "Rua José Vieira ",
            "Avenida Sagrada Famí ",
            "Rua Luís Carlos Rocha"};



    private Spinner sp;
    private Spinner sp2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, ruas);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        sp = (Spinner) (findViewById(R.id.spinner));
        sp.setAdapter(adapter);

        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, ruas2);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        sp2 = (Spinner) (findViewById(R.id.spinner2));
        sp2.setAdapter(adapter);

        sp2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


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
                startActivity(new Intent(MainActivity.this, projetoActivity.class));
                return true;
            case R.id.item3:
                startActivity(new Intent(MainActivity.this, contatoActivity.class));
                return true;
            case R.id.item4:
                startActivity(new Intent(MainActivity.this, feedbackActivity.class));
                return true;
            case R.id.item5:
                startActivity(new Intent(MainActivity.this, loginActivity.class));
                return true;
            case R.id.item6:
                startActivity(new Intent(MainActivity.this, perfilActivity.class));
                return true;

            default:
                return super.onOptionsItemSelected(item);
}
    }


    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuItem novo = menu.add(0,0,0,"Projeto");
        MenuItem novo2 = menu.add(1,1,1,"Quem Somos");
        MenuItem novo3 = menu.add(2,2,2,"Contato");
        MenuItem novo4 = menu.add(3,3,3,"Envie seu Feedback");
        novo.setIcon(R.drawable.novo);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == 0){
            startActivity(new Intent(MainActivity.this, projetoActivity.class));
            startActivity(new Intent(MainActivity.this, Main3Activity.class));
            startActivity(new Intent(MainActivity.this, contatoActivity.class));
            startActivity(new Intent(MainActivity.this, feedbackActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }*/

    public void vaiParaParadas(View v){

        Intent i = new Intent(getApplicationContext(), mapaActivity.class);

        startActivityForResult(i, 123);
    }

    public void pressionarBotao(View v) {
        Intent intent = new Intent(this, Main6Activity.class);
        startActivity(intent);
    }

}





