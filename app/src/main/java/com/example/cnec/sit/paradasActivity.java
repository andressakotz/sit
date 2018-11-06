package com.example.cnec.sit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

public class paradasActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paradas);
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
                startActivity(new Intent(paradasActivity.this, projetoActivity.class));
                return true;
            case R.id.item3:
                startActivity(new Intent(paradasActivity.this, contatoActivity.class));
                return true;
            case R.id.item4:
                startActivity(new Intent(paradasActivity.this, feedbackActivity.class));
                return true;
            case R.id.item5:
                startActivity(new Intent(paradasActivity.this, loginActivity.class));
                return true;
            case R.id.item6:
                startActivity(new Intent(paradasActivity.this, perfilActivity.class));
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
