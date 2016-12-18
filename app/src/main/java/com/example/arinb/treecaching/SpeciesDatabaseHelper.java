package com.example.arinb.treecaching;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by arinb on 2016-10-25.
 */

public class SpeciesDatabaseHelper extends SQLiteOpenHelper {
    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 3;
    public static final String DATABASE_NAME = "Species.db";

    public SpeciesDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(TrailsDatabaseContract.SpeciesReferenceDb.SQL_DELETE_ENTRIES);
        db.execSQL(TrailsDatabaseContract.FactsDb.SQL_DELETE_ENTRIES);
        db.execSQL(TrailsDatabaseContract.SpeciesImageDescriptionDb.SQL_DELETE_ENTRIES);
        db.execSQL(TrailsDatabaseContract.SpeciesImageDb.SQL_DELETE_ENTRIES);
        db.execSQL(TrailsDatabaseContract.ReferenceDb.SQL_DELETE_ENTRIES);
        db.execSQL(TrailsDatabaseContract.SpeciesDb.SQL_DELETE_ENTRIES);

        db.execSQL(TrailsDatabaseContract.SpeciesDb.SQL_CREATE_ENTRIES);
        db.execSQL(TrailsDatabaseContract.ReferenceDb.SQL_CREATE_ENTRIES);
        db.execSQL(TrailsDatabaseContract.SpeciesImageDb.SQL_CREATE_ENTRIES);
        db.execSQL(TrailsDatabaseContract.SpeciesImageDescriptionDb.SQL_CREATE_ENTRIES);
        db.execSQL(TrailsDatabaseContract.FactsDb.SQL_CREATE_ENTRIES);
        db.execSQL(TrailsDatabaseContract.SpeciesReferenceDb.SQL_CREATE_ENTRIES);
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(TrailsDatabaseContract.SpeciesReferenceDb.SQL_DELETE_ENTRIES);
        db.execSQL(TrailsDatabaseContract.FactsDb.SQL_DELETE_ENTRIES);
        db.execSQL(TrailsDatabaseContract.SpeciesImageDescriptionDb.SQL_DELETE_ENTRIES);
        db.execSQL(TrailsDatabaseContract.SpeciesImageDb.SQL_DELETE_ENTRIES);
        db.execSQL(TrailsDatabaseContract.ReferenceDb.SQL_DELETE_ENTRIES);
        db.execSQL(TrailsDatabaseContract.SpeciesDb.SQL_DELETE_ENTRIES);
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
