package com.example.tyokoku;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

class MyDbHelper extends SQLiteOpenHelper {

    public MyDbHelper(Context context){
        super(context,"sculputure.db",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        //create
        sqLiteDatabase.execSQL("create table members(_id integer primary key autoincrement,name text,age integer)");

        //init
        sqLiteDatabase.execSQL("insert into members(name,age) values('hoge',23)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i2) {

        //create
        onCreate(sqLiteDatabase);

    }
}