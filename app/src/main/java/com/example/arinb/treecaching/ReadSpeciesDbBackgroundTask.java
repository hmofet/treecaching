package com.example.arinb.treecaching;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;

import java.util.ArrayList;

/**
 * Created by arinb on 2016-11-11.
 */

public class ReadSpeciesDbBackgroundTask extends AsyncTask<Void, Void, ArrayList<Species>> {
//TODO: Turn into Singleton
    private ArrayList<Species> species = new ArrayList<>();

    protected ArrayList<Species> doInBackground(Void... listSpecies) {

        dbToSpecies();

        return species;

    }

    private void dbToSpecies() {

        SpeciesDatabaseHelper dbHelper = new SpeciesDatabaseHelper(TreeCaching.getAppContext());

        SQLiteDatabase db = dbHelper.getReadableDatabase();

// Define a projection that specifies which columns from the database
// you will actually use after this query.
        String[] projection = {
                TrailsDatabaseContract.SpeciesDb._ID,
                TrailsDatabaseContract.SpeciesDb.COLUMN_NAME_BARK,
                TrailsDatabaseContract.SpeciesDb.COLUMN_NAME_COMMON_NAME,
                TrailsDatabaseContract.SpeciesDb.COLUMN_NAME_FLOWER,
                TrailsDatabaseContract.SpeciesDb.COLUMN_NAME_FRUIT,
                TrailsDatabaseContract.SpeciesDb.COLUMN_NAME_LEAF,
                TrailsDatabaseContract.SpeciesDb.COLUMN_NAME_OTHER_NAMES,
                TrailsDatabaseContract.SpeciesDb.COLUMN_NAME_SCIENTIFIC_NAME,
                TrailsDatabaseContract.SpeciesDb.COLUMN_NAME_SPCODE,
                TrailsDatabaseContract.SpeciesDb.COLUMN_NAME_TWIG,
                TrailsDatabaseContract.SpeciesDb.COLUMN_NAME_WOOD
        };
        String selection = "1 = 1";


        Cursor cursor = db.query(
                TrailsDatabaseContract.SpeciesDb.TABLE_NAME,                     // The table to query
                projection,                               // The columns to return
                selection,                                // The columns for the WHERE clause
                null,                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                null                                 // The sort order
        );

        while(cursor.moveToNext()){
            species.add(new Species(cursor.getString(cursor.getColumnIndexOrThrow(TrailsDatabaseContract.SpeciesDb.COLUMN_NAME_COMMON_NAME)),
                    cursor.getString(cursor.getColumnIndex(TrailsDatabaseContract.SpeciesDb.COLUMN_NAME_SCIENTIFIC_NAME)),
                    cursor.getString(cursor.getColumnIndex(TrailsDatabaseContract.SpeciesDb.COLUMN_NAME_OTHER_NAMES)),
                    cursor.getString(cursor.getColumnIndex(TrailsDatabaseContract.SpeciesDb.COLUMN_NAME_LEAF)),
                    cursor.getString(cursor.getColumnIndex(TrailsDatabaseContract.SpeciesDb.COLUMN_NAME_FLOWER)),
                    cursor.getString(cursor.getColumnIndex(TrailsDatabaseContract.SpeciesDb.COLUMN_NAME_FRUIT)),
                    cursor.getString(cursor.getColumnIndex(TrailsDatabaseContract.SpeciesDb.COLUMN_NAME_TWIG)),
                    cursor.getString(cursor.getColumnIndex(TrailsDatabaseContract.SpeciesDb.COLUMN_NAME_BARK)),
                    cursor.getString(cursor.getColumnIndex(TrailsDatabaseContract.SpeciesDb.COLUMN_NAME_WOOD)),
                    dbToFacts(cursor.getLong(cursor.getColumnIndex(TrailsDatabaseContract.SpeciesDb._ID))),
                    dbToReferences(cursor.getLong(cursor.getColumnIndex(TrailsDatabaseContract.SpeciesDb._ID))),
                    dbToImages(cursor.getLong(cursor.getColumnIndex(TrailsDatabaseContract.SpeciesDb._ID))),
                    dbToImgDescs(cursor.getLong(cursor.getColumnIndex(TrailsDatabaseContract.SpeciesDb._ID))),
                    cursor.getString(cursor.getColumnIndex(TrailsDatabaseContract.SpeciesDb.COLUMN_NAME_SPCODE))));
        }


        db.close();
    }

    private ArrayList<String> dbToFacts(long id) {

        ArrayList<String> facts = new ArrayList<>();

        SpeciesDatabaseHelper dbHelper = new SpeciesDatabaseHelper(TreeCaching.getAppContext());

        SQLiteDatabase db = dbHelper.getReadableDatabase();


        String[] projection1 = {
                TrailsDatabaseContract.FactsDb.COLUMN_NAME_FACT,
                TrailsDatabaseContract.FactsDb.COLUMN_NAME_SPECIES
        };

        String selection = TrailsDatabaseContract.FactsDb.COLUMN_NAME_SPECIES + " = ?";
        String[] selectionArgs = { Long.toString(id) };

        Cursor cursor = db.query(
                TrailsDatabaseContract.FactsDb.TABLE_NAME,                     // The table to query
                projection1,                               // The columns to return
                selection,                                // The columns for the WHERE clause
                selectionArgs,                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                null                                 // The sort order
        );

        while(cursor.moveToNext()) {
            facts.add(cursor.getString(cursor.getColumnIndexOrThrow(TrailsDatabaseContract.FactsDb.COLUMN_NAME_FACT)));
        }

        db.close();

        return facts;
    }

    private ArrayList<String> dbToReferences(long id) {

        ArrayList<String> references = new ArrayList<>();

        SpeciesDatabaseHelper dbHelper = new SpeciesDatabaseHelper(TreeCaching.getAppContext());

        SQLiteDatabase db = dbHelper.getReadableDatabase();


        String[] projection1 = {
                TrailsDatabaseContract.SpeciesReferenceDb.COLUMN_NAME_SPECIES,
                TrailsDatabaseContract.SpeciesReferenceDb.COLUMN_NAME_REFERENCE
        };

        String selection = TrailsDatabaseContract.SpeciesReferenceDb.COLUMN_NAME_SPECIES + " = ?";
        String[] selectionArgs = { Long.toString(id) };

        Cursor cursor = db.query(
                TrailsDatabaseContract.SpeciesReferenceDb.TABLE_NAME,                     // The table to query
                projection1,                               // The columns to return
                selection,                                // The columns for the WHERE clause
                selectionArgs,                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                null                                 // The sort order
        );


        while(cursor.moveToNext()) {

            long referenceId = cursor.getLong(cursor.getColumnIndexOrThrow(TrailsDatabaseContract.SpeciesReferenceDb.COLUMN_NAME_REFERENCE));

            String[] projection2 = {
                    TrailsDatabaseContract.ReferenceDb.COLUMN_NAME_REFERENCE,
                    TrailsDatabaseContract.ReferenceDb._ID
            };

            String selection2 = TrailsDatabaseContract.ReferenceDb._ID + " = ?";
            String[] selectionArgs2 = { Long.toString(referenceId) };

            Cursor cursor2 = db.query(
                TrailsDatabaseContract.ReferenceDb.TABLE_NAME,                     // The table to query
                projection2,                               // The columns to return
                selection2,                                // The columns for the WHERE clause
                selectionArgs2,                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                null                                 // The sort order
            );
            while(cursor2.moveToNext()) {
                references.add(cursor2.getString(cursor2.getColumnIndexOrThrow(TrailsDatabaseContract.ReferenceDb.COLUMN_NAME_REFERENCE)));
                System.out.println(cursor2.getString(cursor2.getColumnIndexOrThrow(TrailsDatabaseContract.ReferenceDb.COLUMN_NAME_REFERENCE)));
            }
        }


        db.close();

        return references;

    }

    private ArrayList<String> dbToImages(long id) {

        ArrayList<String> images = new ArrayList<>();

        SpeciesDatabaseHelper dbHelper = new SpeciesDatabaseHelper(TreeCaching.getAppContext());

        SQLiteDatabase db = dbHelper.getReadableDatabase();


        String[] projection = {
                TrailsDatabaseContract.SpeciesImageDb.COLUMN_NAME_IMAGE_URL,
                TrailsDatabaseContract.SpeciesImageDb.COLUMN_NAME_SPECIES
        };

        String selection = TrailsDatabaseContract.SpeciesImageDb.COLUMN_NAME_SPECIES + " = ?";
        String[] selectionArgs = { Long.toString(id) };

        Cursor cursor = db.query(
                TrailsDatabaseContract.SpeciesImageDb.TABLE_NAME,                     // The table to query
                projection,                               // The columns to return
                selection,                                // The columns for the WHERE clause
                selectionArgs,                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                null                                 // The sort order
        );

        while(cursor.moveToNext()) {
            images.add(cursor.getString(cursor.getColumnIndexOrThrow(TrailsDatabaseContract.SpeciesImageDb.COLUMN_NAME_IMAGE_URL)));
        }

        db.close();

        return images;

    }

    private ArrayList<String> dbToImgDescs(long id) {

        ArrayList<String> imageDesc = new ArrayList<>();

        SpeciesDatabaseHelper dbHelper = new SpeciesDatabaseHelper(TreeCaching.getAppContext());

        SQLiteDatabase db = dbHelper.getReadableDatabase();


        String[] projection = {
                TrailsDatabaseContract.SpeciesImageDescriptionDb.COLUMN_NAME_IMAGE_DESC,
                TrailsDatabaseContract.SpeciesImageDescriptionDb.COLUMN_NAME_SPECIES
        };

        String selection = TrailsDatabaseContract.SpeciesImageDescriptionDb.COLUMN_NAME_SPECIES + " = ?";
        String[] selectionArgs = { Long.toString(id) };

        Cursor cursor = db.query(
                TrailsDatabaseContract.SpeciesImageDescriptionDb.TABLE_NAME,                     // The table to query
                projection,                               // The columns to return
                selection,                                // The columns for the WHERE clause
                selectionArgs,                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                null                                 // The sort order
        );

        while(cursor.moveToNext()) {
            imageDesc.add(cursor.getString(cursor.getColumnIndexOrThrow(TrailsDatabaseContract.SpeciesImageDescriptionDb.COLUMN_NAME_IMAGE_DESC)));
        }

        db.close();

        return imageDesc;

    }

}