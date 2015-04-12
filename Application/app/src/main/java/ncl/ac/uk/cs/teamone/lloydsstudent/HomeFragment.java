package ncl.ac.uk.cs.teamone.lloydsstudent;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

/**
 * Created by Jomin on 19/02/2015.
 */
public class HomeFragment extends Fragment {
    View v;
    ViewPager viewPager;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        final View v =  inflater.inflate(R.layout.tab2_main, container, false);

        //get the customer data
        Data data = new Data();
        //Set the customer name
        TextView customerName = (TextView) v.findViewById(R.id.customerName);

        customerName.setText(data.customer.get("firstname") + " " + data.customer.get("lastname"));

        viewPager = (ViewPager)v.findViewById(R.id.accountswitching);
        AccountSwitching accountSwitching = new AccountSwitching(getChildFragmentManager());
        viewPager.setAdapter(accountSwitching);

        //onclick event for the button - make a transfer
        final Button maketransfer = (Button)v.findViewById(R.id.makeTransferButton);
        maketransfer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                android.support.v4.app.FragmentTransaction transaction = getFragmentManager().beginTransaction();
                ((FrameLayout)getActivity().findViewById(android.R.id.tabcontent)).removeAllViews();
                transaction.replace(android.R.id.tabcontent, new MakeaTransfer(), "");
                transaction.commit();
            }
        });

        final Button paycontact = (Button)v.findViewById(R.id.payContactButton);
        paycontact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                android.support.v4.app.FragmentTransaction transaction = getFragmentManager().beginTransaction();
                ((FrameLayout)getActivity().findViewById(android.R.id.tabcontent)).removeAllViews();
                transaction.replace(android.R.id.tabcontent, new PayaContact(), "");
                transaction.addToBackStack("");
                transaction.commit();
            }
        });

        this.v = v;

        return v;
    }
}