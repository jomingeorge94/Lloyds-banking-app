package ncl.ac.uk.cs.teamone.lloydsstudent;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.RelativeLayout;

/**
 * Created by Jomin on 01/03/2015.
 */
public class FraudAlertDialogBox extends android.support.v4.app.DialogFragment {

    /**
     * method which handles the alert dialog which will pop up when the user clicks on the fraud alert symbol within a certain traansaction
     * dialog has title, a message and a positive button
     * when the user clicks on the positive button (Call Us) it will change the fragment to the FraudAlert
     */
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setCancelable(false);
        builder.setTitle(R.string.AlertTitle).setMessage(R.string.AlertMessage).setPositiveButton(R.string.CallUsButton,new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                android.support.v4.app.FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                ((RelativeLayout)getActivity().findViewById(R.id.main_tab_switch)).removeAllViews();
                transaction.replace(R.id.main_tab_switch, new FraudAlertFragment(), "FraudAlert");
                transaction.commit();

            }
        });

        AlertDialog dialog = builder.create();

        return dialog;
    }
}