package ncl.ac.uk.cs.teamone.lloydsstudent;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by Jomin on 27/03/2015.
 */
public class PagerAdapter extends FragmentPagerAdapter {
    public PagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {

        switch (i){
            case 0:
                return new LloydsBankingTourOne();
            case 1:
                return new LloydsBankingTourTwo();
            case 2:
                return new LloydsBankingTourThree();
            case 3:
                return new LloydsBankingTourFour();
            case 4:
                return new LloydsBankingTourFive();
            case 5:
                return new LloydsBankingTourSix();
            case 6:
                return new LloydsBankingTourSeven();

        }

        return null;
    }

    @Override
    public int getCount() {
        return 7;
    }
}
