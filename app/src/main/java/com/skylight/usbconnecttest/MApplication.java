package com.skylight.usbconnecttest;

import android.app.Application;

import com.skylight.sd.SkylightUsbDataService;

/**
 * Created by fishmov on 18-4-12.
 */

public class MApplication extends Application {
    private static SkylightUsbDataService service;

    @Override
    public void onCreate() {
        super.onCreate();

        initUsbService();
    }


    public static SkylightUsbDataService getService() {
        return service;
    }

    private void initUsbService(){
        /**
         * modify by yxw
         * There are two scene:
         * 1. app start first, will instance SkylightUsbDataService to listening camera plug in/out
         * 2. camera plug in first, will launch app to instance SkylightUsbDataService and init camera
         * so SkylightUsbDataService is a singleton here.
         */
        service = SkylightUsbDataService.instance(this);
    }

    private void uninitUsbService(){
        if(service != null)
            service.release();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        uninitUsbService();
    }

}
