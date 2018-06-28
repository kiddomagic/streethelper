package tc.streethelper.streethelper;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ContentCharacterActivity extends AppCompatActivity {

    private TextView mTxtName, mTxtStory, mTxtTarget, mTxtCurrent;
    private ImageButton imgChr;	

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_character);
	Intent intent = getIntent();
	mTxtName = (TextView) findViewById(R.id.textView);
	mTxtStory = (TextView) findViewById(R.id.editText);
	mTxtTarget = (TextView) findViewById(R.id.textView3);
	mTxtCurrent = (TextView) findViewById(R.id.textView4);
    }
}
