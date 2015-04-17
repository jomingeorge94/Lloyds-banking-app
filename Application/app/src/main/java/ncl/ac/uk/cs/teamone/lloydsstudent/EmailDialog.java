package ncl.ac.uk.cs.teamone.lloydsstudent;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

/**
 * Dialog which generates a GUI popup to give the user the option to compose an email, or to cancel
 * the action and return
 *
 * Created by Jomin on 01/03/2015.
 */
public class EmailDialog extends android.support.v4.app.DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        // Create an AlertDialog object to apply styling to
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setCancelable(false);
        builder.setTitle(R.string.ContactUsEmalTitle).setMessage(R.string.ContactUsEmalMessage).setPositiveButton(R.string.ContactUsEmalOk,new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                // Creates new Email Intent, genreates/populates data so it's ready to send the email
                Intent i = new Intent(Intent.ACTION_SEND);

                // Style email message to be sent to activity
                i.setType("message/rfc822");
                i.putExtra(Intent.EXTRA_EMAIL  , new String[]{"generalenquiry@lloydsbankinggroup.co.uk"});
                i.putExtra(Intent.EXTRA_SUBJECT, "Subject of your email");
                i.putExtra(Intent.EXTRA_TEXT   , "Type your message here");

                // Attempt to start composing email
                try {
                    startActivity(Intent.createChooser(i, "Email"));
                } catch (android.content.ActivityNotFoundException ex) {

                }

            }
        });
        // Assign action to dismiss button
        builder.setNegativeButton(R.string.ContactUsEmalCancel,new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Close dialog
                dialog.dismiss();
            }
        });

        // Create and show the dialog
        AlertDialog dialog = builder.create();
        // Return view
        return dialog;
        
    }
}