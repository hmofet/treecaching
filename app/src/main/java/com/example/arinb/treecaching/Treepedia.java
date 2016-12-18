package com.example.arinb.treecaching;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Filterable;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Treepedia extends MainActivity {
    private ArrayList<Species> species = new ArrayList<>();

    private ListView speciesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getLayoutInflater().inflate(R.layout.activity_treepedia, frameLayout);
        speciesList = (ListView) findViewById(R.id.speciesList);
        final EditText speciesSearchBar = (EditText) findViewById(R.id.speciesSearchBar);
        final ProgressBar spinner = (ProgressBar)findViewById(R.id.speciesProgressBar);


        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        //if connected
        if (networkInfo != null && networkInfo.isConnected()) {
            //start new downloadjson task
            spinner.setVisibility(View.VISIBLE);

            String url = "http://acer-acre.ca/json/jsonfeed.php?species";
            //create volley queue
            RequestQueue queue = Volley.newRequestQueue(this);

            //create listener that will be used by ImageRequest
            final Response.Listener speciesResponseListener = new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    JsonObjectToSpecies converter = new JsonObjectToSpecies();
                    passSpecies(converter.parseJsonObjectToSpecies(response.toString()));

                    new SaveSpeciesDbBackgroundTask().execute(species);
                    spinner.setVisibility(View.GONE);

                };


            };
            JsonObjectRequest speciesJsonRequest = new JsonObjectRequest(Request.Method.GET, url, null, speciesResponseListener, null);

            //fire off request
            queue.add(speciesJsonRequest);

        } else {

            spinner.setVisibility(View.VISIBLE);
            dbToSpecies();

            final SpeciesArrayAdapter adapter = new SpeciesArrayAdapter(this, 0, species);
            speciesList.setAdapter(adapter);
            speciesList.setTextFilterEnabled(true);

            spinner.setVisibility(View.GONE);

        }


        //set up event handler
        speciesList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, final View view, int position, long id) {
                //get product associated with current item tapped in listview
                final Species item = (Species) parent.getItemAtPosition(position);

                Intent intent = new Intent(view.getContext(), ScrollingDisplaySpecies.class);
                intent.putExtra(MainActivity.EXTRA_SPECIES, item);
                startActivity(intent);

            }
        });

        speciesSearchBar.addTextChangedListener(new TextWatcher() {
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            public void afterTextChanged(Editable s) {
                //CharSequence enteredText = s;

                SpeciesArrayAdapter adapter = (SpeciesArrayAdapter) speciesList.getAdapter();
                adapter.getFilter().filter(s.toString().toLowerCase());
                adapter.notifyDataSetChanged();


            }
        });


    }

    private void passSpecies(ArrayList<Species> speciesStringFromJson) {
        species = speciesStringFromJson;

        final SpeciesArrayAdapter adapter = new SpeciesArrayAdapter(this, 0, species);
        speciesList.setAdapter(adapter);
        speciesList.setTextFilterEnabled(true);

    }

    private void dbToSpecies() {
        ReadSpeciesDbBackgroundTask readSpeciesDb = new ReadSpeciesDbBackgroundTask();
        try{
            species = readSpeciesDb.execute().get();
            //System.out.println(species.get(1).getBark());
        } catch (Exception ex){
            System.out.println(ex);
        };

        //System.out.println(species.toString());
    }

    private class SpeciesArrayAdapter extends ArrayAdapter<Species> implements Filterable {

        private Context context;
        private List<Species> theseSpecies;

        //constructor sets context, which resource to use, and the arraylist that it will adapt
        public SpeciesArrayAdapter(Context context, int resource, ArrayList<Species> objects) {
            super(context, resource, objects);

            this.context = context;
            this.theseSpecies= objects;
        }

        //overriden method that takes the current position, current view, and viewgroup it belongs to
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            //get product associated with current listview item tapped
            //need to be final since used in inner class
            final Species thisSpecies = species.get(position);

            //layoutinflater inflates XML layout so can be adapted
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            View view = inflater.inflate(R.layout.species_layout, null);

            TextView speciesNameText = (TextView) view.findViewById(R.id.speciesName);

            //set title and description to relevant views
            speciesNameText.setText(thisSpecies.getCommonName());

            return view;
        }


    }

}
