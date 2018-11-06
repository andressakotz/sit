package com.example.cnec.sit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class contatoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contato);

        TextView tv = findViewById(R.id.end);
        tv.setText("Rua Bento Gonçalves, 765, Santo Ângelo - RS");

        TextView tv1 = findViewById(R.id.tel);
        tv1.setText("(55) 3312-4988");

        TextView tv2 = findViewById(R.id.pend);
        tv2.setText("Endereço");

        TextView tv3 = findViewById(R.id.ptel);
        tv3.setText("Telefone");
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
                startActivity(new Intent(contatoActivity.this, projetoActivity.class));
                return true;
            case R.id.item3:
                startActivity(new Intent(contatoActivity.this, contatoActivity.class));
                return true;
            case R.id.item4:
                startActivity(new Intent(contatoActivity.this, feedbackActivity.class));
                return true;
            case R.id.item5:
                startActivity(new Intent(contatoActivity.this, loginActivity.class));
                return true;
            case R.id.item6:
                startActivity(new Intent(contatoActivity.this, perfilActivity.class));
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
