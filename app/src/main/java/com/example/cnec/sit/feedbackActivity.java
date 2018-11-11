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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class feedbackActivity extends AppCompatActivity {

    private String[] categoria = new String[]{"Ônibus",
            "Paradas de Ônibus ",
            "Atendimento ",
            "Outros "};

    private String[] categoria2 = new String[]{"Elogio ",
            "Crítica ",
            "Sugestão ",
            "Outros "};

    private Spinner sp3;
    private Spinner sp4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        //SPINNER 1

        ArrayAdapter<String> adapter3 =  new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, categoria);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        sp3 = (Spinner) (findViewById(R.id.sAssunto));
        sp3.setAdapter(adapter3);

        sp3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        //SPINNER 2

        ArrayAdapter<String> adapter4 =  new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, categoria2);
        adapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        sp4 = (Spinner) (findViewById(R.id.sCategoria));
        sp4.setAdapter(adapter4);

        sp4.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }


    public void enviarFeedback (View v) {
        EditText campoFeedback = (EditText) findViewById(R.id.campoFeedback);


        String [] envia = new String[4];
        envia[0] = (String) sp3.getSelectedItem();
        envia[1] = (String) sp4.getSelectedItem();
        envia[2] = campoFeedback.getText().toString();
        envia[3] = new SimpleDateFormat("yyyy/MM/dd", Locale.getDefault()).format(new Date());

        new feedbackActivity.AsyncSend().execute(envia);

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
                startActivity(new Intent(feedbackActivity.this, projetoActivity.class));
                return true;
            case R.id.item3:
                startActivity(new Intent(feedbackActivity.this, contatoActivity.class));
                return true;
            case R.id.item4:
                startActivity(new Intent(feedbackActivity.this, feedbackActivity.class));
                return true;
            case R.id.item5:
                startActivity(new Intent(feedbackActivity.this, loginActivity.class));
                return true;
            case R.id.item6:
                startActivity(new Intent(feedbackActivity.this, perfilActivity.class));
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

        ProgressDialog pdLoading = new ProgressDialog(feedbackActivity.this);
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
                url = new URL(Connection.FEEDBACK_URL);

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
                        .appendQueryParameter(Connection.QUERY_ASSUNTO_PARAMETER, params[0]);

                builder.appendQueryParameter(Connection.QUERY_CATEGORIA_PARAMETER, params[1]);
                builder.appendQueryParameter(Connection.QUERY_DESCRICAO_PARAMETER, params[2]);
                builder.appendQueryParameter(Connection.QUERY_DATA_PARAMETER, params[3]);

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
                Toast.makeText(feedbackActivity.this, "Feedback enviado ¯\\_(ツ)_/¯", Toast.LENGTH_LONG).show();

            }
            else if (result.equalsIgnoreCase("exception") || result.equalsIgnoreCase("unsuccessful")) {
                Toast.makeText(feedbackActivity.this, "OOPs! Algo não está certo", Toast.LENGTH_LONG).show();
            }
        }

    }
}
