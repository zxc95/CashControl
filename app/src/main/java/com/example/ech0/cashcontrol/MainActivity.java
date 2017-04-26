package com.example.ech0.cashcontrol;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DatabaseHandler DbHandler = new DatabaseHandler(this);
        SQLiteDatabase db = DbHandler.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM " + DatabaseContract.RecordsTable.TABLE_NAME, null);

        ListView lvRecords = (ListView) findViewById(R.id.lvRecords);
        TableCursorAdapter tableAdapter = new TableCursorAdapter(this, cursor, 0);
        lvRecords.setAdapter(tableAdapter);
    }

    public void addRecordForm(View view) {
        Intent intent = new Intent(this, AddRecordActivity.class);
        startActivity(intent);
    }

    protected void onDestroy(){
        super.onDestroy();
        cursor.close();
    }
}
