package com.example.arinb.treecaching;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by arinb on 2016-11-08.
 */

public class JsonObjectToSpecies {
    public ArrayList<Species> parseJsonObjectToSpecies(String jsonString) {

        ArrayList<Species> speciesList = new ArrayList<>();

        try {
            JSONObject json = new JSONObject(jsonString);
            JSONArray jsonArray = json.getJSONArray("species");
            for (int x = 0; x < jsonArray.length(); x++){
                JSONObject species = jsonArray.getJSONObject(x);
                String commonName = species.getString("commonname");
                String scientificName = species.getString("scientificname");
                String otherNames = species.getString("othernames");
                String leaf = species.getString("leaf");
                String flower = species.getString("flower");
                String fruit = species.getString("fruit");
                String twig = species.getString("twig");
                String bark = species.getString("bark");
                String wood = species.getString("wood");
                String spCode = species.getString("spcode");

                ArrayList<String> facts = new ArrayList<>();
                JSONArray factsJson = species.getJSONArray("facts");
                for (int a = 0; a < factsJson.length(); a++) {
                    facts.add((String) factsJson.get(a));
                }

                ArrayList<String> images = new ArrayList<>();
                JSONArray imagesJson = species.getJSONArray("images");
                for (int b = 0; b < imagesJson.length(); b++) {
                    images.add((String) imagesJson.get(b));
                }

                ArrayList<String> references = new ArrayList<>();
                JSONArray referencesJson = species.getJSONArray("reference");
                for (int c = 0; c < referencesJson.length(); c++) {
                    references.add(referencesJson.get(c).toString());
                }

                ArrayList<String> imageDescription = new ArrayList<>();
                JSONArray imgDescJson = species.getJSONArray("imagedescription");
                for (int d = 0; d < imgDescJson.length(); d++) {
                    imageDescription.add((String) imgDescJson.get(d).toString());
                }

                speciesList.add(new Species(commonName, scientificName, otherNames, leaf, flower,
                        fruit, twig, bark, wood, facts, references, images, imageDescription, spCode));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return speciesList;

    }
}
