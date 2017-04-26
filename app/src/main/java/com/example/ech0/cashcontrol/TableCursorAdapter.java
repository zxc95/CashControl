package com.example.ech0.cashcontrol;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;


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
        TextView tvSum = (TextView) view.findViewById(R.id.tvSum);
        TextView tvCat = (TextView) view.findViewById(R.id.tvCat);
        TextView tvDate =(TextView) view.findViewById(R.id.tvDate);
        TextView tvCom = (TextView) view.findViewById(R.id.tvCom);
        int sum = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseContract.RecordsTable.COLUMN_NAME_SUM));
        int cat = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseContract.RecordsTable.COLUMN_NAME_CATEGORY));
        int date = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseContract.RecordsTable.COLUMN_NAME_DATE));
        String com = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.RecordsTable.COLUMN_NAME_COMMENT));
        tvSum.setText(String.valueOf(sum));
        tvCat.setText(String.valueOf(cat));
        tvDate.setText(String.valueOf(date));
        tvCom.setText(com);
    }
}
