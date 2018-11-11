package com.example.cnec.sit;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
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

            String cadastra [] = new String [3];
            cadastra[0] = rua.getText().toString();
            cadastra[1] = bairro.getText().toString();
            cadastra[2] = info.getText().toString();

            new Main6Activity.AsyncSend().execute(cadastra);

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


    private class AsyncSend extends AsyncTask<String, String, String> {

        ProgressDialog pdLoading = new ProgressDialog(Main6Activity.this);
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
                url = new URL(Connection.PARADAS_URL);

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
                        .appendQueryParameter(Connection.QUERY_RUA_PARAMETER, params[0]);

                builder.appendQueryParameter(Connection.QUERY_BAIRRO_PARAMETER, params[1]);
                builder.appendQueryParameter(Connection.QUERY_DESCRICAO_PARAMETER, params[2]);

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
            if(result.equalsIgnoreCase("true")){
                Toast.makeText(Main6Activity.this, "Parada cadastrada com sucesso. ¯\\_(ツ)_/¯", Toast.LENGTH_LONG).show();
                EditText rua = findViewById(R.id.editText);
                EditText bairro = findViewById(R.id.editText2);
                EditText info = findViewById(R.id.editText3);
                rua.setText("");
                bairro.setText("");
                info.setText("");
            }
            else if (result.equalsIgnoreCase("exception") || result.equalsIgnoreCase("unsuccessful")) {
                Toast.makeText(Main6Activity.this, "OOPs! Não foi possível cadastrar sua parada", Toast.LENGTH_LONG).show();
            }
        }

    }

}

