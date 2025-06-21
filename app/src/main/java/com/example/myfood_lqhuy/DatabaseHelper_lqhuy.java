package com.example.myfood_lqhuy; // Đảm bảo giống package của bạn

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper_lqhuy extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Food_lqhuy.db";
    public static final int DATABASE_VERSION = 1;

    public DatabaseHelper_lqhuy(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Tạo bảng User
        db.execSQL("CREATE TABLE User (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "username TEXT, " +
                "password TEXT)");

        // Tạo bảng Restaurant
        db.execSQL("CREATE TABLE Restaurant (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "name TEXT, " +
                "address TEXT, " +
                "image TEXT)");

        // Tạo bảng Food
        db.execSQL("CREATE TABLE Food (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "name TEXT, " +
                "price REAL, " +
                "size TEXT, " +
                "description TEXT, " +
                "restaurant_id INTEGER, " +
                "image TEXT, " +
                "FOREIGN KEY(restaurant_id) REFERENCES Restaurant(id))");

        // Chèn dữ liệu mẫu
        insertSampleData(db);
    }

    private void insertSampleData(SQLiteDatabase db) {
        // Người dùng mẫu
        db.execSQL("INSERT INTO User (username, password) VALUES " +
                "('alice', '123'), ('bob', 'abc'), ('charlie', 'pass'), ('david', '1111'), ('eva', 'qwerty')");

        // Nhà hàng Việt Nam
        db.execSQL("INSERT INTO Restaurant (name, address, image) VALUES " +
                "('Phở 24', '123 Lê Lợi, Q1', 'pho24.jpg')," +
                "('Bún Chả Hà Nội', '456 Hai Bà Trưng, Q3', 'bunchahanoi.jpg')," +
                "('Cơm Tấm Cali', '789 Nguyễn Trãi, Q5', 'comtamcali.jpg')," +
                "('Bánh Mì Ông Tý', '321 Điện Biên Phủ, Q10', 'banhmiot.jpg')," +
                "('Gỏi Cuốn Thanh Bình', '654 Nguyễn Thị Minh Khai, Q1', 'goicuon.jpg')");

        // Món ăn Việt
        db.execSQL("INSERT INTO Food (name, price, size, description, restaurant_id, image) VALUES " +
                "('Phở Bò Tái', 40000, 'Vừa', 'Phở bò truyền thống với thịt tái mềm', 1, 'pho_bo.jpg')," +
                "('Phở Gà Xé', 38000, 'Vừa', 'Phở gà xé phay thơm ngon', 1, 'pho_ga.jpg')," +
                "('Bún Chả Hà Nội', 45000, 'Phần', 'Bún chả thịt nướng kèm nước mắm', 2, 'bun_cha.jpg')," +
                "('Nem Rán', 30000, '6 cái', 'Chả giò chiên giòn Hà Nội', 2, 'nem_ran.jpg')," +
                "('Cơm Tấm Sườn Bì', 50000, 'Dĩa', 'Cơm tấm sườn nướng và bì trứng', 3, 'comtam_suon.jpg')," +
                "('Cơm Tấm Trứng Ốp', 35000, 'Dĩa', 'Cơm tấm với trứng ốp la', 3, 'comtam_trung.jpg')," +
                "('Bánh Mì Thịt Nướng', 25000, 'Ổ', 'Bánh mì với thịt nướng và rau thơm', 4, 'banhmi_thitnuong.jpg')," +
                "('Bánh Mì Chả Lụa', 22000, 'Ổ', 'Bánh mì kẹp chả lụa truyền thống', 4, 'banhmi_chalua.jpg')," +
                "('Gỏi Cuốn Tôm Thịt', 28000, '2 cuốn', 'Gỏi cuốn tươi với tôm thịt và rau sống', 5, 'goicuon.jpg')," +
                "('Chè Ba Màu', 18000, 'Ly', 'Chè đậu xanh, đậu đỏ, nước cốt dừa', 5, 'che_ba_mau.jpg')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Food");
        db.execSQL("DROP TABLE IF EXISTS Restaurant");
        db.execSQL("DROP TABLE IF EXISTS User");
        onCreate(db);
    }
}
