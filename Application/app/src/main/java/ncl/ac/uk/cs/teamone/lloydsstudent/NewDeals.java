package ncl.ac.uk.cs.teamone.lloydsstudent;

/**
 * Created by Jomin on 15/04/2015.
 */

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class NewDeals extends Fragment {
    ncl.ac.uk.cs.teamone.lloydsstudent.SwipeListView deal;
    ItemAdapter adapter;
    static List<String> forLoved;
    public static List<ItemRow> itemData;
    String string;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        View v=inflater.inflate(R.layout.newdeal,container,false);
        deal=(SwipeListView)v.findViewById(R.id.newDealList);
        itemData=new ArrayList<ItemRow>();
        forLoved=new ArrayList<String>();
        itemData.add(new ItemRow("Reduced Easter Eggs @ Tesco",R.drawable.icon));
        itemData.add(new ItemRow("BluTacPacket Incorrectly",R.drawable.icon));
        itemData.add(new ItemRow("241 Pizza @zizzi with The Co..",R.drawable.icon));
        adapter=new ItemAdapter(getActivity(),R.layout.loathed,itemData);


        deal.setOffsetLeft(convertDpToPixel(260f)); // left side offset
        deal.setOffsetRight(convertDpToPixel(0f)); // right side offset
        deal.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View arg0, MotionEvent arg1) {
                // TODO Auto-generated method stub
                arg0.getParent().requestDisallowInterceptTouchEvent(true);
                return false;
            }
        });
        deal.setSwipeListViewListener(new BaseSwipeListViewListener() {
            @Override
            public void onOpened(int position, boolean toRight) {
                // CAlled first

                if  (!toRight) {
                    Loathed.itemDataLoathed.add(NewDeals.itemData.get(position));
                    NewDeals.itemData.remove(position);
                    Loathed.adapter.notifyDataSetChanged();
                    Toast.makeText(getActivity().getBaseContext(),"Left",Toast.LENGTH_LONG).show();
                }
                else  {
                    Loved.itemDataLoved.add(NewDeals.itemData.get(position));
                    NewDeals.itemData.remove(position);
                    Loved.adapter.notifyDataSetChanged();
                    Toast.makeText(getActivity().getBaseContext(),"Left",Toast.LENGTH_LONG).show();
                }

                adapter.notifyDataSetChanged();

                deal.closeOpenedItems();
            }


            @Override
            public void onClosed(int position, boolean fromRight) {
                // Caslled last

            }
            @Override
            public void onStartOpen(int position, int action, boolean right) {
                Log.i("On click" , "OPEN");
                // TODO Auto-generated method stub
    	/* if  (action==deal.getSwipeActionLeft()) {
    		 Loathed.itemDataLoathed.add(itemData.get(position));
    		 itemData.remove(position);
    		 Toast.makeText(getActivity().getBaseContext(),"Left",Toast.LENGTH_LONG).show();
    	 }
    	 else if(action==deal.getSwipeActionRight()) {
    		 Loved.itemDataLoved.add(itemData.get(position));
    		 itemData.remove(position);
    		 Toast.makeText(getActivity().getBaseContext(),"Right",Toast.LENGTH_LONG).show();
    	 }
    	*/
                adapter.notifyDataSetChanged();
                deal.closeOpenedItems();


            }

            @Override
            public void onListChanged() {
                Log.i("On click" , "change");


            }
            @Override
            public void onClickBackView(int position) {

                // TODO Auto-generated method stub
                deal.closeAnimate(position);
            }


            @Override
            public void onClickFrontView(int position) {
                // TODO Auto-generated method stub
                Log.i("On click" , "FRONT");
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
}