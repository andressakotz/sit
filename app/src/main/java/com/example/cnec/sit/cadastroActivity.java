package com.example.cnec.sit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

public class cadastroActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
    }

    public void vaiParaInicio(View v) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void cancelar(View v) {
        Intent intent = new Intent(this, loginActivity.class);
        startActivity(intent);
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
                startActivity(new Intent(cadastroActivity.this, projetoActivity.class));
                return true;
            case R.id.item3:
                startActivity(new Intent(cadastroActivity.this, contatoActivity.class));
                return true;
            case R.id.item4:
                startActivity(new Intent(cadastroActivity.this, feedbackActivity.class));
                return true;
            case R.id.item5:
                startActivity(new Intent(cadastroActivity.this, loginActivity.class));
                return true;
            case R.id.item6:
                startActivity(new Intent(cadastroActivity.this, perfilActivity.class));
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void onCheckboxClicked(View view) {
        boolean checked = ((CheckBox) view).isChecked();

        switch(view.getId()) {
            case R.id.visitante:
                if (checked)
                    Toast.makeText(cadastroActivity.this, "Visitante", Toast.LENGTH_SHORT).show();
                else
                    break;
            case R.id.municipe:
                if (checked)
                    Toast.makeText(cadastroActivity.this, "Munícipe", Toast.LENGTH_SHORT).show();
                else
                    break;
        }
    }
}
