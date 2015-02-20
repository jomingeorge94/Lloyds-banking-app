package ncl.ac.uk.cs.teamone.lloydsstudent;

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

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Artemiy on 20/02/2015.
 */

public class PHPHandler {

    private String url = "";

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

        //Execute the post request
        try {
            HttpResponse response = httpclient.execute(httppost);
            HttpEntity entity = response.getEntity();

        } catch(ClientProtocolException cpe) {
            Log.v("ClientProtocolException", cpe.getMessage());
        } catch(IOException ioe) {
            Log.v("IOException", ioe.getMessage());
        }
    }

    public void setURL(String url) {
        this.url = url;
    }
}
