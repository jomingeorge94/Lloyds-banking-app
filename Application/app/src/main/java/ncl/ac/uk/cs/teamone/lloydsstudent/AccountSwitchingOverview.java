package ncl.ac.uk.cs.teamone.lloydsstudent;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by Jomin on 27/03/2015.
 */
public class AccountSwitchingOverview extends FragmentPagerAdapter {
    public AccountSwitchingOverview(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {

        switch (i){
            case 0:
                return new HomeScreenFirstAccountOverview();
            case 1:
                return new HomeScreenSecondAccountOverview();
        }

        return null;
    }

    @Override
    public int getCount() {
        return 2;
    }
}
