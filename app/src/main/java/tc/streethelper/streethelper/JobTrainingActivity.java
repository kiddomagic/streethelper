package tc.streethelper.streethelper;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class JobTrainingActivity extends AppCompatActivity {

    private ArrayList<Company> companies = new ArrayList<>();
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_training);
        initData();
        CompanyAdapter adapter = new CompanyAdapter(JobTrainingActivity.this, R.layout.company_list, companies);
        listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(adapter);
    }

    private void initData() {
        companies.add(new Company("Phở 24h - Gò Vấp", "11 Nguyễn Oanh, Phường 10, Gò Vấp, Hồ Chí Minh", "028 3849 1716"));
        companies.add(new Company("Bò né 3 ngon", "C104A Tổ 17 KP1, Đông Hưng Thuận, Quận 12, Hồ Chí Minh", "090 984 57 78"));
        companies.add(new Company("Grab - Việt Nam", "268 Tô Hiến Thành, Phường 15, Quận 10, Hồ Chí Minh", "028 7108 7108"));
        companies.add(new Company("Bánh mì Như Ý 154","154 Đông Bắc, Tân Hưng Thuận, Quận 12, Hồ Chí Minh", "Không có"));
        companies.add(new Company("Trường Trung Cấp Nghề Quang Trung", "538 Quang Trung, Phường 8, Gò Vấp, Hồ Chí Minh", "028 3894 6097"));
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Toast.makeText(JobTrainingActivity.this, "Đăng kí thành công, Thông tin có trong thời gian tới", Toast.LENGTH_SHORT).show();
    }

    public void clickToAdd(View view) {
        Intent intent  = new Intent( JobTrainingActivity.this, AddCompanyActivity.class);
        Bundle bundle = new Bundle();
        ArrayList<String> temp = new ArrayList<>();
        for (int i = 0; i < companies.size(); i++) {
            temp.add(companies.get(i).getName());
        }
        bundle.putStringArrayList("name", temp);
        temp.clear();
        for (int i = 0; i < companies.size(); i++) {
            temp.add(companies.get(i).getAddress());
        }
        bundle.putStringArrayList("address", temp);
        temp.clear();
        for (int i = 0; i < companies.size(); i++) {
            temp.add(companies.get(i).getPhone());
        }
        bundle.putStringArrayList("phone", temp);
        intent.putExtra( "companies", bundle);
        startActivity(intent);
    }
}
