package com.example.tony.represent;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class DetailedViewActivity extends AppCompatActivity {

    private Button startOver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detailed_view);

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
