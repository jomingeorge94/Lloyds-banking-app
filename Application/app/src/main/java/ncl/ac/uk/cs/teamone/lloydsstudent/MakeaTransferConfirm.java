package ncl.ac.uk.cs.teamone.lloydsstudent;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

/**
 * Created by Jomin on 15/03/2015.
 */
public class MakeaTransferConfirm extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inf = getActivity().getLayoutInflater();
        String strSelectedAccountFromSpinner = getArguments().getString("spinnerAccountFrom");
        String strSelectedAccountToSpinner = getArguments().getString("spinnerAccountTo");
        String strSelectedAccountMakeaTransferAmountSpinner = getArguments().getString("spinnerAccountAmount");
        String strSelectedAccountMakeaTransferReferenceSpinner = getArguments().getString("spinnerAccountReference");
        final View theDIalog = inf.inflate(R.layout.makea_transfer_confirm, null);
        builder.setView(theDIalog);

        builder.setCancelable(true);


        final AlertDialog firstdialog = builder.create();
        WindowManager.LayoutParams params = firstdialog.getWindow().getAttributes();
        params.gravity = Gravity.TOP | Gravity.RIGHT;
        params.x = 0;
        params.y = 300;



        TextView spinnerAccountFromText = (TextView) theDIalog.findViewById(R.id.makeatransferconfirmationFrom);
        spinnerAccountFromText.setText(strSelectedAccountFromSpinner);

        TextView spinnerAccountToText = (TextView) theDIalog.findViewById(R.id.makeatransferconfirmationTo);
        spinnerAccountToText.setText(strSelectedAccountToSpinner);

        TextView spinnerAccountMakeaTransferAmount = (TextView) theDIalog.findViewById(R.id.makeaTransferconfirmationamount);
        spinnerAccountMakeaTransferAmount.setText(strSelectedAccountMakeaTransferAmountSpinner);

        TextView spinnerAccountMakeaTransferReference = (TextView) theDIalog.findViewById(R.id.makeaTransferconfirmationreference);
        spinnerAccountMakeaTransferReference.setText(strSelectedAccountMakeaTransferReferenceSpinner);


        theDIalog.findViewById(R.id.makeaTransferOk).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder1 = new AlertDialog.Builder(getActivity());
                builder1.setTitle("Transaction Status");
                builder1.setIcon(getResources().getDrawable(R.drawable.transfer_money_icon));
                builder1.setMessage("The transaction amount has been successfully transferred to the other account. " +
                        "The complete transaction amount of a charged transaction should appear in your bank account within one to two days of processing. ");
                builder1.setCancelable(true);
                builder1.setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                //put your code that needed to be executed when okay is clicked
                                firstdialog.dismiss();

                                android.support.v4.app.FragmentTransaction transaction = getFragmentManager().beginTransaction();
                                transaction.replace(android.R.id.tabcontent, new MakeaTransfer(), "MakeaTransferConfirm");
                                transaction.commit();


                            }
                        });


                AlertDialog alert11 = builder1.create();
                alert11.show();
            }
        });


        theDIalog.findViewById(R.id.makeaTransferCancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firstdialog.dismiss();
            }
        });

        return firstdialog;
    }


}
