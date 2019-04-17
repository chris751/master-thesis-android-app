package com.thegoodthebadtheasian.myapplication;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.thegoodthebadtheasian.myapplication.models.PlaceholderDevice;
import com.thegoodthebadtheasian.myapplication.retrofit.RetrofitClient;
import com.thegoodthebadtheasian.myapplication.retrofit.RetrofitServiceProvider;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity implements MyAdapter.OnItemClickListener {
    public static final String DEVICE_EXTRAS = "DEVICE_EXTRAS";

    RequestHandlerService mService;
    boolean mBound = false;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutMananger;

    private Button newDeviceButton;

    RetrofitClient client;

    List<PlaceholderDevice> devices;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        newDeviceButton = (Button) findViewById(R.id.new_device_button);

        newDeviceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RegisterNewDeviceActivity.class);

                startActivityForResult(intent, 1);
            }
        });

        client = RetrofitServiceProvider.provideService(RetrofitClient.class);

        getAllDevices();
    }

    private void getAllDevices(){
        Call<List<PlaceholderDevice>> call = client.getDevices();

        call.enqueue(new Callback<List<PlaceholderDevice>>() {
            @Override
            public void onResponse(Call<List<PlaceholderDevice>> call, Response<List<PlaceholderDevice>> response) {
                devices = response.body();
                setupRecyclerView();
            }

            @Override
            public void onFailure(Call<List<PlaceholderDevice>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "something went wrong", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onStart(){
        super.onStart();
        //Bind to the service
        Intent intent = new Intent(this, RequestHandlerService.class);
        bindService(intent, connection, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onStop(){
        super.onStop();
        unbindService(connection);
    }

    private void setupRecyclerView(){
        recyclerView = (RecyclerView) findViewById(R.id.device_list_recycler_view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);

        layoutMananger = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutMananger);

        mAdapter = new MyAdapter(this, devices, this);
        recyclerView.setAdapter(mAdapter);
    }

//region Service connection methods
    /** Defines callbacks for service binding, passed to bindService() */
    private ServiceConnection connection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName className,
                                       IBinder service) {
            // We've bound to LocalService, cast the IBinder and get LocalService instance
            RequestHandlerService.LocalBinder binder = (RequestHandlerService.LocalBinder) service;
            mService = binder.getService();
            mBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName arg0) {
            mBound = false;
        }
    };
//endregion

    @Override
    public void onItemClick(int position) {
        // Start activity på baggrund af den valgte ting.. Position giver hvilket item. Det må så være på baggrund af det, vi har til at ligge her i aktiviten og ikke i viewet
        Log.d("ITEM CLICk", "onItemClick: " + position);
        Intent intent = new Intent(this, DeviceDetailsActivity.class);
        intent.putExtra(DEVICE_EXTRAS, devices.get(position));
        startActivity(intent);
    }
}
