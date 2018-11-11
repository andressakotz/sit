package com.example.cnec.sit;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.github.rtoshiro.util.format.MaskFormatter;
import com.github.rtoshiro.util.format.SimpleMaskFormatter;
import com.github.rtoshiro.util.format.text.MaskTextWatcher;
import com.github.rtoshiro.util.format.text.SimpleMaskTextWatcher;

public class cadastroActivity extends AppCompatActivity {

    private EditText etNome;
    private EditText etNasc;
    private EditText etEmail;
    private EditText etSenhaC;
    RadioButton rb1, rb2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        etNome = (EditText) findViewById(R.id.etNome);
        etNasc = (EditText) findViewById(R.id.etNasc);
        etEmail = (EditText) findViewById(R.id.etEmail);
        etSenhaC = (EditText) findViewById(R.id.etSenhaC);
        rb1 = (RadioButton) findViewById(R.id.radioMunicipe);
        rb2 = (RadioButton) findViewById(R.id.radioVisitante);
        //final RadioButton municipe = (RadioButton) findViewById(R.id.radioMunicipe);
        //final RadioButton visitante = (RadioButton) findViewById(R.id.radioVisitante);

        SimpleMaskFormatter smf = new SimpleMaskFormatter("NN/NN/NNNN");
        MaskTextWatcher mtw = new SimpleMaskTextWatcher(etNasc, smf);
        etNasc.addTextChangedListener(mtw);
    }


    public void validaCampos(View v){
        boolean res = false;

        String nome = etNome.getText().toString();
        String nasc = etNasc.getText().toString();
        String email = etEmail.getText().toString();
        String senha = etSenhaC.getText().toString();
        String rbOpcao;

        if(res = ifCampoVazio(nome)){
            etNome.requestFocus();
        }
        else
            if(res = ifCampoVazio(nasc)){
                //nome OK
                etNasc.requestFocus();
            }else
                if(res = !ifEmailValido(email)){
                    //email OK
                    etEmail.requestFocus();
                } else
                    if(res = ifCampoVazio(senha)){
                        //data OK
                        etSenhaC.requestFocus();
                    }else
                        if(rb1.isChecked()){
                            rbOpcao = "Municipe"; //rbOpcao eh o valor do radioButton
                            Toast.makeText(this, rbOpcao, Toast.LENGTH_SHORT).show();
                        }else
                            if(rb2.isChecked()){
                            rbOpcao = "Visitante";
                            Toast.makeText(this, rbOpcao, Toast.LENGTH_SHORT).show();
                        }
                        else{
                               //sla
                            }

        if(res){
            AlertDialog.Builder dlg = new AlertDialog.Builder(this);
            dlg.setTitle("Aviso");
            dlg.setMessage("Há campos vazios.");
            dlg.setNeutralButton("Ok", null);
            dlg.show();
        }

    }

    private boolean ifCampoVazio(String valor){
        boolean result = (TextUtils.isEmpty(valor) || valor.trim().isEmpty());
        return result;
    }

    private boolean ifEmailValido(String email){
        boolean result = (!ifCampoVazio(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches());
        return result;
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
            case R.id.radioVisitante:
                if (checked)
                    Toast.makeText(cadastroActivity.this, "Visitante", Toast.LENGTH_SHORT).show();
                else
                    break;
            case R.id.radioMunicipe:
                if (checked)
                    Toast.makeText(cadastroActivity.this, "Munícipe", Toast.LENGTH_SHORT).show();
                else
                    break;
        }
    }
}
