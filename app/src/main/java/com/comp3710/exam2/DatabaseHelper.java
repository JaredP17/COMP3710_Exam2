package com.comp3710.exam2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "transactions.db";
    public static final String TABLE_NAME = "transaction_table";
    public static final String COL_1 = "Date";
    public static final String COL_2 = "Amount";
    public static final String COL_3 = "Category";
    public static final String COL_4 = "ID";


    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME + "("
                        + COL_1 + " TEXT, "
                        + COL_2 + " REAL, " + COL_3 + " TEXT,"
                        + COL_4 + " INTEGER PRIMARY KEY AUTOINCREMENT)";

        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String date, double amount, String category) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues row = new ContentValues();
        row.put(COL_1, date);
        row.put(COL_2, amount);
        row.put(COL_3, category);

        long result = db.insert(TABLE_NAME, null, row);

        if (result == -1) {
            return false;
        }

        else {
            return true;
        }
    }

    public Cursor getData() {
        SQLiteDatabase db = this.getWritableDatabase();

        return db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
    }
}
