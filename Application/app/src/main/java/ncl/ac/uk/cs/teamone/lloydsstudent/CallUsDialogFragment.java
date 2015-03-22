package ncl.ac.uk.cs.teamone.lloydsstudent;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

/**
 * Created by Jomin on 01/03/2015.
 */

public class CallUsDialogFragment extends android.support.v4.app.DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setCancelable(false);
        builder.setTitle(R.string.ContactUsTitle).setMessage(R.string.ContactUsDialogMessage).setPositiveButton(R.string.ContactUsCall,new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Uri number = Uri.parse("tel:0845 3000 116");
                Intent callIntent = new Intent(Intent.ACTION_DIAL, number);
                startActivity(callIntent);

                /*Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("message/rfc822");
                i.putExtra(Intent.EXTRA_EMAIL  , new String[]{"generalenquiry@lloydsbankinggroup.co.uk"});
                i.putExtra(Intent.EXTRA_SUBJECT, "Subject of your email");
                i.putExtra(Intent.EXTRA_TEXT   , "Type your message here");
                try {
                    startActivity(Intent.createChooser(i, "Email"));
                } catch (android.content.ActivityNotFoundException ex) {

                }*/

            }
        });
        builder.setNegativeButton(R.string.ContactUsCancel,new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });



        AlertDialog dialog = builder.create();
        return dialog;
    }
}