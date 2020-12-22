package com.dinara.sharipova;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class StockBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent){
        Toast.makeText(context, intent.getStringExtra("STOCK_INFO"),Toast.LENGTH_SHORT).show();

    }


}
