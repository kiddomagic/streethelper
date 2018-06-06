package tc.streethelper.streethelper;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity {

    private TextView mTextMessage;
    private ImageButton mBtnFund;
    private ImageButton mBtnFindPlace;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_help:
                    mTextMessage.setText(R.string.help);
                    return true;
                case R.id.navigation_info:
                    mTextMessage.setText(R.string.app_info);
                    return true;
            }
            return false;
        }
    };

    private void initialView(){
        mBtnFund = (ImageButton) findViewById(R.id.btnFund);
        mBtnFindPlace = (ImageButton) findViewById(R.id.btnFindPlace);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    public void clickToRaise(View view) {
        mBtnFund.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, ContentFundRaisingActivity.class);
                startActivity(intent);
            }
        });
    }

    public void clickToFindPlace(View view) {
        mBtnFindPlace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( HomeActivity.this, FindSleepPlaceActivity.class);
                startActivity(intent);
            }
        });
    }
}
