package com.example.ech0.cashcontrol;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    DatabaseHandler DbHandler;
    Cursor cursor;
    TableCursorAdapter tableAdapter;
    public static Calendar fromDateCalendar, toDateCalendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //db
        DbHandler = new DatabaseHandler(this);
        SQLiteDatabase db = DbHandler.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM " + DatabaseContract.RecordsTable.TABLE_NAME, null);

        //calendars
        fromDateCalendar = Calendar.getInstance();
        toDateCalendar = Calendar.getInstance();
        toDateCalendar.add(Calendar.DATE, 7);
        int fromYear = fromDateCalendar.get(Calendar.YEAR);
        int fromMonth = fromDateCalendar.get(Calendar.MONTH);
        int fromDay = fromDateCalendar.get(Calendar.DAY_OF_MONTH);
        int toYear = toDateCalendar.get(Calendar.YEAR);
        int toMonth = toDateCalendar.get(Calendar.MONTH);
        int toDay = toDateCalendar.get(Calendar.DAY_OF_MONTH);
        EditText fromDate = (EditText) findViewById(R.id.fromDate);
        EditText toDate = (EditText) findViewById(R.id.toDate);
        fromDate.setText(fromDay + "." + (fromMonth+1) + "." + fromYear);
        toDate.setText(toDay + "." + (toMonth+1) + "." + toYear);

        //records table
        ListView lvRecords = (ListView) findViewById(R.id.lvRecords);
        tableAdapter = new TableCursorAdapter(this, cursor, 0);
        lvRecords.setAdapter(tableAdapter);
    }

    public void applyDateFilter(View view) {
        SQLiteDatabase db = DbHandler.getReadableDatabase();
        tableAdapter.changeCursor(db.rawQuery("SELECT * FROM " + DatabaseContract.RecordsTable.TABLE_NAME +
                " WHERE date >= " + fromDateCalendar.getTimeInMillis() + " AND date <= " + toDateCalendar.getTimeInMillis(), null));
        tableAdapter.notifyDataSetChanged();
    }

    public void setFromDate(View v) {
        DatePickerFragment newFragment = new DatePickerFragment();
        newFragment.setCalledByView(v);
        newFragment.setCalendar(fromDateCalendar);
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    public void setToDate(View v) {
        DatePickerFragment newFragment = new DatePickerFragment();
        newFragment.setCalledByView(v);
        newFragment.setCalendar(toDateCalendar);
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    public void addRecordForm(View view) {
        Intent intent = new Intent(this, AddRecordActivity.class);
        startActivityForResult(intent, 1);
    }

    public void delRecord(View view) {
        LinearLayout parent = (LinearLayout) view.getParent();
        SQLiteDatabase db = DbHandler.getWritableDatabase();
        db.delete(DatabaseContract.RecordsTable.TABLE_NAME, "_id =" + parent.getTag(), null);
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
