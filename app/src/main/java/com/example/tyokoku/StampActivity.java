package com.example.tyokoku;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


public class StampActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stamp);

        /*ホーム画面に戻る*/
        Button returnButton = findViewById(R.id.returnbutton);
        returnButton.setOnClickListener(v -> finish());




        //db
        MyDbHelper mDbHelper = new MyDbHelper(this);
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        //select
        Cursor c = db.rawQuery("select * from members",null);

        //adapterの準備
        //表示するカラム名
        String[] from = {"name","_id"};
        //バインドするViewリソース
        int[] to = {android.R.id.text1,android.R.id.text2};

        //adapter生成
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this,android.R.layout.simple_list_item_2,c,from,to,0);

        //bindして表示
        myListView.setAdapter(adapter);
        //クリックしたとき各行のデータ（特に_id）を取得
        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                //各要素を取得
                String s1 = ((TextView)view.findViewById(android.R.id.text1)).getText().toString();
                String s2 = ((TextView)view.findViewById(android.R.id.text2)).getText().toString();

                Log.v("tama","position=" + s1);
                Log.v("tama","position=" + s2);
            }
        });

        //loop(いらない）
        while(c.moveToNext()){
            Log.v("tama", c.getString(c.getColumnIndex("name")));
        }

    }
}
