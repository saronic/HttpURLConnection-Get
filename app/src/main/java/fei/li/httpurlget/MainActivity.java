package fei.li.httpurlget;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button sendGetBtn = (Button) findViewById(R.id.send_get_btn);
        sendGetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(
                        new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    URL url = new URL("http://192.168.31.139:3000");
                                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                                    connection.setRequestMethod("GET");
                                    connection.connect();
                                    int responseCode = connection.getResponseCode();
                                    Log.d(TAG, "responseCode: " + responseCode);
                                    BufferedReader reader = null;
                                    reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                                    String line = null;
                                    StringBuffer sb = new StringBuffer();
                                    while ((line = reader.readLine()) != null) {
                                        sb.append(line);
                                    }
                                    Log.d(TAG, "run: response : \n" + sb.toString());
                                } catch (MalformedURLException e) {
                                    e.printStackTrace();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }

                            }
                        }
                ).start();
            }
        });
    }
}
