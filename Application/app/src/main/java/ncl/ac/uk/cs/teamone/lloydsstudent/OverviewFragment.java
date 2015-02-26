package ncl.ac.uk.cs.teamone.lloydsstudent;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Jomin on 13/02/2015.
 */
public class OverviewFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View v =  inflater.inflate(R.layout.home_tab_transactionhistory, container, false);



        v.findViewById(R.id.lastMonthButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("Testing", "Button Clicked");
                v.findViewById(R.id.scrollvisibility).setVisibility(View.VISIBLE);
            }
        });










        return v;
    }





}
