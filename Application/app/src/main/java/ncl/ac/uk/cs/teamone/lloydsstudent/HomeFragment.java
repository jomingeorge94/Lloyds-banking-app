package ncl.ac.uk.cs.teamone.lloydsstudent;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.TextView;

/**
 * Created by Jomin on 19/02/2015.
 */
public class HomeFragment extends Fragment {
    View v;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        final View v =  inflater.inflate(R.layout.tab2_main, container, false);
        //get the customer data
        Data data = new Data();
        //Set the customer name
        TextView customerName = (TextView) v.findViewById(R.id.customerName);

        customerName.setText(data.customer.get("firstname") + " " + data.customer.get("lastname"));

        v.findViewById(R.id.currentMoney).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                android.support.v4.app.FragmentTransaction transaction = getFragmentManager().beginTransaction();
                ((FrameLayout) getActivity().findViewById(android.R.id.tabcontent)).removeAllViews();
                transaction.replace(android.R.id.tabcontent, new OverviewFragment());
                transaction.commit();

                gestureDetector = new GestureDetector(getActivity(), new MyGestureDetector());

                gestureListener = new View.OnTouchListener() {
                    public boolean onTouch(View v, MotionEvent event) {
                        return gestureDetector.onTouchEvent(event);
                    }
                };

            }
        });



        v.findViewById(R.id.moneyStuff).setOnTouchListener(gestureListener);
        v.findViewById(R.id.currentMoney).setOnTouchListener(gestureListener);
        v.findViewById(R.id.leftArrowAccount).setOnTouchListener(gestureListener);
        v.findViewById(R.id.rightArrowAccount).setOnTouchListener(gestureListener);

        v.findViewById(R.id.currentMoney).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                android.support.v4.app.FragmentTransaction transaction = getFragmentManager().beginTransaction();
                ((FrameLayout)getActivity().findViewById(android.R.id.tabcontent)).removeAllViews();
                transaction.replace(android.R.id.tabcontent, new OverviewFragment());
                transaction.commit();
            }
        });

        return v;
    }


    private static final int SWIPE_MIN_DISTANCE = 120;
    private static final int SWIPE_MAX_OFF_PATH = 250;
    private static final int SWIPE_THRESHOLD_VELOCITY = 200;
    private GestureDetector gestureDetector;
    View.OnTouchListener gestureListener;

    class MyGestureDetector extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            try {

                if (Math.abs(e1.getY() - e2.getY()) > SWIPE_MAX_OFF_PATH) {
                    return false;
                }

                // right to left swipe
                if(e1.getX() - e2.getX() > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {

                    final TextView accounttype = (TextView) v.findViewById(R.id.accountType);
                    accounttype.setText("Student Account");
                    Animation anim = AnimationUtils.loadAnimation(getActivity().getApplicationContext(),
                            R.anim.left_to_right);
                    accounttype.startAnimation(anim);


                    final TextView money = (TextView) v.findViewById(R.id.currentMoney);
                    money.setText("£2000.00");
                    Animation anim2 = AnimationUtils.loadAnimation(getActivity().getApplicationContext(),
                            R.anim.left_to_right);
                    money.startAnimation(anim2);

                    final TextView accountdetails = (TextView) v.findViewById(R.id.accountDetails);
                    accountdetails.setText("20-14-19 | 12345678");
                    Animation anim3 = AnimationUtils.loadAnimation(getActivity().getApplicationContext(),
                            R.anim.left_to_right);
                    accountdetails.startAnimation(anim3);

                }  else if (e2.getX() - e1.getX() > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {

                    final TextView accounttype = (TextView) v.findViewById(R.id.accountType);
                    accounttype.setText("Savings Account");
                    Animation anim = AnimationUtils.loadAnimation(getActivity().getApplicationContext(),
                            R.anim.right_to_left);
                    accounttype.startAnimation(anim);


                    final TextView money = (TextView) v.findViewById(R.id.currentMoney);
                    money.setText("£1307.27");
                    Animation anim2 = AnimationUtils.loadAnimation(getActivity().getApplicationContext(),
                            R.anim.right_to_left);
                    money.startAnimation(anim2);

                    final TextView accountdetails = (TextView) v.findViewById(R.id.accountDetails);
                    accountdetails.setText("12-87-12 | 78670912");
                    Animation anim3 = AnimationUtils.loadAnimation(getActivity().getApplicationContext(),
                            R.anim.right_to_left);
                    accountdetails.startAnimation(anim3);

                }
            } catch (Exception e) {
                // nothing
            }

            return false;

        }
    }
}

