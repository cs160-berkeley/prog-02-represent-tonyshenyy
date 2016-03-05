package com.example.tony.represent;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by yshen on 3/4/2016.
 */
public class WatchFragment extends Fragment {
    private TextView repName;
    private TextView repParty;

    public static final WatchFragment newInstance(Bundle bdl) {
        WatchFragment f = new WatchFragment();
//        Bundle bdl = new Bundle();
//        bdl.putString("REP_NAME", repName);
//        bdl.putString("REP_PARTY", repParty);
        f.setArguments(bdl);
        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final String repName = getArguments().getString("REP_NAME");
        final String repParty = getArguments().getString("REP_PARTY");
        final String county = getArguments().getString("COUNTY");
        final String state = getArguments().getString("STATE");
        final String obPer = getArguments().getString("OBAMA_PERCENT");
        final String roPer = getArguments().getString("ROMNEY_PERCENT");

        View v;
        if (repName != "" && repParty != "") {
            v = inflater.inflate(R.layout.rep_view, container, false);
//        TextView messageTextView = (TextView)v.findViewById(R.id.textView);
//        messageTextView.setText(message);
            TextView rep_name = (TextView) v.findViewById(R.id.rep_name);
            TextView rep_party = (TextView) v.findViewById(R.id.rep_party);
            rep_name.setText(repName);
            rep_party.setText(repParty);

            rep_name.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent sendIntent = new Intent(getActivity(), WatchToPhoneService.class);
                    Bundle extras = new Bundle();
                    extras.putString("REP_NAME", repName);
//                extras.putString("REP_PARTY", repParty);
                    sendIntent.putExtras(extras);
                    getActivity().startService(sendIntent);
                }
            });
        } else {
            v = inflater.inflate(R.layout.vote_view, container, false);
            TextView cou = (TextView) v.findViewById(R.id.county);
            TextView sta = (TextView) v.findViewById(R.id.state);
            TextView obama_percent = (TextView) v.findViewById(R.id.obama_percent);
            TextView romney_percent = (TextView) v.findViewById(R.id.romney_percent);
            cou.setText(county);
            sta.setText(state);
            obama_percent.setText(obPer);
            romney_percent.setText(roPer);
        }

        return v;
    }
}
