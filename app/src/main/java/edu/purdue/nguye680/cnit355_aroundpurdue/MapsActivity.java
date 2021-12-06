package edu.purdue.nguye680.cnit355_aroundpurdue;

import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import edu.purdue.nguye680.cnit355_aroundpurdue.databinding.ActivityMapsBinding;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityMapsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(40.423538, -86.921738), 14.0f));

        for (int i = 0; i < MainActivity.locations.size(); i++) {
            LatLng place = new LatLng(MainActivity.lats.get(i), MainActivity.lons.get(i));
            mMap.addMarker(new MarkerOptions().position(place).title(MainActivity.names.get(i)));

            mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
                @Override
                public void onInfoWindowClick(Marker marker) {

                    LatLng latLon = marker.getPosition();

                    //Cycle through places array
                    for(int i = 0; i < MainActivity.locations.size(); i++) {
                        if (latLon.latitude == MainActivity.lats.get(i) && latLon.longitude == MainActivity.lons.get(i)){
                            MainActivity.place = i;
                            Intent intent = new Intent(MapsActivity.this, GuideActivity.class);
                            startActivity(intent);
                        }
                    }
                }
            });
        }


    }
}