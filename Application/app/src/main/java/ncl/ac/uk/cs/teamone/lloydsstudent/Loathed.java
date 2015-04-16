package ncl.ac.uk.cs.teamone.lloydsstudent;

/**
 * Created by Jomin on 15/04/2015.
 */

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


public class Loathed extends Fragment {
    ncl.ac.uk.cs.teamone.lloydsstudent.SwipeListView deal;
    static ItemAdapter adapter;
    static List<ItemRow> itemDataLoathed = new ArrayList<ItemRow>();;
    String string;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        View v=inflater.inflate(R.layout.newdeal,container,false);
        deal=(SwipeListView)v.findViewById(R.id.newDealList);
        NewDeals.click = true;
        adapter=new ItemAdapter(getActivity(),R.layout.loathed, Loathed.itemDataLoathed);

        Log.i("WHEN AM ILOADED", "NOW?");
        if(itemDataLoathed != null)
            Log.i("i", itemDataLoathed.toString());

        deal.setSwipeListViewListener(new BaseSwipeListViewListener() {

            @Override
            public void onOpened(int position, boolean toRight) {
                // TODO Auto-generated method stub
                itemDataLoathed.remove(position);


                adapter.notifyDataSetChanged();


                deal.closeOpenedItems();
            }

            @Override
            public void onStartClose(int position, boolean right) {
                // TODO Auto-generated method stub
                adapter.notifyDataSetChanged();
            }


            @Override
            public void onClosed(int position, boolean fromRight) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onListChanged() {
                // TODO Auto-generated method stub

            }

            @Override
            public void onMove(int position, float x) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onClickFrontView(int position) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onClickBackView(int position) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onDismiss(int[] reverseSortedPositions) {
                // TODO Auto-generated method stub

            }

            @Override
            public int onChangeSwipeMode(int position) {
                // TODO Auto-generated method stub
                return 0;
            }

            @Override
            public void onChoiceChanged(int position, boolean selected) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onChoiceStarted() {
                // TODO Auto-generated method stub

            }

            @Override
            public void onChoiceEnded() {
                // TODO Auto-generated method stub

            }

            @Override
            public void onFirstListItem() {
                // TODO Auto-generated method stub

            }

            @Override
            public void onLastListItem() {
                // TODO Auto-generated method stub

            }


        });

        deal.setAdapter(adapter);
        return v;
    }
}

