package ncl.ac.uk.cs.teamone.lloydsstudent;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

public class ChartAdapter extends FragmentStatePagerAdapter {

    ArrayList<Fragment> pages;

    public ChartAdapter(FragmentManager fm, ArrayList<Fragment> pages) {
        super(fm);
        this.pages = pages;
    }

    @Override
    public int getCount() {
        return pages.size();
    }

    @Override
    public Fragment getItem(int position) {
        if (position < pages.size() & pages.get(position)!=null) {
            return pages.get(position);
        }
        return null;
    }
}