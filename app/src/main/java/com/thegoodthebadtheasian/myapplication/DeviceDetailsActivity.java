package com.thegoodthebadtheasian.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.thegoodthebadtheasian.myapplication.models.PlaceholderDevice;

public class DeviceDetailsActivity extends AppCompatActivity {

    private PlaceholderDevice mDevice;

    private TextView nameView;
    private TextView priceView;
    private FloatingActionButton floatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device_details);

        mDevice = (PlaceholderDevice) getIntent().getSerializableExtra(MainActivity.DEVICE_EXTRAS);

        nameView = findViewById(R.id.nameView);
        priceView = findViewById(R.id.priceView);
        floatingActionButton = findViewById(R.id.floatingActionButton);

        nameView.setText(mDevice.getName());
        priceView.setText(mDevice.getPrice()+"");
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToAddActionActivity();
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if(requestCode == AddActionActivity.PICK_ACTION_REQUEST){
            if(resultCode == Activity.RESULT_OK){
                //TODO: Gem dataen for action på devicet, og post/put/patch det til backend
                Toast.makeText(this,"result ok", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void goToAddActionActivity(){
        Intent intent = new Intent(this, AddActionActivity.class);
        startActivityForResult(intent, AddActionActivity.PICK_ACTION_REQUEST);
    }
}
