package com.example.cnec.sit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class loginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

    TextView tv = findViewById(R.id.textView14);

        tv.setText("Não possui conta? Cadastre-se");
}

    public void vaiParaInicio(View v) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void vaiParaCadastro(View v) {
        Intent intent = new Intent(this, cadastroActivity.class);
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
                startActivity(new Intent(loginActivity.this, projetoActivity.class));
                return true;
            case R.id.item3:
                startActivity(new Intent(loginActivity.this, contatoActivity.class));
                return true;
            case R.id.item4:
                startActivity(new Intent(loginActivity.this, feedbackActivity.class));
                return true;
            case R.id.item5:
                startActivity(new Intent(loginActivity.this, loginActivity.class));
                return true;
            case R.id.item6:
                startActivity(new Intent(loginActivity.this, perfilActivity.class));
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
