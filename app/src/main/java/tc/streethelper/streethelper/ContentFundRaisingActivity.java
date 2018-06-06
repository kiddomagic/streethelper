package tc.streethelper.streethelper;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ContentFundRaisingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_fund_raising);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        finish();
    }
}
