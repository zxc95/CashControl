package com.example.ech0.cashcontrol;

import android.provider.BaseColumns;

public final class DbContract {
    public static final  int    DATABASE_VERSION   = 1;
    public static final  String DATABASE_NAME      = "records.db";

    private DbContract() {}

    public static class RecordsTable implements BaseColumns {
        public static final String TABLE_NAME = "records";
        public static final String COLUMN_NAME_ID = "id";
        public static final String COLUMN_NAME_DATE = "date";
        public static final String COLUMN_NAME_SUM = "sum";
        public static final String COLUMN_NAME_CATEGORY = "category";
        public static final String COLUMN_NAME_COMMENT = "comment";
    }
}
