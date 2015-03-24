package ncl.ac.uk.cs.teamone.lloydsstudent;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
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


        ratingStar = (TextView)findViewById(R.id.ratingscore);
        ratingbar = (RatingBar)findViewById(R.id.ratingBar);

        ratingbar.setOnRatingBarChangeListener(this);


    }


    @Override
    public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {

        ratingStar.setText("Rating: " + rating + "\n");

        if (fromUser)
            ratingStar.setText(ratingStar.getText().toString() + "Change by User");
        else
            ratingStar.setText(ratingStar.getText().toString() + "Change by function");

    }

    public void changeRating (View v){
        ratingbar.setRating(1.5f);
    }
}
