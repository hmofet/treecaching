package com.example.arinb.treecaching;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
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

public class TrailList extends MainActivity {
    private ArrayList<Trail> trails = new ArrayList<>();
    private ArrayList<Species> species = new ArrayList<>();

    private ListView trailsList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getLayoutInflater().inflate(R.layout.activity_trail_list, frameLayout);

        final ProgressBar spinner = (ProgressBar)findViewById(R.id.progressBar);
        trailsList = (ListView) findViewById(R.id.trailsList);




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

                };


            };
            JsonObjectRequest speciesJsonRequest = new JsonObjectRequest(Request.Method.GET, url, null, speciesResponseListener, null);

            //fire off request
            queue.add(speciesJsonRequest);


            url = "http://acer-acre.ca/json/jsonfeed.php?trails";

            //create listener that will be used by ImageRequest
            final Response.Listener trailsResponseListener = new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    JsonObjectToTrail converter = new JsonObjectToTrail();
                    passTrail(converter.parseJsonObjectToTrail(response.toString()));

                    new SaveTrailsDbBackgroundTask().execute(trails);

                    spinner.setVisibility(View.GONE);
                };


            };
            JsonObjectRequest trailsJsonRequest = new JsonObjectRequest(Request.Method.GET, url, null, trailsResponseListener, null);

            //fire off request
            queue.add(trailsJsonRequest);

        } else {

            spinner.setVisibility(View.VISIBLE);
            dbToTrail();
            dbToSpecies();

            final TrailArrayAdapter adapter = new TrailArrayAdapter(this, 0, trails);
            trailsList.setAdapter(adapter);
            spinner.setVisibility(View.GONE);

        }



        //set up event handler
        trailsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, final View view, int position, long id) {
                //get product associated with current item tapped in listview
                final Trail item = (Trail) parent.getItemAtPosition(position);

                Intent intent = new Intent(view.getContext(), DisplayTrailMap.class);
                intent.putExtra(MainActivity.EXTRA_TRAIL, item);
                intent.putExtra(MainActivity.EXTRA_SPECIES_LIST, species);
                startActivity(intent);

            }
        });


    }

    private void dbToTrail() {
        ReadTrailDbBackgroundTask readTrailDb = new ReadTrailDbBackgroundTask();
        try{
            trails = readTrailDb.execute().get();
           // System.out.println(trails.get(0).getLocation());
        } catch (Exception ex){};
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

    private void passSpecies(ArrayList<Species> speciesStringFromJson) {
        species = speciesStringFromJson;
    }

    private void passTrail(ArrayList<Trail> trailStringFromJson){
        trails = trailStringFromJson;

        final TrailArrayAdapter adapter = new TrailArrayAdapter(this, 0, trails);
        trailsList.setAdapter(adapter);

    }

    private class TrailArrayAdapter extends ArrayAdapter<Trail> {

        private Context context;
        private List<Trail> trailsList;

        //constructor sets context, which resource to use, and the arraylist that it will adapt
        public TrailArrayAdapter(Context context, int resource, ArrayList<Trail> objects) {
            super(context, resource, objects);

            this.context = context;
            this.trailsList = objects;
        }

        //overriden method that takes the current position, current view, and viewgroup it belongs to
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            //get product associated with current listview item tapped
            //need to be final since used in inner class
            final Trail trail = trails.get(position);

            //layoutinflater inflates XML layout so can be adapted
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            View view = inflater.inflate(R.layout.trail_layout, null);

            TextView locationText = (TextView) view.findViewById(R.id.locationText);
            TextView descriptionText = (TextView) view.findViewById(R.id.descriptionText);

            //set title and description to relevant views
            locationText.setText(trail.getLocation());
            descriptionText.setText("Number of trees: " + trail.getTrees().size());

            return view;
        }
    }

}
