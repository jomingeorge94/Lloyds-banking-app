package ncl.ac.uk.cs.teamone.lloydsstudent;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

public class SettingsChangePasscode extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        final View v = inflater.inflate(R.layout.home_transfer, container, false);

        // Get an input manager to mange soft keyboard during inputs
        final InputMethodManager keyboard = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);

        // Assign each input box to a local variable
        final EditText box[] = new EditText[8];
        box[0] = (EditText) v.findViewById(R.id.settings_change_passcode_1);
        box[1] = (EditText) v.findViewById(R.id.settings_change_passcode_2);
        box[2] = (EditText) v.findViewById(R.id.settings_change_passcode_3);
        box[3] = (EditText) v.findViewById(R.id.settings_change_passcode_4);
        box[4] = (EditText) v.findViewById(R.id.settings_change_confirm_1);
        box[5] = (EditText) v.findViewById(R.id.settings_change_confirm_2);
        box[6] = (EditText) v.findViewById(R.id.settings_change_confirm_3);
        box[7] = (EditText) v.findViewById(R.id.settings_change_confirm_4);

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
                // Clears the focus of the current box
                box[3].clearFocus();
                // Request the focus of the next box
                box[4].requestFocus();
                // SHows the keyboard for the next box
                keyboard.showSoftInput(box[4], InputMethodManager.SHOW_IMPLICIT);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        for(int i = 4; i < 8; i++) {
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
                // Creates a new confirm transfer fragment and initialises it to a variable
                SettingsChangeVerify fragment = new SettingsChangeVerify();

                if(box[0] == box[4] && box[1] == box[5] && box[2] == box[6] && box[3] == box[7]) {
                    // New bundle to store data to be transferred
                    Bundle args = new Bundle();
                    args.putString("passcode", String.format("%s%s%s%s", box[0].getText().toString(), box[1].getText().toString(), box[2].getText().toString(), box[3].getText().toString()));
                    // Starts the new fragment
                    fragment.show(getFragmentManager(), "Confirm");
                }

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        // Return fragment view
        return v;
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
                    // Clears the focus of the current box
                    box[3].clearFocus();
                    // Request the focus of the next box
                    changePasscode();
                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {

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
        private void changePasscode() {
            // Assigns the passcode the user wishes to change to to a local variable
            String newPasscode = getArguments().getString("passcode");
        }

    }
}
