package com.example.arinb.treecaching;

import android.provider.BaseColumns;

import java.lang.ref.Reference;

/**
 * Created by arinb on 2016-10-25.
 */

public final class TrailsDatabaseContract {

    private TrailsDatabaseContract(){}


    public static class TrailDb implements BaseColumns {
        public static final String TABLE_NAME = "traildb";
        public static final String COLUMN_NAME_LOCATION = "location";
        public static final String COLUMN_NAME_LATITUDE = "latitude";
        public static final String COLUMN_NAME_LONGITUDE = "longitude";

        private static final String TEXT_TYPE = " TEXT";
        private static final String COMMA_SEP = ",";
        public static final String SQL_CREATE_ENTRIES =
                "CREATE TABLE IF NOT EXISTS " + TrailDb.TABLE_NAME + " (" +
                        TrailDb._ID + " INTEGER PRIMARY KEY," +
                        TrailDb.COLUMN_NAME_LOCATION + TEXT_TYPE + COMMA_SEP +
                        TrailDb.COLUMN_NAME_LATITUDE + TEXT_TYPE + COMMA_SEP +
                        TrailDb.COLUMN_NAME_LONGITUDE + TEXT_TYPE + " )";

        public static final String SQL_DELETE_ENTRIES =
                "DROP TABLE IF EXISTS " + TrailDb.TABLE_NAME;
    }

    public static class TreeDb implements BaseColumns {
        public static final String TABLE_NAME = "treedb";
        public static final String COLUMN_NAME_WEBCODE = "webcode";
        public static final String COLUMN_NAME_LOCATION = "location";
        public static final String COLUMN_NAME_TREECODE = "treeCode";
        public static final String COLUMN_NAME_COMMON_NAME = "commonName";
        public static final String COLUMN_NAME_SCIENTIFIC_NAME = "scientificName";
        public static final String COLUMN_NAME_DBH = "dbh";
        public static final String COLUMN_NAME_CW1 = "cw1";
        public static final String COLUMN_NAME_CW2 = "cw2";
        public static final String COLUMN_NAME_HEIGHT = "height";
        public static final String COLUMN_NAME_YEAR = "year";
        public static final String COLUMN_NAME_COMMENT = "comment";
        public static final String COLUMN_NAME_UTMN = "utmn";
        public static final String COLUMN_NAME_UTME = "utme";
        public static final String COLUMN_NAME_LATITUDE = "latitude";
        public static final String COLUMN_NAME_LONGITUDE = "longitude";
        public static final String COLUMN_NAME_KGC = "kgc";
        public static final String COLUMN_NAME_KGCO2 = "kgco2";
        public static final String COLUMN_NAME_WEBPAGES = "webpages";
        public static final String COLUMN_NAME_TREE_SURROUNDINGS = "treeSurroundings";

        private static final String TEXT_TYPE = " TEXT";
        private static final String COMMA_SEP = ",";
        public static final String SQL_CREATE_ENTRIES =
                "CREATE TABLE IF NOT EXISTS " + TreeDb.TABLE_NAME + " (" +
                        TreeDb._ID + " INTEGER PRIMARY KEY," +
                        TreeDb.COLUMN_NAME_WEBCODE + TEXT_TYPE + COMMA_SEP +
                        TreeDb.COLUMN_NAME_LOCATION + TEXT_TYPE + COMMA_SEP +
                        TreeDb.COLUMN_NAME_TREECODE + TEXT_TYPE + COMMA_SEP +
                        TreeDb.COLUMN_NAME_COMMON_NAME + TEXT_TYPE + COMMA_SEP +
                        TreeDb.COLUMN_NAME_SCIENTIFIC_NAME + TEXT_TYPE + COMMA_SEP +
                        TreeDb.COLUMN_NAME_DBH + TEXT_TYPE + COMMA_SEP +
                        TreeDb.COLUMN_NAME_CW1 + TEXT_TYPE + COMMA_SEP +
                        TreeDb.COLUMN_NAME_CW2 + TEXT_TYPE + COMMA_SEP +
                        TreeDb.COLUMN_NAME_HEIGHT + TEXT_TYPE + COMMA_SEP +
                        TreeDb.COLUMN_NAME_YEAR + TEXT_TYPE + COMMA_SEP +
                        TreeDb.COLUMN_NAME_COMMENT + TEXT_TYPE + COMMA_SEP +
                        TreeDb.COLUMN_NAME_UTME + TEXT_TYPE + COMMA_SEP +
                        TreeDb.COLUMN_NAME_UTMN + TEXT_TYPE + COMMA_SEP +
                        TreeDb.COLUMN_NAME_LATITUDE + TEXT_TYPE + COMMA_SEP +
                        TreeDb.COLUMN_NAME_LONGITUDE + TEXT_TYPE + COMMA_SEP +
                        TreeDb.COLUMN_NAME_KGC + TEXT_TYPE + COMMA_SEP +
                        TreeDb.COLUMN_NAME_KGCO2 + TEXT_TYPE + COMMA_SEP +
                        TreeDb.COLUMN_NAME_WEBPAGES + TEXT_TYPE + COMMA_SEP +
                        TreeDb.COLUMN_NAME_TREE_SURROUNDINGS + TEXT_TYPE + " )";

        public static final String SQL_DELETE_ENTRIES =
                "DROP TABLE IF EXISTS " + TreeDb.TABLE_NAME;

    }

    public static class SpeciesDb implements BaseColumns {
        public static final String TABLE_NAME = "speciesinfodb";
        public static final String COLUMN_NAME_COMMON_NAME = "commonName";
        public static final String COLUMN_NAME_SCIENTIFIC_NAME = "scientificName";
        public static final String COLUMN_NAME_OTHER_NAMES = "otherNames";
        public static final String COLUMN_NAME_LEAF = "leaf";
        public static final String COLUMN_NAME_FLOWER = "flower";
        public static final String COLUMN_NAME_FRUIT = "fruit";
        public static final String COLUMN_NAME_TWIG = "twig";
        public static final String COLUMN_NAME_BARK = "bark";
        public static final String COLUMN_NAME_WOOD = "wood";
        public static final String COLUMN_NAME_SPCODE = "spcode";
        private static final String TEXT_TYPE = " TEXT";
        private static final String COMMA_SEP = ",";
        public static final String SQL_CREATE_ENTRIES =
                "CREATE TABLE IF NOT EXISTS " + SpeciesDb.TABLE_NAME + " (" +
                        SpeciesDb._ID + " INTEGER PRIMARY KEY," +
                        SpeciesDb.COLUMN_NAME_COMMON_NAME + TEXT_TYPE + COMMA_SEP +
                        SpeciesDb.COLUMN_NAME_SCIENTIFIC_NAME + TEXT_TYPE + COMMA_SEP +
                        SpeciesDb.COLUMN_NAME_OTHER_NAMES + TEXT_TYPE + COMMA_SEP +
                        SpeciesDb.COLUMN_NAME_LEAF + TEXT_TYPE + COMMA_SEP +
                        SpeciesDb.COLUMN_NAME_FLOWER + TEXT_TYPE + COMMA_SEP +
                        SpeciesDb.COLUMN_NAME_FRUIT + TEXT_TYPE + COMMA_SEP +
                        SpeciesDb.COLUMN_NAME_TWIG + TEXT_TYPE + COMMA_SEP +
                        SpeciesDb.COLUMN_NAME_BARK + TEXT_TYPE + COMMA_SEP +
                        SpeciesDb.COLUMN_NAME_WOOD + TEXT_TYPE + COMMA_SEP +
                        SpeciesDb.COLUMN_NAME_SPCODE + TEXT_TYPE +" )";
        public static final String SQL_DELETE_ENTRIES =
                "DROP TABLE IF EXISTS " + SpeciesDb.TABLE_NAME;
    }

    public static class FactsDb implements BaseColumns {
        public static final String TABLE_NAME = "factsdb";
        public static final String COLUMN_NAME_FACT = "fact";
        public static final String COLUMN_NAME_SPECIES = "species";
        private static final String TEXT_TYPE = " TEXT";
        private static final String COMMA_SEP = ",";
        public static final String SQL_CREATE_ENTRIES =
                "CREATE TABLE IF NOT EXISTS " + FactsDb.TABLE_NAME + " (" +
                        FactsDb._ID + " INTEGER PRIMARY KEY," +
                        FactsDb.COLUMN_NAME_SPECIES + " INTEGER" + COMMA_SEP +
                        FactsDb.COLUMN_NAME_FACT + TEXT_TYPE + COMMA_SEP +
                        " FOREIGN KEY (" + FactsDb.COLUMN_NAME_SPECIES + ") REFERENCES " +
                        FactsDb.TABLE_NAME + "(" + SpeciesDb._ID +") )";
        public static final String SQL_DELETE_ENTRIES =
                "DROP TABLE IF EXISTS " + FactsDb.TABLE_NAME;
    }

    public static class ReferenceDb implements BaseColumns {
        public static final String TABLE_NAME = "referencedb";
        public static final String COLUMN_NAME_REFERENCE = "reference";
        private static final String TEXT_TYPE = " TEXT";
        private static final String COMMA_SEP = ",";
        public static final String SQL_CREATE_ENTRIES =
                "CREATE TABLE IF NOT EXISTS " + ReferenceDb.TABLE_NAME + " (" +
                        ReferenceDb._ID + " INTEGER PRIMARY KEY," +
                        ReferenceDb.COLUMN_NAME_REFERENCE + TEXT_TYPE + " )";
        public static final String SQL_DELETE_ENTRIES =
                "DROP TABLE IF EXISTS " + ReferenceDb.TABLE_NAME;
    }

    public static class SpeciesImageDb implements BaseColumns {
        public static final String TABLE_NAME = "speciesimage";
        public static final String COLUMN_NAME_SPECIES = "species";
        public static final String COLUMN_NAME_IMAGE_URL = "imageUrl";
        private static final String TEXT_TYPE = " TEXT";
        private static final String COMMA_SEP = ",";
        public static final String SQL_CREATE_ENTRIES =
                "CREATE TABLE IF NOT EXISTS " + SpeciesImageDb.TABLE_NAME + " (" +
                        SpeciesImageDb._ID + " INTEGER PRIMARY KEY," +
                        SpeciesImageDb.COLUMN_NAME_SPECIES + " INTEGER" + COMMA_SEP +
                        SpeciesImageDb.COLUMN_NAME_IMAGE_URL + TEXT_TYPE + COMMA_SEP +
                        " FOREIGN KEY (" + SpeciesImageDb.COLUMN_NAME_SPECIES + ") REFERENCES " +
                        SpeciesImageDb.TABLE_NAME + "(" + SpeciesDb._ID +") )";
        public static final String SQL_DELETE_ENTRIES =
                "DROP TABLE IF EXISTS " + SpeciesImageDb.TABLE_NAME;
    }

    public static class SpeciesImageDescriptionDb implements BaseColumns {
        public static final String TABLE_NAME = "speciesimagedescription";
        public static final String COLUMN_NAME_SPECIES = "species";
        public static final String COLUMN_NAME_IMAGE_DESC = "imageDesc";
        private static final String TEXT_TYPE = " TEXT";
        private static final String COMMA_SEP = ",";
        public static final String SQL_CREATE_ENTRIES =
                "CREATE TABLE IF NOT EXISTS " + SpeciesImageDescriptionDb.TABLE_NAME + " (" +
                        SpeciesImageDescriptionDb._ID + " INTEGER PRIMARY KEY," +
                        SpeciesImageDescriptionDb.COLUMN_NAME_SPECIES + " INTEGER" + COMMA_SEP +
                        SpeciesImageDescriptionDb.COLUMN_NAME_IMAGE_DESC + TEXT_TYPE + COMMA_SEP +
                        " FOREIGN KEY (" + SpeciesImageDescriptionDb.COLUMN_NAME_SPECIES + ") REFERENCES " +
                        SpeciesImageDb.TABLE_NAME + "(" + SpeciesDb._ID +") )";
        public static final String SQL_DELETE_ENTRIES =
                "DROP TABLE IF EXISTS " + SpeciesImageDescriptionDb.TABLE_NAME;
    }

    public static class SpeciesReferenceDb implements BaseColumns {
        public static final String TABLE_NAME = "speciesreferencedb";
        public static final String COMMA_SEP = ",";
        public static final String COLUMN_NAME_SPECIES = "species";
        public static final String COLUMN_NAME_REFERENCE = "reference";
        private static final String INTEGER_TYPE = " INTEGER";

        public static final String SQL_CREATE_ENTRIES =
                "CREATE TABLE IF NOT EXISTS " + SpeciesReferenceDb.TABLE_NAME + " (" +
                        SpeciesReferenceDb.COLUMN_NAME_REFERENCE + INTEGER_TYPE + COMMA_SEP +
                        SpeciesReferenceDb.COLUMN_NAME_SPECIES + INTEGER_TYPE + COMMA_SEP +
                        " FOREIGN KEY (" + SpeciesReferenceDb.COLUMN_NAME_REFERENCE + ") REFERENCES " +
                        ReferenceDb.TABLE_NAME + "(" + ReferenceDb._ID +")" + COMMA_SEP +
                        " FOREIGN KEY (" + SpeciesReferenceDb.COLUMN_NAME_SPECIES+ ") REFERENCES " +
                        SpeciesDb.TABLE_NAME + "(" + SpeciesDb._ID + " )" +
                        COMMA_SEP + "PRIMARY KEY (" +
                        SpeciesReferenceDb.COLUMN_NAME_REFERENCE + COMMA_SEP +
                        SpeciesReferenceDb.COLUMN_NAME_SPECIES + ") )";

        public static final String SQL_DELETE_ENTRIES =
                "DROP TABLE IF EXISTS " + SpeciesReferenceDb.TABLE_NAME;
    }

    public static class TrailTreeDb implements BaseColumns {
        public static final String TABLE_NAME = "trailtreedb";
        public static final String COLUMN_NAME_LOCATION = "location";
        public static final String COLUMN_NAME_TREE = "tree";

        private static final String COMMA_SEP = ",";
        public static final String SQL_CREATE_ENTRIES =
                "CREATE TABLE IF NOT EXISTS " + TrailTreeDb.TABLE_NAME + " (" +
                        TrailTreeDb.COLUMN_NAME_LOCATION + " INTEGER" + COMMA_SEP +
                        TrailTreeDb.COLUMN_NAME_TREE + " INTEGER" + COMMA_SEP +
                        " FOREIGN KEY (" + TrailTreeDb.COLUMN_NAME_LOCATION + ") REFERENCES " +
                        TrailDb.TABLE_NAME + "(" + TrailDb._ID +")" + COMMA_SEP +
                        " FOREIGN KEY (" + TrailTreeDb.COLUMN_NAME_TREE+ ") REFERENCES " +
                        TreeDb.TABLE_NAME + "(" + TreeDb._ID + " )" +
                        COMMA_SEP + "PRIMARY KEY (" +
                        TrailTreeDb.COLUMN_NAME_LOCATION + COMMA_SEP +
                        TrailTreeDb.COLUMN_NAME_TREE + ") )";

        public static final String SQL_DELETE_ENTRIES =
                "DROP TABLE IF EXISTS " + TrailTreeDb.TABLE_NAME;
    }

    public static class FoundTreesDb implements BaseColumns {
        public static final String TABLE_NAME = "foundtreesdb";
        public static final String COLUMN_NAME_SPECIES = "species";
        public static final String COLUMN_NAME_LOCATION = "location";

        private static final String COMMA_SEP = ",";
        public static final String SQL_CREATE_ENTRIES =
                "CREATE TABLE IF NOT EXISTS " + FoundTreesDb.TABLE_NAME + " (" +
                        FoundTreesDb.COLUMN_NAME_LOCATION + " INTEGER" + COMMA_SEP +
                        FoundTreesDb.COLUMN_NAME_SPECIES + " INTEGER" + COMMA_SEP +
                        " FOREIGN KEY (" + FoundTreesDb.COLUMN_NAME_LOCATION + ") REFERENCES " +
                        TrailDb.TABLE_NAME + "(" + TrailDb._ID +")" + COMMA_SEP +
                        " FOREIGN KEY (" + FoundTreesDb.COLUMN_NAME_SPECIES+ ") REFERENCES " +
                        TreeDb.TABLE_NAME + "(" + SpeciesDb._ID + " )" +
                        COMMA_SEP + "PRIMARY KEY (" +
                        FoundTreesDb.COLUMN_NAME_LOCATION + COMMA_SEP +
                        FoundTreesDb.COLUMN_NAME_SPECIES + ") )";

        public static final String SQL_DELETE_ENTRIES =
                "DROP TABLE IF EXISTS " + FoundTreesDb.TABLE_NAME;
    }

}
