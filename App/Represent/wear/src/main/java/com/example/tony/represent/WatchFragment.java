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
    String repName;
    String repParty;
    String repInfo;

    public WatchFragment() {}

    public static final WatchFragment newInstance(String name, String party, String info) {
        WatchFragment f = new WatchFragment();
        Bundle bdl = new Bundle();
        bdl.putString("REP_NAME", name);
        bdl.putString("REP_PARTY", party);
        bdl.putString("REP_INFO", info);
        f.setArguments(bdl);
        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = null;
        String repName = getArguments().getString("REP_NAME");
        String repParty = getArguments().getString("REP_PARTY");
        final String repInfo = getArguments().getString("REP_INFO");
        if (repName != null && repParty != null && repInfo != null) {
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
                    extras.putString("REP_INFO", repInfo);
                    sendIntent.putExtras(extras);
                    getActivity().startService(sendIntent);
                }
            });
//        } else {
//            v = inflater.inflate(R.layout.vote_view, container, false);
//            TextView cou = (TextView) v.findViewById(R.id.county);
//            TextView sta = (TextView) v.findViewById(R.id.state);
//            TextView obama_percent = (TextView) v.findViewById(R.id.obama_percent);
//            TextView romney_percent = (TextView) v.findViewById(R.id.romney_percent);
//            cou.setText(county);
//            sta.setText(state);
//            obama_percent.setText(obPer);
//            romney_percent.setText(roPer);
//        }
        }
            return v;
        }
}
