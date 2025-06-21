package com.example.myfood_lqhuy;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.*;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity_lqhuy extends AppCompatActivity {

    RecyclerView recyclerView;
    RestaurantAdapter_lqhuy adapter;
    List<Restaurant> restaurantList;
    DatabaseHelper_lqhuy dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_lqhuy);

        // ✅ Sửa ID đúng theo layout của RecyclerView
        recyclerView = findViewById(R.id.restaurantRecycler_lqhuy);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        dbHelper = new DatabaseHelper_lqhuy(this);
        restaurantList = getAllRestaurants();

        adapter = new RestaurantAdapter_lqhuy(this, restaurantList);
        recyclerView.setAdapter(adapter);
    }

    private List<Restaurant> getAllRestaurants() {
        List<Restaurant> list = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM Restaurant", null);
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String image = cursor.getString(3);
            list.add(new Restaurant(id, name, image));
        }
        cursor.close();
        return list;
    }
}
