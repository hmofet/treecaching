package com.example.arinb.treecaching;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by arinb on 2016-10-24.
 */

public class JsonObjectToTrail {
    public ArrayList<Trail> parseJsonObjectToTrail(String jsonString) {

        ArrayList<Trail> trails = new ArrayList<>();

        try {
            JSONObject json = new JSONObject(jsonString);
            JSONArray jsonArray = json.getJSONArray("trails");
            for (int x = 0; x < jsonArray.length(); x++){
                JSONObject trail = jsonArray.getJSONObject(x);
                String location = trail.getString("location");
                double latitude = Double.parseDouble(trail.getString("latitude"));
                double longitude = Double.parseDouble(trail.getString("longitude"));

                ArrayList<Tree> trees = new ArrayList<>();

                JSONArray treesJson = trail.getJSONArray("trees");
                for (int y = 0; y < treesJson.length(); y++){
                    JSONObject tree = treesJson.getJSONObject(y);
                    String webCode = tree.getString("webcode");
                    String treeLocation = tree.getString("location");
                    String treeCode = tree.getString("treecode");
                    String commonName = tree.getString("commonname");
                    String scientificName = tree.getString("scientificname");
                    double dbh = Double.parseDouble(tree.getString("dbh"));
                    double cw1 = Double.parseDouble(tree.getString("cw1"));
                    double cw2 = Double.parseDouble(tree.getString("cw2"));
                    double height = Double.parseDouble(tree.getString("height"));
                    int year = Integer.parseInt(tree.getString("year"));
                    String comment = tree.getString("comment");
                    double utmn = Double.parseDouble(tree.getString("utmn"));
                    double utme = Double.parseDouble(tree.getString("utme"));
                    double treeLatitude = Double.parseDouble(tree.getString("latitude"));
                    double treeLongitude = Double.parseDouble(tree.getString("longitude"));
                    double kgc = Double.parseDouble(tree.getString("kgc"));
                    double kgco2 = Double.parseDouble(tree.getString("kgco2"));
                    String webpages = tree.getString("webpages");
                    String treeSurroundings = tree.getString("treesurroundings");

                    //Species species = findSpecies(commonName, speciesList);

                    trees.add(new Tree(webCode, treeLocation, treeCode, commonName, scientificName, dbh, cw1, cw2, height,
                            year, comment, utmn, utme, treeLatitude, treeLongitude, kgc, kgco2, webpages, treeSurroundings));

                }

                trails.add(new Trail(location, latitude, longitude, trees));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return trails;

    }

   /* private Species findSpecies(String commonName, ArrayList<Species> species){
        for (int x = 0; x < species.size(); x++){
            if (species.get(x).equals(commonName))
                    return species.get(x);
        }

        return null;
    } */

}
