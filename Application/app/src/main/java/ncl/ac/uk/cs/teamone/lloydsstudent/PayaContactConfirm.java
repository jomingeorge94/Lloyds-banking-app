package ncl.ac.uk.cs.teamone.lloydsstudent;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Jomin on 17/03/2015.
 */
public class PayaContactConfirm extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inf = getActivity().getLayoutInflater();
        String payacontactstrSelectedAccountFromSpinner = getArguments().getString("payacontactspinnerAccountFrom");
        String payacontactphonenumber = getArguments().getString("payacontactphonenumber");
        String payacontactsortcode = getArguments().getString("payacontactsortcode");
        String payacontactamountr = getArguments().getString("payacontactamount");
        String payacontactreference = getArguments().getString("payacontactreference");
        final View theDIalog = inf.inflate(R.layout.paya_contact_confirm, null);
        builder.setView(theDIalog);
        builder.setCancelable(true);

        final AlertDialog firstdialog = builder.create();


        TextView spinnerAccountFromText = (TextView) theDIalog.findViewById(R.id.payacontactconfirmationFrom);
        spinnerAccountFromText.setText(payacontactstrSelectedAccountFromSpinner);

        TextView phonenumber = (TextView) theDIalog.findViewById(R.id.payacontactconfirmphone);
        phonenumber.setText(payacontactphonenumber);

        TextView sortcode = (TextView) theDIalog.findViewById(R.id.payacontactconfirmsortcode);
        sortcode.setText(payacontactsortcode);

        TextView amount = (TextView) theDIalog.findViewById(R.id.payacontactconfirmamount);
        amount.setText(payacontactamountr);

        TextView reference = (TextView) theDIalog.findViewById(R.id.payacontactconfirmreference);
        reference.setText(payacontactreference);

        return firstdialog;
    }
}