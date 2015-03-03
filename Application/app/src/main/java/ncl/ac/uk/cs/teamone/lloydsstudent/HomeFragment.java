package ncl.ac.uk.cs.teamone.lloydsstudent;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Jomin on 19/02/2015.
 */
public class HomeFragment extends Fragment {




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        final View v =  inflater.inflate(R.layout.tab2_main, container, false);

        v.findViewById(R.id.currentMoney).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                        android.support.v4.app.FragmentTransaction transaction = getFragmentManager().beginTransaction();
                 ((FrameLayout)getActivity().findViewById(android.R.id.tabcontent)).removeAllViews();
                        transaction.replace(android.R.id.tabcontent, new OverviewFragment());
                        transaction.commit();


            }
        });

        v.findViewById(R.id.rightArrowAccount).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View ve) {
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
        });

        v.findViewById(R.id.leftArrowAccount).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View ve) {
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

            }
        });




        return v;

    }





}
