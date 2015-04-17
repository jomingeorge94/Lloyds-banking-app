package ncl.ac.uk.cs.teamone.lloydsstudent;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

/**
 * Dialog which generates a GUI popup to give the user the option to make a call or cancel the
 * dialog
 *
 * Created by Jomin on 01/03/2015.
 */
public class CallDialog extends android.support.v4.app.DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        // Create an AlertDialog object to apply styling to
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setCancelable(false);
        builder.setTitle(R.string.ContactUsTitle)
                .setMessage(R.string.ContactUsDialogMessage)
                // Begin defining action for he positive button
                .setPositiveButton(R.string.ContactUsCall, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Declare and parse the number ot be called from a string
                        Uri number = Uri.parse("tel:0845 3000 116");
                        // Create intent object to perform call
                        Intent callIntent = new Intent(Intent.ACTION_DIAL, number);
                        // Begin the intent to start the call
                        startActivity(callIntent);
                    }
                });
        // Being defining action for the negative button
        builder.setNegativeButton(R.string.ContactUsCancel,new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Close dialog
                dialog.dismiss();
            }
        });

        // Show the dialog in the parent view
        AlertDialog dialog = builder.create();
        // Returns the view for the dialog
        return dialog;

    }
}