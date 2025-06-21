package com.example.myfood_lqhuy;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.*;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity_lqhuy extends AppCompatActivity {

    EditText edtUsername_lqhuy, edtPassword_lqhuy, edtRePassword_lqhuy;
    Button btnRegister_lqhuy;

    DatabaseHelper_lqhuy dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_lqhuy);

        edtUsername_lqhuy = findViewById(R.id.edtUsername_lqhuy);
        edtPassword_lqhuy = findViewById(R.id.edtPassword_lqhuy);
        edtRePassword_lqhuy = findViewById(R.id.edtRePassword_lqhuy);
        btnRegister_lqhuy = findViewById(R.id.btnRegister_lqhuy);

        dbHelper = new DatabaseHelper_lqhuy(this);

        btnRegister_lqhuy.setOnClickListener(v -> {
            String user = edtUsername_lqhuy.getText().toString().trim();
            String pass = edtPassword_lqhuy.getText().toString().trim();
            String rePass = edtRePassword_lqhuy.getText().toString().trim();

            if (user.isEmpty() || pass.isEmpty() || rePass.isEmpty()) {
                Toast.makeText(this, "Vui lòng nhập đầy đủ", Toast.LENGTH_SHORT).show();
                return;
            }

            if (!pass.equals(rePass)) {
                Toast.makeText(this, "Mật khẩu không khớp", Toast.LENGTH_SHORT).show();
                return;
            }

            // Kiểm tra username đã tồn tại chưa
            SQLiteDatabase db = dbHelper.getReadableDatabase();
            Cursor cursor = db.rawQuery("SELECT * FROM User WHERE username = ?", new String[]{user});
            if (cursor.moveToFirst()) {
                Toast.makeText(this, "Tên đăng nhập đã tồn tại", Toast.LENGTH_SHORT).show();
                cursor.close();
                return;
            }
            cursor.close();

            // Thêm vào bảng User
            SQLiteDatabase writableDB = dbHelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("username", user);
            values.put("password", pass);
            long result = writableDB.insert("User", null, values);

            if (result != -1) {
                Toast.makeText(this, "Đăng ký thành công!", Toast.LENGTH_SHORT).show();
                // Quay lại LoginActivity
                Intent intent = new Intent(this, LoginActivity_lqhuy.class);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(this, "Đăng ký thất bại", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
