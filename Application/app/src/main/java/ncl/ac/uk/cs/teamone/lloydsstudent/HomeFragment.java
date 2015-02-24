package ncl.ac.uk.cs.teamone.lloydsstudent;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Jomin on 19/02/2015.
 */
public class HomeFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        final View v =  inflater.inflate(R.layout.tab2_main, container, false);

        /*v.findViewById(R.id.rightArrowAccount).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View g) {
                Log.i("Testing", "Clicked on the TextView");


                TextView mytextview = (TextView) v.findViewById(R.id.accountType);
                mytextview.setText("Saving's Account");



                *//*Animation slide = AnimationUtils.loadAnimation(g.getContext(), R.anim.slidetransition);
                g.startAnimation(slide);*//*
            }
        });*/
        return v;

    }



}
