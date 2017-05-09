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
    DatabaseHandler DbHandler;
    Cursor cursor;
    TableCursorAdapter tableAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DbHandler = new DatabaseHandler(this);
        SQLiteDatabase db = DbHandler.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM " + DatabaseContract.RecordsTable.TABLE_NAME, null);

        ListView lvRecords = (ListView) findViewById(R.id.lvRecords);
        tableAdapter = new TableCursorAdapter(this, cursor, 0);
        lvRecords.setAdapter(tableAdapter);
    }

    public void addRecordForm(View view) {
        Intent intent = new Intent(this, AddRecordActivity.class);
        startActivityForResult(intent, 1);
    }

    public void delRecord(View view) {
        SQLiteDatabase db = DbHandler.getWritableDatabase();
        db.delete(DatabaseContract.RecordsTable.TABLE_NAME, "_id =" + view.getTag(), null);
        tableAdapter.changeCursor(db.rawQuery("SELECT * FROM " + DatabaseContract.RecordsTable.TABLE_NAME, null));
        tableAdapter.notifyDataSetChanged();
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        SQLiteDatabase db = DbHandler.getReadableDatabase();
        tableAdapter.changeCursor(db.rawQuery("SELECT * FROM " + DatabaseContract.RecordsTable.TABLE_NAME, null));
        tableAdapter.notifyDataSetChanged();
    }

    protected void onDestroy(){
        super.onDestroy();
        cursor.close();
    }
}
