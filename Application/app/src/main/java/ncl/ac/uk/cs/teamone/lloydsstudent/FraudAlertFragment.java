package ncl.ac.uk.cs.teamone.lloydsstudent;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

/**
 * Created by Jomin on 03/03/2015.
 */
public class FraudAlertFragment extends Fragment {

    /**
     * method to change the fragment to the fraud contact us xml layout
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View v = inflater.inflate(R.layout.fraud_contactus, container, false);

        v.findViewById(R.id.fraudreportus).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                android.support.v4.app.FragmentTransaction transaction = getFragmentManager().beginTransaction();
                ((FrameLayout)getActivity().findViewById(android.R.id.tabcontent)).removeAllViews();
                transaction.replace(android.R.id.tabcontent, new FraudReport(), "FraudReportOnline");
                transaction.commit();
            }
        });

        v.findViewById(R.id.fraudcallus).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri number = Uri.parse("tel:0845 3000 116");
                Intent callIntent = new Intent(Intent.ACTION_DIAL, number);
                startActivity(callIntent);
            }
        });

        return v;

    }
}