package com.cisco.yamba;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class BootReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		Log.d("BootReceiver", "onReceive: "+ System.currentTimeMillis() );
		context.startService( new Intent(context, RefreshService.class) );
	}

}
