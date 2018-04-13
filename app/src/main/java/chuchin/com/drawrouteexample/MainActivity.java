package chuchin.com.drawrouteexample;


import android.graphics.Point;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.ahmadrosid.lib.drawroutemap.DrawMarker;
import com.ahmadrosid.lib.drawroutemap.DrawRouteMaps;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {

    //region Propiedades
    private static final String TAG = "MainActivity";
    FragmentManager fragmentManager;

    //endregion

    //region View
    SupportMapFragment supportMapFragment;
    GoogleMap googleMap;

    //endregion

    //region AppCompatActivity

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragmentManager = getSupportFragmentManager();
        setupMapAndLocation();
    }

    //endregion

    //region Custom methods

    void setupMapAndLocation() {

        //Init map in fragment
        supportMapFragment =
                (SupportMapFragment) fragmentManager
                        .findFragmentById(R.id.fragment_mapa);
        supportMapFragment.getMapAsync(this);

    }

    //endregion


    //region OnMapReadyCallback

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;

        LatLng origin = new LatLng(18.398485, -93.214621);
        LatLng destination = new LatLng(18.413475, -93.194624);
        DrawRouteMaps.getInstance(this)
                .draw(origin, destination, this.googleMap);
        DrawMarker.getInstance(this).draw(this.googleMap, origin, R.drawable.marker_small_icon, "Origin Location");
        DrawMarker.getInstance(this).draw(this.googleMap, destination, R.drawable.marker_small_icon, "Destination Location");

        LatLngBounds bounds = new LatLngBounds.Builder()
                .include(origin)
                .include(destination).build();
        Point displaySize = new Point();

        getWindowManager().getDefaultDisplay().getSize(displaySize);
        this.googleMap.moveCamera(CameraUpdateFactory.newLatLngBounds(bounds, displaySize.x, 800, 0));
        //googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(location.getLatitude(),  location.getLongitude()), 15.0f));
        //this.googleMap.animateCamera(CameraUpdateFactory.newLatLngBounds(bounds, 10));
        //this.googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(destination, 15.0f));

    }

    //endregion

}
















