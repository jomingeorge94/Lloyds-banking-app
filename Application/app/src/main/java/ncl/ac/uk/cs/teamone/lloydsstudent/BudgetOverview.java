package ncl.ac.uk.cs.teamone.lloydsstudent;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by Dan on 19/03/2015.
 */
public class BudgetOverview extends Fragment {

    //method to switch the fragment, this method will switch the fragment to the budget layout xml file
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.budget_overview, container, false);

        Button cash = (Button) v.findViewById(R.id.first_next);

        // Create button listner
        cash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });

        return v;
    }

    }