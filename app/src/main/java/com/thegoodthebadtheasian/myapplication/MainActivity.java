package com.thegoodthebadtheasian.myapplication;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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


public class MainActivity extends AppCompatActivity {
    RequestHandlerService mService;
    boolean mBound = false;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutMananger;

    private Button newDeviceButton;

    RetrofitClient client;

    String[] myDataset = {"yo", "yo", "yo"};
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

        setupRecyclerView();

        client = RetrofitServiceProvider.provideService(RetrofitClient.class);

        getAllDevices();

    }

    private void getAllDevices(){
        Call<List<PlaceholderDevice>> call = client.getDevices();

        call.enqueue(new Callback<List<PlaceholderDevice>>() {
            @Override
            public void onResponse(Call<List<PlaceholderDevice>> call, Response<List<PlaceholderDevice>> response) {
                devices = response.body();
            }

            @Override
            public void onFailure(Call<List<PlaceholderDevice>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "something went wrong", Toast.LENGTH_LONG).show();
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

        mAdapter = new MyAdapter(myDataset);
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
}
