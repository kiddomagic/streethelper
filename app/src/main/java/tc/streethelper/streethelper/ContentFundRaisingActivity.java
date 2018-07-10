package tc.streethelper.streethelper;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;

public class ContentFundRaisingActivity extends AppCompatActivity {

    private ListView mLvCharacters;
    private ArrayList<FundingCharacter> mCharacters;
    final int REQUEST_HELP  = 50;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_fund_raising);
        mCharacters = new ArrayList<>();
        mLvCharacters = (ListView) findViewById(R.id.listView);
        initData();
        FundingCharacterAdapter adapter = new FundingCharacterAdapter(this, R.layout.funding_character_item, mCharacters);
        mLvCharacters.setAdapter(adapter);
        FundingCharacterAdapter fundingCharacterAdapter = new FundingCharacterAdapter(this, 0, mCharacters);
        mLvCharacters.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Intent intent = new Intent( ContentFundRaisingActivity.this, ContentCharacterActivity.class);
                intent.putExtra("name", (Serializable) mCharacters.get(position).getName());
                intent.putExtra("story", (Serializable) mCharacters.get(position).getStory());
                intent.putExtra("target", (Serializable) mCharacters.get(position).getTargetBudgget());
                intent.putExtra("current", (Serializable) mCharacters.get(position).getCurrentBudget());
                intent.putExtra("image", (Serializable) mCharacters.get(position).getImgTag());
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        finish();
    }

    private void initData() {
        mCharacters.add(new FundingCharacter("Lê Thị Thắm", "Cuộc sống hiện tại của bác rất khó khăn, tiền"
                + " ăn nhiều khi không có chứ nói gì tiền để thuê nhà trọ. Nhiều đêm ngủ sợ bị đám thanh niên trẻ cướp tiền"
                + "và mấy chai ve chai nhỏ. Nguyện vọng của bác là có cái nghề để có thể có tiền thuê nhà trọ, trả tiền ăn.", "Không có tiền thuê nhà trọ, mong ước có một cái nghề để thuê nhà trọ, trả tiền ăn", 5000000,1000000, "https://i.imgur.com/v9EpB9Z.png?1"));
        mCharacters.add(new FundingCharacter("Lê Thị Thắm", "Cuộc sống hiện tại của bác rất khó khăn, tiền"
                + " ăn nhiều khi không có chứ nói gì tiền để thuê nhà trọ. Nhiều đêm ngủ sợ bị đám thanh niên trẻ cướp tiền"
                + "và mấy chai ve chai nhỏ. Nguyện vọng của bác là có cái nghề để có thể có tiền thuê nhà trọ, trả tiền ăn.", "Không có tiền thuê nhà trọ, mong ước có một cái nghề để thuê nhà trọ, trả tiền ăn", 5000000,1000000, "https://i.imgur.com/v9EpB9Z.png?1"));
        mCharacters.add(new FundingCharacter("Lê Thị Thắm", "Cuộc sống hiện tại của bác rất khó khăn, tiền"
                + " ăn nhiều khi không có chứ nói gì tiền để thuê nhà trọ. Nhiều đêm ngủ sợ bị đám thanh niên trẻ cướp tiền"
                + "và mấy chai ve chai nhỏ. Nguyện vọng của bác là có cái nghề để có thể có tiền thuê nhà trọ, trả tiền ăn.", "Không có tiền thuê nhà trọ, mong ước có một cái nghề để thuê nhà trọ, trả tiền ăn", 5000000,1000000, "https://i.imgur.com/v9EpB9Z.png?1"));
    }

    public void clickToSort(View view) {
    }

    public void clickToReport(View view) {
        Intent intent = new Intent(ContentFundRaisingActivity.this, ReportHelpingPersonActivity.class);
        startActivityForResult(intent, REQUEST_HELP);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_HELP && resultCode == RESULT_OK) {
            Toast.makeText( ContentFundRaisingActivity.this, "Thông tin đẩy lên thành công, kiểm duyệt sau 72h", Toast.LENGTH_SHORT);
        }
    }
}
