package com.example.weather;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "database.db";

    public static final int DB_VERSION = 1;

    public static final String TABLE_USER= "user";

    private static final String USER_CREATE_TABLE_SQL = "create table " + TABLE_USER + "("
            + "id integer primary key autoincrement,"
            + "username varchar(50) not null,"
            + "password varchar(50) not null,"
            + "name varchar(100) not null,"
            + "phone varchar(30) not null"
            + ");";

    public DatabaseHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(USER_CREATE_TABLE_SQL);
        ContentValues values = new ContentValues();
        values.put("id",1);
        values.put("username","admin");
        values.put("password","123456");
        values.put("name","罗伟斌");
        values.put("phone", "17376595431");
        db.insert("user", null, values);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}