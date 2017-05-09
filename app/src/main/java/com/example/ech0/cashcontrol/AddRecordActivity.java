package com.example.ech0.cashcontrol;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

public class AddRecordActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_record);

        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.categories_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    public void addRecord(View view) {
        DatabaseHandler DbHandler = new DatabaseHandler(this);
        SQLiteDatabase db = DbHandler.getWritableDatabase();

        Spinner spinner = (Spinner)findViewById(R.id.spinner);
        EditText dateText = (EditText)findViewById(R.id.dateText);
        EditText sumText = (EditText)findViewById(R.id.sumText);
        EditText comText = (EditText)findViewById(R.id.comText);

        ContentValues values = new ContentValues();
        values.put(DatabaseContract.RecordsTable.COLUMN_NAME_DATE, Integer.parseInt(dateText.getText().toString()));
        values.put(DatabaseContract.RecordsTable.COLUMN_NAME_SUM, Integer.parseInt(sumText.getText().toString()));
        values.put(DatabaseContract.RecordsTable.COLUMN_NAME_CATEGORY, (int)spinner.getSelectedItemId());
        values.put(DatabaseContract.RecordsTable.COLUMN_NAME_COMMENT, comText.getText().toString());

        db.insert(DatabaseContract.RecordsTable.TABLE_NAME, null, values);
        db.close();
        finish();
    }
}
