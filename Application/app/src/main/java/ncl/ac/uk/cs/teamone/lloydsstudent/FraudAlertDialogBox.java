package ncl.ac.uk.cs.teamone.lloydsstudent;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;

/**
 * Created by Jomin on 01/03/2015.
 */
public class FraudAlertDialogBox extends android.support.v4.app.DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setCancelable(false);
        builder.setTitle(R.string.AlertTitle).setMessage(R.string.AlertMessage).setPositiveButton(R.string.CallUsButton,new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(android.R.id.tabcontent, new FraudAlertFragment());
                transaction.addToBackStack(null);

                transaction.commit();


            }
        });

        AlertDialog dialog = builder.create();

        return dialog;
    }
}
