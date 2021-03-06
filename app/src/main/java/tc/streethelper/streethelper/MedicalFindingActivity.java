package tc.streethelper.streethelper;

import android.Manifest;
import android.content.pm.PackageManager;
import android.content.Intent;
import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.view.View;
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
    static final int REQUEST_HELP = 50;
    private List<LatLng> places = new ArrayList<>();
    private List<String> names = new ArrayList<>(), ages = new ArrayList<>();

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
        if (!places.isEmpty() && !names.isEmpty() && !ages.isEmpty()) {
            for (int i = 0; i < places.size(); i++) {
                mMap.addMarker(new MarkerOptions().position(places.get(i)).title("Người cần giúp tên: " + names.get(i)).snippet("Tuổi: " + ages.get(i) + " (Thông tin đang kiểm duyệt)" ));
            }
        }
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
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mMap.setMyLocationEnabled(true);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(hochiminh, 16));
        final List<Polyline> polylines = new ArrayList<>();
		mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                if(!polylines.isEmpty() && polylines.get(0) != null) {
                    polylines.get(0).remove();
                    polylines.remove(0);
                }

//                Toast.makeText(MedicalFindingActivity.this, "abc", Toast.LENGTH_SHORT).show();
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
                                PolylineOptions polylineOptions = DirectionConverter.createPolyline(MedicalFindingActivity.this, directionPositionList, 5, Color.BLUE);
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

    public void clickToReport(View view) {
        Intent intent = new Intent( MedicalFindingActivity.this, ReportHelpingPersonActivity.class);
        intent.putExtra("la", mMap.getMyLocation().getLatitude());
        intent.putExtra("lng", mMap.getMyLocation().getLongitude());
        startActivityForResult(intent, REQUEST_HELP);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if ( requestCode == REQUEST_HELP && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            places.add((LatLng) extras.get("latLng"));
            mMap.addMarker(new MarkerOptions().position(places.get(places.size() - 1)).title("Người cần giúp tên: " + extras.getString("name")).snippet("Tuổi: " + extras.getString("age") + " (Thông tin đang kiểm duyệt)" ));
            names.add(extras.getString("name"));
            ages.add(extras.getString("age"));
            Toast.makeText(MedicalFindingActivity.this, "Đã nhận thông tin, kiểm duyệt sau 72h", Toast.LENGTH_SHORT).show();
        }
    }
}
