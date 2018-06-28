package tc.streethelper.streethelper;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.io.Serializable;
import java.util.ArrayList;

public class ContentFundRaisingActivity extends AppCompatActivity {

    private ListView mLvCharacters;
    private ArrayList<FundingCharacter> mCharacters;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_fund_raising);
        initData();
        mLvCharacters = (ListView) findViewById(R.id.listView);
        FundingCharacterAdapter fundingCharacterAdapter = new FundingCharacterAdapter(this, 0, mCharacters);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        finish();
    }

    private void initData() {
        mCharacters.add(new FundingCharacter("Lê Thị Thắm", "Cuộc sống hiện tại của bác rất khó khăn, tiền"
                + " ăn nhiều khi không có chứ nói gì tiền để thuê nhà trọ. Nhiều đêm ngủ sợ bị đám thanh niên trẻ cướp tiền"
                + "và mấy chai ve chai nhỏ. Nguyện vọng của bác là có cái nghề để có thể có tiền thuê nhà trọ, trả tiền ăn.", "Không có tiền thuê nhà trọ, mong ước có một cái nghề để thuê nhà trọ, trả tiền ăn", 5000000,1000000, "https://drive.google.com/file/d/1sKFR4tLjvmEyh4AVi3Is7frvZ-DX-sYW/view"));
        mCharacters.add(new FundingCharacter("Lê Thị Thắm", "Cuộc sống hiện tại của bác rất khó khăn, tiền"
                + " ăn nhiều khi không có chứ nói gì tiền để thuê nhà trọ. Nhiều đêm ngủ sợ bị đám thanh niên trẻ cướp tiền"
                + "và mấy chai ve chai nhỏ. Nguyện vọng của bác là có cái nghề để có thể có tiền thuê nhà trọ, trả tiền ăn.", "Không có tiền thuê nhà trọ, mong ước có một cái nghề để thuê nhà trọ, trả tiền ăn", 5000000,1000000, "https://drive.google.com/file/d/1sKFR4tLjvmEyh4AVi3Is7frvZ-DX-sYW/view"));
        mCharacters.add(new FundingCharacter("Lê Thị Thắm", "Cuộc sống hiện tại của bác rất khó khăn, tiền"
                + " ăn nhiều khi không có chứ nói gì tiền để thuê nhà trọ. Nhiều đêm ngủ sợ bị đám thanh niên trẻ cướp tiền"
                + "và mấy chai ve chai nhỏ. Nguyện vọng của bác là có cái nghề để có thể có tiền thuê nhà trọ, trả tiền ăn.", "Không có tiền thuê nhà trọ, mong ước có một cái nghề để thuê nhà trọ, trả tiền ăn", 5000000,1000000, "https://drive.google.com/file/d/1sKFR4tLjvmEyh4AVi3Is7frvZ-DX-sYW/view"));
        mLvCharacters.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Intent intent = new Intent( ContentFundRaisingActivity.this, ContentCharacterActivity.class);
                intent.putExtra("Character", (Serializable) mCharacters.get(position));
                startActivity(intent);
            }
        });
    }

    public void clickToSort(View view) {
    }
}
