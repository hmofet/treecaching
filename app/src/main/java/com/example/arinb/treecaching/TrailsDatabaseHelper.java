package com.example.arinb.treecaching;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by arinb on 2016-10-25.
 */

public class TrailsDatabaseHelper extends SQLiteOpenHelper {
    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 3;
    public static final String DATABASE_NAME = "Trails.db";

    public TrailsDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public void onCreate(SQLiteDatabase db) {


        db.execSQL(TrailsDatabaseContract.TrailTreeDb.SQL_DELETE_ENTRIES);
        db.execSQL(TrailsDatabaseContract.TreeDb.SQL_DELETE_ENTRIES);
        db.execSQL(TrailsDatabaseContract.TrailDb.SQL_DELETE_ENTRIES);

        db.execSQL(TrailsDatabaseContract.TrailDb.SQL_CREATE_ENTRIES);
        db.execSQL(TrailsDatabaseContract.TreeDb.SQL_CREATE_ENTRIES);
        db.execSQL(TrailsDatabaseContract.TrailTreeDb.SQL_CREATE_ENTRIES);
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(TrailsDatabaseContract.TrailTreeDb.SQL_DELETE_ENTRIES);
        db.execSQL(TrailsDatabaseContract.TrailDb.SQL_DELETE_ENTRIES);
        db.execSQL(TrailsDatabaseContract.TreeDb.SQL_DELETE_ENTRIES);
        onCreate(db);
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    @Override
    public void onConfigure(SQLiteDatabase db){
        db.setForeignKeyConstraintsEnabled(true);
    }
}
