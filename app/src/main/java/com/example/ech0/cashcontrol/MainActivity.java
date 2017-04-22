package com.example.ech0.cashcontrol;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<Record> recordsList = new ArrayList<>();
        DatabaseHandler DbHandler = new DatabaseHandler(this);
        SQLiteDatabase db = DbHandler.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + DatabaseContract.RecordsTable.TABLE_NAME, null);

        while (cursor.moveToNext()) {
            Record record = new Record();

            record.set_id(cursor.getInt(0));
            record.set_date(cursor.getInt(1));
            record.set_sum(cursor.getInt(2));
            record.set_category(cursor.getInt(3));
            record.set_comment(cursor.getString(4));

            recordsList.add(record);
        }
        cursor.close();

    }

    public void addRecordForm(View view) {
        Intent intent = new Intent(this, AddRecordActivity.class);
        startActivity(intent);
    }
}
