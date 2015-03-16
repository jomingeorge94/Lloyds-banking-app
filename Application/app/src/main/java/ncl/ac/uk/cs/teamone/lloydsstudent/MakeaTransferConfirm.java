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
public class MakeaTransferConfirm extends DialogFragment {

    View v;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inf = getActivity().getLayoutInflater();
        String strSelectedAccountFromSpinner = getArguments().getString("spinnerAccountFrom");
        String strSelectedAccountToSpinner = getArguments().getString("spinnerAccountTo");
        String strSelectedAccountMakeaTransferAmountSpinner = getArguments().getString("spinnerAccountAmount");
        String strSelectedAccountMakeaTransferReferenceSpinner = getArguments().getString("spinnerAccountReference");
        View theDIalog = inf.inflate(R.layout.makea_transfer_confirm, null);
        builder.setView(theDIalog);

        builder.setCancelable(true);


        AlertDialog dialog = builder.create();
        WindowManager.LayoutParams params = dialog.getWindow().getAttributes();
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

        return dialog;
    }







}
