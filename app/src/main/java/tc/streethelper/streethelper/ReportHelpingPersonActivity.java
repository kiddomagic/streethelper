package tc.streethelper.streethelper;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.maps.model.LatLng;

public class ReportHelpingPersonActivity extends AppCompatActivity {

    static final int REQUEST_IMAGE_CAPTURE = 1;
    static final int WIDTH = 300;
    static Location mCLocation;
    private LinearLayout layout = (LinearLayout) findViewById(R.id.clayout);
    private LocationManager mLocation;
    private Bitmap bitmap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_helping_person);
    }

    public void clickToShot(View view) {
        Intent camera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(camera, REQUEST_IMAGE_CAPTURE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            bitmap = imageBitmap;
            layout.setOrientation(LinearLayout.HORIZONTAL);
            layout.removeAllViews();
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(WIDTH, ViewGroup.LayoutParams.WRAP_CONTENT);
            TextView textView = new TextView(this);
            mLocation = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            Intent intent = new Intent(this, ReportHelpingPersonActivity.class);
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
            mLocation.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, (LocationListener) intent);
            LocationListener listener = new LocationListener() {
                @Override
                public void onLocationChanged(Location location) {
                      mCLocation = location;
                      return;
                }

                @Override
                public void onStatusChanged(String s, int i, Bundle bundle) {

                }

                @Override
                public void onProviderEnabled(String s) {

                }

                @Override
                public void onProviderDisabled(String s) {

                }
            };
            textView.setText("Người cần hỗ trợ ở vị trí " + mCLocation.getLatitude() + " " + mCLocation.getLongitude() + " lúc bạn chụp");
            textView.setLayoutParams(layoutParams);
            layout.addView(textView);
            ImageView imageView = new ImageView(this);
            imageView.setLayoutParams(layoutParams);
            imageView.setImageBitmap(imageBitmap);
            layout.addView(imageView);
            Button btn = new Button(this);
            btn.setText("Chụp hình lại");
            btn.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            layout.addView(btn);
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    clickToShot(view);
                }
            });
        }
    }

    public void clickToSubmit(View view) {
        EditText edtName = (EditText) findViewById(R.id.edtName);
        EditText edtAge = (EditText) findViewById(R.id.edtAge);
        EditText edtPhone = (EditText) findViewById(R.id.edtPhone);
        LatLng latLng = new LatLng(mCLocation.getLatitude(), mCLocation.getLongitude());
        Intent intent = this.getIntent();
        Bundle bundle = new Bundle();
        intent.putExtra( "name", edtName.getText().toString());
        intent.putExtra( "age", edtAge.getText().toString());
        intent.putExtra( "phone", edtPhone.getText().toString());
        intent.putExtra( "latLng", latLng);
        intent.putExtra("bitmap", bitmap);
        this.setResult(RESULT_OK);
        finish();
    }
}
