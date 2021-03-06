package ncl.ac.uk.cs.teamone.lloydsstudent;

/**
 * Created by Jomin on 15/04/2015.
 */
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class ItemAdapter extends ArrayAdapter {
    List  data;
    Context context;
    int layoutResID;

    public ItemAdapter(Context context, int layoutResourceId,List data) {
        super(context, layoutResourceId, data);

        this.data=data;
        this.context=context;
        this.layoutResID=layoutResourceId;

        // TODO Auto-generated constructor stub
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        NewsHolder holder = null;
        View row = convertView;
        holder = null;

        if(row == null)
        {
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(layoutResID, parent, false);

            holder = new NewsHolder();

            holder.dealtitle = (TextView)row.findViewById(R.id.dealtitle);
            holder.icon=(ImageView)row.findViewById(R.id.dealicon);
            //      holder.button1=(Button)row.findViewById(R.id.swipe_button1);
            //      holder.button2=(Button)row.findViewById(R.id.swipe_button2);
            //      holder.button3=(Button)row.findViewById(R.id.swipe_button3);
            row.setTag(holder);
        }
        else
        {
            holder = (NewsHolder)row.getTag();
        }

        ItemRow itemdata = (ItemRow) data.get(position);
        holder.dealtitle.setText(itemdata.getItemName());
        holder.icon.setImageResource((int) itemdata.getIcon());





        return row;

    }

    static class NewsHolder{

        TextView dealtitle;
        ImageView icon;

    }

}


