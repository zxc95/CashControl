package com.example.ech0.cashcontrol;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


public class TableCursorAdapter extends CursorAdapter {

    public TableCursorAdapter(Context context, Cursor c, int flags) {
        super(context, c, flags);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.item_record, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        //db for cat
        DatabaseHandler DbHandler = new DatabaseHandler(context);
        SQLiteDatabase db = DbHandler.getReadableDatabase();

        //binds
        view.setTag(cursor.getInt(cursor.getColumnIndexOrThrow("_id")));
        TextView tvSum = (TextView) view.findViewById(R.id.tvSum);
        TextView tvCat = (TextView) view.findViewById(R.id.tvCat);
        TextView tvDate = (TextView) view.findViewById(R.id.tvDate);
        TextView tvCom = (TextView) view.findViewById(R.id.tvCom);

        //get data from table
        int sum = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseContract.RecordsTable.COLUMN_NAME_SUM));
        String cat = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.RecordsTable.COLUMN_NAME_CATEGORY));
        long dateLong = cursor.getLong(cursor.getColumnIndexOrThrow(DatabaseContract.RecordsTable.COLUMN_NAME_DATE));
        String com = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.RecordsTable.COLUMN_NAME_COMMENT));

        //format date
        Date date = new Date(dateLong);
        DateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
        String dateFormatted = formatter.format(date);

        //set values
        tvSum.setText(String.valueOf(sum));
        tvCat.setText(String.valueOf(cat));
        tvDate.setText(dateFormatted);
        tvCom.setText(com);
    }
}
