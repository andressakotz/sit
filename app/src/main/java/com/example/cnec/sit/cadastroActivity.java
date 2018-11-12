package com.example.cnec.sit;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
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

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class cadastroActivity extends AppCompatActivity {

    String [] cadastra;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
        intent = new Intent(this, MainActivity.class);
        cadastra = new String[6];
        cadastra[4] = "f";
        cadastra[5] = "Munícipe";
    }

    public void cancelar(View v) {
        Intent intent = new Intent(this, loginActivity.class);
        startActivity(intent);
    }

    public void botaoCadastrar(View v) {

        EditText nome = findViewById(R.id.etNome);
        EditText nascimento = findViewById(R.id.etNasc);
        EditText email = findViewById(R.id.etEmail);
        EditText senha = findViewById(R.id.etSenhaC);
        RadioButton rb1 = (RadioButton) findViewById(R.id.radioMunicipe);
        RadioButton rb2 = (RadioButton) findViewById(R.id.radioVisitante);



        cadastra[0] = nome.getText().toString();
        cadastra[1] = nascimento.getText().toString();
        try {

            cadastra[2] = email.getText().toString();
            cadastra[3] = senha.getText().toString();

            if(ifCampoVazio(cadastra[0])){
                nome.requestFocus();
                Toast.makeText(cadastroActivity.this, "Nome vazio", Toast.LENGTH_SHORT).show();
            } else if(ifCampoVazio(cadastra[1])){
                //nome OK
                nascimento.requestFocus();
                Toast.makeText(cadastroActivity.this, "Nascimento vazio", Toast.LENGTH_SHORT).show();
            }else if(!ifEmailValido(cadastra[2])){
                
                email.requestFocus();
                Toast.makeText(cadastroActivity.this, "email inválido", Toast.LENGTH_SHORT).show();
            } else if(ifCampoVazio(cadastra[3])) {
                //data OK
                senha.requestFocus();
                Toast.makeText(cadastroActivity.this, "senha vazia", Toast.LENGTH_SHORT).show();
            }
            else{
                SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                SimpleDateFormat formatOut = new SimpleDateFormat("yyyy/MM/dd");
                Date birth = format.parse(cadastra[1]);
                cadastra[1] = formatOut.format(birth);
                if (rb1.isChecked()) {
                    cadastra[5] = "Municipe";

                } else if (rb2.isChecked()) {
                    cadastra[5] = "Visitante";
                }

                new AsyncSend().execute(cadastra);
                nome.setText("");
                nascimento.setText("");
                email.setText("");
                senha.setText("");
            }
        }catch (ParseException err){
            Toast.makeText(cadastroActivity.this, "Data de nascimento inválida", Toast.LENGTH_SHORT).show();
        }

    }

    private boolean ifEmailValido(String email){
        boolean result = (!ifCampoVazio(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches());
        return result;
    }

    private boolean ifCampoVazio(String valor){
        boolean result = (TextUtils.isEmpty(valor) || valor.trim().isEmpty());
        return result;
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
                break;
            case R.id.radioMunicipe:
                if (checked)
                    Toast.makeText(cadastroActivity.this, "Munícipe", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    private class AsyncSend extends AsyncTask<String, String, String> {

        ProgressDialog pdLoading = new ProgressDialog(cadastroActivity.this);
        HttpURLConnection conn;
        URL url = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            //this method will be running on UI thread
            pdLoading.setMessage("\tProcessando...");
            pdLoading.setCancelable(false);
            pdLoading.show();

        }

        @Override
        protected String doInBackground(String... params) {
            try {

                // Enter URL address where your php file resides
                url = new URL(Connection.ENVIA_CADASTRO_URL);

            } catch (MalformedURLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                return "exception";
            }
            try {
                // Setup HttpURLConnection class to send and receive data from php and mysql
                conn = (HttpURLConnection)url.openConnection();
                conn.setReadTimeout(Connection.READ_TIMEOUT);
                conn.setConnectTimeout(Connection.CONNECTION_TIMEOUT);
                conn.setRequestMethod(Connection.METHOD);

                // setDoInput and setDoOutput method depict handling of both send and receive
                conn.setDoInput(true);
                conn.setDoOutput(true);

                // Append parameters to URL
                Uri.Builder builder = new Uri.Builder()
                        .appendQueryParameter(Connection.QUERY_NAME_PARAMETER, params[0]);

                builder.appendQueryParameter(Connection.QUERY_BIRTHDAY_PARAMETER, params[1]);
                builder.appendQueryParameter(Connection.QUERY_EMAIL_PARAMETER, params[2]);
                builder.appendQueryParameter(Connection.QUERY_PASSWORD_PARAMETER, params[3]);
                builder.appendQueryParameter(Connection.QUERY_GENDER_PARAMETER, params[4]);
                builder.appendQueryParameter(Connection.QUERY_PLACE_PARAMETER, params[5]);


                String query = builder.build().getEncodedQuery();

                // Open connection for sending data

                OutputStream os = conn.getOutputStream();

                BufferedWriter writer = new BufferedWriter(
                        new OutputStreamWriter(os, "UTF-8"));

                writer.write(query);
                writer.flush();
                writer.close();
                os.close();
                conn.connect();


            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();

                return "exception";
            }

            try {

                int response_code = conn.getResponseCode();

                // Check if successful connection made
                if (response_code == HttpURLConnection.HTTP_OK) {

                    // Read data sent from server
                    InputStream input = conn.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                    StringBuilder result = new StringBuilder();
                    String line;

                    while ((line = reader.readLine()) != null) {
                        result.append(line);
                    }

                    // Pass data to onPostExecute method
                    return(result.toString());

                }else{
                    return("unsuccessful");
                }

            } catch (IOException e) {
                e.printStackTrace();
                return "exception";
            } finally {
                conn.disconnect();
            }


        }

        @Override
        protected void onPostExecute(String result) {
            pdLoading.dismiss();
            if(result.equalsIgnoreCase("true")) {
                Toast.makeText(cadastroActivity.this, "Cadastrado com sucesso!", Toast.LENGTH_SHORT).show();
                startActivity(intent);

            }else if(result.equalsIgnoreCase("existe")){
                Toast.makeText(cadastroActivity.this, "Você já esta cadastrado. ¯\\_(ツ)_/¯", Toast.LENGTH_LONG).show();
            }
            else if (result.equalsIgnoreCase("exception") || result.equalsIgnoreCase("unsuccessful")) {
                Toast.makeText(cadastroActivity.this, "OOPs! Algo deu errado :(", Toast.LENGTH_LONG).show();
            }
        }

    }

}
