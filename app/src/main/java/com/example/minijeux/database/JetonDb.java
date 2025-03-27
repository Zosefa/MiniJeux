package com.example.minijeux.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class JetonDb extends SQLiteOpenHelper {
    private static final String DB_NAME = "jetonDB";
    private static final int DB_VERSION = 1;
    private static final String TABLE_JETON = "jeton";
    private static final String COL_ID = "id";
    private static final String COL_JETON = "jeton";

    public JetonDb(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_JETON + "(" +
                COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COL_JETON + " INTEGER" +
                ")";
        db.execSQL(CREATE_TABLE);

        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_JETON, null);
        if (cursor.getCount() == 0) {
            db.execSQL("INSERT INTO " + TABLE_JETON + " (" + COL_JETON + ") VALUES (1000)");
        }
        cursor.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_JETON);
        onCreate(db);
    }

    public int getJetons() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT " + COL_JETON + " FROM " + TABLE_JETON + " WHERE " + COL_ID + " = 1", null);
        if (cursor != null) {
            cursor.moveToFirst();
            int jetons = cursor.getInt(cursor.getColumnIndex(COL_JETON));
            cursor.close();
            return jetons;
        }
        return 0;
    }

    public void reduction(int jetons) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = null;
        try {
            cursor = db.rawQuery("SELECT " + COL_JETON + " FROM " + TABLE_JETON + " WHERE " + COL_ID + " = 1", null);
            if (cursor != null && cursor.moveToFirst()) {
                int jetonsActuel = cursor.getInt(cursor.getColumnIndex(COL_JETON));
                int newJetons;

                if(jetonsActuel != 0){
                    if (jetonsActuel > jetons) {
                        newJetons = jetonsActuel - jetons;
                    } else {
                        newJetons = 0;
                    }


                    updateJetons(newJetons);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }

    public void addition(int jetons) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = null;
        try {
            cursor = db.rawQuery("SELECT " + COL_JETON + " FROM " + TABLE_JETON + " WHERE " + COL_ID + " = 1", null);
            if (cursor != null && cursor.moveToFirst()) {
                int jetonsActuel = cursor.getInt(cursor.getColumnIndex(COL_JETON));
                int newJetons = jetonsActuel + jetons;
                updateJetons(newJetons);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }

    public void updateJetons(int newJetons) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("UPDATE " + TABLE_JETON + " SET " + COL_JETON + " = " + newJetons + " WHERE " + COL_ID + " = 1");
    }
}
