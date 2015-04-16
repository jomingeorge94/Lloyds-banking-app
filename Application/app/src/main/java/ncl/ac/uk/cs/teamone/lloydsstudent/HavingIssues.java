package ncl.ac.uk.cs.teamone.lloydsstudent;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

/**
 * Created by Jomin on 21/03/2015.
 */
public class HavingIssues extends FragmentActivity {
    Button havinganissue_submit;
    EditText havinganissue_brifinfo;
    Spinner havinganissue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.having_issue_screen);

        havinganissue = (Spinner)findViewById(R.id.spinner_havinganissue);
        ArrayAdapter adapter = ArrayAdapter.createFromResource(getApplicationContext(), R.array.havingissuecategory,R.layout.spinner_layout);
        adapter.setDropDownViewResource(R.layout.spinner_layout);
        havinganissue.setAdapter(adapter);

        havinganissue_submit = (Button)findViewById(R.id.havinganissue_button);
        havinganissue_brifinfo = (EditText)findViewById(R.id.havinganissue_brifinfo);

        findViewById(R.id.havinganissue_dropdownlist).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findViewById(R.id.spinner_havinganissue).setVisibility(View.VISIBLE);
                findViewById(R.id.havinganissue_dropdownlist).setVisibility(View.GONE);
                havinganissue.performClick();
            }
        });

        havinganissue_submit.setOnClickListener(new View.OnClickListener() {
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

                if(havinganissue_brifinfo.length() >= 1){
                    havinganissue_submit.setEnabled(true);
                    havinganissue_submit.setBackgroundColor(Color.parseColor("#369742"));
                } else  {
                    havinganissue_submit.setEnabled(false);
                    havinganissue_submit.setBackgroundColor(Color.parseColor("#ffcacaca"));
                }
            }
        };

        havinganissue_brifinfo.addTextChangedListener(watcher);



        findViewById(R.id.settings_change_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(HavingIssues.this,AccountManagementScreen.class);
                startActivity(i);
                finish();
            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i=new Intent(HavingIssues.this,AccountManagementScreen.class);
        startActivity(i);
        finish();
    }

    private class SendMail extends AsyncTask<String, Void, Integer>
    {
        ProgressDialog pd = null;
        String error = null;
        Integer result;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pd = new ProgressDialog(HavingIssues.this);
            pd.setTitle("Sending Enquiry");
            pd.setMessage("In progress....");
            pd.setIcon(R.drawable.report_lost);
            pd.setCancelable(false);
            pd.show();
        }

        @Override
        protected Integer doInBackground(String... params) {
            MailSender sender = new MailSender("csc2022team1@gmail.com", "team1isgreat");
            sender.setTo(new String[]{"csc2022team1@gmail.com "});
            sender.setFrom("csc2022team1@gmail.com");
            sender.setSubject("Having an issue");
            sender.setBody("Issue Category : " + havinganissue.getSelectedItem().toString() + "\n" +
                    "Briefly explain the issue : " + havinganissue_brifinfo.getText().toString() + "\n");
            try {
                if(sender.send()) {
                    System.out.println("Enquiry sent successfully");
                    return 1;
                } else {
                    return 2;
                }
            } catch (Exception e) {
                error = e.getMessage();
                Log.e("Enquiry", e.getMessage(), e);
            }
            return 3;
        }

        protected void onPostExecute(Integer result) {
            pd.dismiss();
            if(error!=null) {
                Log.i("Status",error);
            }
            if(result==1) {
                Toast.makeText(HavingIssues.this, "Enquiry was sent successfully.", Toast.LENGTH_LONG).show();
            } else if(result==2) {
                Toast.makeText(HavingIssues.this,"Enquiry was not sent, please try again later !", Toast.LENGTH_LONG).show();
            } else if(result==3) {
                Toast.makeText(HavingIssues.this,"There was a problem sending the Enquiry.",Toast.LENGTH_LONG).show();
            }
        }
    }

}