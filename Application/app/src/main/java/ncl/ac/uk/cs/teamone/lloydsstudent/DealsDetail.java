package ncl.ac.uk.cs.teamone.lloydsstudent;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;


/**
 * Created by Dan on 19/03/2015.
 */
public class DealsDetail extends DialogFragment {

    private String name;
    private int distance;
    private String distanceMeasure;
    private int loves;
    private int loathes;
    private int iconId;

    //method to switch the fragment, this method will switch the fragment to the budget layout xml file
    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.deal_details, container, false);
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);

        TextView one = (TextView) v.findViewById(R.id.textView126);

        one.setText(name);

        return v;
    }

    public void addVariables(String name, int distance, String distanceMeasure, int loves, int loathes, int iconId) {
        this.name = name;
        this.distance = distance;
        this.distanceMeasure = distanceMeasure;
        this.loves = loves;
        this.loathes = loathes;
        this.iconId = iconId;
    }

}