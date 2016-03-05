package com.example.tony.represent;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.support.wearable.view.CardFragment;
import android.support.wearable.view.FragmentGridPagerAdapter;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class SampleGridPagerAdapter extends FragmentGridPagerAdapter {

    private final Context mContext;
    private List mRows;
    List<ArrayList<WatchFragment>> mData;

    public SampleGridPagerAdapter(Context ctx, FragmentManager fm, List<ArrayList<WatchFragment>> data) {
        super(fm);
        mContext = ctx;
        mData = data;
    }
      @Override
    public Fragment getFragment(int row, int column) {
        return mData.get(row).get(column);
    }

    @Override
    public int getRowCount() {
        return mData.size();
    }

    @Override
    public int getColumnCount(int row) {
        return mData.get(row).size();
    }


}
