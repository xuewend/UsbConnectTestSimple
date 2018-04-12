package com.skylight.usbconnecttest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.skylight.sd.OnStatusChangedListener;
import com.skylight.sd.SkylightUsbDataService;

public class MainActivity extends AppCompatActivity implements OnStatusChangedListener {
    SkylightUsbDataService service;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        service = MApplication.getService();
        if(service != null) {
            service.addOnStatusChangedListener(this);
        }

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(service != null){
            service.removeOnStatusChangedListener(this);
        }
    }

    @Override
    public void onStatusUpdate(USB_STATUS status) {
        if(status == null){
            return ;
        }

        if(status == OnStatusChangedListener.USB_STATUS.USBDEVICE_CONNECTED){
//            onCameraConnected();
            Toast.makeText(this,"USBDEVICE_CONNECTED",Toast.LENGTH_SHORT).show();
        }
        else if(status == OnStatusChangedListener.USB_STATUS.USBDEVICE_DETACHED){
//            onCameraDisconnected();/
            Toast.makeText(this,"USBDEVICE_DETACHED",Toast.LENGTH_SHORT).show();
        }else if(status == USB_STATUS.USBDEVICE_NEED_PERMISSION){
            Toast.makeText(this,"USBDEVICE_NEED_PERMISSION",Toast.LENGTH_SHORT).show();
            if(service != null){
                service.requestPermission();
            }
        }
    }
}
