package com.example.arinb.treecaching;

/**
 * Created by arinb on 2016-11-08.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.widget.ArrayAdapter;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class SaveTrailsDbBackgroundTask extends AsyncTask<Object, Void, Void> {

//Todo Turn into Singleton

    @Override
    protected Void doInBackground(Object... listTrails) {
        ArrayList<Trail> trails = (ArrayList<Trail>) listTrails[0];

        TrailsDatabaseHelper dbHelper = new TrailsDatabaseHelper(TreeCaching.getAppContext());

        SQLiteDatabase db = dbHelper.getWritableDatabase();


        //dbHelper.onUpgrade(db, 1, 1);
        dbHelper.onCreate(db);
        dbHelper.onConfigure(db);

        ContentValues valuesTrails = new ContentValues();

        for (int x = 0; x < trails.size(); x++) {


            valuesTrails.put(TrailsDatabaseContract.TrailDb.COLUMN_NAME_LOCATION, trails.get(x).getLocation());
            valuesTrails.put(TrailsDatabaseContract.TrailDb.COLUMN_NAME_LATITUDE, trails.get(x).getLatitude());
            valuesTrails.put(TrailsDatabaseContract.TrailDb.COLUMN_NAME_LONGITUDE, trails.get(x).getLongitude());

            long trailRowId = db.insert(TrailsDatabaseContract.TrailDb.TABLE_NAME, null, valuesTrails);

            for (int y = 0; y < trails.get(x).getTrees().size(); y++) {

                ContentValues valuesTree = new ContentValues();

                valuesTree.put(TrailsDatabaseContract.TreeDb.COLUMN_NAME_WEBCODE, trails.get(x).getTrees().get(y).getWebcode());
                valuesTree.put(TrailsDatabaseContract.TreeDb.COLUMN_NAME_LOCATION, trails.get(x).getTrees().get(y).getLocation());
                valuesTree.put(TrailsDatabaseContract.TreeDb.COLUMN_NAME_TREECODE, trails.get(x).getTrees().get(y).getTreeCode());
                valuesTree.put(TrailsDatabaseContract.TreeDb.COLUMN_NAME_COMMON_NAME, trails.get(x).getTrees().get(y).getCommonName());
                valuesTree.put(TrailsDatabaseContract.TreeDb.COLUMN_NAME_SCIENTIFIC_NAME, trails.get(x).getTrees().get(y).getScientificName());
                valuesTree.put(TrailsDatabaseContract.TreeDb.COLUMN_NAME_DBH, trails.get(x).getTrees().get(y).getDbh());
                valuesTree.put(TrailsDatabaseContract.TreeDb.COLUMN_NAME_CW1, trails.get(x).getTrees().get(y).getCw1());
                valuesTree.put(TrailsDatabaseContract.TreeDb.COLUMN_NAME_CW2, trails.get(x).getTrees().get(y).getCw2());
                valuesTree.put(TrailsDatabaseContract.TreeDb.COLUMN_NAME_HEIGHT, trails.get(x).getTrees().get(y).getHeight());
                valuesTree.put(TrailsDatabaseContract.TreeDb.COLUMN_NAME_YEAR, trails.get(x).getTrees().get(y).getYear());
                valuesTree.put(TrailsDatabaseContract.TreeDb.COLUMN_NAME_COMMENT, trails.get(x).getTrees().get(y).getComment());
                valuesTree.put(TrailsDatabaseContract.TreeDb.COLUMN_NAME_UTMN, trails.get(x).getTrees().get(y).getUtme());
                valuesTree.put(TrailsDatabaseContract.TreeDb.COLUMN_NAME_UTME, trails.get(x).getTrees().get(y).getUtmn());
                valuesTree.put(TrailsDatabaseContract.TreeDb.COLUMN_NAME_LATITUDE, trails.get(x).getTrees().get(y).getLatitude());
                valuesTree.put(TrailsDatabaseContract.TreeDb.COLUMN_NAME_LONGITUDE, trails.get(x).getTrees().get(y).getLongitude());
                valuesTree.put(TrailsDatabaseContract.TreeDb.COLUMN_NAME_KGC, trails.get(x).getTrees().get(y).getKgc());
                valuesTree.put(TrailsDatabaseContract.TreeDb.COLUMN_NAME_KGCO2, trails.get(x).getTrees().get(y).getKgco2());
                valuesTree.put(TrailsDatabaseContract.TreeDb.COLUMN_NAME_WEBPAGES, trails.get(x).getTrees().get(y).getWebpages());
                valuesTree.put(TrailsDatabaseContract.TreeDb.COLUMN_NAME_TREE_SURROUNDINGS, trails.get(x).getTrees().get(y).getTreeSurroundings());


                long treeRowId = db.insert(TrailsDatabaseContract.TreeDb.TABLE_NAME, null, valuesTree);

                ContentValues valuesTrailTree = new ContentValues();
                valuesTrailTree.put(TrailsDatabaseContract.TrailTreeDb.COLUMN_NAME_LOCATION, trailRowId);
                valuesTrailTree.put(TrailsDatabaseContract.TrailTreeDb.COLUMN_NAME_TREE, treeRowId);
                long trailTreeRowId = db.insert(TrailsDatabaseContract.TrailTreeDb.TABLE_NAME, null, valuesTrailTree);
            }


        }

        db.close();
        return null;
    }

}