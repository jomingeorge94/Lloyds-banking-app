package ncl.ac.uk.cs.teamone.lloydsstudent;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * A class which extends page adapter to control the switching of accounts in the View Pager
 * objects of other fragments
 *
 * Created by Jomin on 27/03/2015.
 */
public class AccountSwitchingOverview extends FragmentPagerAdapter {

    // Call to super
    public AccountSwitchingOverview(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {

        // Switch statement to control which fragment should be returned from the adapter
        switch (i){
            case 0:
                // First account
                return new HomeFirstOverview();
            case 1:
                // Second Account
                return new HomeSecondOverview();
        }

        // Error
        return null;
    }

    /**
     * A method which returns the total number of fragments in the adapter
     * @return number of fragments
     */
    @Override
    public int getCount() {
        return 2;
    }
}
