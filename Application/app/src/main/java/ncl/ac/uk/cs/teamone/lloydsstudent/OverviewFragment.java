package ncl.ac.uk.cs.teamone.lloydsstudent;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

/**
 * Created by Jomin on 13/02/2015.
 */
public class OverviewFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View v = inflater.inflate(R.layout.home_tab_transactionhistory, container, false);


        v.findViewById(R.id.lastMonthButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                v.findViewById(R.id.scrollvisibility).setVisibility(View.VISIBLE);
            }
        });

        v.findViewById(R.id.thisMonth).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(getActivity(), "Last Month Button is Clicked", Toast.LENGTH_SHORT).show();


            }
        });

        final ImageButton b = (ImageButton)v.findViewById(R.id.fraudalert1);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FraudAlertDialogBox dialog = new FraudAlertDialogBox();
                dialog.show(getFragmentManager(),"Hellooo");
            }
        });

        return v;
    }








}
