package com.example.arinb.treecaching;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import java.util.ArrayList;

public class ScrollingDisplayTree extends MainActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getLayoutInflater().inflate(R.layout.activity_scrolling_display_tree, frameLayout);

        //setContentView(R.layout.activity_display_tree);


        TextView treeDescriptionText = (TextView) findViewById(R.id.treeDescriptionText);
        TextView treeBarkText = (TextView) findViewById(R.id.treeBarkText);
        TextView treeTwigText = (TextView) findViewById(R.id.treeTwigText);
        TextView treeFruitText = (TextView) findViewById(R.id.treeFruitText);
        TextView treeFlowerText = (TextView) findViewById(R.id.treeFlowerText);
        TextView treeLeafText = (TextView) findViewById(R.id.treeLeafText);
        TextView treeWoodText = (TextView) findViewById(R.id.treeWoodText);
        TextView treeFactsText = (TextView) findViewById(R.id.treeFactsText);
        TextView treeReferencesText = (TextView) findViewById(R.id.treeReferencesText);

        //grab intent from last activity
        Intent intent = getIntent();

        //needs to be final since used in inner class
        Tree tree = (Tree) intent.getParcelableExtra(MainActivity.EXTRA_TREE);
        Species species = (Species) intent.getParcelableExtra(MainActivity.EXTRA_SPECIES);


        this.setTitle(species.getCommonName());

        ArrayList<String> images = new ArrayList<>();
        ArrayList<String> imgDescs = new ArrayList<>();
        for (int x = 0; x < species.getImages().size(); x++){
            images.add(species.getImages().get(x));
            if (x < species.getImageDescription().size()) {
                imgDescs.add(species.getImageDescription().get(x));
            } else {
                imgDescs.add("");
            }
        }

        ViewPager viewPager = (ViewPager)findViewById(R.id.treeImagesViewPager);
        CustomTreeImageAdapter viewPagerAdapter = new CustomTreeImageAdapter(this, images, imgDescs);
        viewPager.setAdapter(viewPagerAdapter);


        String description = "Scientific name: " + tree.getScientificName() + "\n" +
                "Location: " + tree.getLocation() + "\n" +
                "Year: " + tree.getYear() + "\n" +
                "Height: " + tree.getHeight() + "\n" +
                "Crown Width 1: " + tree.getCw1() + "\n" +
                "Crown Width 2: " + tree.getCw2() + "\n" +
                "Diameter at Breast Height: " + tree.getDbh() + "\n" +
                "Latitude: " + tree.getLatitude() + "\n" +
                "Longitude: " + tree.getLongitude() + "\n" +
                "KgC: " + tree.getKgc() + "\n" +
                "KgCO2: " + tree.getKgco2() + "\n" +
                "Comments: " + tree.getComment() + "\n" +
                "Extra: "  + "\n" +
                "Other names: " + species.getOtherNames() + "\n";

                treeBarkText.setText(species.getBark());
                treeTwigText.setText(species.getTwig());
                treeFruitText.setText(species.getFruit());
                treeFlowerText.setText(species.getFlower());
                treeLeafText.setText(species.getLeaf());
                treeWoodText.setText(species.getWood());

        String facts = "";
        for (String fact : species.getFacts()){
            facts = facts + fact + "\n\n";
        }

        String references = "";
        for (String reference : species.getReference()){
            references = references + reference + "\n\n";
        }

        treeFactsText.setText(facts);
        treeReferencesText.setText(references);
        treeDescriptionText.setText(description);
    }
}
