package com.example.ech0.cashcontrol;

import android.content.ContentValues;
import android.provider.BaseColumns;

public final class DatabaseContract {
    public static final  int    DATABASE_VERSION   = 1;
    public static final  String DATABASE_NAME      = "records.db";
    private static final String TEXT_TYPE          = " TEXT";
    private static final String INTEGER_TYPE       = " INTEGER";
    private static final String COMMA_SEP          = ",";

    private DatabaseContract() {}

    public static class RecordsTable implements BaseColumns {
        public static final String TABLE_NAME = "records";
        public static final String COLUMN_NAME_DATE = "date";
        public static final String COLUMN_NAME_SUM = "sum";
        public static final String COLUMN_NAME_CATEGORY = "category";
        public static final String COLUMN_NAME_COMMENT = "comment";

        public static final String CREATE_TABLE = "CREATE TABLE " +
                TABLE_NAME + " (" +
                _ID + INTEGER_TYPE + " PRIMARY KEY AUTOINCREMENT" + COMMA_SEP +
                COLUMN_NAME_DATE + INTEGER_TYPE + COMMA_SEP +
                COLUMN_NAME_SUM + INTEGER_TYPE + COMMA_SEP +
                COLUMN_NAME_CATEGORY + TEXT_TYPE + COMMA_SEP +
                COLUMN_NAME_COMMENT + TEXT_TYPE + ")";
        public static final String DELETE_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;
    }
}
