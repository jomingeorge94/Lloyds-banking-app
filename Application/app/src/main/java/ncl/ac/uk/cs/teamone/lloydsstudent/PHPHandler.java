package ncl.ac.uk.cs.teamone.lloydsstudent;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.telephony.TelephonyManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by Artemiy on 20/02/2015.
 */

public class PHPHandler extends AsyncTask<String, Void, String> {

    private Map<String, String> data = new HashMap<>();
    private String[] keys = null;
    private String[] values = null;
    //progressDialog variables
    private ProgressDialog progressDialog = null;
    private Context activity = null;
    private AlertDialog alertDialog = null;
    private int error = 0;
    private int table = 0;

    public PHPHandler(Context activity, String[] keys, String[] values, int table) {
        this.table = table;
        //set the current activity
        this.activity = activity;
        //set the keys and the mapping of the keys to use
        this.keys = keys;
        this.values = values;
        //Create a progress dialog
        this.progressDialog = new ProgressDialog(this.activity);
        this.progressDialog.show();
        this.progressDialog.setCancelable(false);
        this.progressDialog.setContentView(R.layout.progressdialog);
        //creates an alert dialog
        this.alertDialog = new AlertDialog.Builder(this.activity).create();
        this.alertDialog.setButton(DialogInterface.BUTTON_NEUTRAL, "OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
            }
        });
    }


    public void setData(JSONObject data) throws JSONException {
        //defensive programming to prevent an empty array from adding nothing
        if(data == null) {
            return;
        }
        //gets an iterator of the keys in the JSON
        Iterator<String> jsonKeys = data.keys();
        //go through the data
        while(jsonKeys.hasNext()) {
            //get the next key
            String key = jsonKeys.next();
            //add the value to the data
            this.data.put(key, data.get(key).toString());
        }
    }

    public Map getData() {
        return data;
    }

    @Override
    protected String doInBackground(String... params) {
        //the client
        HttpClient httpclient = new DefaultHttpClient();
        //the post receiver
        HttpPost httppost = new HttpPost(params[0]);

        List<NameValuePair> nameValuePairs = new ArrayList<>();

        //Adds all the data that is going to be sent to the php script
        for(int i = 0; i < this.keys.length; i++) {
            nameValuePairs.add(new BasicNameValuePair(this.keys[i], this.values[i]));
        }

        //Encodes the data structure and links it to the post object
        try {
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

            //Execute the post request
            HttpResponse response = httpclient.execute(httppost);
            HttpEntity entity = response.getEntity();
            //assigns the content of the execution to an input stream
            InputStream inStream = entity.getContent();

            //converts the data into a string
            BufferedReader reader = new BufferedReader(new InputStreamReader(inStream));
            StringBuilder sb = new StringBuilder();
            sb.append(reader.readLine() + '\n');
            String line = "0";

            while ((line = reader.readLine()) != null) {
                sb.append(line + '\n');
            }
            //close input stream
            inStream.close();
            //assign data to a string variable
            String result = sb.toString();

            try {
                checkForError(result);
            } catch (NumberFormatException nfe) {
                //different ways of handling data
                if(params[0].contains("retrieve_accounts.php")) {
                    JSONArray jsonA = new JSONArray(result);

                    Data d = new Data();

                    for(int i = 0; i < jsonA.length(); i++) {
                        JSONObject jsonO = jsonA.getJSONObject(i);

                        setData(jsonO);

                        d.accounts.add(data);
                    }
                }
                else {
                    JSONObject data = new JSONObject(result);
                    //places the retrieved data into a java data structure
                    setData(data);
                }
            }

        } catch(UnsupportedEncodingException uee) {
            Log.v("UnsupportedEncodingException", uee.getMessage());
            ((Activity) activity).finish();
        }
        catch(IOException ioe) {
            Log.v("IOException", ioe.getMessage());
            ((Activity) activity).finish();
        }
        catch(JSONException je) {
            Log.v("JSONException", je.getMessage());
            ((Activity) activity).finish();
        }

        return null;
    }

    @Override
    protected void onPostExecute(String result) {
        if(progressDialog.isShowing()) {
            progressDialog.dismiss();
        }

        if(this.error > 0) {
            catchError();
            return;
        }

        //starts new activity if data was retrieved
        switch(table) {
            case 1:
                //Start new activity
                Intent I = new Intent(activity, MainActivity.class);
                activity.startActivity(I);
                ((Activity) activity).finish();
                break;
            case 2:
                Retrieve r = new Retrieve(((Activity) activity), "http://www.abunities.co.uk/t2022t1/retrieve_accounts.php", 1);
                break;
        }

    }

    private void checkForError(String error) throws NumberFormatException {
        int err = Integer.parseInt(error.trim());
        if(err > 0) {
            this.error = err;
        }
    }

    private void catchError() {

        if(progressDialog.isShowing()) {
            progressDialog.dismiss();
        }

        switch(this.error) {
            //Wrong PassCode
            case 1:
                this.alertDialog.setTitle("Wrong Passcode");
                this.alertDialog.show();
                break;
            //New User
            case 2:
                Intent mainIntent;
                mainIntent = new Intent(activity, FirstLoginActivity.class);
                activity.startActivity(mainIntent);
                ((Activity) activity).finish();
                break;
            //Error - Could be anything
            case 3:
                this.alertDialog.setTitle("Error Occurred");
                this.alertDialog.show();
                ((Activity) activity).finish();
                break;
            //No Internet Connection
            case 4:
                this.alertDialog.setTitle("No Internet Connection");
                this.alertDialog.show();
                break;
            //Connection to database error
            case 5:
                this.alertDialog.setTitle("Error In Retrieving Data");
                this.alertDialog.show();
                break;
            //Wrong passwords for setting up mobile banking
            case 7:
                this.alertDialog.setTitle("Passwords did not match");
                this.alertDialog.show();
                Activity a = (Activity) activity;
                EditText user = (EditText) a.findViewById(R.id.userID);
                EditText pass = (EditText) a.findViewById(R.id.userPass);
                user.setText(null);
                pass.setText(null);
                break;
            //Mobile Banking - Successful set up
            case 8:
                Intent otherIntent;
                otherIntent = new Intent(activity, LoginActivity.class);
                activity.startActivity(otherIntent);
                ((Activity) activity).finish();
                break;
            //Is user first time using the mobile app? Success value
            case 9:
                Activity ac = (Activity) activity;
                // Change view to next set of inputs
                ac.setContentView(R.layout.initial_setup_passcode);
                PassCodeActivity pca = new PassCodeActivity(ac);
                break;
            //If unexpected error occurs then end any activity open
            default:
                ((Activity) activity).finish();
        }
    }

}
