package com.example.arinb.treecaching;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.widget.TextView;

import java.util.ArrayList;

public class ScrollingDisplaySpecies extends MainActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getLayoutInflater().inflate(R.layout.activity_scrolling_display_species, frameLayout);

        //setContentView(R.layout.activity_display_tree);


        TextView speciesBarkText = (TextView) findViewById(R.id.speciesBarkText);
        TextView speciesTwigText = (TextView) findViewById(R.id.speciesTwigText);
        TextView speciesFruitText = (TextView) findViewById(R.id.speciesFruitText);
        TextView speciesFlowerText = (TextView) findViewById(R.id.speciesFlowerText);
        TextView speciesLeafText = (TextView) findViewById(R.id.speciesLeafText);
        TextView speciesWoodText = (TextView) findViewById(R.id.speciesWoodText);
        TextView speciesFactsText = (TextView) findViewById(R.id.speciesFactsText);
        TextView speciesReferencesText = (TextView) findViewById(R.id.speciesReferencesText);

        //grab intent from last activity
        Intent intent = getIntent();

        Species species = intent.getParcelableExtra(MainActivity.EXTRA_SPECIES);


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


        speciesBarkText.setText(species.getBark());
        speciesTwigText.setText(species.getTwig());
        speciesFruitText.setText(species.getFruit());
        speciesFlowerText.setText(species.getFlower());
        speciesLeafText.setText(species.getLeaf());
        speciesWoodText.setText(species.getWood());

        String facts = "";
        for (String fact : species.getFacts()){
            facts = facts + fact + "\n\n";
        }

        String references = "";
        for (String reference : species.getReference()){
            references = references + reference + "\n\n";
        }

        speciesFactsText.setText(facts);
        speciesReferencesText.setText(references);
    }
}
