package tc.streethelper.streethelper;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class CompanyAdapter extends ArrayAdapter<Company> {
    private Context context;
    private int resource;
    private List<Company> arrFunding;
    private LayoutInflater inflater;

    public CompanyAdapter(Context context, int resource, @NonNull List<Company> arrFunding) {
        super(context, resource, arrFunding);
        this.context = context;
        this.resource = resource;
        this.arrFunding = arrFunding;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.funding_character_item, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.txtName = (TextView) convertView.findViewById(R.id.txtCompany);
            viewHolder.txtAddress = (TextView) convertView.findViewById(R.id.txtAddress);
            viewHolder.txtPhone = (TextView) convertView.findViewById(R.id.txtPhone);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        Company company = arrFunding.get(position);
        viewHolder.txtName.setText(company.getName());
        viewHolder.txtAddress.setText(company.getAddress());
        viewHolder.txtPhone.setText(company.getPhone());
        return convertView;

    }

    public class ViewHolder {
        TextView txtName, txtAddress, txtPhone;
    }
}
