package tc.streethelper.streethelper;

import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.Toast;

import com.akexorcist.googledirection.DirectionCallback;
import com.akexorcist.googledirection.GoogleDirection;
import com.akexorcist.googledirection.model.Direction;
import com.akexorcist.googledirection.model.Leg;
import com.akexorcist.googledirection.model.Route;
import com.akexorcist.googledirection.model.Step;
import com.akexorcist.googledirection.util.DirectionConverter;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.ArrayList;
import java.util.List;

public class MedicalFindingActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medical_finding);
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
        mMap.setOnMapLoadedCallback(new GoogleMap.OnMapLoadedCallback() {
            @Override
            public void onMapLoaded() {
//                dismissDialog(1);
                mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                mMap.getUiSettings().setZoomControlsEnabled(true);
            }
        });
        // Add a marker in Sydney and move the camera
        LatLng hochiminh = new LatLng(10.8607196, 106.630514);
        mMap.addMarker(new MarkerOptions().position(hochiminh).title("Bệnh viện Q12").snippet("Địa chỉ: 111 Dương Thị Mười, Tân Chánh Hiệp, 12, Hồ Chí Minh"));
//        mMap.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {
//            @Override
//            public View getInfoWindow(Marker marker) {
//                return null;
//            }
//
//            @Override
//            public View getInfoContents(Marker marker) {
//                View v = getLayoutInflater().inflate(R.layout.activity_find_sleep_place, null);
//                TextView mTxtLat = (TextView) v.findViewById(R.id.map);
//                return null;
//            }
//        });
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(hochiminh, 18));
        final List<Polyline> polylines = new ArrayList<>();
		mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                if(!polylines.isEmpty() && polylines.get(0) != null) {
                    polylines.get(0).remove();
                    polylines.remove(0);
                }

                Toast.makeText(MedicalFindingActivity.this, "abc", Toast.LENGTH_SHORT).show();
                Location curPosition = mMap.getMyLocation();
                String serverKey = "AIzaSyAw_wWGQoFu8LpWqUs6iCn73WERk0SBKHU";
                GoogleDirection.withServerKey(serverKey)
                        .from(new LatLng(curPosition.getLatitude(), curPosition.getLongitude()))
                        .to(marker.getPosition())
                        .execute(new DirectionCallback() {
                            @Override
                            public void onDirectionSuccess(Direction direction, String rawBody) {
                                String status = direction.getStatus();
                                Route route = direction.getRouteList().get(0);
                                Leg leg = route.getLegList().get(0);
                                List<Step> stepList= leg.getStepList();
                                ArrayList<LatLng> pointList = leg.getDirectionPoint();

                                ArrayList<LatLng> directionPositionList = leg.getDirectionPoint();
                                PolylineOptions polylineOptions = DirectionConverter.createPolyline(MedicalFindingActivity.this, directionPositionList, 5, Color.RED);
//                                mMap.addPolyline(polylineOptions);
                                polylines.add(mMap.addPolyline(polylineOptions));
                            }

                            @Override
                            public void onDirectionFailure(Throwable t) {

                            }
                        });



                return false;
            }
        });
    }
}
