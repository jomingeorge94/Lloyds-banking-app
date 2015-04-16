package ncl.ac.uk.cs.teamone.lloydsstudent;

/**
 * Created by Jomin on 15/04/2015.
 */

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;

public class FragmentAdapter extends FragmentStatePagerAdapter {

    public FragmentAdapter(FragmentManager fm) {
        super(fm);
        // TODO Auto-generated constructor stub
    }

    @Override
    public int getItemPosition(Object object){
        return PagerAdapter.POSITION_NONE;
    }

    @Override
    public Fragment getItem(int arg0) {
        Log.i("HI", "I WORK");
        // TODO Auto-generated method stub
        Fragment fragment = new NewDeals();
        switch(arg0){
            case 0:
                fragment = new Loathed();
                break;
            case 1:
                fragment = new NewDeals();
                break;
            case 2:
                fragment = new Loved();
                break;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        // TODO Auto-generated method stub
        String title = "";
        switch(position){
            case 0:
                title = "Loathed";
                break;
            case 1:
                title = "New Deals";
                break;
            case 2:
                title = "Loved";
                break;
        }
        return title;
    }


}
