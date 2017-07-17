package bit.harrl7.overlaydemo;

import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.GroundOverlay;
import com.google.android.gms.maps.model.GroundOverlayOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback
{

    private GoogleMap mMap;
    private GroundOverlay overlay;

    private Button btnShow;

    private int overlayIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        overlayIndex = 0;

        btnShow = (Button) findViewById(R.id.btnShow);
        btnShow.setOnClickListener(new BtnShowPathHandler());

    }

    public class BtnShowPathHandler implements View.OnClickListener
    {

        @Override
        public void onClick(View v)
        {
            setOverlay();
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
    public void onMapReady(GoogleMap googleMap)
    {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng gardens = new LatLng(-45.865092, 170.511513);
        mMap.addMarker(new MarkerOptions().position(gardens).title("Marker in Sydney"));
        //mMap.moveCamera(CameraUpdateFactory.newLatLng(gardens));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(gardens, 15));
    }

    public void setOverlay()
    {
        overlayIndex++;
        overlayIndex %= 3;

        if(overlay != null) overlay.remove();

        // Draw a path
        if(overlayIndex != 0)
        {
            // Select the path
            int pathImg = 0;
            switch (overlayIndex)
            {
                case 1:
                    pathImg = R.drawable.path1;
                    break;
                case 2:
                    pathImg = R.drawable.path2;
                    break;
            }

            //Set the bounds of where the overlay will be
            LatLngBounds polyBounds = new LatLngBounds(
                    new LatLng(-45.865092, 170.511513),       // South west corner
                    new LatLng(-45.860092, 170.516513));      // North east corner

            //Create the ground the groundoverlay options
            GroundOverlayOptions groundMap = new GroundOverlayOptions()
                    .image(BitmapDescriptorFactory.fromResource(pathImg))
                    .positionFromBounds(polyBounds);

            //Set the overly to the map
            overlay = mMap.addGroundOverlay(groundMap);
        }
    }
}
