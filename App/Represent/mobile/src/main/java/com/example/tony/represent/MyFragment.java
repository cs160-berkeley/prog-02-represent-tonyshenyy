package com.example.tony.represent;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class MyFragment extends Fragment {
//    public static final String EXTRA_MESSAGE = "EXTRA_MESSAGE";
    private Button startOver;
    private Button moreInfo;

    public static final MyFragment newInstance(String repName, String repParty)
    {
        MyFragment f = new MyFragment();
        Bundle bdl = new Bundle();
        bdl.putString("REP_NAME", repName);
        bdl.putString("REP_PARTY", repParty);
        f.setArguments(bdl);
        return f;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final String repName = getArguments().getString("REP_NAME");
        String repParty = getArguments().getString("REP_PARTY");
        View v = inflater.inflate(R.layout.congressional_view, container, false);
        TextView rep_name = (TextView)v.findViewById(R.id.rep_name);
        rep_name.setText(repName);
        TextView rep_party = (TextView) v.findViewById(R.id.rep_party);
        rep_party.setText(repParty);

        Intent intent = getActivity().getIntent();
        Bundle extras = intent.getExtras();
        if (extras != null) {
            String rn = extras.getString("REP_NAME");
            Intent sendIntent = new Intent(getActivity(), DetailedViewActivity.class);
            Bundle ex = new Bundle();
            ex.putString("REP_NAME", rn);
            sendIntent.putExtras(ex);
            startActivity(sendIntent);
        }

        startOver = (Button) v.findViewById(R.id.start_over);
        startOver.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), MainActivity.class);
                startActivityForResult(myIntent, 0);
            }
        });

        moreInfo = (Button) v.findViewById(R.id.more_info);
        moreInfo.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), DetailedViewActivity.class);
                Bundle temp = new Bundle();
                temp.putString("REP_NAME", repName);
                myIntent.putExtras(temp);
                startActivityForResult(myIntent, 0);
            }
        });
        return v;
    }
}
