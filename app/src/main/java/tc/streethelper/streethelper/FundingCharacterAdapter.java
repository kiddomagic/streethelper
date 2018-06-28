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

public class FundingCharacterAdapter extends ArrayAdapter<FundingCharacter> {

    private Context context;
    private int resource;
    private List<FundingCharacter> arrFunding;
    private LayoutInflater inflater;

    public FundingCharacterAdapter(Context context, int resource, @NonNull List<FundingCharacter> arrFunding) {
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
            viewHolder.txtName = (TextView) convertView.findViewById(R.id.txtName);
            viewHolder.txtStorySummary = (TextView) convertView.findViewById(R.id.txtSummaryStory);
            viewHolder.txtTarget = (TextView) convertView.findViewById(R.id.txtTarget);
            viewHolder.txtCurrent = (TextView) convertView.findViewById(R.id.txtCurrent);
            viewHolder.imgAvt = (ImageView) convertView.findViewById(R.id.imgView);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        FundingCharacter fundingCharacter = arrFunding.get(position);
        Picasso.get().load(fundingCharacter.getImgTag()).into(viewHolder.imgAvt);
        viewHolder.txtName.setText(fundingCharacter.getName());
        viewHolder.txtCurrent.setText(fundingCharacter.getCurrentBudget());
        viewHolder.txtTarget.setText(fundingCharacter.getTargetBudgget());
        viewHolder.txtStorySummary.setText(fundingCharacter.getSummaryStory());

        return convertView;

    }

    public class ViewHolder {
        TextView txtName, txtStorySummary, txtTarget, txtCurrent;
        ImageView imgAvt;
    }
}
