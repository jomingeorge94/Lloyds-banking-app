package ncl.ac.uk.cs.teamone.lloydsstudent;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.CountDownTimer;
import android.util.Log;
import android.widget.Button;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
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

    private Map<String, String> data = new HashMap();
    private String[] keys = null; //{ "uid", "passcode" };
    private String[] values = null;
    //progressDialog variables
    private ProgressDialog progressDialog = null;
    private Context activity = null;
    private AlertDialog alertDialog = null;

    public PHPHandler(Context activity, String[] keys, String[] values) {
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
        this.alertDialog.setTitle("Wrong Passcode");
        this.alertDialog.setMessage("3 tries remaining");
    }


    public void setData(JSONObject data, String[] keys) throws JSONException {
        //defensive programming to prevent an empty array from adding nothing
        if(data == null) {
            return;
        }
        //add data
        //gets an iterator of the keys in the JSON
        Iterator<String> jsonKeys = data.keys();

        while(jsonKeys.hasNext()) {
            //get the next key
            String key = jsonKeys.next();
            //add the value to the data
            this.data.put(key, data.get(key).toString());
            Log.d("DataTAG", "value: " + this.data.get(key));
        }
    }

    public Map getData() {
        return data;
    }
    /**
     * Used to invoke a call to the progress dialog
     */

    @Override
    protected String doInBackground(String... params) {
        //the client
        HttpClient httpclient = new DefaultHttpClient();
        //the post receiver
        HttpPost httppost = new HttpPost(params[0]);

        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();

        //Adds all the data that is going to be sent to the php script
        for(int i = 0; i < this.keys.length; i++) {
            nameValuePairs.add(new BasicNameValuePair(this.keys[i], this.values[i]));
        }

        //Encodes the data structure and links it to the post object
        try {
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
        } catch(UnsupportedEncodingException uee) {
            Log.v("UnsupportedEncodingException", uee.getMessage());
        }

        InputStream inStream = null;
        //Execute the post request
        try {
            HttpResponse response = httpclient.execute(httppost);
            HttpEntity entity = response.getEntity();
            //assigns the content of the execution to an input stream
            inStream = entity.getContent();
        } catch(ClientProtocolException cpe) {
            Log.v("ClientProtocolException", cpe.getMessage());
        } catch(IOException ioe) {
            Log.v("IOException", ioe.getMessage());
        }

        StringBuilder sb = null;
        String result = null;

        //converts the data into a string
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(inStream));
            sb = new StringBuilder();
            sb.append(reader.readLine() + '\n');
            String line="0";

            while ((line = reader.readLine()) != null) {
                sb.append(line + '\n');
            }

            inStream.close();
            result = sb.toString();
        } catch(UnsupportedEncodingException uee) {
            Log.v("UnsupportedEncodingException", uee.getMessage());
        } catch(IOException ioe) {
            Log.v("IOException", ioe.getMessage());
        }

        Log.d("StringTAG", result);

        //If the passcode is wrong
        if(result.trim().equalsIgnoreCase("1")) {

            if(progressDialog.isShowing()) {
                progressDialog.dismiss();
            }
            //countdown from 1 second
            CountDownTimer timer = new CountDownTimer(1000, 100) {
                @Override
                public void onTick(long millisUntilFinished) {}

                @Override
                public void onFinish() {
                    alertDialog.dismiss();
                }
            }.start();

            return null;
        }

        if(result.equalsIgnoreCase("2") || result.equalsIgnoreCase("3")) {
            return null;
        }

        try {
            JSONObject data = new JSONObject(result);
            //places the retrieved data into a java data structure
            setData(data, keys);
        } catch(JSONException je) {
            Log.v("JSONException", je.getMessage());
        }

        return "SUCCESS";
    }

    protected void onPostExecute(String result) {
        if(progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
        //starts new activity if data was retrieved
        if(this.data.size() > 0) {
            //Start new activity
            Intent I = new Intent(activity, MainActivity.class);
            activity.startActivity(I);
        }
    }
}
