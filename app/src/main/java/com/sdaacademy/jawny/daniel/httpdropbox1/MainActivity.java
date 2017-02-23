package com.sdaacademy.jawny.daniel.httpdropbox1;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.io.IOException;
import java.net.URL;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    public static final String DROP_BOX = "DropBox";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new dropBoxCommunicationTask().execute();
    }

    public class dropBoxCommunicationTask extends AsyncTask<URL, Integer, String> {

        @Override
        protected String doInBackground(URL... params) {
            String response = "null";

            try {
                response = sentPost();
            } catch (IOException e) {
                Log.i(DROP_BOX, "Bląd komunikacji dropBox");
            }

            return response;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Log.i(DROP_BOX, s);
        }

        private String sentPost() throws IOException {
            MediaType jsonMediaType = MediaType.parse("application/json; charset=utf-8");
            OkHttpClient client = new OkHttpClient();
            String url = "https://api.dropboxapi.com/2/users/get_current_account";
            String json = "null";

            RequestBody body = RequestBody.create(jsonMediaType, json);

            Request request = new Request.Builder()
                    .addHeader("Authorization", "Bearer 3TS3KjVdr6AAAAAAAAAAEsa1_XIUjU9uGjQFCeEDejQ_wbmISUFSvuC8uW-rL7Mx")
                    .addHeader("Content-Type", "application/json")
                    .url(url)
                    .post(body)
                    .build();
            Response response = client.newCall(request).execute();
            return response.body().string();
        }
    }
}
