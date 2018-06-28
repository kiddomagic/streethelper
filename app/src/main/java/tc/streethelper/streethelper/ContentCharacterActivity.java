package tc.streethelper.streethelper;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

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
	    imgChr = (ImageButton) findViewById(R.id.imageButton2);
        mTxtName.setText(intent.getStringExtra("name"));
        mTxtStory.setText(intent.getStringExtra("story"));
        mTxtTarget.setText("" + intent.getStringExtra("target"));
        mTxtCurrent.setText("" + intent.getStringExtra("current"));
        Picasso.get().load(intent.getStringExtra("image")).into(imgChr);
    }
}
