package com.example.myfood_lqhuy;

import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.*;

import androidx.appcompat.app.AppCompatActivity;

public class FoodDetailActivity_lqhuy extends AppCompatActivity {

    ImageView imgFoodDetail;
    TextView tvName, tvPrice, tvDesc, tvAddress;
    RadioGroup radioSize;
    RadioButton rbSmall, rbMedium, rbLarge;
    Button btnAddToCart;

    DatabaseHelper_lqhuy dbHelper;

    double basePrice = 0;
    int foodId;
    String foodName = "", foodDesc = "", foodImage = "", foodAddress = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_detail_lqhuy);

        imgFoodDetail = findViewById(R.id.imgFoodDetail);
        tvName = findViewById(R.id.tvFoodNameDetail);
        tvPrice = findViewById(R.id.tvFoodPriceDetail);
        tvDesc = findViewById(R.id.tvFoodDescDetail);
        tvAddress = findViewById(R.id.tvFoodAddress);
        radioSize = findViewById(R.id.radioSize);
        rbSmall = findViewById(R.id.rbSmall);
        rbMedium = findViewById(R.id.rbMedium);
        rbLarge = findViewById(R.id.rbLarge);
        btnAddToCart = findViewById(R.id.btnAddToCart);

        dbHelper = new DatabaseHelper_lqhuy(this);
        foodId = getIntent().getIntExtra("food_id", -1);

        if (foodId != -1) loadFoodDetail(foodId);

        // Tính lại giá khi chọn size
        radioSize.setOnCheckedChangeListener((group, checkedId) -> updatePrice());

        btnAddToCart.setOnClickListener(v -> {
            String selectedSize = "Nhỏ";
            double finalPrice = basePrice;

            if (rbMedium.isChecked()) {
                finalPrice *= 1.2;
                selectedSize = "Vừa";
            } else if (rbLarge.isChecked()) {
                finalPrice *= 1.5;
                selectedSize = "Lớn";
            }

            // Lưu vào SharedPreferences hoặc danh sách tạm
            Toast.makeText(this, "Đã thêm: " + foodName + " - " + selectedSize + " (" + (int)finalPrice + " VND)", Toast.LENGTH_SHORT).show();
        });
    }

    private void loadFoodDetail(int id) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT Food.name, price, description, image, address FROM Food JOIN Restaurant ON Food.restaurant_id = Restaurant.id WHERE Food.id = ?", new String[]{String.valueOf(id)});

        if (cursor.moveToFirst()) {
            foodName = cursor.getString(0);
            basePrice = cursor.getDouble(1);
            foodDesc = cursor.getString(2);
            foodImage = cursor.getString(3);
            foodAddress = cursor.getString(4);

            tvName.setText(foodName);
            tvDesc.setText(foodDesc);
            tvAddress.setText("Địa chỉ: " + foodAddress);
            updatePrice();
            imgFoodDetail.setImageResource(R.drawable.ic_launcher_background);
        }

        cursor.close();
    }

    private void updatePrice() {
        double finalPrice = basePrice;

        if (rbMedium.isChecked()) finalPrice *= 1.2;
        else if (rbLarge.isChecked()) finalPrice *= 1.5;

        tvPrice.setText(String.format("Giá: %.0f VND", finalPrice));
    }
}
