package com.example.fenrir;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.appsearch.SearchResult;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

// 検索結果を返してくれる
public class SearchResultActivity extends AppCompatActivity {

    JSONObject jsonObject;
    JSONArray resultShop;
    int totalHitCount;
    String result, imageUrl_s, storeName, genreName, mobileAccess;
    TextView total;
    ImageButton backSearch;
    private RecyclerView rcvStore;
    private MyAdapter myAdapter;
    List<Store> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);

        myAdapter = new MyAdapter(this);

        total = findViewById(R.id.total);
        result = getIntent().getStringExtra("result");

        rcvStore = findViewById(R.id.rcv_store);
        myAdapter = new MyAdapter(this);


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        rcvStore.setLayoutManager(linearLayoutManager);

        myAdapter.setData(getListStore());
        rcvStore.setAdapter(myAdapter);

        myAdapter.setOnItemClickListener(new MyAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                // アイテムがクリックされた時の処理

                try {
                    JSONObject choiceStoreJson = resultShop.getJSONObject(position);
                    String choiceStore = choiceStoreJson.toString();
                    System.out.println(choiceStore);
                    Intent intent = new Intent(SearchResultActivity.this, DetailActivity.class);
                    intent.putExtra("choiceStore", choiceStore);
                    startActivity(intent);
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        });

        backSearch = findViewById(R.id.backSearch);
        backSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SearchResultActivity.this, SearchRestaurantActivity.class);
                startActivity(intent);
            }
        });
    }

    private List<Store> getListStore() {

        if (result != null) {
            try {
                jsonObject = new JSONObject(result);
                totalHitCount = jsonObject.getJSONObject("results").getInt("results_available");
                total.setText(String.format(Locale.getDefault(), "検索結果：%d件", totalHitCount));

                resultShop = jsonObject.getJSONObject("results").getJSONArray("shop");
                int listLength = totalHitCount;
                for (int i = 0; i < listLength; i++) {
                    JSONObject shop = resultShop.getJSONObject(i);
                    imageUrl_s = shop.getJSONObject("photo").getJSONObject("mobile").getString("s");
                    genreName = shop.getJSONObject("genre").getString("name");
                    storeName = shop.getString("name");
                    mobileAccess = shop.getString("mobile_access");
                    list.add(new Store(imageUrl_s, genreName, storeName, mobileAccess));

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return list;
    }

}