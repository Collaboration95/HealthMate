package com.example.healthmate;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONArray;
import org.json.JSONObject;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.maps.android.PolyUtil;
import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;


import java.util.List;


// Implement OnMapReadyCallback.
public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;
    private FusedLocationProviderClient fusedLocationClient;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        } else {
            Log.e("MapsActivity", "Error - Map Fragment was null!");
        }
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
    }

    // Get a handle to the GoogleMap object and display marker.
    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        enableMyLocation();

        // Add an OnMapClickListener
        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                // Remove previous markers if you want only one marker on the map at a time
                mMap.clear();

                // Add a new marker at the clicked location
                mMap.addMarker(new MarkerOptions().position(latLng).title("New Marker"));

                // Move the camera to the new marker
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, mMap.getCameraPosition().zoom));
            }
        });
    }
    private void enableMyLocation() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{
                    Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION_PERMISSION_REQUEST_CODE);
        } else {
            mMap.setMyLocationEnabled(true);
            getLastKnownLocation();
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                enableMyLocation();
            }
        }
    }
    private void getLastKnownLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        fusedLocationClient.getLastLocation().addOnSuccessListener(this, new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if (location != null) {
                    LatLng origin = new LatLng(location.getLatitude(), location.getLongitude());
                    mMap.addMarker(new MarkerOptions().position(origin).title("Origin"));
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(origin, 13));

                    LatLng destination = new LatLng(-33.820230, 151.034427);
                    mMap.addMarker(new MarkerOptions().position(destination).title("Destination"));

                    findShortestRoute(origin, destination);
                }
            }
        });
    }
    private void findShortestRoute(LatLng origin, LatLng destination) {
        String apiKey = "AIzaSyBFiJNB3fgiEj5CFTz8Hn5YdV-xRtj_3t4";
        String url = "https://maps.googleapis.com/maps/api/directions/json?origin=" + origin.latitude + "," + origin.longitude + "&destination=" + destination.latitude + "," + destination.longitude + "&mode=walking&key=" + apiKey;

        RequestQueue queue = Volley.newRequestQueue(this);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray routes = response.getJSONArray("routes");
                            if (routes.length() > 0) {
                                JSONObject shortestRoute = routes.getJSONObject(0);
                                String polyline = shortestRoute.getJSONObject("overview_polyline").getString("points");

                                // Decode the polyline and draw the route on the map
                                drawRouteOnMap(polyline);
                            }
                        } catch (Exception e) {
                            Log.e("MapsActivity", "Error parsing directions response", e);
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("MapsActivity", "Error finding shortest route", error);
            }
        });

        queue.add(jsonObjectRequest);
    }
    private void drawRouteOnMap(String encodedPolyline) {
        List<LatLng> decodedPath = PolyUtil.decode(encodedPolyline);
        PolylineOptions polylineOptions = new PolylineOptions().addAll(decodedPath);
        mMap.addPolyline(polylineOptions);
    }
}
/*
1. enableMyLocation() method checks for location permission and requests it if not granted.
If permission is granted, it enables the My Location layer on the map and calls getLastKnownLocation().

2. getLastKnownLocation() method retrieves the app's current location using the FusedLocationProviderClient object.
If a valid location is found, it creates a LatLng object for the origin and adds a marker with the title "Origin" at that position. It also sets the camera's position to the origin with a zoom level of 13.

3. In the same method, the destination LatLng object is created and added as a marker on the map with the title "Destination".

4. The findShortestRoute(LatLng origin, LatLng destination) method is called with the origin and destination positions.
This method sends a request to the Google Maps Directions API to retrieve the shortest walking route between the two points.

5. If a valid route is found in the API response, the drawRouteOnMap(String encodedPolyline) method is called, which decodes the polyline and adds it to the map as a Polyline object.
 */
