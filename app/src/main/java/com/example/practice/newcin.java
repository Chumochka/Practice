package com.example.practice;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;

public class newcin extends AppCompatActivity {
    TextView tvInfo;
    EditText tvName;
    EditText tvAddress;
    newcin.MyTask mt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newcin);
        tvInfo = (TextView) findViewById(R.id.tvInfo);
        tvName = (EditText) findViewById(R.id.editTextTextPersonName);
        tvAddress = (EditText) findViewById(R.id.editTextTextPersonAddress);
    }
    public void onClick3(View v) {
        mt = new newcin.MyTask();
        mt.execute(tvName.getText().toString(),tvAddress.getText().toString());
    }
    class MyTask extends AsyncTask<String, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            tvInfo.setText("Выполняем операцию, пожалуйста подождите");
        }
        @Override
        protected Void doInBackground(String... params) {
            HttpURLConnection myConnection=null;
            try {//ip меняется в зависимости ip компьютера-сервера
                URL githubEndpoint = new URL("http://192.168.0.104:8080/bd/");
                myConnection = (HttpURLConnection) githubEndpoint.openConnection();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                myConnection.setRequestMethod("POST");
                myConnection.setDoOutput(true);
            } catch (ProtocolException e) {
                e.printStackTrace();
            }
            try {
                myConnection.getOutputStream().write( ("ID=1&Name=" + params[0]+"&Adress="+params[1]).getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
            int i=0;
            try {
                i = myConnection.getResponseCode();
            } catch (IOException e) {
                e.printStackTrace();
            }
// tvInfo.setText(str);
            if (i==200) {
            }
            return null;
        }
        protected void onPostExecute(Void result){
            super.onPostExecute(result);
            tvInfo.setText("Кинотеатр добавлен");
        }

    }
}
