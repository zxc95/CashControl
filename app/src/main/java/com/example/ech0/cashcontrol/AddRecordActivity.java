package com.example.ech0.cashcontrol;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.Calendar;

public class AddRecordActivity extends AppCompatActivity {
    public static Calendar c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_record);

        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.categories_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        EditText dateText = (EditText) findViewById(R.id.dateText);
        dateText.setText(day + "." + (month+1) + "." + year);
    }

    public void addRecord(View view) {
        DatabaseHandler DbHandler = new DatabaseHandler(this);
        SQLiteDatabase db = DbHandler.getWritableDatabase();

        Spinner spinner = (Spinner)findViewById(R.id.spinner);
        //EditText dateText = (EditText)findViewById(R.id.dateText);
        EditText sumText = (EditText)findViewById(R.id.sumText);
        EditText comText = (EditText)findViewById(R.id.comText);

        ContentValues values = new ContentValues();
        values.put(DatabaseContract.RecordsTable.COLUMN_NAME_DATE, c.getTimeInMillis());
        values.put(DatabaseContract.RecordsTable.COLUMN_NAME_SUM, Integer.parseInt(sumText.getText().toString()));
        values.put(DatabaseContract.RecordsTable.COLUMN_NAME_CATEGORY, (String)spinner.getSelectedItem());
        values.put(DatabaseContract.RecordsTable.COLUMN_NAME_COMMENT, comText.getText().toString());

        db.insert(DatabaseContract.RecordsTable.TABLE_NAME, null, values);
        db.close();
        finish();
    }

    public void showDatePickerDialog(View v) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }
}
