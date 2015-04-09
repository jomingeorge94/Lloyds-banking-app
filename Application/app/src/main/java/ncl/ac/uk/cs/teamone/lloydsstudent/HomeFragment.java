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
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;

/**
 * Created by Jomin on 19/02/2015.
 */
public class HomeFragment extends Fragment {
    View v;
    private static final int SWIPE_MIN_DISTANCE = 120;
    private static final int SWIPE_MAX_OFF_PATH = 250;
    private static final int SWIPE_THRESHOLD_VELOCITY = 200;
    private GestureDetector gestureDetector;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        final View v =  inflater.inflate(R.layout.tab2_main, container, false);

        //get the customer data
        Data data = new Data();
        //Set the customer name
        TextView customerName = (TextView) v.findViewById(R.id.customerName);

        customerName.setText(data.customer.get("firstname") + " " + data.customer.get("lastname"));

        gestureDetector = new GestureDetector(getActivity(), new MyGestureDetector());
        gestureListener = new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                return gestureDetector.onTouchEvent(event);
            }
        };

        v.findViewById(R.id.moneyStuff).setOnTouchListener(gestureListener);
        v.findViewById(R.id.currentMoney).setOnTouchListener(gestureListener);
        v.findViewById(R.id.leftArrowAccount).setOnTouchListener(gestureListener);
        v.findViewById(R.id.rightArrowAccount).setOnTouchListener(gestureListener);

        //on click event for the text - current money which will change the fragment to Overview Fragment.
        v.findViewById(R.id.currentMoney).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                android.support.v4.app.FragmentTransaction transaction = getFragmentManager().beginTransaction();
                ((FrameLayout)getActivity().findViewById(android.R.id.tabcontent)).removeAllViews();
                transaction.replace(android.R.id.tabcontent, new OverviewFragment(), "OverView");
                transaction.commit();
            }
        });

        //onclick event for the button - make a transfer
        final Button maketransfer = (Button)v.findViewById(R.id.makeTransferButton);
        maketransfer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                android.support.v4.app.FragmentTransaction transaction = getFragmentManager().beginTransaction();
                ((FrameLayout)getActivity().findViewById(android.R.id.tabcontent)).removeAllViews();
                transaction.replace(android.R.id.tabcontent, new MakeaTransfer(), "");
                transaction.commit();
            }
        });

        final Button paycontact = (Button)v.findViewById(R.id.payContactButton);
        paycontact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                android.support.v4.app.FragmentTransaction transaction = getFragmentManager().beginTransaction();
                ((FrameLayout)getActivity().findViewById(android.R.id.tabcontent)).removeAllViews();
                transaction.replace(android.R.id.tabcontent, new PayaContact(), "");
                transaction.addToBackStack("");
                transaction.commit();
            }
        });

        this.v = v;

        return v;




    }

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


                    final ImageButton leftarrow = (ImageButton)v.findViewById(R.id.leftArrowAccount);
                    Animation anim4 = AnimationUtils.loadAnimation(getActivity().getApplicationContext(),
                            R.anim.left_to_right);
                    leftarrow.startAnimation(anim4);

                    final ImageButton rightarrow = (ImageButton)v.findViewById(R.id.rightArrowAccount);
                    Animation anim5 = AnimationUtils.loadAnimation(getActivity().getApplicationContext(),
                            R.anim.left_to_right);
                    rightarrow.startAnimation(anim5);

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

                    final ImageButton leftarrow = (ImageButton)v.findViewById(R.id.leftArrowAccount);
                    Animation anim4 = AnimationUtils.loadAnimation(getActivity().getApplicationContext(),
                            R.anim.right_to_left);
                    leftarrow.startAnimation(anim4);

                    final ImageButton rightarrow = (ImageButton)v.findViewById(R.id.rightArrowAccount);
                    Animation anim5 = AnimationUtils.loadAnimation(getActivity().getApplicationContext(),
                            R.anim.right_to_left);
                    rightarrow.startAnimation(anim5);
                }
            } catch (Exception e) {
                // nothing
            }
            return false;
        }
    }
}