package ncl.ac.uk.cs.teamone.lloydsstudent;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Jomin on 21/03/2015.
 */
public class FraudReport extends Fragment {

    EditText AccountNumber;
    EditText SortCode;
    EditText BriefBox;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View v = inflater.inflate(R.layout.fraud_report_online, container, false);



        AccountNumber = (EditText)v.findViewById(R.id.report_AccountNumber);
        SortCode = (EditText)v.findViewById(R.id.report_SortCode);
        BriefBox = (EditText)v.findViewById(R.id.report_Brief);

        final Button reportButton = (Button)v.findViewById(R.id.report_Button);


        reportButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new SendMail().execute();
            }
        });



        final TextWatcher watcher = new TextWatcher(){
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                if(AccountNumber.length() >= 8 && SortCode.length() >=6 && BriefBox.length() >=2){
                    reportButton.setEnabled(true);
                    reportButton.setBackgroundColor(Color.parseColor("#369742"));
                } else  {
                    reportButton.setEnabled(false);
                    reportButton.setBackgroundColor(Color.parseColor("#ffcacaca"));
                }
            }
        };
        AccountNumber.addTextChangedListener(watcher);
        SortCode.addTextChangedListener(watcher);
        BriefBox.addTextChangedListener(watcher);

        return v;
    }

    private class SendMail extends AsyncTask<String, Void, Integer>
    {
        ProgressDialog pd = null;
        String error = null;
        Integer result;

        @Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub
            super.onPreExecute();
            pd = new ProgressDialog(getActivity());
            pd.setTitle("Sending Report");
            pd.setMessage("In progress....");
            pd.setIcon(R.drawable.report_lost);
            pd.setCancelable(false);
            pd.show();
        }

        @Override
        protected Integer doInBackground(String... params) {
            // TODO Auto-generated method stub
            MailSender sender = new MailSender("csc2022team1@gmail.com", "team1isgreat");
            sender.setTo(new String[]{"csc2022team1@gmail.com "});
            sender.setFrom("csc2022team1@gmail.com");
            sender.setSubject("Fraud Detection");
            sender.setBody("Account Number : " + AccountNumber.getText().toString() + "\n" +
                    "Sort Code : " + SortCode.getText().toString() + "\n" +
                    "Problem : " + BriefBox.getText().toString());
            try {
                if(sender.send()) {
                    System.out.println("Report sent successfully");
                    return 1;
                } else {
                    return 2;
                }
            } catch (Exception e) {
                error = e.getMessage();
                Log.e("SendReport", e.getMessage(), e);
            }
            return 3;
        }

        protected void onPostExecute(Integer result) {
            pd.dismiss();
            if(error!=null) {
                Log.i("Status",error);
            }
            if(result==1) {
                Toast.makeText(getActivity(), "Report was sent successfully.", Toast.LENGTH_LONG).show();
            } else if(result==2) {
                Toast.makeText(getActivity(),"Report was not sent, please try again later !", Toast.LENGTH_LONG).show();
            } else if(result==3) {
                Toast.makeText(getActivity(),"There was a problem sending the Report.",Toast.LENGTH_LONG).show();
            }
        }
    }

}