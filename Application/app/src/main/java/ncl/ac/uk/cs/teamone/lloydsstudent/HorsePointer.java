package ncl.ac.uk.cs.teamone.lloydsstudent;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;

/**
 * Created by Jomin on 12/03/2015.
 */
public class HorsePointer extends DialogFragment {

    /**
     * method which handles the alert dialog which will pop up when the user clicks on the fraud alert symbol within a certain traansaction
     * dialog has title, a message and a positive button
     * when the user clicks on the positive button (Call Us) it will change the fragment to the FraudAlert
     */
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inf = getActivity().getLayoutInflater();
        View theDIalog = inf.inflate(R.layout.horse_layout, null);
        builder.setView(theDIalog);

        builder.setCancelable(false);


        AlertDialog dialog = builder.create();
        WindowManager.LayoutParams params = dialog.getWindow().getAttributes();
        params.gravity = Gravity.TOP | Gravity.RIGHT;
        params.x = 230;
        params.y = 230;

        return dialog;
    }

}
