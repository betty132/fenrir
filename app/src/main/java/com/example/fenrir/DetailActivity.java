package com.example.fenrir;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class DetailActivity extends AppCompatActivity {

    String  imageUrlSmall, imageUrlLarge, catchphrase, storeName, open, genreName, address, mobileAccess;
    ImageButton backList;
    ImageView storePhoto;
    TextView catchPhraseText, storeText, openText, addressText, accessText;
    Button like, dislike;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        DBHelper dbHelper = new DBHelper(getApplicationContext());

        backList = findViewById(R.id.backSearch);
        like = findViewById(R.id.like);
        dislike = findViewById(R.id.dislike);

        storePhoto = findViewById(R.id.storePhoto);
        catchPhraseText = findViewById(R.id.catchPhrase);
        storeText = findViewById(R.id.storeName);
        openText = findViewById(R.id.open);
        addressText = findViewById(R.id.address);
        accessText = findViewById(R.id.access);

        String choiceStore = getIntent().getStringExtra("choiceStore");

        try {
            JSONObject resultShop = new JSONObject(choiceStore);
            imageUrlSmall = resultShop.getJSONObject("photo").getJSONObject("mobile").getString("s");
            imageUrlLarge = resultShop.getJSONObject("photo").getJSONObject("pc").getString("l");
            storeName = resultShop.getString("name");
            genreName = resultShop.getJSONObject("genre").getString("name");
            catchphrase = resultShop.getString("catch");
            open = resultShop.getString("open");
            address = resultShop.getString("address");
            mobileAccess = resultShop.getString("mobile_access");
        } catch (JSONException e) {
            e.printStackTrace();
        }




        Picasso.get().load(imageUrlLarge).into(storePhoto);
        storeText.setText(storeName);
        catchPhraseText.setText(catchphrase);
        openText.setText(open);
        addressText.setText(address);
        accessText.setText(mobileAccess);

        String query = "SELECT * FROM favoriteShops WHERE name = '" + storeName + "'";

        Cursor cursor = dbHelper.rawQuery(query, null);
        if (cursor.getCount() > 0) {
            like.setVisibility(View.GONE);
        } else {
            dislike.setVisibility(View.GONE);
        }


        backList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DetailActivity.this, SearchRestaurantActivity.class);
                startActivity(intent);
            }
        });

        like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Shop shop = new Shop(storeName, genreName, mobileAccess, imageUrlSmall, imageUrlLarge, catchphrase, open, address);

                // 店舗情報をデータベースに保存する

                Cursor cursor = dbHelper.rawQuery(query, null);
                if (cursor.getCount() == 0) {
                    Toast.makeText(getApplicationContext(), "お気に入り登録しました", Toast.LENGTH_SHORT).show();
                    dbHelper.addShop(shop);
                }
            }
        });

        dislike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Cursor cursor = dbHelper.rawQuery(query, null);
                if (cursor.getCount() != 0) {
                    Toast.makeText(getApplicationContext(), "お気に入りを解除しました", Toast.LENGTH_SHORT).show();
                    dbHelper.deleteShop(storeName);
                }

            }
        });


    }
}