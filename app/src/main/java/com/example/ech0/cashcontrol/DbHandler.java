package com.example.ech0.cashcontrol;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

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
    }
}
