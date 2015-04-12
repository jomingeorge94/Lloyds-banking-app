package ncl.ac.uk.cs.teamone.lloydsstudent;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

/**
 * Created by Jomin on 27/03/2015.
 */
public class HomeScreenFirstAccount extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

        final View v = inflater.inflate(R.layout.home_screenmain_firstaccount, container, false);

        v.findViewById(R.id.firstaccountoverview).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                ((LinearLayout)getActivity().findViewById(R.id.tabmainswitch)).removeAllViews();
                transaction.replace(R.id.tabmainswitch, new OverviewFragment(), "");
                transaction.commit();
            }
        });

        return v;
    }

}
