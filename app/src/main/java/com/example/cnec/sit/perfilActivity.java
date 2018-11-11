package com.example.cnec.sit;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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

public class perfilActivity extends AppCompatActivity {

    Intent intent;
    Intent intentExcluido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        intent = new Intent(this, loginActivity.class);
        intentExcluido = new Intent(this, MainActivity.class);
        String login = Settings.Secure.getString(getApplicationContext().getContentResolver(), Settings.Secure.ANDROID_ID);

        new perfilActivity.AsyncSend().execute(login);
    }

    public void alterar (View v) {

        Toast.makeText(perfilActivity.this, "Alterado com sucesso!", Toast.LENGTH_SHORT).show();
    }

    public void excluir (View v) {

        String [] delete = new String[2];
        TextView tv7 = findViewById(R.id.email);

        TextView tv8 = findViewById(R.id.senha);

        delete[0] = tv7.getText().toString();
        delete[1] = tv8.getText().toString();
        new perfilActivity.AsyncSendDelete().execute(delete);
    }

    private class AsyncSend extends AsyncTask<String, String, String> {

        ProgressDialog pdLoading = new ProgressDialog(perfilActivity.this);
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
                url = new URL(Connection.GETLOGIN_URL);

            } catch (MalformedURLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                return "exception";
            }
            try {
                // Setup HttpURLConnection class to send and receive data from php and mysql
                conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(Connection.READ_TIMEOUT);
                conn.setConnectTimeout(Connection.CONNECTION_TIMEOUT);
                conn.setRequestMethod(Connection.METHOD);

                // setDoInput and setDoOutput method depict handling of both send and receive
                conn.setDoInput(true);
                conn.setDoOutput(true);

                // Append parameters to URL
                Uri.Builder builder = new Uri.Builder()
                        .appendQueryParameter(Connection.QUERY_ANDROIDID_PARAMETER, params[0]);

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
            if (result.equalsIgnoreCase("exception") || result.equalsIgnoreCase("unsuccessful")) {
                Toast.makeText(perfilActivity.this, "OOPs! Você não está logado :(", Toast.LENGTH_LONG).show();
                startActivity(intent);
            }else{
                String r = result.replace("]", "");
                r = r.replace("\"", "");
                String[] resultSplit = r.split(",");

                TextView tv = findViewById(R.id.nome);
                tv.setText(resultSplit[1]);

                TextView tv1 = findViewById(R.id.nasc);
                tv1.setText(resultSplit[6]);

                TextView tv8 = findViewById(R.id.email);
                tv8.setText(resultSplit[2]);

                TextView tv7 = findViewById(R.id.senha);
                tv7.setText(resultSplit[3]);
            }
        }

    }

    private class AsyncSendDelete extends AsyncTask<String, String, String> {

        ProgressDialog pdLoading = new ProgressDialog(perfilActivity.this);
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
                url = new URL(Connection.DELETE_URL);

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
                        .appendQueryParameter(Connection.QUERY_EMAIL_PARAMETER, params[0]);

                builder.appendQueryParameter(Connection.QUERY_PASSWORD_PARAMETER, params[1]);

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
                Toast.makeText(perfilActivity.this, "Seu cadastro foi excluido :(", Toast.LENGTH_LONG).show();
                startActivity(intentExcluido);

            }
            else if (result.equalsIgnoreCase("exception") || result.equalsIgnoreCase("unsuccessful")) {
                Toast.makeText(perfilActivity.this, "OOPs! Algo não está certo", Toast.LENGTH_LONG).show();
            }
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
                startActivity(new Intent(perfilActivity.this, projetoActivity.class));
                return true;
            case R.id.item3:
                startActivity(new Intent(perfilActivity.this, contatoActivity.class));
                return true;
            case R.id.item4:
                startActivity(new Intent(perfilActivity.this, feedbackActivity.class));
                return true;
            case R.id.item5:
                startActivity(new Intent(perfilActivity.this, loginActivity.class));
                return true;
            case R.id.item6:
                startActivity(new Intent(perfilActivity.this, perfilActivity.class));
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
