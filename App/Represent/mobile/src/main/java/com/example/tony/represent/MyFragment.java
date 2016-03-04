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

    public static final MyFragment newInstance(String message)
    {
        MyFragment f = new MyFragment();
//        Bundle bdl = new Bundle(1);
//        bdl.putString(EXTRA_MESSAGE, message);
//        f.setArguments(bdl);
        return f;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
//        String message = getArguments().getString(EXTRA_MESSAGE);
        View v = inflater.inflate(R.layout.congressional_view, container, false);
//        TextView messageTextView = (TextView)v.findViewById(R.id.textView);
//        messageTextView.setText(message);
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
                startActivityForResult(myIntent, 0);
            }
        });
        return v;
    }
}
