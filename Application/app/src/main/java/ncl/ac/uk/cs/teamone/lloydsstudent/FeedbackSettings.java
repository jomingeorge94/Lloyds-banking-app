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
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Jomin on 24/03/2015.
 */
public class FeedbackSettings extends FragmentActivity implements RatingBar.OnRatingBarChangeListener {

    TextView ratingStar ;
    RatingBar ratingbar;
    EditText commentbox;
    EditText fullname;
    TextView ratingscore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.feedback_screen);

        commentbox = (EditText)findViewById(R.id.feedback_comment);
        fullname = (EditText)findViewById(R.id.customer_feedback_name);
        ratingscore = (TextView)findViewById(R.id.ratingscore);
        final RatingBar rating = (RatingBar)findViewById(R.id.ratingBar);

        findViewById(R.id.backbutton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(FeedbackSettings.this,MainActivity.class);
                startActivity(i);
                finish();
            }
        });

        final Button feedbackbutton = (Button)findViewById(R.id.feedback_submit);

        final TextWatcher watcher = new TextWatcher(){

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                if(commentbox.length() >= 3 && rating.getRating() >=0.5 && fullname.length() >=2){
                    feedbackbutton.setEnabled(true);
                    feedbackbutton.setBackgroundColor(Color.parseColor("#369742"));
                } else  {
                    feedbackbutton.setEnabled(false);
                    feedbackbutton.setBackgroundColor(Color.parseColor("#ffcacaca"));
                }
            }
        };
        commentbox.addTextChangedListener(watcher);
        rating.setOnRatingBarChangeListener((this));

        findViewById(R.id.feedback_submit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new SendMail().execute();
            }
        });

        ratingStar = (TextView)findViewById(R.id.ratingscore);
        ratingbar = (RatingBar)findViewById(R.id.ratingBar);

        ratingbar.setOnRatingBarChangeListener(this);

    }

    @Override
    public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
        ratingStar.setText("Rating: " + rating + "/ 5.0");
    }

    public void changeRating (View v){
        ratingbar.setRating(1.5f);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i=new Intent(FeedbackSettings.this,MainActivity.class);
        startActivity(i);
        finish();
    }

    //http://stackoverflow.com/questions/11945602/fail-to-send-mail-using-javamail-api
    private class SendMail extends AsyncTask<String, Void, Integer>
    {
        ProgressDialog pd = null;
        String error = null;
        Integer result;

        @Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub
            super.onPreExecute();
            pd = new ProgressDialog(FeedbackSettings.this);
            pd.setTitle("Sending Feedback");
            pd.setMessage("Please wait...");
            pd.setIcon(R.drawable.feedback_icon);
            pd.setCancelable(false);
            pd.show();
        }

        @Override
        protected Integer doInBackground(String... params) {
            // TODO Auto-generated method stub
            MailSender sender = new MailSender("csc2022team1@gmail.com", "team1isgreat");
            sender.setTo(new String[]{"csc2022team1@gmail.com "});
            sender.setFrom("csc2022team1@gmail.com");
            sender.setSubject("Feedback Review");
            sender.setBody("Full Name : " + fullname.getText().toString() + "\n" +
                    ratingscore.getText().toString() + "\n" +
                    commentbox.getText().toString());
            try {
                if(sender.send()) {
                    System.out.println("Message sent");
                    return 1;
                } else {
                    return 2;
                }
            } catch (Exception e) {
                error = e.getMessage();
                Log.e("SendMail", e.getMessage(), e);
            }
            return 3;
        }

        protected void onPostExecute(Integer result) {
            pd.dismiss();
            if(error!=null) {
                Log.i("Status",error);
            }
            if(result==1) {
                Toast.makeText(FeedbackSettings.this,"Email was sent successfully.", Toast.LENGTH_LONG).show();
            } else if(result==2) {
                Toast.makeText(FeedbackSettings.this,"Email was not sent.", Toast.LENGTH_LONG).show();
            } else if(result==3) {
                Toast.makeText(FeedbackSettings.this,"There was a problem sending the email.",Toast.LENGTH_LONG).show();
            }
        }
    }
}