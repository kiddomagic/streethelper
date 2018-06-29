package tc.streethelper.streethelper;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class AddCompanyActivity extends AppCompatActivity {

    private List<Company> companies = new ArrayList<>();
    private EditText edtComp, edtAddr, edtPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_company);
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("companies");
        ArrayList<String> name = bundle.getStringArrayList("name");
        ArrayList<String> address = bundle.getStringArrayList("address");
        ArrayList<String> phone = bundle.getStringArrayList("phone");
        for (int i = 0; i < name.size(); i++) {
            companies.add(new Company(name.get(i), address.get(i), phone.get(i)));
        }
        edtComp = (EditText) findViewById(R.id.edtCompany);
        edtAddr = (EditText) findViewById(R.id.edtAddress);
        edtPhone = (EditText) findViewById(R.id.edtPhone);
    }

    public void clickToSave(View view) {
        companies.add( new Company(edtComp.getText().toString(), edtAddr.getText().toString(), edtPhone.getText().toString()));
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
        super.onBackPressed();
    }
}
