package ncl.ac.uk.cs.teamone.lloydsstudent;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Jomin on 19/02/2015.
 */
public class HomeFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View v =  inflater.inflate(R.layout.tab2_main, container, false);
        v.findViewById(R.id.rightArrowAccount).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View g) {



              ( (TextView) v.findViewById(R.id.accountType)).clearComposingText();
                ( (TextView) v.findViewById(R.id.accountType)).setText(null);
                ( (TextView) v.findViewById(R.id.accountType)).setText("Saving Account");
            }
        });
        return v;
    }



}
