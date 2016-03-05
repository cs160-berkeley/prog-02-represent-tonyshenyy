package com.example.tony.represent;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.wearable.activity.WearableActivity;
import android.support.wearable.view.BoxInsetLayout;
import android.support.wearable.view.GridViewPager;
import android.view.View;
import android.widget.TextView;

import org.apache.http.impl.conn.tsccm.WaitingThread;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

public class MainActivity extends WearableActivity {
    private String repName;
    private String repParty;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setAmbientEnabled();

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        if (extras != null) {
            String[] repNameParty = extras.getString("REP_INFO").split(",");
            repName = repNameParty[0];
            repParty = repNameParty[1];
//        WatchFragment fragobj = new WatchFragment();
//        fragobj.setArguments(extras);

            final List<ArrayList<WatchFragment>> data = new LinkedList<ArrayList<WatchFragment>>();
            ArrayList<WatchFragment> temp = new ArrayList<WatchFragment>();
            Bundle bdl1 = new Bundle();
            bdl1.putString("REP_NAME", repName);
            bdl1.putString("REP_PARTY", repParty);
            bdl1.putString("STATE", "California");
            bdl1.putString("COUNTY", "Alameda");
            bdl1.putString("OBAMA_PERCENT", "78%");
            bdl1.putString("ROMNEY_PERCENT", "22");
            Bundle bdl2 = new Bundle();
            bdl2.putString("REP_NAME", "");
            bdl2.putString("REP_PARTY", "");
            bdl2.putString("STATE", "California");
            bdl2.putString("COUNTY", "Alameda");
            bdl2.putString("OBAMA_PERCENT", "78%");
            bdl2.putString("ROMNEY_PERCENT", "22%");
            temp.add(WatchFragment.newInstance(bdl1));
            temp.add(WatchFragment.newInstance(bdl2));
            data.add(temp);

            ArrayList<WatchFragment> temp1 = new ArrayList<WatchFragment>();
            Bundle bdl11 = new Bundle();
            bdl11.putString("REP_NAME", "Robert Aderholt");
            bdl11.putString("REP_PARTY", "Republican");
            bdl11.putString("STATE", "California");
            bdl11.putString("COUNTY", "Alameda");
            bdl11.putString("OBAMA_PERCENT", "78%");
            bdl11.putString("ROMNEY_PERCENT", "22");
            temp1.add(WatchFragment.newInstance(bdl11));
            data.add(temp1);


            final GridViewPager pager = (GridViewPager) findViewById(R.id.pager);
            pager.setAdapter(new SampleGridPagerAdapter(this, getFragmentManager(), data));
        }

    }

//
//    @Override
//    public void onEnterAmbient(Bundle ambientDetails) {
//        super.onEnterAmbient(ambientDetails);
//        updateDisplay();
//    }
//
//    @Override
//    public void onUpdateAmbient() {
//        super.onUpdateAmbient();
//        updateDisplay();
//    }
//
//    @Override
//    public void onExitAmbient() {
//        updateDisplay();
//        super.onExitAmbient();
//    }

//    private void updateDisplay() {
//        if (isAmbient()) {
//            mContainerView.setBackgroundColor(getResources().getColor(android.R.color.black));
//            mTextView.setTextColor(getResources().getColor(android.R.color.white));
//            mClockView.setVisibility(View.VISIBLE);
//
//            mClockView.setText(AMBIENT_DATE_FORMAT.format(new Date()));
//        } else {
//            mContainerView.setBackground(null);
//            mTextView.setTextColor(getResources().getColor(android.R.color.black));
//            mClockView.setVisibility(View.GONE);
//        }
//    }
}
