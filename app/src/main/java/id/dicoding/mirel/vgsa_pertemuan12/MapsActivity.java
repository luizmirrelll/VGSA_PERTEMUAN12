package id.dicoding.mirel.vgsa_pertemuan12;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, LocationListener {

    private GoogleMap mMap;
    LocationManager lokasiManeger;
    double latitude;
    double langtude;

    private String provider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        assert mapFragment !=null;
        mapFragment.getMapAsync(this);
        lokasiManeger =(LocationManager)getSystemService(Context,LOCATION_SERVICE);
        Criteria criteria = new Criteria();
        provider = lokasiManeger.getBestProvider(criteria,false);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)!= PackageManager.PERMISSION_GRANTED){
            Log.d("GPSapk","USER TIDAK DIIJINKAN AKSES LOKASI");
            return;
        }
        Location location=lokasiManeger.getLastKnownLocation(provider);
        if (location !=null){
            System.out.println("provider"+provider+"Ditentukan");
            latitude =location.getLatitude();
            langtude =location.getLongitude();
            onLocationChanged(location);
        }else{
            Log.d("Erorr","lokasi belum tersedia");
        }
    }
    public void btnmaps(View v){
        if (v.getTag().equals("posisi")) {
            LatLng posmaps = new LatLng(latitude,langtude);
            mMap.addMarker(new MarkerOptions().position(posmaps).title("POSISI SAYA SEKARANG"));
            mMap.moveCamera((CameraUpdateFactory.newLatLng(posmaps));
            else if (v.getTag().equals("kordinat"));
        }
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

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }
}