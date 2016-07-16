package com.yeniyoo.Core;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by YJLaptop on 2016-07-16.
 */
public class ToastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        String name = intent.getAction();
        String msg = intent.getStringExtra("msg");
        if(name.equals("com.mathpresso.core.toastreciever")){
            Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
        }
    }
}