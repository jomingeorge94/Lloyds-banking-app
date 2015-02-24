package ncl.ac.uk.cs.teamone.lloydsstudent;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.util.Log;

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
import java.util.List;
import java.util.Map;

/**
 * Created by Artemiy on 20/02/2015.
 */

public class PHPHandler {

    private String url = "";
    private Map data = new HashMap();

    public PHPHandler(String url, String[] keys, String[] values) {
        //Sets the php files location
        setURL(url);
        //the client
        HttpClient httpclient = new DefaultHttpClient();
        //the post receiver
        HttpPost httppost = new HttpPost(this.url);

        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(keys.length);

        //Adds all the data that is going to be sent to the php script
        for(int i = 0; i < nameValuePairs.size(); i++) {
            nameValuePairs.add(new BasicNameValuePair(keys[i], values[i]));
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
            BufferedReader reader = new BufferedReader(new InputStreamReader(inStream, "iso-8859-1"));
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

        if(result.equalsIgnoreCase("1") || result.equalsIgnoreCase("2") || result.equalsIgnoreCase("3")) {
            return;
        }

        try {
            JSONArray jArray = new JSONArray(result);
            JSONObject json_data = jArray.getJSONObject(0);
            //places the retrieved data into a java data structure
            setData(json_data, keys);
        } catch(JSONException je) {
            Log.v("JSONException", je.getMessage());
        }
    }

    public void setData(JSONObject json_data, String[] keys) throws JSONException {
        //defensive programming to prevent an empty array from adding nothing
        if(keys == null) {
            return;
        }
        //add data
        for(int i = 0; i < keys.length; i++) {
            this.data.put(keys[i], json_data.getString(keys[i]));
        }
    }

    public Map getData() {
        return data;
    }

    public void setURL(String url) {
        this.url = url;
    }
}
