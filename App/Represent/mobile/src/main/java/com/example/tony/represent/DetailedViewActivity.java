package com.example.tony.represent;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.concurrent.ExecutionException;

public class DetailedViewActivity extends AppCompatActivity {

    private Button startOver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detailed_view);

        Intent intend = getIntent();
        Bundle extras = intend.getExtras();
        String repInfo = extras.getString("REP_INFO");
        JSONObject jsonObj = null;
        try {
            jsonObj = new JSONObject(repInfo);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        TextView repName = (TextView) findViewById(R.id.rep_name);
        ImageView repPhoto = (ImageView) findViewById(R.id.rep_photo);
        TextView repParty = (TextView) findViewById(R.id.rep_party);
        TextView repEndDate = (TextView) findViewById(R.id.end_date);

        String bioId = "";
        try {
            bioId = jsonObj.getString("bioguide_id");
            String url = "https://theunitedstates.io/images/congress/225x275/" + bioId + ".jpg";
            new GetRepPhoto((ImageView) repPhoto).execute(url);
            repName.setText(jsonObj.getString("first_name") + " " + jsonObj.getString("last_name"));
            repParty.setText(jsonObj.getString("party"));
            repEndDate.setText(jsonObj.getString("term_end"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        LinearLayout commLayout = (LinearLayout) findViewById(R.id.comm_names);
        LinearLayout billLayout = (LinearLayout) findViewById(R.id.bills);

        String baseURL = "https://congress.api.sunlightfoundation.com";
        String committeeURL = "/committees?member_ids=";
        String billURL = "/bills?sponsor_id=";
        String apiKey = "&apikey=8e5a3fcf06064378825a9eea1df15305";
        GetJSONObject gjo1 = new GetJSONObject();
        GetJSONObject gjo2 = new GetJSONObject();
        try {
            String committee = gjo1.execute(baseURL + committeeURL + bioId + apiKey).get();
            String bills = gjo2.execute(baseURL + billURL + bioId + apiKey).get();
            JSONObject commObj = new JSONObject(committee);
            JSONObject billObj = new JSONObject(bills);
            JSONArray commArray = commObj.getJSONArray("results");
            JSONArray billArray = billObj.getJSONArray("results");

            for (int i = 0; i < commArray.length(); i++) {
                JSONObject oneObject = commArray.getJSONObject(i);
                String commName = oneObject.getString("name");
                TextView comm = new TextView(this);
                comm.setText(String.valueOf(i + 1) + ". " + commName);
                comm.setLayoutParams(new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT));
                comm.setTextSize(12);
                commLayout.addView(comm);
            }

            for (int i = 0; i < billArray.length(); i++) {
                JSONObject oneObject = billArray.getJSONObject(i);
                String billName = oneObject.getString("official_title");
                String billDate = oneObject.getString("introduced_on");
                TextView bDate = new TextView(this);
                bDate.setText(billDate);
                bDate.setLayoutParams(new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT));
                bDate.setTextSize(12);
                billLayout.addView(bDate);

                TextView bName = new TextView(this);
                bName.setText(billName);
                bName.setLayoutParams(new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT));
                bName.setTextSize(12);
                billLayout.addView(bName);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        startOver = (Button) findViewById(R.id.start_over);
        startOver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(v.getContext(), MainActivity.class);
                startActivity(myIntent);
            }
        });
    }
}
