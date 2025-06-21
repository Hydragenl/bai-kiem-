package com.example.myfood_lqhuy;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.*;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity_lqhuy extends AppCompatActivity {

    EditText edtUsername_lqhuy, edtPassword_lqhuy;
    Button btnLogin_lqhuy;
    TextView tvRegister_lqhuy;

    DatabaseHelper_lqhuy dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_lqhuy);

        edtUsername_lqhuy = findViewById(R.id.edtUsername_lqhuy);
        edtPassword_lqhuy = findViewById(R.id.edtPassword_lqhuy);
        btnLogin_lqhuy = findViewById(R.id.btnLogin_lqhuy);
        tvRegister_lqhuy = findViewById(R.id.tvRegister_lqhuy);

        dbHelper = new DatabaseHelper_lqhuy(this);

        btnLogin_lqhuy.setOnClickListener(v -> {
            String user = edtUsername_lqhuy.getText().toString().trim();
            String pass = edtPassword_lqhuy.getText().toString().trim();

            if (user.isEmpty() || pass.isEmpty()) {
                Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                return;
            }

            SQLiteDatabase db = dbHelper.getReadableDatabase();
            Cursor cursor = db.rawQuery("SELECT * FROM User WHERE username=? AND password=?", new String[]{user, pass});

            if (cursor.moveToFirst()) {
                Toast.makeText(this, "Đăng nhập thành công!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(LoginActivity_lqhuy.this, HomeActivity_lqhuy.class);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(this, "Sai tài khoản hoặc mật khẩu", Toast.LENGTH_SHORT).show();
            }

            cursor.close();
        });

        tvRegister_lqhuy.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity_lqhuy.this, RegisterActivity_lqhuy.class);
            startActivity(intent);
        });
    }
}
