package com.example.tony.represent;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.wearable.view.CardFragment;
import android.support.wearable.view.FragmentGridPagerAdapter;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class GridPagerAdapter extends FragmentGridPagerAdapter {
    ArrayList<WatchFragment> mData;
    private final Context mContext;

    public GridPagerAdapter(Context ctx, FragmentManager fm, ArrayList<WatchFragment> data) {
        super(fm);
        mContext = ctx;
        mData = data;
    }
      @Override
    public Fragment getFragment(int row, int column) {
          if (row > 0) {
              Intent intent = new Intent(mContext, VoteViewActivity.class);
//              Bundle extras = new Bundle();
//              extras.putString("REP_INFO", mData.get(column).repInfo);
//              intent.putExtras(extras);
              mContext.startActivity(intent);
          }
          return mData.get(column);
    }

    @Override
    public int getRowCount() {
        return 1;
    }

    @Override
    public int getColumnCount(int row) {
        return mData.size();
    }
}
