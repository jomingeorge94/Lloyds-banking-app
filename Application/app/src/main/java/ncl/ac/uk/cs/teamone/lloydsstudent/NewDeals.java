package ncl.ac.uk.cs.teamone.lloydsstudent;

/**
 * Created by Jomin on 15/04/2015.
 */

import android.graphics.Point;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
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
    float WHERE;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        View v=inflater.inflate(R.layout.newdeal,container,false);
        deal=(SwipeListView)v.findViewById(R.id.newDealList);
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




        deal.setOffsetLeft(convertDpToPixel(260f)); // left side offset
        deal.setOffsetRight(convertDpToPixel(0f)); // right side offset
        deal.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View arg0, MotionEvent arg1) {
                // TODO Auto-generated method stub

                    WHERE = arg1.getX();
                    /** Intercepts touches from going through. */

                arg0.getParent().requestDisallowInterceptTouchEvent(true);
                return false;
            }
        });



        deal.setSwipeListViewListener(new BaseSwipeListViewListener() {
            @Override
            public void onOpened(int position, boolean toRight) {




            }


            @Override
            public void onClosed(int position, boolean fromRight) {
                // Caslled last

            }
            @Override
            public void onStartOpen(int position, int action, boolean right) {
                Log.i("On click" , "OPEN");

                adapter.notifyDataSetChanged();
                deal.closeOpenedItems();


            }

            @Override
            public void onListChanged() {
                Log.i("On click" , "change");


            }
            @Override
            public void onClickBackView(int position) {


                final ArrayList<Integer> fix = new ArrayList<Integer>();
                fix.add(position);

                deal.getChildAt(position).findViewById(R.id.brokenheart).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.i("WHAT THE FUCK",NewDeals.itemData.get(0).itemName);

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

                // TODO Auto-generated method stub
                deal.closeAnimate(position);
            }


            @Override
            public void onClickFrontView(int position) {
                // TODO Auto-generated method stub

                deal.openAnimate(position);

            }






        });


        deal.setAdapter(adapter);
        return v;
    }

    public int convertDpToPixel(float dp) {
        DisplayMetrics metrics = getResources().getDisplayMetrics();
        float px = dp * (metrics.densityDpi / 160f);
        return (int) px;
    }

    public int getSize(){
        Display display = getActivity().getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        return size.x;
    }


}