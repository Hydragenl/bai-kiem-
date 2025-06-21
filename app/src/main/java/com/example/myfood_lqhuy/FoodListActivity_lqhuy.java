package com.example.myfood_lqhuy;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.*;

import java.util.ArrayList;
import java.util.List;

public class FoodListActivity_lqhuy extends AppCompatActivity {

    RecyclerView recyclerView;
    List<Food> foodList;
    FoodAdapter_lqhuy adapter;
    DatabaseHelper_lqhuy dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_list_lqhuy);

        int restaurantId = getIntent().getIntExtra("restaurant_id", -1);

        recyclerView = findViewById(R.id.foodRecycler_lqhuy);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        dbHelper = new DatabaseHelper_lqhuy(this);
        foodList = getFoodByRestaurant(restaurantId);

        adapter = new FoodAdapter_lqhuy(this, foodList);
        recyclerView.setAdapter(adapter);
    }

    private List<Food> getFoodByRestaurant(int restaurantId) {
        List<Food> list = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM Food WHERE restaurant_id = ?", new String[]{String.valueOf(restaurantId)});

        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            double price = cursor.getDouble(2);
            String size = cursor.getString(3);
            String desc = cursor.getString(4);
            String image = cursor.getString(6);

            list.add(new Food(id, name, price, image, desc));
        }

        cursor.close();
        return list;
    }
}
