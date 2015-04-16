package ncl.ac.uk.cs.teamone.lloydsstudent;

/**
 * Created by Jomin on 16/04/2015.
 */
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;

import com.viewpagerindicator.TitlePageIndicator;

public class Deals extends Fragment{
    ViewPager viewPager;
    FragmentAdapter adapter;
    TitlePageIndicator indicator;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v=inflater.inflate(R.layout.viewpager_main,container,false);
        viewPager=(ViewPager)v.findViewById(R.id.pager);
        adapter=new FragmentAdapter(getChildFragmentManager());
        viewPager.setAdapter(adapter);
        viewPager.setOnTouchListener(new OnTouchListener() {

            @Override
            public boolean onTouch(View arg0, MotionEvent arg1) {
                return true;
            }
        });

        indicator=(TitlePageIndicator)v.findViewById(R.id.indicator);
        viewPager.setCurrentItem(1);
        indicator.setViewPager(viewPager);
        indicator.setSelectedColor(Color.parseColor("#2FB3E3"));
        indicator.setFooterColor(Color.parseColor("#2FB3E3"));

        return v;
    }
}