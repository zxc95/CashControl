package com.example.ech0.cashcontrol;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DbHandler extends SQLiteOpenHelper {
    public DbHandler(Context context) {
        super(context, DbContract.DATABASE_NAME, null, DbContract.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DbContract.RecordsTable.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DbContract.RecordsTable.DELETE_TABLE);
        onCreate(db);
    }

    public void addContact(Record record) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DbContract.RecordsTable.COLUMN_NAME_DATE, record.get_date());
        values.put(DbContract.RecordsTable.COLUMN_NAME_SUM, record.get_sum());
        values.put(DbContract.RecordsTable.COLUMN_NAME_CATEGORY, record.get_category());
        values.put(DbContract.RecordsTable.COLUMN_NAME_COMMENT, record.get_comment());

        db.insert(DbContract.RecordsTable.TABLE_NAME, null, values);
        db.close();
    }

    public List<Record> getAllRecords(){
        List<Record> recordsList = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + DbContract.RecordsTable.TABLE_NAME, null);

        while (cursor.moveToNext()) {
            Record record = new Record();

            record.set_id(cursor.getInt(0));
            record.set_date(cursor.getInt(1));
            record.set_sum(cursor.getInt(2));
            record.set_category(cursor.getInt(3));
            record.set_comment(cursor.getString(4));

            recordsList.add(record);
        }

        return recordsList;
    }
}
