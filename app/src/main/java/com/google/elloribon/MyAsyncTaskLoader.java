package com.google.elloribon;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Roman on 07.10.2015.
 */
public abstract class MyAsyncTaskLoader extends AsyncTask<Void, Void, String> {

    HttpURLConnection urlConnection = null;
    BufferedReader reader = null;
    ProgressDialog progressDialog;
    String urlLink, line, messageProgressDialog, resultJson = "";
    Context context;

    public MyAsyncTaskLoader(Context context, String urlLink, ProgressDialog progressDialog, String messageProgressDialog) {

        this.progressDialog = progressDialog;
        this.urlLink = urlLink;
        this.context = context;
        this.messageProgressDialog = messageProgressDialog;

    }

    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage(messageProgressDialog);
        progressDialog.setIndeterminate(false);
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

    @Override
    protected String doInBackground(Void... params) {


        try {
            URL url = new URL(urlLink);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            InputStream inputStream = urlConnection.getInputStream();

            StringBuffer buffer = new StringBuffer();
            reader = new BufferedReader(new InputStreamReader(inputStream));

            while ((line = reader.readLine()) != null) {
                buffer.append(line);
            }

            resultJson = buffer.toString();

        } catch (IOException e) {

            e.printStackTrace();

            return context.getResources().getString(R.string.errorReturn);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultJson;
    }

    protected abstract void onPostExecute(String strJson);
}