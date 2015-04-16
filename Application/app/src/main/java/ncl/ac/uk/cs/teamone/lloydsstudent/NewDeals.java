package ncl.ac.uk.cs.teamone.lloydsstudent;

/**
 * Created by Jomin on 15/04/2015.
 */
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.fortysevendeg.swipelistview.BaseSwipeListViewListener;
import com.fortysevendeg.swipelistview.SwipeListView;

import java.util.ArrayList;
import java.util.List;

public class NewDeals extends Fragment {
    SwipeListView deal;
    ItemAdapter adapter;
    List<ItemRow> itemData;
    String string;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        View v=inflater.inflate(R.layout.newdeal,container,false);
        deal=(SwipeListView)v.findViewById(R.id.newDealList);
        itemData=new ArrayList<ItemRow>();
        itemData.add(new ItemRow("Reduced Easter Eggs @ Tesco",R.drawable.icon));
        itemData.add(new ItemRow("BluTacPacket Incorrectly",R.drawable.icon));
        itemData.add(new ItemRow("241 Pizza @zizzi with The Co..",R.drawable.icon));
        adapter=new ItemAdapter(getActivity(),R.layout.loathed,itemData);
        deal.setSwipeListViewListener(new BaseSwipeListViewListener() {
            @Override
            public void onOpened(int position, boolean toRight) {
                if(toRight)
                    Toast.makeText(getActivity().getBaseContext(),"Right",Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(getActivity().getBaseContext(), "Left",Toast.LENGTH_LONG).show();
            }

            @Override
            public void onClosed(int position, boolean fromRight) {
            }
            @Override
            public void onStartOpen(int position, int action, boolean right) {
                // TODO Auto-generated method stub

                if(right) {
                    itemData.remove(position);
                } else {
                    Toast.makeText(getActivity().getBaseContext(), itemData.get(position).getItemName(),Toast.LENGTH_LONG).show();
                }
                adapter.notifyDataSetChanged();


            }
            @Override
            public void onListChanged() {
                deal.closeOpenedItems();
            }

            @Override
            public void onMove(int position, float x) {
            }

            @Override
            public void onClickFrontView(int position) {
                // TODO Auto-generated method stub
                super.onClickFrontView(position);
                Toast.makeText(getActivity().getBaseContext(), itemData.get(position).getItemName(),Toast.LENGTH_LONG).show();
            }







        });


        deal.setAdapter(adapter);
        return v;
    }
}
