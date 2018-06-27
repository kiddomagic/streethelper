package tc.streethelper.streethelper;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class FundingCharacterAdapter extends ArrayAdapter<FundingCharacter> {

    private Context context;
    private int resource;
    private List<FundingCharacter> arrFunding;

    public FundingCharacterAdapter(Context context, int resource, @NonNull List<FundingCharacter> arrFunding) {
        super(context, resource, arrFunding);
        this.context = context;
        this.resource = resource;
        this.arrFunding = arrFunding;
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
        //viewHolder.imgAvt.setBackgroundResource(fundingCharacter.getCurrentBudget());
        viewHolder.txtName.setText(fundingCharacter.getName());
        viewHolder.txtCurrent.setText(fundingCharacter.getCurrentBudget());
        viewHolder.txtTarget.setText(fundingCharacter.getTargetBudgget());
        viewHolder.txtStorySummary.setText(fundingCharacter.getSummaryStory());

        return super.getView(position, convertView, parent);

    }

    public class ViewHolder {
        TextView txtName, txtStorySummary, txtTarget, txtCurrent;
        ImageView imgAvt;
    }
}
