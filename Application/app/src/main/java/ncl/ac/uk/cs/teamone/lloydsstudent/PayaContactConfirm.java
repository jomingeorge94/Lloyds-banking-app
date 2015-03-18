package ncl.ac.uk.cs.teamone.lloydsstudent;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

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

        final AlertDialog dialogscreen = builder.create();


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






        theDIalog.findViewById(R.id.payacontactOk).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                final ProgressDialog dialogpage = new ProgressDialog(getActivity());
                dialogpage.setTitle("Payment Success");
                dialogpage.setMessage("A payment that you've initiated has been completed successfully. " +
                        "It might take a couple minutes until this line item shows up on the Transaction history.");
                dialogpage.setIcon(R.drawable.transfer_money_icon);
                dialogpage.setIndeterminate(true);
                dialogpage.setCancelable(false);
                dialogpage.show();

                long delayInMillis = 8000;
                Timer timer = new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        dialogscreen.dismiss();
                        dialogpage.dismiss();
                        android.support.v4.app.FragmentTransaction transaction = getFragmentManager().beginTransaction();
                        transaction.replace(android.R.id.tabcontent, new PayaContact(), "PayaContact");
                        transaction.commit();
                    }
                }, delayInMillis);


            }
        });

        theDIalog.findViewById(R.id.payacontactCancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogscreen.dismiss();
            }
        });

        return dialogscreen;
    }
}