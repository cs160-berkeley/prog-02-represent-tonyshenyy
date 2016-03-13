package com.example.tony.represent;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.net.ssl.HttpsURLConnection;

public class CongressionalViewActivity extends AppCompatActivity {
    private List<Reps> representatives = new ArrayList<>();
    ;

    // This method creates an ArrayList that has three Person objects
// Checkout the project associated with this tutorial on Github if
// you want to use the same images.
//    private void initializeData(){
//        persons.add(new Reps("Emma Wilson", "23 years old", R.drawable.emma));
//        persons.add(new Reps("Lavery Maiss", "25 years old", R.drawable.lavery));
//        persons.add(new Reps("Lillie Watts", "35 years old", R.drawable.lillie));
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycler_view);

        Intent receivedIntent = getIntent();
        Bundle extras = receivedIntent.getExtras();
        String url = "https://congress.api.sunlightfoundation.com/legislators/locate?";
        String apiKey = "&apikey=8e5a3fcf06064378825a9eea1df15305";

        String geocodeURL = "https://maps.googleapis.com/maps/api/geocode/json?";
        String geocodeAPI = "&key=AIzaSyDtJURSnFJRymqUq5eenWrR5RDe0Nruo5M";

        if (extras != null) {
            if (extras.containsKey("ZIPCODE")) {
                String zipcode = extras.getString("ZIPCODE");
                url += "zip=" + zipcode + apiKey;
                geocodeURL += "address=" + zipcode + geocodeAPI;
            } else {
                String latitude = extras.getString("LATITUDE");
                String longitude = extras.getString("LONGITUDE");
                url += "latitude=" + latitude + "&longitude=" + longitude + apiKey;
                geocodeURL += "latlng=" + latitude + "," + longitude + geocodeAPI;
            }

            GetJSONObject obj = new GetJSONObject();
            GetJSONObject obj2 = new GetJSONObject();
            String result = null;
            String result2 = null;
            try {
                result = obj.execute(url).get();
                result2 = obj2.execute(geocodeURL).get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
            JSONObject jObject = null;
            JSONObject jObject2 = null;
            try {
                jObject = new JSONObject(result);
                jObject2 = new JSONObject(result2);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            JSONArray jArray = null;
            JSONArray jArray2 = null;
            JSONArray tempArray = null;
            try {
                jArray = jObject.getJSONArray("results");
//                tempArray = jObject2.getJSONArray("results");
//                JSONObject tempObject = tempArray.getJSONObject(0);
//                jArray2 = tempObject.getJSONArray("address_components");
            } catch (JSONException e) {
                e.printStackTrace();
            }

            Intent sendIntent = new Intent(getBaseContext(), PhoneToWatchService.class);
            Bundle exs = new Bundle();
            exs.putString("REP_LIST", jObject.toString());
            sendIntent.putExtras(exs);
            startService(sendIntent);

            for (int i = 0; i < jArray.length(); i++) {
                try {
                    JSONObject oneObject = jArray.getJSONObject(i);
                    String firstName = oneObject.getString("first_name");
                    String lastName = oneObject.getString("last_name");
                    String party = oneObject.getString("party");
                    String email = oneObject.getString("oc_email");
                    String website = oneObject.getString("website");
                    String bioId = oneObject.getString("bioguide_id");
                    String repInfo = oneObject.toString();
                    representatives.add(new Reps(firstName + " " + lastName, party, bioId, email, website, repInfo));
                } catch (JSONException e) {}
            }

//            for (int i = 0; i < jArray2.length(); i++) {
//                try {
//                    JSONObject twoObject = jArray2.getJSONObject(i);
//                    String types = twoObject.get("")
//                } catch (JSONException e) {}
//            }
//            String county = null;
//            try {
//                String tempCounty = jArray2.getJSONObject(2).getString("long_name");
//                int len = tempCounty.length() - 7;
//                county = tempCounty.substring(0, len);
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }

//            String jsonCounty = loadJSONFromAsset();
//            JSONArray countyArray = null;
//            try {
//                countyArray = new JSONArray(jsonCounty);
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }

//            for (int i = 0; i < countyArray.length(); i++) {
//                try {
//                    JSONObject voteInfo = countyArray.getJSONObject(i);
//                    String tempCounty = voteInfo.getString("county-name");
//                    if (tempCounty.equalsIgnoreCase(county)) {
//                        Intent newIntent = new Intent(getBaseContext(), PhoneToWatchService.class);
//                        Bundle bdl = new Bundle();
//                        bdl.putString("VOTE_INFO", voteInfo.toString());
//                        newIntent.putExtras(bdl);
//                        startService(newIntent);
//                    }
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            }

            RecyclerView rv = (RecyclerView) findViewById(R.id.rv);
            LinearLayoutManager llm = new LinearLayoutManager(this);
            rv.setLayoutManager(llm);
            RVAdapter adapter = new RVAdapter(representatives, this);
            rv.setAdapter(adapter);
        }
    }

    public String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = getAssets().open("election-county-2012.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }
}




