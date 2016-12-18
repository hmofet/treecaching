package com.example.arinb.treecaching;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;

import java.util.ArrayList;

/**
 * Created by arinb on 2016-11-11.
 */

public class ReadTrailDbBackgroundTask extends AsyncTask<Void, Void, ArrayList<Trail>> {
//TODO: Turn into Singleton
    private ArrayList<Trail> trails = new ArrayList<>();

    protected ArrayList<Trail> doInBackground(Void... listTrails) {


        dbToTrail();

        return trails;
    }

    private void dbToTrail(){

        TrailsDatabaseHelper dbHelper = new TrailsDatabaseHelper(TreeCaching.getAppContext());

        SQLiteDatabase db = dbHelper.getReadableDatabase();

// Define a projection that specifies which columns from the database
// you will actually use after this query.
        String[] projection = {
                TrailsDatabaseContract.TrailDb._ID,
                TrailsDatabaseContract.TrailDb.COLUMN_NAME_LOCATION,
                TrailsDatabaseContract.TrailDb.COLUMN_NAME_LATITUDE,
                TrailsDatabaseContract.TrailDb.COLUMN_NAME_LONGITUDE
        };

// Filter results WHERE "title" = 'My Title'
        String selection = "1 = 1";


        Cursor cursor = db.query(
                TrailsDatabaseContract.TrailDb.TABLE_NAME,                     // The table to query
                projection,                               // The columns to return
                selection,                                // The columns for the WHERE clause
                null,                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                null                                 // The sort order
        );

        while(cursor.moveToNext()){
            trails.add(new Trail(cursor.getString(cursor.getColumnIndexOrThrow(TrailsDatabaseContract.TrailDb.COLUMN_NAME_LOCATION)),
                    cursor.getDouble(cursor.getColumnIndexOrThrow(TrailsDatabaseContract.TrailDb.COLUMN_NAME_LATITUDE)),
                    cursor.getDouble(cursor.getColumnIndexOrThrow(TrailsDatabaseContract.TrailDb.COLUMN_NAME_LONGITUDE)),
                    dbToTree(cursor.getLong(cursor.getColumnIndexOrThrow(TrailsDatabaseContract.TrailDb._ID)))));
        }

        db.close();

    }

    private ArrayList<Tree> dbToTree(long id){

        ArrayList<Tree> trees = new ArrayList<>();

        TrailsDatabaseHelper dbHelper = new TrailsDatabaseHelper(TreeCaching.getAppContext());

        SQLiteDatabase db = dbHelper.getReadableDatabase();


        String[] projection1 = {
                TrailsDatabaseContract.TrailTreeDb.COLUMN_NAME_TREE
        };

        String selection = TrailsDatabaseContract.TrailTreeDb.COLUMN_NAME_LOCATION + " = ?";
        String[] selectionArgs = { Long.toString(id) };

        Cursor cursor = db.query(
                TrailsDatabaseContract.TrailTreeDb.TABLE_NAME,                     // The table to query
                projection1,                               // The columns to return
                selection,                                // The columns for the WHERE clause
                selectionArgs,                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                null                                 // The sort order
        );

        while(cursor.moveToNext()) {
            String[] projection2 = {
                    TrailsDatabaseContract.TreeDb._ID,
                    TrailsDatabaseContract.TreeDb.COLUMN_NAME_WEBCODE,
                    TrailsDatabaseContract.TreeDb.COLUMN_NAME_LOCATION,
                    TrailsDatabaseContract.TreeDb.COLUMN_NAME_TREECODE,
                    TrailsDatabaseContract.TreeDb.COLUMN_NAME_COMMON_NAME,
                    TrailsDatabaseContract.TreeDb.COLUMN_NAME_SCIENTIFIC_NAME,
                    TrailsDatabaseContract.TreeDb.COLUMN_NAME_DBH,
                    TrailsDatabaseContract.TreeDb.COLUMN_NAME_CW1,
                    TrailsDatabaseContract.TreeDb.COLUMN_NAME_CW2,
                    TrailsDatabaseContract.TreeDb.COLUMN_NAME_HEIGHT,
                    TrailsDatabaseContract.TreeDb.COLUMN_NAME_YEAR,
                    TrailsDatabaseContract.TreeDb.COLUMN_NAME_COMMENT,
                    TrailsDatabaseContract.TreeDb.COLUMN_NAME_UTME,
                    TrailsDatabaseContract.TreeDb.COLUMN_NAME_UTMN,
                    TrailsDatabaseContract.TreeDb.COLUMN_NAME_LATITUDE,
                    TrailsDatabaseContract.TreeDb.COLUMN_NAME_LONGITUDE,
                    TrailsDatabaseContract.TreeDb.COLUMN_NAME_KGC,
                    TrailsDatabaseContract.TreeDb.COLUMN_NAME_KGCO2,
                    TrailsDatabaseContract.TreeDb.COLUMN_NAME_WEBPAGES,
                    TrailsDatabaseContract.TreeDb.COLUMN_NAME_TREE_SURROUNDINGS
            };

            String selection2 = TrailsDatabaseContract.TreeDb._ID + " = ?";
            String[] selectionArgs2 = { Long.toString(cursor.getLong(cursor.getColumnIndexOrThrow(TrailsDatabaseContract.TrailTreeDb.COLUMN_NAME_TREE))) };


            Cursor cursor2 = db.query(
                    TrailsDatabaseContract.TreeDb.TABLE_NAME,                     // The table to query
                    projection2,                               // The columns to return
                    selection2,                                // The columns for the WHERE clause
                    selectionArgs2,                            // The values for the WHERE clause
                    null,                                     // don't group the rows
                    null,                                     // don't filter by row groups
                    null                                 // The sort order
            );

            while(cursor2.moveToNext()){
                trees.add(new Tree(cursor2.getString(cursor2.getColumnIndexOrThrow(TrailsDatabaseContract.TreeDb.COLUMN_NAME_WEBCODE)),
                        cursor2.getString(cursor2.getColumnIndexOrThrow(TrailsDatabaseContract.TreeDb.COLUMN_NAME_LOCATION)),
                        cursor2.getString(cursor2.getColumnIndexOrThrow(TrailsDatabaseContract.TreeDb.COLUMN_NAME_TREECODE)),
                        cursor2.getString(cursor2.getColumnIndexOrThrow(TrailsDatabaseContract.TreeDb.COLUMN_NAME_COMMON_NAME)),
                        cursor2.getString(cursor2.getColumnIndexOrThrow(TrailsDatabaseContract.TreeDb.COLUMN_NAME_SCIENTIFIC_NAME)),
                        cursor2.getDouble(cursor2.getColumnIndexOrThrow(TrailsDatabaseContract.TreeDb.COLUMN_NAME_DBH)),
                        cursor2.getDouble(cursor2.getColumnIndexOrThrow(TrailsDatabaseContract.TreeDb.COLUMN_NAME_CW1)),
                        cursor2.getDouble(cursor2.getColumnIndexOrThrow(TrailsDatabaseContract.TreeDb.COLUMN_NAME_CW2)),
                        cursor2.getDouble(cursor2.getColumnIndexOrThrow(TrailsDatabaseContract.TreeDb.COLUMN_NAME_LOCATION)),
                        cursor2.getInt(cursor2.getColumnIndexOrThrow(TrailsDatabaseContract.TreeDb.COLUMN_NAME_YEAR)),
                        cursor2.getString(cursor2.getColumnIndexOrThrow(TrailsDatabaseContract.TreeDb.COLUMN_NAME_COMMENT)),
                        cursor2.getDouble(cursor2.getColumnIndexOrThrow(TrailsDatabaseContract.TreeDb.COLUMN_NAME_UTME)),
                        cursor2.getDouble(cursor2.getColumnIndexOrThrow(TrailsDatabaseContract.TreeDb.COLUMN_NAME_UTMN)),
                        cursor2.getDouble(cursor2.getColumnIndexOrThrow(TrailsDatabaseContract.TreeDb.COLUMN_NAME_LATITUDE)),
                        cursor2.getDouble(cursor2.getColumnIndexOrThrow(TrailsDatabaseContract.TreeDb.COLUMN_NAME_LONGITUDE)),
                        cursor2.getDouble(cursor2.getColumnIndexOrThrow(TrailsDatabaseContract.TreeDb.COLUMN_NAME_KGC)),
                        cursor2.getDouble(cursor2.getColumnIndexOrThrow(TrailsDatabaseContract.TreeDb.COLUMN_NAME_KGCO2)),
                        cursor2.getString(cursor2.getColumnIndexOrThrow(TrailsDatabaseContract.TreeDb.COLUMN_NAME_WEBPAGES)),
                        cursor2.getString(cursor2.getColumnIndexOrThrow(TrailsDatabaseContract.TreeDb.COLUMN_NAME_TREE_SURROUNDINGS))
                ));
            }

        }
// Define a projection that specifies which columns from the database
// you will actually use after this query.

        db.close();
        return trees;

    }

}

