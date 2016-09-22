package com.example.avalanche.herodetails;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends Activity {

    String HTML = "";
    String addr = "http://www.hotslogs.com/Sitewide/HeroDetails?Hero=Chen";
    StringBuilder sb = new StringBuilder();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
            //TextView tv = (TextView) findViewById(R.id.status);
        new GetHTML().execute(addr);
        TextView html = (TextView) findViewById(R.id.html);
        html.setText(HTML);

    }



    public class GetHTML extends AsyncTask<String, Void, Void> {

        TextView tv = (TextView) findViewById(R.id.status);
        String st = "";
        @Override
        protected Void doInBackground(String... params) {

            download(params[0]);

            return null;
        }


        void download(String address) {
            try {
                URL url = new URL(address);

                try {

                    LineNumberReader reader = new LineNumberReader(new InputStreamReader(url.openStream()));
                    String string = reader.readLine();
                    while (string != null) {
                        string = reader.readLine();
                        //System.out.println(string);
                        if (string != null) {
                            sb.append(string);
                        }
                    }
                    HTML = sb.toString();
                    System.out.println("COMMENCING OUTPUT");
                    System.out.println(HTML);
                    System.out.println("---END---");
                    long len = HTML.length();
                    System.out.println("LENGTH");
                    System.out.println(len);
                    reader.close();
                } catch (IOException e) {
                    st = "IO ERR";
                    tv.setText(st);
                    e.printStackTrace();
                }

            } catch (MalformedURLException ex) {
                st = "URL ERR";
                tv.setText(st);
                ex.printStackTrace();
            }
        }
        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            st = "SUCCESS";
            tv.setText(st);
        }
    }



}
