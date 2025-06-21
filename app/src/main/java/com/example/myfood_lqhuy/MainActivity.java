package com.example.myfood_lqhuy;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper_lqhuy dbHelper;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);  // XML layout chính

        // Khởi tạo và mở database
        dbHelper = new DatabaseHelper_lqhuy(this);
        db = dbHelper.getWritableDatabase();

        Toast.makeText(this, "Database myfood_lqhuy đã khởi tạo!", Toast.LENGTH_SHORT).show();

        // Hiển thị danh sách user
        showAllUsers();
    }

    private void showAllUsers() {
        Cursor cursor = db.rawQuery("SELECT * FROM User", null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String username = cursor.getString(1);
                String password = cursor.getString(2);

                Log.d("USER_DATA", "ID: " + id + ", Username: " + username + ", Password: " + password);
            } while (cursor.moveToNext());
        } else {
            Log.d("USER_DATA", "Không có user nào trong database.");
        }

        cursor.close();
    }
}
