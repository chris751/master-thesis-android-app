package com.thegoodthebadtheasian.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.thegoodthebadtheasian.myapplication.Adapters.ActionListAdapter;
import com.thegoodthebadtheasian.myapplication.models.Action;

import java.util.ArrayList;

public class AddActionActivity extends AppCompatActivity{

    public static final int PICK_ACTION_REQUEST = 1;
    public static final String ACTION_TYPE_EXTRA = "ACTION_TYPE_EXTRA";
    public static final int SMS_ACTION = 0;
    public static final int GA_ACTION = 1;
    public static final int NOTIFICATION_ACTION = 2;

    private Button smsActionBtn;
    private Button gaActionBtn;
    private Button notificationBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_action);

        smsActionBtn = findViewById(R.id.smsActionBtn);
        gaActionBtn = findViewById(R.id.gaActionBtn);
        notificationBtn = findViewById(R.id.notificationActionBtn);

        smsActionBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToActionDetailsActivity(SMS_ACTION);
            }
        });
        gaActionBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToActionDetailsActivity(GA_ACTION);
            }
        });
        notificationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToActionDetailsActivity(NOTIFICATION_ACTION);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == PICK_ACTION_REQUEST){
            if(resultCode == Activity.RESULT_OK){
                Toast.makeText(this,"result ok", Toast.LENGTH_SHORT).show();
                setResult(Activity.RESULT_OK, data);
                finish();
            }
        }
    }

    private void goToActionDetailsActivity(int actionType){
        Intent intent = new Intent(this, ActionDetailsActivity.class);
        intent.putExtra(ACTION_TYPE_EXTRA, actionType);
        startActivityForResult(intent, PICK_ACTION_REQUEST);
    }
}
