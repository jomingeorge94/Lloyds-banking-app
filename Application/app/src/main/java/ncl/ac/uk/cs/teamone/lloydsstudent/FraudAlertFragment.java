package ncl.ac.uk.cs.teamone.lloydsstudent;
/*kirsty is the best*/
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Jomin on 03/03/2015.
 */
public class FraudAlertFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View v = inflater.inflate(R.layout.fraud_contactus, container, false);

        return v;

    }
}
