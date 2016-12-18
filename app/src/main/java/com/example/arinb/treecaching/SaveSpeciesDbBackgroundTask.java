package com.example.arinb.treecaching;

/**
 * Created by arinb on 2016-11-08.
 */

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;

import java.util.ArrayList;

public class SaveSpeciesDbBackgroundTask extends AsyncTask<Object, Void, Void> {

//TODO: Turn into Singleton

    @Override
    protected Void doInBackground(Object... listTrails) {
        ArrayList<Species> species = (ArrayList<Species>) listTrails[0];


        SpeciesDatabaseHelper dbHelper = new SpeciesDatabaseHelper(TreeCaching.getAppContext());

        SQLiteDatabase db = dbHelper.getWritableDatabase();


        //dbHelper.onUpgrade(db, 1, 1);
        dbHelper.onCreate(db);
        dbHelper.onConfigure(db);

        ContentValues valuesSpecies = new ContentValues();

        ArrayList<String> references = new ArrayList<>();

        for (int i = 0; i < species.size(); i++) {


            ContentValues valuesReferences = new ContentValues();
            ContentValues valuesFacts = new ContentValues();
            ContentValues valuesImages = new ContentValues();
            ContentValues valuesSpeciesReferences = new ContentValues();
            ContentValues valuesImageDescriptions = new ContentValues();

            valuesSpecies.put(TrailsDatabaseContract.SpeciesDb.COLUMN_NAME_COMMON_NAME, species.get(i).getCommonName());
            valuesSpecies.put(TrailsDatabaseContract.SpeciesDb.COLUMN_NAME_SCIENTIFIC_NAME, species.get(i).getScientificName());
            valuesSpecies.put(TrailsDatabaseContract.SpeciesDb.COLUMN_NAME_OTHER_NAMES, species.get(i).getOtherNames());
            valuesSpecies.put(TrailsDatabaseContract.SpeciesDb.COLUMN_NAME_LEAF, species.get(i).getLeaf());
            valuesSpecies.put(TrailsDatabaseContract.SpeciesDb.COLUMN_NAME_FLOWER, species.get(i).getFlower());
            valuesSpecies.put(TrailsDatabaseContract.SpeciesDb.COLUMN_NAME_FLOWER, species.get(i).getFruit());
            valuesSpecies.put(TrailsDatabaseContract.SpeciesDb.COLUMN_NAME_TWIG, species.get(i).getTwig());
            valuesSpecies.put(TrailsDatabaseContract.SpeciesDb.COLUMN_NAME_BARK, species.get(i).getBark());
            valuesSpecies.put(TrailsDatabaseContract.SpeciesDb.COLUMN_NAME_WOOD, species.get(i).getWood());
            valuesSpecies.put(TrailsDatabaseContract.SpeciesDb.COLUMN_NAME_SPCODE, species.get(i).getSpCode());

            long speciesSpeciesRowId = db.insert(TrailsDatabaseContract.SpeciesDb.TABLE_NAME, null, valuesSpecies);

            for (int a = 0; a < species.get(i).getFacts().size(); a++) {

                valuesFacts.put(TrailsDatabaseContract.FactsDb.COLUMN_NAME_SPECIES, speciesSpeciesRowId);
                valuesFacts.put(TrailsDatabaseContract.FactsDb.COLUMN_NAME_FACT, species.get(i).getFacts().get(a));
                long speciesFactsRowId = db.insert(TrailsDatabaseContract.FactsDb.TABLE_NAME, null, valuesFacts);


            }

            for (int b = 0; b < species.get(i).getReference().size(); b++) {


                if (!references.contains(species.get(i).getReference().get(b))) {
                    references.add(species.get(i).getReference().get(b));
                    valuesReferences.put(TrailsDatabaseContract.ReferenceDb.COLUMN_NAME_REFERENCE, species.get(i).getReference().get(b));
                    long speciesReferencesRowId = db.insert(TrailsDatabaseContract.ReferenceDb.TABLE_NAME, null, valuesReferences);

                    valuesSpeciesReferences.put(TrailsDatabaseContract.SpeciesReferenceDb.COLUMN_NAME_REFERENCE, speciesReferencesRowId);
                    valuesSpeciesReferences.put(TrailsDatabaseContract.SpeciesReferenceDb.COLUMN_NAME_SPECIES, speciesSpeciesRowId);
                    long speciesSpeciesReferenceRowId = db.insert(TrailsDatabaseContract.SpeciesReferenceDb.TABLE_NAME, null, valuesSpeciesReferences);
                }
            }


            for (int c = 0; c < species.get(i).getImages().size(); c++) {

                valuesImages.put(TrailsDatabaseContract.SpeciesImageDb.COLUMN_NAME_SPECIES, speciesSpeciesRowId);
                valuesImages.put(TrailsDatabaseContract.SpeciesImageDb.COLUMN_NAME_IMAGE_URL, species.get(i).getImages().get(c));
                long speciesImagesRowId = db.insert(TrailsDatabaseContract.SpeciesImageDb.TABLE_NAME, null, valuesImages);

            }

            for (int d = 0; d < species.get(i).getImageDescription().size(); d++) {

                valuesImageDescriptions.put(TrailsDatabaseContract.SpeciesImageDescriptionDb.COLUMN_NAME_SPECIES, speciesSpeciesRowId);
                valuesImageDescriptions.put(TrailsDatabaseContract.SpeciesImageDescriptionDb.COLUMN_NAME_IMAGE_DESC, species.get(i).getImageDescription().get(d));
                long speciesImageDescriptionRowId = db.insert(TrailsDatabaseContract.SpeciesImageDescriptionDb.TABLE_NAME, null, valuesImageDescriptions);

            }

        }


        db.close();
        return null;
    }

}