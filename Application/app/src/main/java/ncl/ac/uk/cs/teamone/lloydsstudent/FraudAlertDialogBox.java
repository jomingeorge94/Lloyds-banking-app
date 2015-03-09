package ncl.ac.uk.cs.teamone.lloydsstudent;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.FrameLayout;

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


                android.support.v4.app.FragmentTransaction transaction = getFragmentManager().beginTransaction();
                ((FrameLayout)getActivity().findViewById(android.R.id.tabcontent)).removeAllViews();
                transaction.replace(android.R.id.tabcontent, new FraudAlertFragment());
                transaction.commit();

            }
        });

        AlertDialog dialog = builder.create();

        return dialog;
    }
}