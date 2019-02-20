package com.thegoodthebadtheasian.myapplication;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

public class RequestHandlerService extends Service {


    //region Binder methods
    //Binder to give to the clients
    private final IBinder binder = new LocalBinder();
    /**
     * Class used for the client Binder.  Because we know this service always
     * runs in the same process as its clients, we don't need to deal with IPC.
     */
    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    public class LocalBinder extends Binder {
        RequestHandlerService getService(){
            //Return the instance of this service, so the client can call methods from the service
            return RequestHandlerService.this;
        }
    }
    //endregion
}
