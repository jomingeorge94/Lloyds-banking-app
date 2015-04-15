package ncl.ac.uk.cs.teamone.lloydsstudent;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

/**
 * Created by Jomin on 27/03/2015.
 */
public class AccountSwitching extends FragmentPagerAdapter {
    public AccountSwitching(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {

        switch (i){
            case 0:
                return new HomeScreenFirstAccount();
            case 1:
                return new HomeScreenSecondAccount();
        }

        return null;
    }

    @Override
    public int getCount() {
        return 2;
    }
}
