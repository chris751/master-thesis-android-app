package com.thegoodthebadtheasian.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Region;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.thegoodthebadtheasian.myapplication.models.Action;
import com.thegoodthebadtheasian.myapplication.models.Device;
import com.thegoodthebadtheasian.myapplication.models.Sensor;
import com.thegoodthebadtheasian.myapplication.retrofit.RetrofitClient;
import com.thegoodthebadtheasian.myapplication.retrofit.RetrofitServiceProvider;

import org.w3c.dom.Text;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.thegoodthebadtheasian.myapplication.AddActionActivity.ACTION_TYPE_EXTRA;
import static com.thegoodthebadtheasian.myapplication.AddActionActivity.PICK_ACTION_REQUEST;

public class DeviceDetailsActivity extends AppCompatActivity {

    public static final String ACTION_EXTRA = "ACTION_EXTRA";

    private Device mDevice;
    private FloatingActionButton floatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device_details);

        mDevice = (Device) getIntent().getSerializableExtra(MainActivity.DEVICE_EXTRAS);

        floatingActionButton = findViewById(R.id.floatingActionButton);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToAddActionActivity();
            }
        });

        updateInfo();
        showCurrentActions();
    }

    private void showCurrentActions(){
        Action actions = mDevice.getAction();

        LinearLayout smsView = findViewById(R.id.smsActionWrapper);
        LinearLayout gaView = findViewById(R.id.gaActionWrapper);
        LinearLayout notificationView = findViewById(R.id.notificationActionWrapper);

        smsView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToActionDetailsActivity(AddActionActivity.SMS_ACTION);
            }
        });

        gaView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToActionDetailsActivity(AddActionActivity.GA_ACTION);
            }
        });

        notificationView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToActionDetailsActivity(AddActionActivity.NOTIFICATION_ACTION);
            }
        });

        if(actions.getSms() == null){
            smsView.setVisibility(View.GONE);

        }
        if(actions.getGoogleAnalytics() == null) {
            gaView.setVisibility(View.GONE);

        }
        if(actions.getNotification() == null) {
            notificationView.setVisibility(View.GONE);
        }
    }

    private void updateInfo(){

        List<Sensor> sensors = mDevice.getTrigger().getSensors();

        for (Sensor sensor : sensors) {
            inflateCorrectSensorLayout(sensor.getType());
        }
    }

    private void inflateCorrectSensorLayout(String sensorType) {
        if(sensorType.equals("pir")){
            showPirSensorLayout();
        } else if (sensorType.equals("timer")) {
            showTimerModuleLayout();
        } else if (sensorType.equals("hall_effect")) {
            showDoorSensorLayout();
        }
    }

    //region sensor methods

    private void showPirSensorLayout(){
        LinearLayout pirSensorWrapper = findViewById(R.id.pirSensorWrapper);
        pirSensorWrapper.setVisibility(View.VISIBLE);

        TextView pirSensorType = findViewById(R.id.pirNameView);
        TextView pirSensorInfo = findViewById(R.id.pirInfoView);
        ImageView pirImageView = findViewById(R.id.pirImageView);

        pirImageView.setImageDrawable(getDrawable(R.drawable.icons8_motion_sensor_96));
        pirSensorType.setText(getString(R.string.pir_sensor_name));
        pirSensorInfo.setText(getString(R.string.pir_sensor_info));
    }

    private void showTimerModuleLayout() {
        LinearLayout timerModuleWrapper = findViewById(R.id.timerModuleWrapper);
        timerModuleWrapper.setVisibility(View.VISIBLE);

        TextView timerModuleType = findViewById(R.id.timerNameView);
        TextView timerModuleInfo = findViewById(R.id.timerInfoView);
        ImageView timerImageView = findViewById(R.id.timerImageView);


        timerImageView.setImageDrawable(getDrawable(R.drawable.baseline_timer_24px));
        timerModuleType.setText(getString(R.string.timer_module_name));
        timerModuleInfo.setText(getString(R.string.timer_module_info));
    }

    private void showDoorSensorLayout() {
        LinearLayout halleEffectWrapper = findViewById(R.id.doorSensorWrapper);
        halleEffectWrapper.setVisibility(View.VISIBLE);

        TextView hallEffectType = findViewById(R.id.doorNameView);
        TextView hallEffectInfo = findViewById(R.id.doorInfoView);
        ImageView hallEffectImageView = findViewById(R.id.doorImageView);

        hallEffectImageView.setImageDrawable(getDrawable(R.drawable.icons8_door_sensor_100));
        hallEffectType.setText(getString(R.string.hall_effect_sensor_name));
        hallEffectInfo.setText(getString(R.string.hall_effect_info));
    }

    //endregion

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if(requestCode == PICK_ACTION_REQUEST){
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

    private void goToActionDetailsActivity(int actionType){
        Intent intent = new Intent(this, ActionDetailsActivity.class);
        intent.putExtra(ACTION_TYPE_EXTRA, actionType);
        intent.putExtra(DeviceDetailsActivity.ACTION_EXTRA, mDevice.getAction());
        startActivityForResult(intent, PICK_ACTION_REQUEST);
    }

    private void goToAddActionActivity(){
        Intent intent = new Intent(this, AddActionActivity.class);
        intent.putExtra(ACTION_EXTRA, mDevice.getAction());
        startActivityForResult(intent, PICK_ACTION_REQUEST);
    }
}
