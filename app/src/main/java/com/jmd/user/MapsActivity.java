package com.jmd.user;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnMapClickListener {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;
        mMap.setOnMapClickListener(this);
        int zoom=13;
        mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);

        // Add a marker in Sydney and move the camera
        LatLng p1 = new LatLng(  -31.32399481,  -54.12874043);
        mMap.addMarker(new MarkerOptions().position(p1).title("Chácara da Cristina"));

        LatLng p2 = new LatLng(-31.30328737, -54.12083423);
        mMap.addMarker(new MarkerOptions().position(p2).title("Leandro Beer"));

        LatLng p3 = new LatLng(-31.35283609, -54.10931887);
        mMap.addMarker(new MarkerOptions().position(p3).title("Whiskeria do Arco"));

        LatLng p4 = new LatLng(-31.33141349, -54.10291810);
        mMap.addMarker(new MarkerOptions().position(p4).title("Bar do Amassado!"));

        LatLng p5 = new LatLng(-31.30139809, -54.08052782);
        mMap.addMarker(new MarkerOptions().position(p5).title("Sta. Tecla Rosa"));

        LatLng p6 = new LatLng(-31.344785, -54.0980067);
        mMap.addMarker(new MarkerOptions().position(p6).title("The Flowers"));

        // mMap.moveCamera(CameraUpdateFactory.newLatLng(p4));

        CameraUpdate update = CameraUpdateFactory.newLatLngZoom(p4, zoom);
        mMap.moveCamera(update);

//        CameraPosition cameraPosition = new CameraPosition.Builder()
//                .target(p4)
//                .zoom(1)
//                .bearing(90)
//                .tilt(90)
//                .build();
//           mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

    }

    @Override
    public void onMapClick(LatLng latLng) {

        Toast.makeText(getBaseContext(), "clicou em: "+latLng, Toast.LENGTH_SHORT).show();

        Toast.makeText(getBaseContext(), "latitude: "+latLng.latitude +"longitude: "+latLng.longitude, Toast.LENGTH_SHORT).show();

//        mMap.addMarker(new MarkerOptions().position(latLng).title("Aqui vc clicou"));
    }

    private void adicionarMarcador(GoogleMap map, LatLng latLng){

        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(latLng).title("meu marcador").snippet("exemplo de texto");
//        markerOptions.icon(BitmapDescriptorFactory.fromResource(R.mipmap.download2));
        Marker marker = mMap.addMarker(markerOptions);
    }

}

