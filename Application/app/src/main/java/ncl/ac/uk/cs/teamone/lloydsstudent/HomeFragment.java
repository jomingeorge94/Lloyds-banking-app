package ncl.ac.uk.cs.teamone.lloydsstudent;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

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


        return v;

    }





}
