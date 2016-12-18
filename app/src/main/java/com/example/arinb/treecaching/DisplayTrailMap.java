package com.example.arinb.treecaching;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.Arrays;

public class DisplayTrailMap extends MainActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private Trail trail;
    private ArrayList<Species> species;
    private ArrayList<LatLng>  treeLatLongs = new ArrayList<>();
    private final int PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 0;
    private ArrayList<Boolean> found = new ArrayList<>();
    private LocationManager locationManager;
    private LocationListener locationListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getLayoutInflater().inflate(R.layout.activity_display_trail_map, frameLayout);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        //grab intent from last activity
        Intent intent = getIntent();

        trail = (Trail) intent.getParcelableExtra(MainActivity.EXTRA_TRAIL);
        species = intent.getParcelableArrayListExtra(MainActivity.EXTRA_SPECIES_LIST);

        for (int x = 0; x < trail.getTrees().size(); x++){
            found.add(true);
        }

    }


    public void trackLocation() {
        // Acquire a reference to the system Location Manager
        locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);

// Define a listener that responds to location updates
        locationListener = new LocationListener() {
            public void onLocationChanged(Location location) {
                // Called when a new location is found by the network location provider.
                for (int x = 0; x < treeLatLongs.size(); x++) {
                    float[] results = new float[3];
                    Location.distanceBetween(location.getLatitude(), location.getLongitude(), treeLatLongs.get(x).latitude, treeLatLongs.get(x).longitude, results);


                    if (found.get(x) == false && results[0] < 5 && location.getAccuracy() < 12) {

                        found.add(x, true);
                        foundTree(trail.getTrees().get(x));

                    } else if (found.get(x) == true && results[0] >= 12 && location.getAccuracy() < 12){

                        found.add(x, false);
                    }
                }

            }


            public void onStatusChanged(String provider, int status, Bundle extras) {
            }

            public void onProviderEnabled(String provider) {
            }

            public void onProviderDisabled(String provider) {
            }
        };

        Context context = this;
        PackageManager pm = context.getPackageManager();
        int hasPerm = pm.checkPermission(
                android.Manifest.permission.ACCESS_FINE_LOCATION, context.getPackageName());

        if (hasPerm == PackageManager.PERMISSION_GRANTED) {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    trackLocation();

                    Context context = this;
                    PackageManager pm = context.getPackageManager();
                    int hasPerm = pm.checkPermission(
                            android.Manifest.permission.ACCESS_FINE_LOCATION,context.getPackageName());

                    if (hasPerm == PackageManager.PERMISSION_GRANTED) {

                        mMap.setMyLocationEnabled(true);
                    }

                } else {

                    Context context = getApplicationContext();
                    CharSequence text = "The trail map requires GPS permissions to work. Please enable the correct setting.";
                    int duration = Toast.LENGTH_LONG;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        ArrayList<Marker> markerArray = new ArrayList<Marker>();

        for(int x = 0; x < trail.getTrees().size(); x++){
            treeLatLongs.add(new LatLng(trail.getTrees().get(x).getLatitude(), trail.getTrees().get(x).getLongitude()));
             Marker marker = mMap.addMarker(new MarkerOptions().position(treeLatLongs.get(x)).title(trail.getTrees().get(x).getCommonName()));
            markerArray.add(marker);
        }

        if (markerArray.size() > 0) {
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(markerArray.get(0).getPosition(), 15));

            LatLngBounds.Builder builder = new LatLngBounds.Builder();
            ;

            for (Marker marker : markerArray) {
                builder.include(marker.getPosition());
            }


            final LatLngBounds bounds = builder.build();

            mMap.setOnMapLoadedCallback(new GoogleMap.OnMapLoadedCallback() {
                @Override
                public void onMapLoaded() {
                    mMap.animateCamera(CameraUpdateFactory.newLatLngBounds(bounds, 100));
                }
            });
        }


        Context context = this;
        PackageManager pm = context.getPackageManager();
        int hasPerm = pm.checkPermission(
                android.Manifest.permission.ACCESS_FINE_LOCATION,context.getPackageName());

        if (hasPerm == PackageManager.PERMISSION_GRANTED) {

            mMap.setMyLocationEnabled(true);
        }

    }

    private void foundTree(Tree tree){

        //String message = "You found a " + tree.getCommonName() + " tree!";
        //Toast.makeText(getApplicationContext(), message , Toast.LENGTH_LONG).show();

        //Intent intent = new Intent(getApplicationContext(), DisplayTree.class);
        Intent intent = new Intent(getApplicationContext(), ScrollingDisplayTree.class);
        intent.putExtra(MainActivity.EXTRA_TREE, tree);

        for (Species thisSpecies : species){
            if (tree.getCommonName().equals(thisSpecies.getCommonName())){
                intent.putExtra(MainActivity.EXTRA_SPECIES, thisSpecies);
            }
        }

        startActivity(intent);

    }

    @Override
    protected void onStart() {

        super.onStart();  // Always call the superclass method first

        Context context = this;
        PackageManager pm = context.getPackageManager();
        int hasPerm = pm.checkPermission(
                android.Manifest.permission.ACCESS_FINE_LOCATION,context.getPackageName());

        if (hasPerm == PackageManager.PERMISSION_GRANTED) {

            trackLocation();

        } else {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);
        }

    }

    @Override
    public void onStop(){

        super.onStop();

        Context context = this;
        PackageManager pm = context.getPackageManager();
        int hasPerm = pm.checkPermission(
                android.Manifest.permission.ACCESS_FINE_LOCATION,context.getPackageName());

        if (hasPerm == PackageManager.PERMISSION_GRANTED) {

            locationManager.removeUpdates(locationListener);

        }

    }

}
