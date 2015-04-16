package ncl.ac.uk.cs.teamone.lloydsstudent;

/**
 * Created by Jomin on 15/04/2015.
 */

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


public class NewDeals extends Fragment {
    ncl.ac.uk.cs.teamone.lloydsstudent.SwipeListView deal;
    ItemAdapter adapter;
    static List<String> forLoved;
    public static List<ItemRow> itemData;
    static int COUNTER = 0;
    String string;
    public static boolean click = false;
    float WHERE;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        View v=inflater.inflate(R.layout.newdeal,container,false);
        deal=(SwipeListView)v.findViewById(R.id.newDealList);
        NewDeals.click = false;
        deal.setSwipeOpenOnLongPress(false);

        if(COUNTER == 0){
            itemData=new ArrayList<ItemRow>();
            forLoved=new ArrayList<String>();
            itemData.add(new ItemRow("Reduced Easter Eggs @ Tesco",R.drawable.icon));
            itemData.add(new ItemRow("BluTacPacket Incorrectly",R.drawable.icon));
            itemData.add(new ItemRow("241 Pizza @zizzi with ",R.drawable.icon));
            itemData.add(new ItemRow("£5 top up with EE",R.drawable.icon));
            COUNTER++;
        }


        adapter=new ItemAdapter(getActivity(),R.layout.loathed,itemData);





        deal.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View arg0, MotionEvent arg1) {

                arg0.getParent().requestDisallowInterceptTouchEvent(true);
                return false;
            }
        });



        deal.setSwipeListViewListener(new BaseSwipeListViewListener() {



            @Override
            public void onClickBackView(int position) {


                final ArrayList<Integer> fix = new ArrayList<Integer>();
                fix.add(position);

                deal.getChildAt(position).findViewById(R.id.brokenheart).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Loathed.itemDataLoathed.add(NewDeals.itemData.get(fix.get(fix.size()-1)));
                        NewDeals.itemData.remove(fix.get(fix.size()-1).intValue());
                        Loathed.adapter.notifyDataSetChanged();
                        adapter.notifyDataSetChanged();

                        deal.closeOpenedItems();
                    }
                });

                deal.getChildAt(position).findViewById(R.id.heart).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Loved.itemDataLoved.add(NewDeals.itemData.get(fix.get(fix.size()-1)));
                        NewDeals.itemData.remove(fix.get(fix.size()-1).intValue());
                        Loved.adapter.notifyDataSetChanged();
                        adapter.notifyDataSetChanged();

                        deal.closeOpenedItems();
                    }
                });


            }





        });


        deal.setAdapter(adapter);
        return v;
    }






}