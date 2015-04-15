package ncl.ac.uk.cs.teamone.lloydsstudent;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Jomin on 13/02/2015.
 */

public class OverviewFragment extends Fragment {
    ViewPager viewPager;
    /**
     * method to change the fragment to the home tab transaction xml layout, also implemented the two buttons
     * which will change the data
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View v = inflater.inflate(R.layout.home_tab_transactionhistory, container, false);

        viewPager = (ViewPager) v.findViewById(R.id.accountswitchingoverview);
        AccountSwitchingOverview accountSwitching = new AccountSwitchingOverview(getChildFragmentManager());

        viewPager.setAdapter(accountSwitching);

        return v;
    }
}