package ncl.ac.uk.cs.teamone.lloydsstudent;

import android.app.AlertDialog;
import android.app.Dialog;
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
public class HomeTransferConfirm extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inf = getActivity().getLayoutInflater();
        String strSelectedAccountFromSpinner = getArguments().getString("spinnerAccountFrom");
        String strSelectedAccountToSpinner = getArguments().getString("spinnerAccountTo");
        final String strSelectedAccountMakeaTransferAmountSpinner = getArguments().getString("spinnerAccountAmount");
        String strSelectedAccountMakeaTransferReferenceSpinner = getArguments().getString("spinnerAccountReference");
        final View theDIalog = inf.inflate(R.layout.home_transfer_confirm, null);
        builder.setView(theDIalog);

        builder.setCancelable(true);


        final AlertDialog firstdialog = builder.create();
        WindowManager.LayoutParams params = firstdialog.getWindow().getAttributes();
        params.gravity = Gravity.TOP | Gravity.RIGHT;
        params.x = 0;
        params.y = 300;

        TextView spinnerAccountFromText = (TextView) theDIalog.findViewById(R.id.transfer_confirm_from);
        spinnerAccountFromText.setText(strSelectedAccountFromSpinner);

        TextView spinnerAccountToText = (TextView) theDIalog.findViewById(R.id.transfer_confirm_to);
        spinnerAccountToText.setText(strSelectedAccountToSpinner);

        TextView spinnerAccountMakeaTransferAmount = (TextView) theDIalog.findViewById(R.id.transfer_confirm_amount);
        spinnerAccountMakeaTransferAmount.setText(strSelectedAccountMakeaTransferAmountSpinner);

        TextView spinnerAccountMakeaTransferReference = (TextView) theDIalog.findViewById(R.id.transfer_confirm_reference);
        spinnerAccountMakeaTransferReference.setText(strSelectedAccountMakeaTransferReferenceSpinner);


        theDIalog.findViewById(R.id.transfer_confirm_ok).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    //initialize the async task
                    String[] keys = {"uid", "from", "from_money", "to", "to_money", "amount"};
                    String[] values = {new Data().customer.get("uid"), getArguments().getString("from"), getArguments().getString("from_money"), getArguments().getString("to"), getArguments().getString("to_money"), strSelectedAccountMakeaTransferAmountSpinner};
                    PHPHandler handler = new PHPHandler(getActivity(), keys, values, 5);
                    //execute the script
                    handler.execute("http://www.abunities.co.uk/t2022t1/maketransfer.php");
               }
        });


        theDIalog.findViewById(R.id.transfer_confirm_edit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firstdialog.dismiss();
            }
        });

        return firstdialog;
    }


}
