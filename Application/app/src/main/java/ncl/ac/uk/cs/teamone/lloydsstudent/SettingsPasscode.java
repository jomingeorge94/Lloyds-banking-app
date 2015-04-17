package ncl.ac.uk.cs.teamone.lloydsstudent;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Timer;

/**
 * Fragment activity that represents a settings page and creates a GUI to allow the user to change
 * their login passcode and save the change on the server
 *
 * Created by Jomin on 21/03/2015.
 */
public class SettingsPasscode extends FragmentActivity {

    private static CountDownTimer timer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // Call to super
        super.onCreate(savedInstanceState);
        // Inflate the view to show the XML in the current window
        setContentView(R.layout.settings_change_passcode);

        // Get an input manager to mange soft keyboard during inputs
        final InputMethodManager keyboard = (InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE);

        // Assign each input box to a local variable
        final EditText box[] = new EditText[8];
        box[0] = (EditText) findViewById(R.id.settings_change_passcode_1);
        box[1] = (EditText) findViewById(R.id.settings_change_passcode_2);
        box[2] = (EditText) findViewById(R.id.settings_change_passcode_3);
        box[3] = (EditText) findViewById(R.id.settings_change_passcode_4);
        box[4] = (EditText) findViewById(R.id.settings_change_confirm_1);
        box[5] = (EditText) findViewById(R.id.settings_change_confirm_2);
        box[6] = (EditText) findViewById(R.id.settings_change_confirm_3);
        box[7] = (EditText) findViewById(R.id.settings_change_confirm_4);

        // Loop to assign listeners to each input box
        for(int i = 0; i < 7; i++) {
            // Temporary integer for passing into embedded class
            final int j = i;
            // Adds listener to change focus when "next" key is pressed
            box[i].addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    // Clears the focus of the current box
                    box[j].clearFocus();
                    // Request the focus of the next box
                    box[j + 1].requestFocus();
                    // SHows the keyboard for the next box
                    keyboard.showSoftInput(box[j + 1], InputMethodManager.SHOW_IMPLICIT);
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });
        }

        box[7].addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // Creates a new confirm transfer fragment and initialises it to a variable
                SettingsChangeVerify fragment = new SettingsChangeVerify();

                // Clears the focus of the textox
                box[7].clearFocus();

                // Creates two strings to hold inputs
                String passcode = null;
                String confirm = null;

                // Gets the inputs from the user and assigns the passcode and the confirm to variables
                passcode = String.format("%s%s%s%s", box[0].getText().toString(), box[1].getText().toString(), box[2].getText().toString(), box[3].getText().toString());
                confirm = String.format("%s%s%s%s", box[4].getText().toString(), box[5].getText().toString(), box[6].getText().toString(), box[7].getText().toString());

                // Statement checking inputs match
                if(passcode.equals(confirm)) {
                    // New bundle to store data to be transferred
                    Bundle args = new Bundle();
                    // Adds new passcode tot he arguments
                    args.putString("passcode", passcode);
                    fragment.setArguments(args);
                    // Starts the new fragment
                    fragment.show(getSupportFragmentManager(), "Confirm");
                } else {
                    // Resets the values for each of the
                    for(int i = 0; i < 8; i++) {
                        box[i].setText("");
                    }

                    // Show message to user
                    Toast.makeText(getApplicationContext(), "Passwords Didn't Match", Toast.LENGTH_LONG);

                    // Requests the focus to the first text box
                    box[1].requestFocus();
                    // Shows the keyboard
                    keyboard.showSoftInput(box[1], InputMethodManager.SHOW_IMPLICIT);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        // Assigns on click listener to back button to swap to previous view
        findViewById(R.id.settings_change_back).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // Create new intent
                Intent i=new Intent(SettingsPasscode.this, SettingsAccount.class);
                startActivity(i);
                // End current activity
                finish();
            }

        });


    }

    //Simply prevents the app from being in the background, user has to enter passcode again!
    @Override
    protected void onUserLeaveHint() {
        this.finish();
    }

    /**
     * Waits for a back button to be pressed, when it is the previous view is displayed and the
     * current closed
     */
    @Override
    public void onBackPressed() {
        // Call to super
        super.onBackPressed();
        // New intent to change view
        Intent i=new Intent(SettingsPasscode.this,SettingsAccount.class);
        // Start the new intent
        startActivity(i);
        // Close current view
        finish();
    }

    public static class SettingsChangeVerify extends DialogFragment {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {

            // Create and inflater to inflate the view and align the inflated view to a local variable
            final LayoutInflater inflater = getActivity().getLayoutInflater();
            final View dialog = inflater.inflate(R.layout.settings_change_verify, null);

            // Initialises a dialog builder to display the dialog
            final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            // Assigns the view to the builder
            builder.setView(dialog);
            // To allow the dialog to be closed
            builder.setCancelable(true);

            // Uses the dialog builder to create the dialog
            final AlertDialog verify = builder.create();

            // Get an input manager to mange soft keyboard during inputs
            final InputMethodManager keyboard = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);

            // Assign each input box to a local variable
            final EditText box[] = new EditText[8];
            box[0] = (EditText) dialog.findViewById(R.id.settings_change_passcode_1);
            box[1] = (EditText) dialog.findViewById(R.id.settings_change_passcode_2);
            box[2] = (EditText) dialog.findViewById(R.id.settings_change_passcode_3);
            box[3] = (EditText) dialog.findViewById(R.id.settings_change_passcode_4);

            for(int i = 0; i < 3; i++) {
                // Temporary integer for passing into embedded class
                final int j = i;
                // Adds listener to change focus when "next" key is pressed
                box[i].addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        // Clears the focus of the current box
                        box[j].clearFocus();
                        // Request the focus of the next box
                        box[j + 1].requestFocus();
                        // SHows the keyboard for the next box
                        keyboard.showSoftInput(box[j + 1], InputMethodManager.SHOW_IMPLICIT);
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
            }

            box[3].addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    // Clears the focus of the current box
                    box[3].clearFocus();

                    box[0].requestFocus();
                }

                @Override
                public void afterTextChanged(Editable s) {

                    if(!box[0].getText().toString().isEmpty() && !box[1].getText().toString().isEmpty() && !box[2].getText().toString().isEmpty() && !box[3].getText().toString().isEmpty()) {
                        String passcode = String.format("%s%s%s%s", box[0].getText().toString(), box[1].getText().toString(), box[2].getText().toString(), box[3].getText().toString());
                        //checks if the passcode was successfully changed
                        if (changePasscode(passcode)) {
                            dismiss();
                        } else {
                            AlertDialog ad = new AlertDialog.Builder(getActivity()).create();

                            ad.setButton(DialogInterface.BUTTON_NEUTRAL, "OK", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    //clears all the boxes to enable user to re-enter the passcode
                                    for (int i = 0; i < 4; i++) {
                                        box[i].setText("");
                                    }
                                    //sets the focus to the first box
                                    box[0].requestFocus();
                                    //removes the dialog
                                    dialog.dismiss();
                                }

                            });

                            ad.setTitle("Wrong Passcode");
                            ad.show();
                        }
                    }
                }
            });

            // Get and set the position of the window and align with parent
            WindowManager.LayoutParams params = verify.getWindow().getAttributes();
            params.gravity = Gravity.TOP | Gravity.RIGHT;
            params.x = 0;
            params.y = 300;

            // Return the view
            return verify;
        }

        /**
         * Method which should check the current passcode against the checked passcode, and if they
         * match commit the changed passcode to the database
         */
        private boolean changePasscode(String passcode) {

            Data d = new Data();

            if(passcode.equalsIgnoreCase(d.customer.get("passcode"))) {
                // Assigns the passcode the user wishes to change to a local variable
                String newPasscode = getArguments().getString("passcode");

                String[] keys = {"uid", "passcode"};
                String[] values = {d.customer.get("uid"), newPasscode};

                PHPHandler handler = new PHPHandler(getActivity(), keys, values, 7);

                handler.execute("http://www.abunities.co.uk/t2022t1/changepasscode.php");

                return true;
            }
            else {
                return false;
            }
        }

    }

}