package tc.streethelper.streethelper;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

public class ContentCharacterActivity extends AppCompatActivity {

    private TextView mTxtName, mTxtStory, mTxtTarget, mTxtCurrent;
    private ImageView imgChr;
    private ProgressBar mProgresBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_character);
	    Intent intent = getIntent();
	    mTxtName = (TextView) findViewById(R.id.textView);
	    mTxtStory = (TextView) findViewById(R.id.editText);
	    mTxtTarget = (TextView) findViewById(R.id.textView3);
	    mTxtCurrent = (TextView) findViewById(R.id.textView4);
	    imgChr = (ImageView) findViewById(R.id.imageButton2);
	    mProgresBar = (ProgressBar) findViewById(R.id.progressBar);
        mTxtName.setText(intent.getStringExtra("name"));
        mTxtStory.setText(intent.getStringExtra("story"));
        String target = intent.getStringExtra("target");
        String current = intent.getStringExtra("current");
        mTxtTarget.setText("Target: " + target.trim());
        mTxtCurrent.setText("Current: " + current.trim());
        mProgresBar.setMax(Integer.parseInt(target.trim()));
        mProgresBar.setProgress(Integer.parseInt(current.trim()));
        Picasso.get().load(intent.getStringExtra("image")).into(imgChr);
    }
}
