package com.thegoodthebadtheasian.myapplication.retrofit;

import com.thegoodthebadtheasian.myapplication.models.Device;
import com.thegoodthebadtheasian.myapplication.models.PlaceholderDevice;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface RetrofitClient {

    static final String DEVICE_END_POINT = "devices";

    @GET(DEVICE_END_POINT)
    Call<List<Device>> getDevices();

    @GET(DEVICE_END_POINT + "/{id}")
    Call<Device> getDevice(@Path("id") String id);

    @POST(DEVICE_END_POINT)
    Call<Void> createDevice(@Body Device placeholderDevice);

    @DELETE(DEVICE_END_POINT + "/{id}")
    Call<Void> deleteDevice(@Path("id") String id);
}
