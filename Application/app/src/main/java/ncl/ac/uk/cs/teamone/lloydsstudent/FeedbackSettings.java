package ncl.ac.uk.cs.teamone.lloydsstudent;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;

/**
 * Created by Jomin on 24/03/2015.
 */
public class FeedbackSettings extends FragmentActivity implements RatingBar.OnRatingBarChangeListener {

    TextView ratingStar ;
    RatingBar ratingbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.feedback_screen);


        findViewById(R.id.backbutton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(FeedbackSettings.this,MainActivity.class);
                startActivity(i);
                finish();
            }
        });


        final EditText commentbox = (EditText)findViewById(R.id.feedback_comment);
        final EditText fullname = (EditText)findViewById(R.id.customer_feedback_name);
        final TextView ratingscore = (TextView)findViewById(R.id.ratingscore);
        final RatingBar rating = (RatingBar)findViewById(R.id.ratingBar);
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
                Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", "feedback@lloydsbankinggroup.co.uk", null));
                intent.putExtra(Intent.EXTRA_SUBJECT, "Feedback Review");
                intent.putExtra(Intent.EXTRA_TEXT, ratingscore.getText().toString() + "\nFull Name : " + fullname.getText().toString() + "\nComment : " + commentbox.getText().toString());
                startActivity(Intent.createChooser(intent, "Choose an Email client :"));
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

}
