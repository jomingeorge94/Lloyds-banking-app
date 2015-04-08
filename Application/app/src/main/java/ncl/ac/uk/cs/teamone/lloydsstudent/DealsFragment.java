package ncl.ac.uk.cs.teamone.lloydsstudent;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


import java.util.List;


/**
 * Created by Jomin on 26/02/2015.
 */
public class DealsFragment extends Fragment implements View.OnClickListener {
    private List<Deal> deals = new ArrayList<Deal>();
    private static final int SWIPE_MIN_DISTANCE = 120;
    private static final int SWIPE_MAX_OFF_PATH = 250;
    private static final int SWIPE_THRESHOLD_VELOCITY = 200;
    private GestureDetector gestureDetector;

    //method to switch the fragment, this method will switch the fragment to the deals layout xml file
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View v =  inflater.inflate(R.layout.deals_test, container, false);

        //Populate deals list
        populateDealsList();
        populateDealListView(v);
        //So that when clicked something can happen
       // registerClickCallBack(v, adapter);

        return v;
    }
    //Method to add all of the deals to list to be used to display
    private void populateDealsList() {
        deals.add(new Deal("chocolate",10,"km",10,5,R.drawable.beauty));
        deals.add(new Deal("skirt",10,"km",10,5,R.drawable.clothes));
    }

    //Method to populate the listView with list
    private void populateDealListView(View v){
        //Build adapter
        ArrayAdapter<Deal> adapter = new MyListAdapter();
        ListView list = (ListView) v.findViewById(R.id.listViewMain);
        list.setAdapter(adapter);
    }

    private class MyListAdapter extends ArrayAdapter<Deal>{

        public MyListAdapter() {
            super(getActivity().getApplicationContext(),R.layout.deal_list, deals);
        }
           @Override
        public View getView(int position, View convertView, ViewGroup parent){
               View itemView = convertView;
               if (itemView == null){
                   itemView = getActivity().getLayoutInflater().inflate(R.layout.deal_list, parent, false);
               }

               //Find the deal to work with

               Deal currentDeal = deals.get(position);


               //Fill the view
               ImageView imageView = (ImageView) itemView.findViewById(R.id.itemIcon);
               imageView.setImageResource(currentDeal.getIconId());


               //Make:
               TextView makeText = (TextView) itemView.findViewById(R.id.textDescription);
               makeText.setText(currentDeal.getName());

               TextView lovesText = (TextView) itemView.findViewById(R.id.itemLoves);
               lovesText.setText("" + currentDeal.getLoves());

               TextView loathesText = (TextView) itemView.findViewById(R.id.itemLoathes);
               loathesText.setText("" + currentDeal.getLoathes());

               TextView distanceText = (TextView) itemView.findViewById(R.id.itemDistance);
               distanceText.setText("" + currentDeal.getDistance());

               TextView distanceMeasureText = (TextView) itemView.findViewById(R.id.distanceMeasure);
               distanceMeasureText.setText(currentDeal.getDistanceMeasure());

               return itemView;
           }
    }

    View.OnTouchListener gestureListener;


    //Make the deals move
    class MyGestureDetector extends GestureDetector.SimpleOnGestureListener {
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            try {

                if (Math.abs(e1.getY() - e2.getY()) > SWIPE_MAX_OFF_PATH) {
                    return false;}

                // right to left swipe

                /*final TextView accounttype = (TextView) v.findViewById(R.id.accountType);
                accounttype.setText("Student Account");
                Animation anim = AnimationUtils.loadAnimation(getActivity().getApplicationContext(),
                        R.anim.left_to_right);
                accounttype.startAnimation(anim);*/

                if(e1.getX() - e2.getX() > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {


                    Toast.makeText(getActivity().getApplicationContext(), "This worked", Toast.LENGTH_LONG).show();

                }


            } catch (Exception e) {
                // nothing
            }
            return false;
        }
    }



    //Method to register a click on menu
    public void registerClickCallBack(View v, Adapter adapter){
        ListView list = (ListView) v.findViewById(R.id.listViewMain);
        //Listener for item click
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView textView = (TextView) view;
                String message = "You clicked # " + position + ", which is string: " + textView.getText().toString();

                //Create a pop up window in response to menu click
                //Needs to be altered to show further detail of the deal rather than a pop up message
                Toast.makeText(getActivity().getApplicationContext(), message, Toast.LENGTH_LONG).show();
            }
        });



    }

    public void onClick(View v) {


    }



    
}

