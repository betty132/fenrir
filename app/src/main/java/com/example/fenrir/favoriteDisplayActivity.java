package com.example.fenrir;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class favoriteDisplayActivity extends AppCompatActivity {

    private MyAdapter myAdapter;
    private RecyclerView favStore;
    ImageButton backHome;
    List<Store> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite_display);

        myAdapter = new MyAdapter(this);
        favStore = findViewById(R.id.fav_store);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        favStore.setLayoutManager(linearLayoutManager);

        myAdapter.setData(getListStore());
        favStore.setAdapter(myAdapter);

        backHome = findViewById(R.id.backHome);
        backHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(favoriteDisplayActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private List<Store> getListStore() {

        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/my_database", "username", "password");
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM favoriteShops");
            while(rs.next()) {
                String genre = rs.getString("genre");
                String name = rs.getString("name");
                String access = rs.getString("access");
                String imageUrlSmall = rs.getString("image_url_small");

                list.add(new Store(genre, name, access, imageUrlSmall));
            }

            rs.close();
            stmt.close();
            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (list == null) {
            System.out.println("List is null");
        }

        return list;
    }
}