package ncl.ac.uk.cs.teamone.lloydsstudent;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.View;

import com.github.amlcurran.showcaseview.ShowcaseView;
import com.github.amlcurran.showcaseview.targets.Target;
import com.github.amlcurran.showcaseview.targets.ViewTarget;

/**
 * Created by Jomin on 12/03/2015.
 */
public class HorsePointerFIrstTrial extends ActionBarActivity implements View.OnClickListener {

    private ShowcaseView showcaseView;
    private int counter = 0;
    private Target t1,t2,t3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.horse_first_trial);

        t1 = new ViewTarget(R.id.makeTransferButton,this);
        t2 = new ViewTarget(R.id.payContactButton,this);
        t3 = new ViewTarget(R.id.circle_progress,this);

        showcaseView = new ShowcaseView.Builder(this).setTarget(Target.NONE).setOnClickListener(this).setContentTitle("Welcome to Tutorial mode!").setContentText("This helpful companion will attempt to answer any queries you might have about this app").setStyle(R.style.Transparency).build();
        showcaseView.setButtonText("Click Me");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    @Override
    public void onClick(View v) {
        switch (counter){
            case 0:
                showcaseView.setContentTitle(" ");
                showcaseView.setContentTitle("This is the main screen of the app, where you have some basic details of your bank account and buttons to navigate to other areas in the app");
                break;

            case 1:
                showcaseView.setShowcase(t2,true);
                showcaseView.setContentTitle("Second");
                showcaseView.setContentTitle("This is a button with the text saying Second");
                break;

            case 2:
                showcaseView.setShowcase(t3,true);
                showcaseView.setContentTitle("Third");
                showcaseView.setContentTitle("This is a button with the text saying Third, To go out of this tutorial click on the close button");
                showcaseView.setButtonText("Close");
                break;

            case 3:
                showcaseView.hide();
                Intent home =new Intent(getApplicationContext(),MainActivity.class);
                startActivity(home);
                break;
        }
        counter ++;

    }
}
