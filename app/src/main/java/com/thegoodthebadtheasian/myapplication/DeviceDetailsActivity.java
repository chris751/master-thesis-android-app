package com.thegoodthebadtheasian.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.thegoodthebadtheasian.myapplication.models.Action;
import com.thegoodthebadtheasian.myapplication.models.Device;
import com.thegoodthebadtheasian.myapplication.models.PlaceholderDevice;
import com.thegoodthebadtheasian.myapplication.retrofit.RetrofitClient;
import com.thegoodthebadtheasian.myapplication.retrofit.RetrofitServiceProvider;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DeviceDetailsActivity extends AppCompatActivity {

    public static final String ACTION_EXTRA = "ACTION_EXTRA";

    private Device mDevice;

    private TextView nameView;
    private TextView priceView;
    private FloatingActionButton floatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device_details);

        mDevice = (Device) getIntent().getSerializableExtra(MainActivity.DEVICE_EXTRAS);

        nameView = findViewById(R.id.nameView);
        priceView = findViewById(R.id.priceView);
        floatingActionButton = findViewById(R.id.floatingActionButton);

        updateInfo();
        showCurrentActions();
    }

    private void showCurrentActions(){
        Action actions = mDevice.getAction();

        if(actions.getSms() == null){
            findViewById(R.id.smsActionWrapper).setVisibility(View.GONE);
        }
        if(actions.getGoogleAnalytics() == null) {
            findViewById(R.id.gaActionWrapper).setVisibility(View.GONE);

        }
        if(actions.getNotification() == null) {
            findViewById(R.id.notificationActionWrapper).setVisibility(View.GONE);
        }
    }

    private void updateInfo(){
        nameView.setText(mDevice.get_id());
        priceView.setText(mDevice.getTrigger()+"");
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
                Action actionResult = (Action) data.getSerializableExtra(ActionDetailsActivity.ACTION_DETAILS_RESULT_EXTRAS);
                Log.d("HEJHEJHEJ", actionResult+"");
                sendResultToBackend(actionResult);
                finish();
            }
        }

    }

    private void sendResultToBackend(Action actionResult){
        RetrofitClient client = RetrofitServiceProvider.provideService(RetrofitClient.class);
        mDevice.setAction(actionResult);

        Call<Device> call = client.addAction(mDevice.get_id(), mDevice);

        call.enqueue(new Callback<Device>() {
            @Override
            public void onResponse(Call<Device> call, Response<Device> response) {
                mDevice = response.body();
                Log.d("Devices", mDevice.toString());
                updateInfo();
            }

            @Override
            public void onFailure(Call<Device> call, Throwable t) {
                Toast.makeText(DeviceDetailsActivity.this, "something went wrong", Toast.LENGTH_SHORT).show();
                Log.d("Deviced", t.toString());
            }
        });
    }

    private void goToAddActionActivity(){
        Intent intent = new Intent(this, AddActionActivity.class);
        intent.putExtra(ACTION_EXTRA, mDevice.getAction());
        startActivityForResult(intent, AddActionActivity.PICK_ACTION_REQUEST);
    }
}
