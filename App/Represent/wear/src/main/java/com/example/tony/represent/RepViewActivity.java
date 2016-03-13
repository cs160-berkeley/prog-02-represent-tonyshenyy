package com.example.tony.represent;

import android.content.Intent;
import android.os.Bundle;
import android.support.wearable.activity.WearableActivity;
import android.support.wearable.view.GridViewPager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yshen on 3/12/2016.
 */
public class RepViewActivity extends WearableActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.grid_view);
        setAmbientEnabled();

        String repList;
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        if (extras != null) {
            repList = extras.getString("REP_LIST");
//        WatchFragment fragobj = new WatchFragment();
//        fragobj.setArguments(extras);
            JSONObject jsonObj = null;
            try {
                jsonObj = new JSONObject(repList);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            JSONArray jArray = null;
            try {
                jArray = jsonObj.getJSONArray("results");
            } catch (JSONException e) {
                e.printStackTrace();
            }

            ArrayList<WatchFragment> reps = new ArrayList<>();
            for (int i = 0; i < jArray.length(); i++) {
                try {
                    JSONObject oneObject = jArray.getJSONObject(i);
                    String firstName = oneObject.getString("first_name");
                    String lastName = oneObject.getString("last_name");
                    String party = oneObject.getString("party");
                    String info = oneObject.toString();
                    reps.add(WatchFragment.newInstance(firstName + " " + lastName, party, info));
                } catch (JSONException e) {}
            }
            final GridViewPager pager = (GridViewPager) findViewById(R.id.pager);
            pager.setAdapter(new GridPagerAdapter(this, getFragmentManager(), reps));
        }

    }
}
