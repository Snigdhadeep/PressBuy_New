package com.techpenta.pressbuy;

/**
 * Created by Diku on 23-03-2017.
 */

import android.content.Context;
import android.os.AsyncTask;
import android.os.Handler;
import android.text.Html;
import android.util.Log;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by Snikdha on 2/7/2017.
 */

public class MyAsynctask extends AsyncTask<String,Void,String> {

    Context context;
    public MyAsynctask(Context ct){

        context=ct;
    }



    @Override
    protected String doInBackground(String... strings) {


        String built=strings[0];

        String response = "";
        for (String url : strings) {
            DefaultHttpClient client = new DefaultHttpClient();
            HttpGet httpGet = new HttpGet(url);
            try {
                HttpResponse execute = client.execute(httpGet);
                InputStream content = execute.getEntity().getContent();

                BufferedReader buffer = new BufferedReader(
                        new InputStreamReader(content));
                String s = "";
                while ((s = buffer.readLine()) != null) {
                    response += s;
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return response;
    }

    @Override
    protected void onPostExecute(String s) {

        Toast.makeText(context, ""+s, Toast.LENGTH_SHORT).show();
        HomeActivity.cartcount.setText(Html.fromHtml(s));


    }
}