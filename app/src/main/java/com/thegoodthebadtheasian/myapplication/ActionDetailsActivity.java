package com.thegoodthebadtheasian.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ActionDetailsActivity extends AppCompatActivity {

    public static final String ACTION_DETAILS_RESULT_EXTRAS = "ACTION_DETAILS_RESULT_EXTRAS";

    private String resultData = "HEEEJ";

    private Button confirmBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_action_details);

        confirmBtn = findViewById(R.id.confirmBtn);

        confirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveResultAndFinish();
            }
        });
    }

    private void saveResultAndFinish(){
        Intent returnIntent = new Intent();
        returnIntent.putExtra(ACTION_DETAILS_RESULT_EXTRAS, resultData);
        setResult(Activity.RESULT_OK, returnIntent);
        finish();
    }
}
