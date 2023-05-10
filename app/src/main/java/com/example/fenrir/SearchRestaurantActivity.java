package com.example.fenrir;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import android.Manifest;
import android.app.ProgressDialog;
import android.app.appsearch.SearchResult;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationRequest;
import android.widget.EditText;
import android.widget.Toast;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.*;


//検索条件の入力
public class SearchRestaurantActivity extends AppCompatActivity {

    Button searchButton, button1, button2, button3, button4, button5, button6, button7;
    EditText word;
    private int buttonCheck = 0;
    private boolean sortCheck = false;
    private boolean button1Clicked = false;
    private boolean button2Clicked = false;
    private boolean button3Clicked = false;
    private boolean button4Clicked = false;
    private boolean button5Clicked = false;
    private boolean button6Clicked = false;
    private boolean button7Clicked = false;
    private Double lat, lng;
    private Location location;
    private FusedLocationProviderClient fusedLocationProviderClient;
    private String url = "https://webservice.recruit.co.jp/hotpepper/gourmet/v1/";
    private static final String apiKey = "98bd70f3c98f5d6a";
    int totalHitCount;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_restaurant);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("位置情報を取得しています");
        progressDialog.setCancelable(false);
        progressDialog.show();

        //コンポーネントの取得
        searchButton = findViewById(R.id.searchButton);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);
        button5 = findViewById(R.id.button5);
        button6 = findViewById(R.id.button6);
        button7 = findViewById(R.id.button7);
        word = findViewById(R.id.word);

        //LocationClientクラスとインスタンスを生成
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        //位置情報取得開始
        startUpdateLocation();

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                button1Clicked = true;
                buttonCheck = 1;
                button1.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.green));
                if (button2Clicked) {
                    button2.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
                    button2Clicked = false;
                }
                else if (button3Clicked) {
                    button3.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
                    button3Clicked = false;
                }
                else if (button4Clicked) {
                    button4.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
                    button4Clicked = false;
                }
                else if (button5Clicked) {
                    button5.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
                    button5Clicked = false;
                }
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                button2Clicked = true;
                buttonCheck = 2;
                button2.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.green));
                if (button3Clicked) {
                    button3.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
                    button3Clicked = false;
                }
                else if (button4Clicked) {
                    button4.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
                    button4Clicked = false;
                }
                else if (button5Clicked) {
                    button5.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
                    button5Clicked = false;
                }
                else if (button1Clicked) {
                    button1.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
                    button1Clicked = false;
                }
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                button3Clicked = true;
                buttonCheck = 3;
                button3.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.green));
                if (button4Clicked) {
                    button4.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
                    button4Clicked = false;
                }
                else if (button5Clicked) {
                    button5.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
                    button5Clicked = false;
                }
                else if (button1Clicked) {
                    button1.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
                    button1Clicked = false;
                }
                else if (button2Clicked) {
                    button2.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
                    button2Clicked = false;
                }
            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                button4Clicked = true;
                buttonCheck = 4;
                button4.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.green));
                if (button5Clicked) {
                    button5.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
                    button5Clicked = false;
                }
                else if (button1Clicked) {
                    button1.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
                    button1Clicked = false;
                }
                else if (button2Clicked) {
                    button2.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
                    button2Clicked = false;
                }
                else if (button3Clicked) {
                    button3.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
                    button3Clicked = false;
                }
            }
        });

        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                button5Clicked = true;
                buttonCheck = 5;
                button5.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.green));
                if (button1Clicked) {
                    button1.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
                    button1Clicked = false;
                }
                else if (button2Clicked) {
                    button2.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
                    button2Clicked = false;
                }
                else if (button3Clicked) {
                    button3.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
                    button3Clicked = false;
                }
                else if (button4Clicked) {
                    button4.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
                    button4Clicked = false;
                }
            }
        });

        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sortCheck = false;
                button6Clicked = true;
                button6.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.purple));
                if (button7Clicked) {
                    button7Clicked = false;
                    button7.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
                }
            }
        });

        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sortCheck = true;
                button7Clicked = true;
                button7.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.purple));
                if (button6Clicked) {
                    button6Clicked = false;
                    button6.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
                }
            }
        });


        searchButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                lat = location.getLatitude();
                lng = location.getLongitude();
                stopUpdateLocation();

                String stringRange = String.valueOf(buttonCheck);
                String keyword = word.getText().toString();

                if ((keyword.isEmpty()) && (buttonCheck == 0)) {
                    Toast.makeText(getApplicationContext(), "検索ワードを入力してください", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (!button6Clicked && !button7Clicked) {
                    Toast.makeText(getApplicationContext(), "距離順、もしくはおススメ順を選択してください", Toast.LENGTH_SHORT).show();
                    return;
                }

                try {
                    String encodedKeyword = URLEncoder.encode(keyword, "UTF-8");
                    String urls = url + "?key=" + apiKey + "&range=" + stringRange + "&lat=" + lat + "&lng=" + lng + "&keyword=" + encodedKeyword + "&format=json" + "&count=100";

                    if (sortCheck)
                    {
                        urls = urls + "&order=4";
                    }

                    new DownloadTask().execute(urls);

                    Log.d("TAG", "URL:" + urls);
                    Intent intent = new Intent(SearchRestaurantActivity.this, SearchResultActivity.class);
                    startActivity(intent);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }

            }
        });
    }

    //位置情報取得開始メソッド
    private void startUpdateLocation() {
        //位置情報取得権限の確認
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // 権限がない場合、許可ダイアログ表示
            String[] permissions = {Manifest.permission.ACCESS_FINE_LOCATION};
            ActivityCompat.requestPermissions(this, permissions, 2000);
            return;
        }

        //位置情報の取得方法を設定
        LocationRequest locationRequest = LocationRequest.create();
        locationRequest.setInterval(5000); //位置情報更新間隔の希望
        locationRequest.setFastestInterval(1000); //位置情報更新間隔の最速値
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY); // この位置情報要求の優先度
        fusedLocationProviderClient.requestLocationUpdates(locationRequest,  new MyLocationCallback(), null);
    }

    //位置情報受取コールバッククラス
    private class MyLocationCallback extends LocationCallback {

        @Override
        public void onLocationResult(LocationResult locationResult) {
            if (locationResult == null) {
                return;
            }
            // 現在値を取得
            location = locationResult.getLastLocation();
            if (progressDialog.isShowing()) {
                progressDialog.dismiss();
            }
        };
    }

    public void OnRequestPermissionResult(int requestCode, String[] permissions, int[] grantResults) {
        if(requestCode == 2000 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            // 位置情報取得開始
            startUpdateLocation();
        }
    }

    private void stopUpdateLocation() {
        fusedLocationProviderClient.removeLocationUpdates(new MyLocationCallback());
    }

    private class DownloadTask extends AsyncTask<String, Void, String> {

        protected String doInBackground(String... urls) {

            HttpURLConnection connection = null;
            StringBuilder sb = new StringBuilder();

            try {
                URL url = new URL(urls[0]);
                connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.connect();

                int resCd = connection.getResponseCode();
                if (resCd != HttpURLConnection.HTTP_OK) {
                    throw new IOException("HTTP responseCode:" + resCd);
                }

                InputStream inputStream = connection.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                String line;
                while ((line = reader.readLine()) != null) {
                    sb.append(line);
                }

                inputStream.close();
                reader.close();

            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (connection != null) {
                    connection.disconnect();
                }
            }

            return sb.toString();
        }

        @Override
        protected void onPostExecute(String result) {

            try {
                JSONObject responseJson = new JSONObject(result);
                totalHitCount = responseJson.getJSONObject("results").getInt("results_available");

                if (totalHitCount == 0) {
                    Intent intent = new Intent(SearchRestaurantActivity.this, SearchRestaurantFailureActivity.class);
                    startActivity(intent);
                }

                else {
                    Intent intent = new Intent(SearchRestaurantActivity.this, SearchResultActivity.class);
                    intent.putExtra("result", result);
                    startActivity(intent);
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}